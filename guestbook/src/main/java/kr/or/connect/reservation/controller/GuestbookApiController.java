package kr.or.connect.reservation.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.service.*;

@RestController
@RequestMapping(path="/guestbooks")
public class GuestbookApiController {
	@Autowired
	GuestbookService guestbookService;
	
	@GetMapping
	public Map<String, Object> list(@RequestParam(name="start", required=false, defaultValue="0") int start) {
		//client한테 응답이 갈때 json 변환
		List<GuestBook> list = guestbookService.getGuestbooks(start);
		
		int count = guestbookService.getCount();
		int pageCount = count / GuestbookService.LIMIT;
		if(count % GuestbookService.LIMIT > 0)
			pageCount++;
		
		List<Integer> pageStartList = new ArrayList<>();
		for(int i = 0; i < pageCount; i++) {
			pageStartList.add(i * GuestbookService.LIMIT);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("count", count);
		map.put("pageStartList", pageStartList);
		
		return map;
	}
	
	@PostMapping
	public GuestBook write(@RequestBody GuestBook guestbook,
						HttpServletRequest request) {
		String clientIp = request.getRemoteAddr();
		// id가 입력된 guestbook이 반환된다.
		GuestBook resultGuestbook = guestbookService.addGuestbook(guestbook, clientIp);
		return resultGuestbook;
	}
	
	@DeleteMapping("/{id}")// /guestbooks/id 매칭. id
	public Map<String, String> delete(@PathVariable(name="id") Long id,
			HttpServletRequest request) {
		String clientIp = request.getRemoteAddr();
		
		int deleteCount = guestbookService.deleteGuestbook(id, clientIp);
		return Collections.singletonMap("success", deleteCount > 0 ? "true" : "false");
	}
}
