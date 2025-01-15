package com.ll.sb_25_01.domain.article.article.repository;

import com.ll.sb_25_01.domain.article.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findByOrderByIdDesc();

    Page<Article> findByTitleContainingOrBodyContaining(String kw, String kw1, Pageable pageable);
    Page<Article> findByTitleContaining(String kw, Pageable pageable);
    Page<Article> findByBodyContaining(String kw, Pageable pageable);

    Page<Article> findByAuthor_usernameContainingOrTitleContainingOrBodyContaining(String kw, String kw1, String kw2, Pageable pageable);
    Page<Article> findByAuthor_usernameContaining(String kw, Pageable pageable);


}
