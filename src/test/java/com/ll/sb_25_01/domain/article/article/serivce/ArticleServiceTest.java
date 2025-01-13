package com.ll.sb_25_01.domain.article.article.serivce;

import com.ll.sb_25_01.domain.article.article.articleTag.entity.ArticleTag;
import com.ll.sb_25_01.domain.article.article.articleTag.service.ArticleTagService;
import com.ll.sb_25_01.domain.article.article.articlecomment.entity.ArticleComment;
import com.ll.sb_25_01.domain.article.article.articlecomment.service.ArticleCommentService;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional

public class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ArticleCommentService articleCommentService;

    @Autowired
    private ArticleTagService articleTagService;


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
        Article article2 = articleService.findById(2L).get();
        Member member1 = memberService.findById(1L).get();
        article2.addComment(member1, "댓글 입니다.");
    }
    @DisplayName("1번 글의 댓글을 수정")
    @Test
    void t6() {
        Article article1 = articleService.findById(1L).get();

        article1.getComments().getLast().setBody("수정 댓글");}

    @DisplayName("1번 글의 댓글 중 마지막 것을 삭제")
    @Test
    void t7() {
        Article article1 = articleService.findById(1L).get();
        ArticleComment lastComment = article1.getComments().getLast();

        article1.removeComment(lastComment);

    }

    @DisplayName("게시물 별 댓글 수 출력")
    @Test
    void t8(){
        List<Article> articles = articleService.findAll();

        articles.forEach(article -> {
            System.out.println("게시물 번호: "+article.getId());
            System.out.println("댓글 수: "+article.getComments().size());
        });
    }


    @DisplayName("1번 게시물 태그(String) 반환")
    @Test
    void t9() {
        Article article1 = articleService.findById(1L).get();
        String tagsStr = article1.getTagsStr();

        assertThat(tagsStr).isEqualTo("#자바 #백엔드");
    }


    @DisplayName("1번 게시물 toString")
    @Test
    void t10() {
        Article article1 = articleService.findById(1L).get();
        System.out.println(article1);
    }

    @DisplayName("1번 회원이 작성한 태그들")
    @Test
    void t12(){
        List<ArticleTag> articleTags = articleTagService.findByAuthorId(1L);

        assertThat(articleTags.size()).isGreaterThan(0);
    }
}