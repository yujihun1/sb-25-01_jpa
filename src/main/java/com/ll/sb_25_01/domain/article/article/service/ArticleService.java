package com.ll.sb_25_01.domain.article.article.service;

import com.ll.sb_25_01.domain.article.article.entity.Article;
import com.ll.sb_25_01.domain.article.article.repository.ArticleRepository;
import com.ll.sb_25_01.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {
    private  final ArticleRepository articleRepository;

    @Transactional
    public RsData<Article> write(String title, String body){
        Article article = Article.builder()
                .title(title)
                .body(body)
                .build();

         articleRepository.save(article);

         return  RsData.of("200","%d번 게시물이 작성되었습니다.".formatted(article.getId()),article);
    }
}
