package com.example.test.dto;


import com.example.test.domain.Entity.TableEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TableDTO {
    private Long idx;
    private String name;
    private String detail;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public TableEntity toEntity() {
        TableEntity build = TableEntity.builder()
                .idx(idx)
                .name(name)
                .detail(detail)
                .build();
        return build;
    }

    @Builder
    public TableDTO(Long idx, String name, String detail, LocalDateTime created_at, LocalDateTime updated_at) {
        this.idx=idx;
        this.name=name;
        this.detail=detail;
        this.created_at=created_at;
        this.updated_at=updated_at;
    }
}
