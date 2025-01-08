package com.ll.sb_25_01.domain.article.article.entity;

import com.ll.sb_25_01.domain.article.article.articlecomment.entity.ArticleComment;
import com.ll.sb_25_01.domain.member.member.entitiy.Member;
import com.ll.sb_25_01.global.jpa.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ArticleComment> comments = new ArrayList<>();
    public void addComment(Member commentAuthor, String commentBody) {
        ArticleComment comment = ArticleComment
                .builder()
                .article(this)
                .author(commentAuthor)
                .body(commentBody)
                .build();
        comments.add(comment);
    }
    public void removeComment(ArticleComment comment) {
        comments.remove(comment);
    }
}
