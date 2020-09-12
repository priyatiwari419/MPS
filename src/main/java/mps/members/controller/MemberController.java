package mps.members.controller;


import lombok.AllArgsConstructor;
import mps.members.dto.ExpenditureDto;
import mps.members.dto.MemberDto;
import mps.members.request.ExpenditureRequest;
import mps.members.request.MemberRequest;
import mps.members.response.MemberResponse;
import mps.members.service.MemberService;
import mps.members.translator.BaseObjectTranslator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/members")
@AllArgsConstructor
@Validated
public class MemberController {

private final MemberService memberService;
private final BaseObjectTranslator baseObjectTranslator;

@PostMapping("/create")
public ResponseEntity<MemberResponse> createMemberOfParliament(@Valid @RequestBody MemberRequest memberRequest){

    MemberDto memberDto = memberService.createMember(baseObjectTranslator.map(memberRequest, MemberDto.class));

    return new ResponseEntity<>(baseObjectTranslator.map(memberDto, MemberResponse.class),HttpStatus.CREATED);
}


@GetMapping("/{memberId}")
public ResponseEntity<String> findWinningConstituencyByMemberId(@PathVariable Long memberId)
{
    return new ResponseEntity<>(memberService.findWinningConstituencyByMemberId(memberId),HttpStatus.OK);
}

@GetMapping
public ResponseEntity<String> findDriverNameByMemberId(@RequestParam Long id)
{
    return new ResponseEntity<>(memberService.findDriverNameByMemberId(id),HttpStatus.OK);
}

@PatchMapping("/{id}")
public ResponseEntity<Object> updateSpendingAmountByMemberId(@PathVariable Long id,@Valid @RequestBody ExpenditureRequest expenditureRequest)
{
    memberService.updateSpendLimitByMemberId(baseObjectTranslator.map(expenditureRequest, ExpenditureDto.class),id);
    return new ResponseEntity<>(HttpStatus.OK);
}

@GetMapping("/drivers")
public ResponseEntity<List<String>> findAllSpecialDriver(@RequestHeader(value = "Authorization") String token)
{
    return new ResponseEntity<>(memberService.listOfDrivers(token.substring(7)),HttpStatus.OK);
}

}
