package mps.members.dao;

import lombok.AllArgsConstructor;
import mps.members.dto.ExpenditureDto;
import mps.members.dto.MemberDto;
import mps.members.entity.ExpenditureEntity;
import mps.members.entity.MemberEntity;
import mps.members.repository.ExpenditureRepository;
import mps.members.repository.MemberRepository;
import mps.members.translator.BaseObjectTranslator;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MPSMemberDao implements MemberDao {

    private final MemberRepository memberRepository;
    private final BaseObjectTranslator baseObjectTranslator;
    private final ExpenditureRepository expenditureRepository;

    @Override
    public Optional<MemberDto> createMember(MemberDto memberDto) {
        MemberEntity memberEntityFromRequest = baseObjectTranslator.map(memberDto, MemberEntity.class);
        setExpenditureEntity(memberEntityFromRequest);
        MemberEntity memberEntityFromDb  = memberRepository.save(memberEntityFromRequest);
       return Optional.ofNullable(baseObjectTranslator.map(memberEntityFromDb, MemberDto.class));
    }

    @Override
    public String findWinningConstituencyByMemberId(Long memberId) {
        Optional<MemberEntity> memberEntityOptional =  memberRepository.findById(memberId);
        return memberEntityOptional.map(MemberEntity::getConstituencyName).orElse(null);
    }

    @Override
    public String findDriverNameByMemberId(Long memberId) {
        Optional<MemberEntity> memberEntityOptional =  memberRepository.findById(memberId);
        return memberEntityOptional.map(MemberEntity::getDriverName).orElse(null);
    }

    @Override
    public void updateSpendLimitByMemberId(ExpenditureDto expenditureDto, Long memberId,boolean spendExceedFlag) {

        expenditureRepository.updateSpendingAmountAndIsSpendingAmountExceedsByMemberId(expenditureDto.getSpendingAmount(),spendExceedFlag,memberId);
    }

    @Override
    public Optional<ExpenditureDto> findExpenditureByMemberIdAndMemberRole(Long memberId,String memberRole) {
        Optional<ExpenditureEntity> expenditureEntity = expenditureRepository.findByMemberIdAndMemberRole(memberId,memberRole);
        return expenditureEntity.map(expenditureEntity1 -> {
            ExpenditureDto expenditureDto =baseObjectTranslator.map(expenditureEntity1,ExpenditureDto.class);
            expenditureDto.setMemberId(expenditureEntity1.getMemberEntity().getId());
        return expenditureDto;
        });
    }

    @Override
    public Optional<MemberDto> validationFromDb(Long memberId) {
       Optional<MemberEntity> memberEntity = memberRepository.findById(memberId);
      return memberEntity.map(memberEntity1 -> baseObjectTranslator.map(memberEntity1,MemberDto.class));
    }

    @Override
    public Optional<MemberDto> findByUserName(String userName) {
     return memberRepository.findByUserName(userName).map(memberEntity -> baseObjectTranslator.map(memberEntity,MemberDto.class));
    }


    private void setExpenditureEntity(MemberEntity memberEntityFromRequest){
        ExpenditureEntity expenditureEntity = new ExpenditureEntity();
        expenditureEntity.setMemberEntity(memberEntityFromRequest);
        expenditureEntity.setSpendingAmount(new BigInteger("0"));
        expenditureEntity.setSpendingAmountExceeds(false);
        expenditureEntity.setMemberRole(memberEntityFromRequest.getMemberRole());
        memberEntityFromRequest.setExpenditureEntity(expenditureEntity);
    }
}


