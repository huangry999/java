/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package configclient.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
@Slf4j
@EnableConfigurationProperties({Config.class})
public class AppClient implements CommandLineRunner {

    @Autowired
    private Config config;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AppClient.class);
        ConfigurableApplicationContext context = app.run();
        Map<String, Object> map = context.getEnvironment().getSystemProperties();
    }


    @Override
    public void run(String... args) throws Exception {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("Config value: {}", config);
            }
        }, 0, 5000);
    }
}
