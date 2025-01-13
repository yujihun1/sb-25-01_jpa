package com.ll.sb_25_01.domain.article.article.entity;

import com.ll.sb_25_01.domain.article.article.articleTag.entity.ArticleTag;
import com.ll.sb_25_01.domain.article.article.articlecomment.entity.ArticleComment;
import com.ll.sb_25_01.domain.member.member.entitiy.Member;
import com.ll.sb_25_01.global.jpa.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @ToString.Exclude
    private List<ArticleComment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL,orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<ArticleTag> tags = new ArrayList<>();
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
    public void addComment(ArticleComment comment){
        comments.add(comment);
    }

    public void addTag(String tagContent){
        ArticleTag tag = ArticleTag.builder()
                .article(this)
                .content(tagContent)
                .build();
        tags.add(tag);
    }
    public void addTag(String... tagContents) {
        for (String tagContent : tagContents) {
            addTag(tagContent);
        }
    }

    public String getTagsStr() {
        String tagsStr= tags
                .stream()
                .map(ArticleTag::getContent)
                .collect(Collectors.joining(" #"));
        if (tagsStr.isBlank()) {
            return "";
        }
        return "#" + tagsStr;
    }
}
