package com.beyond.basic.b2_board.dtos;


import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PostCreateDto {
    @NotEmpty
    private String title;
    private String contents;
    private Long memberId;

    public Post toEntity(Member member){
//        빌더패턴은 엔티티명.builder().build();
        return Post.builder().title(this.title)
                .member(member).contents(this.contents).build();
    }
}
