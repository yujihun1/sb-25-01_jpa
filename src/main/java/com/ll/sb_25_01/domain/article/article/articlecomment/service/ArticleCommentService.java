package com.ll.sb_25_01.domain.article.article.articlecomment.service;

import com.ll.sb_25_01.domain.article.article.articlecomment.entity.ArticleComment;
import com.ll.sb_25_01.domain.article.article.articlecomment.repository.ArticleCommentRepository;
import com.ll.sb_25_01.domain.article.article.entity.Article;
import com.ll.sb_25_01.domain.member.member.entitiy.Member;
import com.ll.sb_25_01.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleCommentService {
    private final ArticleCommentRepository articleCommentRepository;
    public RsData<ArticleComment> write(Member author, Article article, String body) {
        ArticleComment articleComment = ArticleComment.builder()
                .article(article)
                .author(author)
                .body(body)
                .build();
        articleCommentRepository.save(articleComment);
        return RsData.of("200", "%d번 댓글이 작성되었습니다.".formatted(articleComment.getId()), articleComment);
    }
    @Transactional
    public void modify(ArticleComment comment, String body) {
        comment.setBody(body);
    }
    public void delete(ArticleComment comment) {
        articleCommentRepository.delete(comment);
    }
    public Optional<ArticleComment> findLatest() {
        return articleCommentRepository.findFirstByOrderByIdDesc();
    }
    public Optional<ArticleComment> findFirstByArticleIdOrderByIdDesc(long id) {
        return articleCommentRepository.findFirstByArticleIdOrderByIdDesc(1);
    }
}