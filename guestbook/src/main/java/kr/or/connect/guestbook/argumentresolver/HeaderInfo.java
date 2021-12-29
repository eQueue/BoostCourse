package kr.or.connect.guestbook.argumentresolver;
import java.util.*;

public class HeaderInfo {
	private Map<String, String> map; //map 객체를 넘기기위해 따로 class 를 만들어야한다(따로 객체를 만들어야함)	
	public HeaderInfo() {
		map = new HashMap<>();
	}

	public void put(String name, String value) {
		map.put(name,  value);
	}
	
	public String get(String name) {
		return map.get(name);
	}

}
