package mps.members.dao;

import mps.members.dto.ExpenditureDto;
import mps.members.dto.MemberDto;

import java.util.Optional;

public interface MemberDao {

  Optional<MemberDto> createMember(MemberDto memberDto);

  String findWinningConstituencyByMemberId(Long memberId);

  String findDriverNameByMemberId(Long memberId);

  void updateSpendLimitByMemberId(ExpenditureDto expenditureDto, Long memberId,boolean spendExceedFlag);

  Optional<ExpenditureDto> findExpenditureByMemberIdAndMemberRole(Long memberId,String memberRole);

  Optional<MemberDto> validationFromDb(Long memberId);

  Optional<MemberDto> findByUserName(String userName);

}
