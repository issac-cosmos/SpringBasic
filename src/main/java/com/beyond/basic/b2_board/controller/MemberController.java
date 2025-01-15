package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
//    의존성주입(DI) 방법1. Aotowired 어노테이션 사용 : 필드 주입
//    @Autowired
//    private MemberService memberService;

//    의존성주입(DI) 방법2. 생성자 주입방식(가장 많이 사용하는 방식)
//    장점1) final을 통해 상수로 사용가능(안정성 향상)
//    장점2) 다형성 구현가능
//    장점3) 순환참조 방지(컴파일 시점에 check해준다)

//    private final MemberService memberService;
////    싱글톤객체로 만들어지는 시점에 아래 생성자가 호출. 생성자가 하나밖에 없을때에는 Autowired 생략가능
////    @Autowired
//    public MemberController(MemberService memberService){
//        this.memberService = memberService;
//    }

//    의존성 주입방법3. RequiredArgs 어노테이션 사용방법
//    RequiredArgs : 반드시 초기화 되어야 하는 필드(final키워드 , @NonNull어노테이션)를 대상으로 생성자를 자동으로 만들어주는 어노테이션.
    private final MemberService memberService;


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
    public String memberDetail(@PathVariable Long id,Model model){
        try{
        MemberDetailDto dto = memberService.findById(id);
        model.addAttribute("member",dto);
        return "/member/member-detail";
        }catch (NoSuchElementException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "/member/member-error";
        }catch (RuntimeException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "/member/member-error";
        }
    }
    // 회원가입

    @GetMapping("/create")
    public String memberCreate(){
        return "/member/member-create";
    }
    // 등록
    @PostMapping("/create")
    public String memberCreatePost(MemberCreateDto memberCreateDto, Model model){
        try {
            memberService.save(memberCreateDto);
            //새로운 화면 리턴이 아닌 url 재호출을 통해 redirect
            return "redirect:/member/list";
        }catch (IllegalArgumentException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "/member/member-error";
        }
    }
}
