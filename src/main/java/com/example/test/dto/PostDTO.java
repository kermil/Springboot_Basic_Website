package com.example.test.dto;

import com.example.test.domain.Entity.PostEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@NoArgsConstructor
public class PostDTO {
    private Long tidx;
    private String title;
    private String content;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Long idx;
    private Long author_midx;


    public PostEntity toEntity() {
        PostEntity build = PostEntity.builder()
                .idx(idx)
                .title(title)
                .content(content)
                .author_midx(author_midx)
                .tidx(tidx)
                .build();
        return build;
    }

    @Builder
    public PostDTO(Long idx, String title, String content, LocalDateTime created_at, LocalDateTime updated_at, Long author_midx, Long tidx) {
        this.idx=idx;
        this.tidx=tidx;
        this.title=title;
        this.content=content;
        this.created_at=created_at;
        this.updated_at=updated_at;
        this.author_midx=author_midx;
    }
}
