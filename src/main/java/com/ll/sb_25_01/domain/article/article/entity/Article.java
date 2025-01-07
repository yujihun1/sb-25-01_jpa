package com.ll.sb_25_01.domain.article.article.entity;

import com.ll.sb_25_01.domain.member.member.entitiy.Member;
import com.ll.sb_25_01.global.jpa.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true)
public class Article extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    private  Member author;
    private  String title;
    private  String body;
}
