/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ZookeeperApp implements CommandLineRunner {

    @Value("${spring.cloud.zookeeper.connect-string}")
    private String connectString;

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperApp.class);
    }

    @Override
    public void run(String... args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory
                .builder()
                .connectString(connectString)
                .retryPolicy(retryPolicy)
                .namespace("testzookeeper")
                .build();
        client.start();
        DistributedLock lock1 = new DistributedLock(client, "/lock");
        DistributedLock lock2 = new DistributedLock(client, "/lock");
        DistributedLock lock3 = new DistributedLock(client, "/lock");
        new Thread(() -> {
            try {
                log.info("lock 1 try get lock");
                lock1.lock();
                log.info("lock 1 get lock");
                Thread.sleep(2000);
                log.info("lock 1.2 try get lock");
                lock1.lock();
                log.info("lock 1.2 get lock");
                Thread.sleep(2000);
                lock1.unlock();
                log.info("lock 1 release lock");
                Thread.sleep(1000);
                lock1.unlock();
                log.info("lock 1.2 release lock");
            } catch (Exception e) {
                //ignore
            }
        }).start();
        Thread.sleep(500);
        new Thread(() -> {
            try {
                log.info("lock 2 try get lock");
                lock2.lock();
                log.info("lock 2 get lock");
                Thread.sleep(1000);
                log.info("lock 2.2 try get lock");
                lock2.lock();
                log.info("lock 2.2 get lock");
                Thread.sleep(1000);
                lock2.unlock();
                log.info("lock 2 release lock");
                lock2.unlock();
                log.info("lock 2.2 release lock");
            } catch (Exception e) {
                //ignore
            }
        }).start();
        Thread.sleep(500);
        new Thread(() -> {
            try {
                log.info("lock 3 try get lock");
                lock3.lock();
                log.info("lock 3 get lock");
                Thread.sleep(5000);
                lock3.unlock();
                log.info("lock 3 release lock");
            } catch (Exception e) {
                //ignore
            }
        }).start();
    }
}
