package com.beyond.basic.b2_board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
    private Long id ;
    private String name;
    private String email;
    private String password;
}
