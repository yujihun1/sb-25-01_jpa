package com.ll.sb_25_01.domain.article.article.entity;

import com.ll.sb_25_01.domain.member.member.entitiy.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private  Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private  Member author;
    private  String title;
    private  String body;
}
