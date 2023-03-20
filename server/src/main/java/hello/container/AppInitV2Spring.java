package hello.container;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration.Dynamic;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import hello.spring.HelloConfiguration;

public class AppInitV2Spring implements AppInit {

    @Override
    public void onStartup(ServletContext servletContext) {
        System.out.println("AppInitV2Spring.onStartup");

        //  Spring Container 만들기
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfiguration.class);

        // Spring MVC Dispatcher Servlet 생성, Spring Container 연결
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);

        // dispatcher servlet 을 servlet container 에 등록
        Dynamic servlet = servletContext.addServlet("dispatcherV2", dispatcherServlet);

        // /spring/* 요청이 dispatcher servlet 을 통하도록 설정
        servlet.addMapping("/spring/*");
    }
}
