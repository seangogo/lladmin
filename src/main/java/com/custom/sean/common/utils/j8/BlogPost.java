package com.custom.sean.common.utils.j8;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlogPost {
    private String p1;
    private String p2;
    private Integer i1;
    private Integer i2;

    public BlogPost(String p1, String p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
}
