package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

@Slf4j
public class ArgsTest {

    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        //public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);

    }

    private AspectJExpressionPointcut pointcut(String expression) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }

    /*
     * execution은 메소드의 시그니쳐(정적)로 판단함, 정확하게 매칭해야함
     * args는 런타임의 객체 인스턴스를 보고 확인함(동적), 부모 타입으로 검사를 하고 인스턴스는 그것의 상속 구현체라면 가능
     */
    @Test
    void args() {
        // 파라미터 매칭 여부만 검사
        Assertions.assertThat(pointcut("args(String)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
}
