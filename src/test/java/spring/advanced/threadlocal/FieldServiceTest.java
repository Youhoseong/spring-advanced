package spring.advanced.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.advanced.threadlocal.code.FieldService;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> {
            fieldService.login("userA");
        };

        Runnable userB = () -> {
            fieldService.login("userB");
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