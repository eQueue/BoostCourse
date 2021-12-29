package kr.or.connect.reservation.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import kr.or.connect.reservation.dao.*;
import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.service.*;

@Service
public class GuestbookServiceImpl implements GuestbookService{
	@Autowired//Bean 자동생성 주입.
	GuestBookDao guestbookDao;
	
	@Autowired
	LogDao logDao;

	@Override
	@Transactional //읽기만하는 메소드이므로 readonly를 사용
	public List<GuestBook> getGuestbooks(Integer start) {//시작점부터 몇개?
		List<GuestBook> list = guestbookDao.selectAll(start, GuestbookService.LIMIT); //한페이지 몇개씩 보여줄지 limit로 지정.
		return list;
	}

	@Override
	@Transactional(readOnly=false) //readonly 가아니므로 
	public int deleteGuestbook(Long id, String ip) {
		int deleteCount = guestbookDao.deleteById(id);
		Log log = new Log(); //delete 할때 로그 남김
		log.setIp(ip);
		log.setMethod("delete");
		log.setRegdate(new Date());
		logDao.insert(log);
		return deleteCount;
	}
	
	@Override
	@Transactional(readOnly=false)
	public GuestBook addGuestbook(GuestBook guestbook, String ip) {
		guestbook.setRegdate(new Date());
		Long id = guestbookDao.insert(guestbook); //전체가 이루어지지 않으면 취소됨 transactional 어노테이션 때문
		guestbook.setId(id);
		
//		if(1 == 1)
//			throw new RuntimeException("test exception");
//			
		Log log = new Log();
		log.setIp(ip);
		log.setMethod("insert");
		log.setRegdate(new Date());
		logDao.insert(log);
		
		
		return guestbook;
	}

	@Override
	public int getCount() {
		return guestbookDao.selectCount(); //전체 면건인지 얻어오는 것
	}
	
	
}
