package com.example.test.dto;

import com.example.test.domain.Entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@NoArgsConstructor
public class MemberDTO {

    private Long idx;
    private String id;
    private String password;
    private String name;
    private String nickname;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;



    public MemberEntity toEntity() {
        MemberEntity build = MemberEntity.builder()
                .idx(idx)
                .id(id)
                .password(password)
                .name(name)
                .nickname(nickname)
                .build();
        return build;
    }

    @Builder
    public MemberDTO(Long idx, String id, String password, String name, String nickname, LocalDateTime created_at, LocalDateTime updated_at) {
        this.idx=idx;
        this.id=id;
        this.password=password;
        this.name=name;
        this.nickname=nickname;
        this.created_at=created_at;
        this.updated_at=updated_at;
    }
}
