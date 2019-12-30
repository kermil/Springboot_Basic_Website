package com.example.test.domain.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Entity
@Table(name="members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 30, nullable = false)
    @NotEmpty(message = "ID를 입력해주세요.")
    private String id;

    @Column(length = 100, nullable = false)
    @NotEmpty(message = "Password를 입력해주세요.")
    private String password;

    @Column(length = 100, nullable = false)
    @NotEmpty(message = "Name을 입력해주세요.")
    private String name;

    @Column(length = 100, nullable = false)
    @NotEmpty(message = "Nickname을 입력해주세요.")
    private String nickname;

    @Builder
    public MemberEntity(Long idx, String id, String password, String name, String nickname) {
        this.idx=idx;
        this.id=id;
        this.password=password;
        this.name=name;
        this.nickname=nickname;
    }
}
