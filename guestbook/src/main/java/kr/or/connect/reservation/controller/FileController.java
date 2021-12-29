package kr.or.connect.reservation.controller;

import java.io.*;

import javax.servlet.http.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

@Controller
public class FileController {
	
	@GetMapping("/uploadform")
	public String uploadform() {
		return "uploadform";
	}
	
	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		
		System.out.println("파일 이름 : " + file.getOriginalFilename());
		System.out.println("파일 크기 : " + file.getSize());
		
        try(
                // 맥일 경우 
                //FileOutputStream fos = new FileOutputStream("/tmp/" + file.getOriginalFilename());
                // 윈도우일 경우
                FileOutputStream fos = new FileOutputStream("D:/tmp/" + file.getOriginalFilename());
                InputStream is = file.getInputStream();
        ){
        	    int readCount = 0;
        	    byte[] buffer = new byte[1024];
            while((readCount = is.read(buffer)) != -1){
                fos.write(buffer,0,readCount);
            }
        }catch(Exception ex){
            throw new RuntimeException("file Save Error");
        }
		
		
		return "uploadok";
	}
	
	@GetMapping("/download")
	public void download(HttpServletResponse response) {
		
		

        // 직접 파일 정보를 변수에 저장해 놨지만, 이 부분이 db에서 읽어왔다고 가정한다.
		String fileName = "sample.png";
		String saveFileName = "D:/tmp/sample.png"; // 맥일 경우 "/tmp/connect.png" 로 수정
		String contentType = "image/png";
		File file=new File(saveFileName);
		long fileLength = 0;
		if(file.exists()) {
			fileLength=file.length();
		}
		else {
			System.out.println("file not exist");
		}
		
		
		
		
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Length", "" + fileLength);
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        
        try(
                FileInputStream fis = new FileInputStream(saveFileName);
                OutputStream out = response.getOutputStream();
        		
        ){
        	    int readCount = 0;
        	    byte[] buffer = new byte[1024];
            while((readCount = fis.read(buffer)) != -1){
            		out.write(buffer,0,readCount);
            }
        }catch(Exception ex){
            throw new RuntimeException("file Save Error");
        }
	}
}
