package kr.or.connect.guestbook.argumentresolver;

import java.util.*;

import org.springframework.core.*;
import org.springframework.web.bind.support.*;
import org.springframework.web.context.request.*;
import org.springframework.web.method.support.*;

public class HeaderMapArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType() == HeaderInfo.class;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		HeaderInfo headerInfo = new HeaderInfo();
		
		Iterator<String> headerNames = webRequest.getHeaderNames();
		while(headerNames.hasNext()) {
			String headerName = headerNames.next();
			String headerValue = webRequest.getHeader(headerName);
			System.out.println(headerName + " , " + headerValue);
			headerInfo.put(headerName, headerValue);
		}
		
		return headerInfo;

	}

}
