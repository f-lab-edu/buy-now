package flab.buynow.member.controller;

import flab.buynow.member.domain.Member;
import flab.buynow.member.dto.FindMemberDto;
import flab.buynow.member.dto.InsertMemberDto;
import flab.buynow.member.dto.UpdateMemberDto;
import flab.buynow.member.service.MemberService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<FindMemberDto> findByLoginId(@PathVariable String loginId) {
        return ResponseEntity.ok().body(new FindMemberDto(service.findByLoginId(loginId)));
    }

    /**
     * 전체회원조회
     */
    @GetMapping("/members")
    public ResponseEntity<List<FindMemberDto>> findSliceBy(@RequestParam(defaultValue = "0") long offset,
                @PageableDefault(size=5, sort="id", direction = Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(service.findSliceById(offset, pageable).stream().map(FindMemberDto::new)
            .collect(Collectors.toList()));
    }

    /**
     * 회원가입
     */
    @PostMapping("/member")
    public ResponseEntity<FindMemberDto> save(@RequestBody @Valid InsertMemberDto member) {
        Member joinMember = Member.builder()
            .loginId(member.getLoginId())
            .password(member.getPassword())
            .name(member.getName())
            .telephoneNumber(member.getTelephoneNumber())
            .address(member.getAddress())
            .addressDetail(member.getAddressDetail())
            .build();

        return ResponseEntity.ok().body(new FindMemberDto(service.save(joinMember)));
    }

    /**
     * 회원정보수정
     */
    @PutMapping("/member/{loginId}")
    public ResponseEntity<FindMemberDto> update(@PathVariable String loginId, @RequestBody @Valid UpdateMemberDto member) {
        Member updateMember = Member.builder()
            .loginId(loginId)
            .password(member.getPassword())
            .name(member.getName())
            .telephoneNumber(member.getTelephoneNumber())
            .address(member.getAddress())
            .addressDetail(member.getAddressDetail())
            .build();

        return ResponseEntity.ok().body(new FindMemberDto(service.update(loginId, updateMember)));
    }

}