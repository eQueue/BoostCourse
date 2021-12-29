package kr.or.connect.reservation.dto;

import java.util.*;

public class GuestBook {
	private Long id;
	private String name;
	private String content;
	private Date regdate;
	private int test;
	
	public int getTest() {
		return test;
	}
	public void setTest(int test) {
		this.test = test;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "Guestbook [id=" + id + ", name=" + name + ", content=" + content + ", regdate=" + regdate + "]";
	}
}
