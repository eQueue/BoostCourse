package kr.or.connect.reservation.config;

import java.util.*;

import org.springframework.context.annotation.*;
import org.springframework.web.method.support.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.*;

import kr.or.connect.guestbook.argumentresolver.*;
import kr.or.connect.reservation.interceptor.*;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"kr.or.connect.reservation.controller"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/img_map/**").addResourceLocations("/img_map/").setCachePeriod(31556926);
        registry.addResourceHandler("/font/**").addResourceLocations("/font/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }
 
    // default servlet handler를 사용하게 합니다.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
   
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
    		System.out.println("addViewControllers가 호출됩니다. ");
        registry.addViewController("/").setViewName("index"); //특정 url에 대한 처리. 컨트롤러 클래스 작성하지 않고 매핑할수 있도록 해줌
        //처음화면 addView
        //main만 가지고 view를 찾을수 없음. 밑의 bean필요
    }
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/"); //어떤 view 인지 찾아내게해줌
        resolver.setSuffix(".jsp");//확장자 선택
        return resolver;
    }
    
    @Bean
    public MultipartResolver multipartResolver() {
        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10 최대 10MB 크기 업로드 가능
        return multipartResolver;
    }
    
    @Override//인터셉터 등록
	public void addInterceptors(InterceptorRegistry registry) {
    		registry.addInterceptor(new LogInterceptor());
//    		모든 request가 아닌 특정한 req에만 실행할 interceptor는 
//    		registry.addInterceptor(new GuestBookInterceptor()).addPathPatterns("/auth/**");
    		
	}
    
    @Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    		System.out.println("아규먼트 리졸버 등록..");
		argumentResolvers.add(new HeaderMapArgumentResolver());
	}

}
