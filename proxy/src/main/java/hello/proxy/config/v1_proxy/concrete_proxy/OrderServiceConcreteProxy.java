package hello.proxy.config.v1_proxy.concrete_proxy;


import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2 {

    public OrderServiceConcreteProxy(OrderServiceV2 target, LogTrace logTrace) {
        super(null); // 어차피 super 객체 사용안하고 target을 통해 접근함
        this.target = target;
        this.logTrace = logTrace;
    }

    private final OrderServiceV2 target;
    private final LogTrace logTrace;


    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderService.request()");
            //target( 실제 객체 ) 호출
            target.orderItem(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }

    }
}
