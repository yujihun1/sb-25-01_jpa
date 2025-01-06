package com.ll.sb_25_01.domain.article.article.serivce;

import com.ll.sb_25_01.domain.article.article.entity.Article;
import com.ll.sb_25_01.domain.article.article.service.ArticleService;
import com.ll.sb_25_01.domain.member.member.entitiy.Member;
import com.ll.sb_25_01.domain.member.member.service.MemberService;
import com.ll.sb_25_01.global.rsData.RsData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional

public class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private MemberService memberService;
    @DisplayName("글 작성")
    @Test

    void t() {
        RsData<Article> writeRs = articleService.write(1, "제목", "내용");
        Article article = writeRs.getData();
        assertThat(article.getId()).isGreaterThan(0L);
    }
    @DisplayName("1번 글 작성자는 user1")
    @Test
    void t2(){
        Article article = articleService.findById(1L).get();

        Member author = article.getAuthor();

        assertThat(author.getUsername()).isEqualTo("user1");
    }
}