package mps.commisioner.controller;

import lombok.AllArgsConstructor;
import mps.commisioner.dto.ArrestHistoryDto;
import mps.commisioner.request.ArrestHistoryRequest;
import mps.commisioner.response.ArrestHistoryResponse;
import mps.commisioner.response.ExpenditureResponse;
import mps.commisioner.service.ArrestService;
import mps.members.service.MemberService;
import mps.members.translator.BaseObjectTranslator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/commissioners")
@AllArgsConstructor
@Validated
public class CommissionerController {

    private final ArrestService arrestService;
    private final MemberService memberService;
    private final BaseObjectTranslator baseObjectTranslator;

    @PostMapping("/submit")
    public ResponseEntity<Object> submitRequestForArresting(@Valid @RequestBody ArrestHistoryRequest arrestHistoryRequest, @RequestHeader(value = "Authorization") String token){
        return new ResponseEntity<>(arrestService.submitRequestForArresting(baseObjectTranslator.map(arrestHistoryRequest, ArrestHistoryDto.class),token.substring(7)),HttpStatus.CREATED);
    }

    @GetMapping("/{submitId}")
    public ResponseEntity<Object> findArrestHistoryById(@PathVariable Long submitId){
        return new ResponseEntity<>(baseObjectTranslator.map(arrestService.findArrestHistoryByArrestHistoryId(submitId), ArrestHistoryResponse.class),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ExpenditureResponse> findSpendingExceedFlagByMemberIdAndMemberRole(@RequestParam Long memberId,@RequestParam String memberRole){
        return new ResponseEntity<>(baseObjectTranslator.map(memberService.findExpenditureByMemberIdAndMemberRole(memberId,memberRole), ExpenditureResponse.class),HttpStatus.OK);
    }


}
