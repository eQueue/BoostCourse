package kr.or.connect.reservation.main;

import java.util.*;

import org.springframework.context.*;
import org.springframework.context.annotation.*;

import kr.or.connect.reservation.config.*;
import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.service.*;

public class GuestbookServiceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		GuestbookService guestbookService = ac.getBean(GuestbookService.class);
		
		GuestBook guestbook = new GuestBook();
		guestbook.setName("kang kyungmi22");
		guestbook.setContent("반갑습니다. 여러분. 여러분이 재미있게 공부하고 계셨음 정말 좋겠네요^^22");
		guestbook.setRegdate(new Date());
		GuestBook result = guestbookService.addGuestbook(guestbook, "127.0.0.1");
		System.out.println(result);
	}

}
