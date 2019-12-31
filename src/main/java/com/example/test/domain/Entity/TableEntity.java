package com.example.test.domain.Entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name="table_list")
//@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TableEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String detail;

    @Builder
    public TableEntity(Long idx, String name, String detail) {
        this.idx=idx;
        this.name=name;
        this.detail=detail;
    }
}
