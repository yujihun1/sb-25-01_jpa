package com.ll.sb_25_01.domain.article.article.articleTag.entity;

import com.ll.sb_25_01.domain.article.article.entity.Article;
import com.ll.sb_25_01.global.jpa.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString(callSuper = true)
public class ArticleTag extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;
    private String content;

}
