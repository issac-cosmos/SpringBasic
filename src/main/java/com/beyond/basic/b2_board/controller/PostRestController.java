package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.domain.Post;
import com.beyond.basic.b2_board.dtos.CommonDto;
import com.beyond.basic.b2_board.dtos.PostCreateDto;
import com.beyond.basic.b2_board.dtos.PostDetailDto;
import com.beyond.basic.b2_board.dtos.PostListRes;
import com.beyond.basic.b2_board.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post/rest")
public class PostRestController {

    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
//    Valid와 NotEmpty 등 검증 어노테이션이 한쌍.
    public ResponseEntity<?> postCreate(@Valid @RequestBody PostCreateDto dto){
        postService.save(dto);
        return null;
    }
    @GetMapping("/list")
    public ResponseEntity<?> postList(){
        List<PostListRes> postListResList = postService.findAll();
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(),
                "postlist is found",postListResList),HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> postDetail(@PathVariable Long id){
        PostDetailDto detailDto = postService.findById(id);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(),
                "postlist is found",detailDto),HttpStatus.OK);
    }
}
