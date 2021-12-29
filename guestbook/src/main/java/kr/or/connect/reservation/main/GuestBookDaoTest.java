package kr.or.connect.reservation.main;

import java.util.*;

import org.springframework.context.*;
import org.springframework.context.annotation.*;

import kr.or.connect.reservation.config.*;
import kr.or.connect.reservation.dao.*;
import kr.or.connect.reservation.dto.*;

public class GuestBookDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		GuestBookDao guestbookDao = ac.getBean(GuestBookDao.class);
		
//		GuestBook guestbook = new GuestBook();
//		guestbook.setName("이규");
//		guestbook.setContent("반갑습니다. 여러분.");
//		guestbook.setRegdate(new Date());
//		Long id = guestbookDao.insert(guestbook);
//		System.out.println("id : " + id);
		
		LogDao logDao = ac.getBean(LogDao.class);
		Log log = new Log();
		log.setIp("127.0.0.1");
		log.setMethod("insert");
		log.setRegdate(new Date());
		logDao.insert(log);
	}

}
