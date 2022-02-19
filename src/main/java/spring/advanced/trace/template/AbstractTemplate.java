package spring.advanced.trace.template;

import lombok.RequiredArgsConstructor;
import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            //로직
            T result = call();

            trace.end(status);
            return result;

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();

}
