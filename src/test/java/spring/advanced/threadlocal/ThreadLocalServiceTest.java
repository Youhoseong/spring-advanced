package spring.advanced.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.advanced.threadlocal.code.FieldService;
import spring.advanced.threadlocal.code.ThreadLocalService;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService service = new ThreadLocalService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> {
            service.login("userA");
        };

        Runnable userB = () -> {
            service.login("userB");
        };

        Thread threadA =  new Thread(userA);
        threadA.setName("thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
       // sleep(2000); // 동시성 문제 발생X
        sleep(100); // 동시성 문제 발생
        threadB.start();

        sleep(2000); // 메인 쓰레드 종료 대기
        log.info("main thread exit");
    }

    private void sleep(int millis)  {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
