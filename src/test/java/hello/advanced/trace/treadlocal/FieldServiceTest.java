package hello.advanced.trace.treadlocal;

import hello.advanced.trace.treadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private final FieldService service = new FieldService();

    @Test
    void field() throws InterruptedException {
        log.info("main start");
        Runnable userA = () -> {
            service.logic("userA");
        };
        Runnable userB = () -> {
            service.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        // sleep(2000); // 동시성 문제X
        sleep(100); // 동시성 문제 발생
        threadB.start();

        sleep(3000);
        log.info("main exit");
    }

    private void sleep(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}