package flab.buynow.member.controller;

import flab.buynow.member.domain.Member;
import flab.buynow.member.dto.InsertMemberDto;
import flab.buynow.member.dto.PageInfoDto;
import flab.buynow.member.dto.UpdateMemberDto;
import flab.buynow.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    /**
     * 회원조회
     */
    @GetMapping("/member/{loginId}")
    public ResponseEntity findByLoginId(@PathVariable String loginId) {
        return ResponseEntity.ok().body(service.findByLoginId(loginId));
    }

    /**
     * 전체회원조회
     */
    @GetMapping("/members")
    public ResponseEntity findAll(@RequestParam(defaultValue = "0") Long offset) {
        PageInfoDto pageInfo = PageInfoDto.builder().offset(offset).build();
        return ResponseEntity.ok().body(service.findAll(pageInfo));
    }

    /**
     * 회원가입
     */
    @PostMapping("/member")
    public ResponseEntity create(@RequestBody @Valid InsertMemberDto member) {
        Member joinMember = Member.builder()
            .loginId(member.getLoginId())
            .password(member.getPassword())
            .name(member.getName())
            .tel(member.getTel())
            .address(member.getAddress())
            .addressDetail(member.getAddressDetail())
            .build();

        return ResponseEntity.ok().body(service.create(joinMember));
    }

    /**
     * 회원정보수정
     */
    @PutMapping("/member/{loginId}")
    public ResponseEntity update(@PathVariable String loginId,
        @RequestBody @Valid UpdateMemberDto member) {
        Member updateMember = Member.builder()
            .loginId(loginId)
            .password(member.getPassword())
            .name(member.getName())
            .tel(member.getTel())
            .address(member.getAddress())
            .addressDetail(member.getAddressDetail())
            .build();

        return ResponseEntity.ok().body(service.update(updateMember));
    }

}