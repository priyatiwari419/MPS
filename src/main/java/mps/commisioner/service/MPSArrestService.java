package mps.commisioner.service;

import lombok.AllArgsConstructor;
import mps.commisioner.dao.ArrestDao;
import mps.commisioner.dto.ArrestHistoryDto;
import mps.exception.ArrestHistoryNotFoundException;
import mps.exception.MemberNotFoundException;
import mps.exception.MemberRoleException;
import mps.members.dao.MemberDao;
import mps.members.dto.MemberDto;
import mps.util.JwtTokenHelper;
import mps.util.MPSConstant;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MPSArrestService implements ArrestService {

    private final ArrestDao arrestDao;
    private final JwtTokenHelper jwtTokenHelper;
    private final MemberDao memberDao;


    @Override
    public Long submitRequestForArresting(ArrestHistoryDto arrestHistoryDto,String token) {
        validateMemberId(arrestHistoryDto);
        validateMemberRole(arrestHistoryDto,token);
        setArrestStatus(arrestHistoryDto);
        return arrestDao.submitRequestForArresting(arrestHistoryDto);
    }

    @Override
    public ArrestHistoryDto findArrestHistoryByArrestHistoryId(Long arrestHistoryId) {
        Optional<ArrestHistoryDto> arrestHistoryDto = arrestDao.findArrestHistoryByArrestHistoryId(arrestHistoryId);
        return arrestHistoryDto.orElseThrow(() -> new ArrestHistoryNotFoundException(MPSConstant.ARREST_HISTORY_NOT_FOUND_EXCEPTION));
    }

    private void validateMemberRole(ArrestHistoryDto arrestHistoryDto,String token) {
        if(!jwtTokenHelper.validateRoleForArrest(token,arrestHistoryDto.getMemberRole()))
        {
            throw new MemberRoleException(MPSConstant.MEMBER_NOT_AUTHORIZED_EXCEPTION);
        }
    }

    private void setArrestStatus(ArrestHistoryDto arrestHistoryDto)
    {
        arrestHistoryDto.setArrestStatus(MPSConstant.ARRESTED);
    }

    private void validateMemberId(ArrestHistoryDto arrestHistoryDto){
        Optional<MemberDto> memberDtoOptional = memberDao.validationFromDb(arrestHistoryDto.getMemberId());
        memberDtoOptional.ifPresentOrElse(memberDto -> {
            if(!arrestHistoryDto.getMemberRole().equalsIgnoreCase(memberDto.getMemberRole())){
                throw new MemberRoleException(MPSConstant.MEMBER_ROLE_WITH_MEMBER_ID_EXCEPTION);
            }
            },() -> {
            throw new MemberNotFoundException(MPSConstant.MEMBER_NOT_FOUND_EXCEPTION);
        });


    }
}
