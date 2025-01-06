package com.ll.sb_25_01.domain.article.article.serivce;

import com.ll.sb_25_01.domain.article.article.entity.Article;
import com.ll.sb_25_01.domain.article.article.service.ArticleService;
import com.ll.sb_25_01.global.rsData.RsData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
public class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;
    @DisplayName("글 작성")
    @Test
    void t1() {
        RsData<Article> WriteRs = articleService.write("user1", "1234");
        Article article = WriteRs.getData();
        assertThat(article.getId()).isGreaterThan(0L);
    }
}