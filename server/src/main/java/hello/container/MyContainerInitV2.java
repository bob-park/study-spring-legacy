package hello.container;

import java.util.Set;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

@HandlesTypes(AppInit.class) // AppInit 의 구현체를 모두 가져와 정보를 넘겨줌
public class MyContainerInitV2 implements ServletContainerInitializer {

    // c 에 구현체의 class 가 넘어옴
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("MyContainerInitV2.onStartup");
        System.out.println("c = " + c);
        System.out.println("ctx = " + ctx);

        for (Class<?> appInitClass : c) {
            try {
                // new AppInitV1Servlet() 과 같은 코드
                AppInit appInit = (AppInit) appInitClass.getDeclaredConstructor().newInstance();

                // 직접 onStartup(..) 을 호출
                appInit.onStartup(ctx);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }


    }
}
