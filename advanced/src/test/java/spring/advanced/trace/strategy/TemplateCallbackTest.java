package spring.advanced.trace.strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.advanced.trace.strategy.code.template.Callback;
import spring.advanced.trace.strategy.code.template.TimeLogTemplate;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class TemplateCallbackTest {

    /*
     * 템플릿 콜백 패턴 - 익명 내부 클래스
     */
    @Test
    void callbackV1() {
        TimeLogTemplate template = new TimeLogTemplate();

        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1 실행");
            }
        });

        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 로직 2 실행");
            }
        });
    }

    @Test
    void callbackV2() {
        TimeLogTemplate template = new TimeLogTemplate();

        template.execute(() -> log.info("비즈니스 로직 1 실행"));
        template.execute(() -> log.info("비즈니스 로직 2 실행"));
    }

    @AllArgsConstructor
    @Getter
    class Obj{
        int value;
        String temp;
    }

    @Test
    void compareTest() {
        List<Obj> list = new ArrayList<>();
        for(int i=0; i<10; i++) {
            Obj obj = new Obj(ThreadLocalRandom.current().nextInt(10), "Context");
            list.add(obj);
        }

        list.forEach((item)->System.out.println(item.getValue()));
        list.sort(Comparator.comparing(Obj::getValue));
        System.out.println();
        list.forEach((item)->System.out.println(item.getValue()));
    }


}
