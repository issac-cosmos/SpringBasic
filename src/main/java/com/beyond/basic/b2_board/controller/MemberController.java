package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
//    의존성주입(DI) 방법1. Aotowired 어노테이션 사용 : 필드 주입
    @Autowired
    private MemberService memberService;

    //홈 화면
    @GetMapping("")
    public String memberHome(){

        return "/member/home";
    }

    // 회원 목록 조회
    @GetMapping("/list")
    public String memberList(Model model){
        List<MemberListRes> memberListResList = memberService.findAll();
        model.addAttribute("memberList",memberListResList);
        return "/member/member-list";
    }

    // 회원 상세 조회
    @GetMapping("/detail/{id}")
    public String memberDetail(@PathVariable Long id){
        System.out.println(id);
        return "/member/member-detail";
    }
    // 회원가입

    @GetMapping("/create")
    public String memberCreate(){
        return "/member/member-create";
    }
    // 등록
    @PostMapping("/create")
    public String memberCreatePost(MemberCreateDto memberCreateDto){
        memberService.save(memberCreateDto);
        //새로운 화면 리턴이 아닌 url 재호출을 통해 redirect
        return "redirect:/member/list";
    }
}