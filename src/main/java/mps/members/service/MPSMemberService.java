package mps.members.service;

import lombok.AllArgsConstructor;
import mps.exception.MemberCreationException;
import mps.exception.MemberNotAuthorizedException;
import mps.exception.MemberNotFoundException;
import mps.exception.MemberRoleException;
import mps.members.dao.MemberDao;
import mps.members.dto.ExpenditureDto;
import mps.members.dto.MemberDto;
import mps.enums.MPSMemberRoleEnum;
import mps.util.JwtTokenHelper;
import mps.enums.DriverListEnum;
import mps.enums.SpendLimitEnum;
import mps.util.MPSConstant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static mps.util.MPSConstant.MEMBER_CREATION_EXCEPTION;

@Service
@AllArgsConstructor
public class MPSMemberService implements MemberService {

    private final MemberDao memberDao;
    private final JwtTokenHelper jwtTokenHelper;

    @Override
    public MemberDto createMember(MemberDto memberDto) {
        validateMemberRole(memberDto);
        enhanceMemberDto(memberDto);
        return memberDao.createMember(memberDto).orElseThrow(()->{
            throw new MemberCreationException(MEMBER_CREATION_EXCEPTION);
        });
    }


    @Override
    public String findWinningConstituencyByMemberId(Long memberId) {
        validateMemberDto(memberId);
        return memberDao.findWinningConstituencyByMemberId(memberId);
    }

    @Override
    public String findDriverNameByMemberId(Long memberId) {
        validateMemberDto(memberId);
        return memberDao.findDriverNameByMemberId(memberId);
    }

    @Override
    public void updateSpendLimitByMemberId(ExpenditureDto expenditureDto, Long memberId) {
        Optional<ExpenditureDto> expenditureDtoFromDb = findExpenditureByMemberIdAndMemberRole(memberId,expenditureDto.getMemberRole());
        expenditureDtoFromDb.ifPresentOrElse(expenditureDto1 -> {
            boolean spendingLimitFlag = isSpendingLimitFlag(expenditureDto, expenditureDto1);
            memberDao.updateSpendLimitByMemberId(expenditureDto,memberId,spendingLimitFlag);
        },() -> {
            throw new MemberNotFoundException(MPSConstant.MEMBER_NOT_FOUND_EXCEPTION);
        });

    }


    @Override
    public Optional<ExpenditureDto> findExpenditureByMemberIdAndMemberRole(Long memberId,String memberRole) {
        return memberDao.findExpenditureByMemberIdAndMemberRole(memberId,memberRole);
    }

    @Override
    public void validateMemberDto(Long memberId) {
        if(isNull(memberId)){
            throw new MemberNotFoundException(MPSConstant.MEMBER_NOT_FOUND_EXCEPTION);
        }
        else if(memberDao.validationFromDb(memberId).isEmpty()){
         throw new MemberNotFoundException(MPSConstant.MEMBER_NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public List<String> listOfDrivers(String token) {
        if(!jwtTokenHelper.validateTokenForDrivers(token))
        {
            throw new MemberNotAuthorizedException(MPSConstant.MEMBER_NOT_AUTHORIZED_EXCEPTION);
        }
        return Stream.of(DriverListEnum.values()).map(Enum::name).collect(Collectors.toList());
    }

    private void validateMemberRole(MemberDto memberDto) {
        if (MPSMemberRoleEnum.stream().noneMatch(roleAssigned-> roleAssigned.name().equalsIgnoreCase(memberDto.getMemberRole())) || "ADMIN_USER".equals(MPSMemberRoleEnum.valueOf(memberDto.getMemberRole()).name()))
        {
            throw new MemberRoleException(MPSConstant.MEMBER_ROLE_EXCEPTION);
        }
    }

    private void enhanceMemberDto(MemberDto memberDto) {
        StringBuilder stringBuilder = new StringBuilder(memberDto.getFirstName()).append("_").append(memberDto.getPanCard());
        memberDto.setUserName(stringBuilder.toString());
        memberDto.setPassword(memberDto.getPanCard());
        memberDto.setPermission((MPSMemberRoleEnum.valueOf(memberDto.getMemberRole()).toString()));
    }
    private boolean isSpendingLimitFlag(ExpenditureDto expenditureDto, ExpenditureDto expenditureDtoFromDb) {
        if(expenditureDtoFromDb.getSpendingAmount()
                .add(expenditureDto.getSpendingAmount())
                .compareTo(SpendLimitEnum.valueOf(expenditureDto.getMemberRole()).getSpendLimit()) > 0)
        {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
