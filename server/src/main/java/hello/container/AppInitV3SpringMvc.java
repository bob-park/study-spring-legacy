package hello.container;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import hello.spring.HelloConfiguration;

/**
 * 이미 Spring Web MVC 에서 지금까지의 부분을 이미 구현한 상태
 */
public class AppInitV3SpringMvc implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("AppInitV3SpringMvc.onStartup");

        //  Spring Container 만들기
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfiguration.class);

        // Spring MVC Dispatcher Servlet 생성, Spring Container 연결
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);

        // dispatcher servlet 을 servlet container 에 등록
        Dynamic servlet = servletContext.addServlet("dispatcherV3", dispatcherServlet);

        // 모든 요청이 dispatcher servlet 을 통하도록 설정
        servlet.addMapping("/");

    }
}
