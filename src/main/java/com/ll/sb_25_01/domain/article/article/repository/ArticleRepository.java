package com.ll.sb_25_01.domain.article.article.repository;

import com.ll.sb_25_01.domain.article.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}
