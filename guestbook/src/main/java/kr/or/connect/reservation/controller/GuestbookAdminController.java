package kr.or.connect.reservation.controller;

import javax.servlet.http.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

@Controller
public class GuestbookAdminController {

       @GetMapping(path="/loginform")
		public String loginform() {
			return "loginform";
		}
		
        @PostMapping(path="/login")
		public String login(@RequestParam(name="passwd", required=true) String passwd, 
				HttpSession session,
				RedirectAttributes redirectAttr) {
			
			if("1234".equals(passwd)) {
				session.setAttribute("isAdmin", "true");
			}else {
				redirectAttr.addFlashAttribute("errorMessage","암호가 틀렸습니다."); //redirectAttr 리다이렉트시 단 한번만 값을 유지.
				return "redirect:/loginform"; //요청이 달라지므로 redirect 사용
			}
			return "redirect:/list";
		}
		
       @GetMapping(path="/logout")
		public String login(HttpSession session) {
			session.removeAttribute("isAdmin");
			return "redirect:/list";
		}

}
