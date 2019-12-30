package com.example.test.domain.Entity;

import lombok.*;

import javax.persistence.*;

@Getter @Entity
@Table(name="post_list")
//@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private Long author_midx;

    @Column(nullable = false)
    private Long tidx;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;


    @Builder
    public PostEntity(Long idx, String title, String content, Long author_midx, Long tidx) {
        this.idx = idx;
        this.author_midx = author_midx;
        this.title = title;
        this.content = content;
        this.tidx=tidx;
    }

}
