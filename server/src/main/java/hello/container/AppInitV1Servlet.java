package hello.container;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration.Dynamic;

import hello.servlet.HelloServlet;

public class AppInitV1Servlet implements AppInit {

    @Override
    public void onStartup(ServletContext servletContext) {
        System.out.println("AppInitV1Servlet.onStartup");

        // 순수 서블릿 코드 등록
        Dynamic helloServlet = servletContext.addServlet("helloServlet", new HelloServlet());

        helloServlet.addMapping("/hello-servlet");
    }
}
