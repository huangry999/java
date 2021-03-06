package aqs.demo.lock;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ReentrantLockTest {

    @Test
    public void testLock() throws Exception{
        ReentrantLock lock = new ReentrantLock();
        new Thread(()->{
            log.info("sub thread get lock");
            lock.lock();
            log.info("sub thread get lock again");
            lock.lock();
            try {
                int i = 0;
                while (i++<5){
                    log.info("sub thread sleep {} second", i);
                    Thread.sleep(1000);
                }
            }catch (InterruptedException e){
                log.error(e.getMessage());
            }finally {
                lock.unlock();
                log.info("sleep 2 second before release last lock");
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e) {
                    log.error(e.getMessage());
                }finally {
                    lock.unlock();
                }
            }
            log.info("sub thread unlock");
        }).start();
        Thread.sleep(1000);
        log.info("main thread try to get lock");
        lock.lock();
        log.info("main thread get lock");
        lock.unlock();
    }
}
