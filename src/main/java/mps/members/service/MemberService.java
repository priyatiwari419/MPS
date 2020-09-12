package mps.members.service;

import mps.members.dto.ExpenditureDto;
import mps.members.dto.MemberDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {

  MemberDto createMember(MemberDto memberDto);

  String findWinningConstituencyByMemberId(Long memberId);

  String findDriverNameByMemberId(Long memberId);

  void updateSpendLimitByMemberId(ExpenditureDto expenditureDto,Long memberId);

  Optional<ExpenditureDto> findExpenditureByMemberIdAndMemberRole(Long memberId,String memberRole);

  void validateMemberDto(Long memberId);

  List<String> listOfDrivers(String token);
}
