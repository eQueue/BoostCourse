package kr.or.connect.reservation.service;

import java.util.*;

import kr.or.connect.reservation.dto.*;

public interface GuestbookService {
	public static final Integer LIMIT = 5;
	public List<GuestBook> getGuestbooks(Integer start);
	public int deleteGuestbook(Long id, String ip);
	public GuestBook addGuestbook(GuestBook guestbook, String ip);
	public int getCount();
}
