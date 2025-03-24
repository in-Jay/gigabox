package com.example.gigabox;

import com.example.gigabox.service.FaqService;
import com.example.gigabox.service.NoticeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GigaboxApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private FaqService faqService;
	@Test
	void testJpa1() {
		for (int i=1; i<=20;i++) {
			String category = "영화예매";
			String question = String.format("영화예매 : [%03d]", i);
			String answer = "영화예매" + i;
			this.faqService.create(category, question, answer);
		}
	}

	@Test
	void testJpa2() {
		for (int i = 21; i <= 40; i++) {
			String category = "결제/관람권";
			String question = String.format("결제/관람권 : [%03d]", i);
			String answer = "결제/관람권" + i;
			this.faqService.create(category, question, answer);
		}
	}

	@Test
	void testJpa3() {
		for (int i = 41; i <= 60; i++) {
			String category = "극장";
			String question = String.format("극장 : [%03d]", i);
			String answer = "극장" + i;
			this.faqService.create(category, question, answer);
		}
	}

	@Test
	void testJpa4() {
		for (int i = 61; i <= 80; i++) {
			String category = "스토어";
			String question = String.format("스토어 : [%03d]", i);
			String answer = "스토어" + i;
			this.faqService.create(category, question, answer);
		}
	}

	@Test
	void testJpa5() {
		for (int i = 81; i <= 100; i++) {
			String category = "홈페이지/모바일";
			String question = String.format("홈페이지/모바일 : [%03d]", i);
			String answer = "홈페이지/모바일" + i;
			this.faqService.create(category, question, answer);
		}
	}

	@Autowired
	private NoticeService noticeService;
	@Test
	void testJpa6() {
		for (int i = 1; i <= 20; i++) {
			String theaterName = "메가박스";
			String sortation = "공지";
			String title = String.format("메가박스 : [%03d]", i);
			String content = "메가박스" + i;

			this.noticeService.create(theaterName, sortation, title, content);
		}
	}

	@Test
	void testJpa7() {
		for (int i = 21; i <= 25; i++) {
			String theaterName = "천안";
			String sortation = "공지";
			String title = String.format("천안 : [%03d]", i);
			String content = "천안" + i;

			this.noticeService.create(theaterName, sortation, title, content);
		}
	}

	@Test
	void testJpa8() {
		for (int i = 26; i <= 30; i++) {
			String theaterName = "센트럴";
			String sortation = "공지";
			String title = String.format("센트럴 : [%03d]", i);
			String content = "센트럴" + i;

			this.noticeService.create(theaterName, sortation, title, content);
		}
	}

	@Test
	void testJpa9() {
		for (int i = 31; i <= 35; i++) {
			String theaterName = "상암월드컵경기장";
			String sortation = "공지";
			String title = String.format("상암월드컵경기장 : [%03d]", i);
			String content = "상암월드컵경기장" + i;

			this.noticeService.create(theaterName, sortation, title, content);
		}
	}

	@Test
	void testJpa10() {
		for (int i = 36; i <= 40; i++) {
			String theaterName = "분당";
			String sortation = "공지";
			String title = String.format("분당 : [%03d]", i);
			String content = "분당" + i;

			this.noticeService.create(theaterName, sortation, title, content);
		}
	}

}
