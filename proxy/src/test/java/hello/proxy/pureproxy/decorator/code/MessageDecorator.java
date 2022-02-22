package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component{
    private Component component;

    public MessageDecorator(Component component) {
        this.component = component;
    }


    @Override
    public String operation() {
        log.info("MessageDecorator 실행");
        // 데코레이터는 부가기능을 제공하기 위한 프록시 패턴 중 하나
        String result = component.operation();
        String decoResult = "*****" + result + "*****";

        log.info("MessageDecorator 꾸미기 적용 전 = {}, 적용 후 = {},", result, decoResult);
        return decoResult;
    }
}
