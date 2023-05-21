package flab.buynow.member.controller;

import flab.buynow.common.ValidGroups;
import flab.buynow.member.domain.Member;
import flab.buynow.member.dto.MemberDto;
import flab.buynow.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService service;

    /**
     * 회원조회
     */
    @GetMapping("/{loginId}")
    public ResponseEntity findByLoginId(@PathVariable String loginId) {
        return ResponseEntity.ok().body(service.findByLoginId(loginId));
    }

    /**
     * 전체회원조회
     */
    @GetMapping
    public ResponseEntity getMembers() {
        return ResponseEntity.ok().body(service.getMembers());
    }

    /**
     * 회원가입
     */
    @PostMapping
    public ResponseEntity create(@RequestBody @Validated(ValidGroups.createMemberGroup.class) MemberDto member) {
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
    @PutMapping("/{loginId}")
    public ResponseEntity update(@PathVariable String loginId,
                                 @RequestBody @Validated(ValidGroups.updateMemberGroup.class) MemberDto member) {
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