package com.ll.sb_25_01.domain.article.article.serivce;

import com.ll.sb_25_01.domain.article.article.articlecomment.entity.ArticleComment;
import com.ll.sb_25_01.domain.article.article.entity.Article;
import com.ll.sb_25_01.domain.article.article.service.ArticleService;
import com.ll.sb_25_01.domain.member.member.entitiy.Member;
import com.ll.sb_25_01.domain.member.member.service.MemberService;
import com.ll.sb_25_01.global.rsData.RsData;
import com.ll.sb_25_01.standard.util.Ut;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
    void t1() {
        RsData<Article> writeRs = articleService.write(1, "제목", "내용");
        Article article = writeRs.getData();
        assertThat(article.getId()).isGreaterThan(0L);
    }

    @DisplayName("1번 글 제목 가져오기")
    @Test
    void t2() {
        Article article = articleService.findById(1L).get();


        assertThat(article.getTitle()).isEqualTo("제목1");
    }


    @DisplayName("1번 글 작성자는 user1")
    @Test
    void t3() {
        Article article = articleService.findById(1L).get();

        Member author = article.getAuthor();

        assertThat(author.getUsername()).isEqualTo("user1");
    }

    @DisplayName("1번 글 제목 수정")
    @Test
    @Rollback(false)
    void t4() {
        Article article = articleService.findById(1L).get();
        LocalDateTime oldModifyDate = article.getModifyDate();

        Ut.thread.sleep(1000);

        articleService.modify(article, "수정된 제목", "수정된 내용");

        Article article_ = articleService.findById(1L).get();
        assertThat(article_.getTitle()).isEqualTo("수정된 제목");

    }

    @DisplayName("1번 글의 댓글들을 추가")
    @Test
    @Rollback(false)
    void t5() {
        Article article1 = articleService.findById(1L).get();
        Member member2 = memberService.findById(2L).get();
        article1.addComment(member2, "댓글 입니다.");
    }


    @DisplayName("1번 글의 댓글들을 수정")
    @Test
    void t6() {
        Article article = articleService.findById(1L).get();

        article.getComments().forEach(comment -> {
            articleService.modifyComment(comment, comment.getBody() + "!!");
        });
    }

    @DisplayName("1번 글의 댓글 중 마지막 것을 삭제")
    @Test
    void t7() {
        Article article = articleService.findById(1L).get();

        ArticleComment lastComment = article.getComments().getLast();

        article.removeComment(lastComment);
    }
}