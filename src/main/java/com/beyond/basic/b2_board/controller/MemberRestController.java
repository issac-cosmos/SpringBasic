package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.dtos.MemberUpdateDto;
import com.beyond.basic.b2_board.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/member/rest")
@RestController
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 목록 조회
    @GetMapping("/list")
    public List<MemberListRes> memberList(){
        List<MemberListRes> memberListResList = memberService.findAll();
        return memberListResList;
    }

    // 회원 상세 조회
    @GetMapping("/detail/{id}")
    public MemberDetailDto memberDetail(@PathVariable Long id){
        MemberDetailDto dto = memberService.findById(id);
        return dto;
    }
    // 등록
    @PostMapping("/create")
    public String memberCreatePost(@RequestBody MemberCreateDto memberCreateDto){
            memberService.save(memberCreateDto);
        return "OK";
    }
    // 비밀번호 변경
    // 매핑(Mapping)종류 get:조회 , post:등록, patch:부분수정, put:대체, delete:삭제
    // axios.patch
    @PatchMapping("/update/pw")
    public String memberUpdatePw(@RequestBody MemberUpdateDto memberUpdateDto){
        memberService.updatePw(memberUpdateDto);
        return "OK";
    }
}
