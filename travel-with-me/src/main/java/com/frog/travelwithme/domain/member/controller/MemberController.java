package com.frog.travelwithme.domain.member.controller;

import com.frog.travelwithme.domain.member.controller.dto.MemberDto;
import com.frog.travelwithme.domain.member.service.MemberService;
import com.frog.travelwithme.global.dto.SingleResponseDto;
import com.frog.travelwithme.global.security.auth.userdetails.CustomUserDetails;
import com.frog.travelwithme.global.validation.CustomAnnotationCollection.CustomEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 작성자: 김찬빈
 * 버전 정보: 1.0.0
 * 작성일자: 2023/03/29
 **/
@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@Valid @RequestBody MemberDto.SignUp signUpDto) {
        MemberDto.Response response = memberService.signUp(signUpDto);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getMember(@AuthenticationPrincipal CustomUserDetails user) {
        String email = user.getEmail();
        MemberDto.Response response = memberService.findMemberByEmail(email);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity patchMember(@AuthenticationPrincipal CustomUserDetails user,
                                      @RequestBody MemberDto.Patch patchDto) {
        String email = user.getEmail();
        MemberDto.Response response = memberService.updateMember(patchDto, email);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteMember(@AuthenticationPrincipal CustomUserDetails user) {
        String email = user.getEmail();
        memberService.deleteMember(email);

        return new ResponseEntity<>(new SingleResponseDto<>("Member deleted successfully"), HttpStatus.NO_CONTENT);
    }

    @PostMapping("/emails/verification-requests")
    public ResponseEntity sendMessage(@RequestParam("email") @Valid @CustomEmail  String email) {
        memberService.sendCodeToEmail(email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/emails/verifications")
    public ResponseEntity verificationEmail(@RequestParam("email") @Valid @CustomEmail  String email,
                                            @RequestParam("code") String authCode) {

        return new ResponseEntity<>(memberService.verifiedCode(email, authCode), HttpStatus.OK);
    }
}
