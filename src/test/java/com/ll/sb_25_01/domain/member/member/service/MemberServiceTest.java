package com.ll.sb_25_01.domain.member.member.service;

import com.ll.sb_25_01.domain.member.member.entitiy.Member;
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
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @DisplayName("회원가입")
    @Test
    void t1() {
        RsData<Member> joinRs = memberService.join("user1", "1234");
        Member member = joinRs.getData();
        assertThat(member.getId()).isGreaterThan(0L);
    }
}
