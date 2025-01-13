package com.ll.sb_25_01.domain.article.article.articleTag.repository;

import com.ll.sb_25_01.domain.article.article.articleTag.entity.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleTagRepository extends JpaRepository<ArticleTag, Long>    {
    List<ArticleTag> findByArticle_authorId(long authorId);
}
