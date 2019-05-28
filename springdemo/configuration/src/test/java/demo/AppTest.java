/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package demo;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(properties = "tps: tpsV")
@RunWith(SpringRunner.class)
public class AppTest {

    @Value("${machine.name:null}")
    private String machineName;

    @Value("${tps:null}")
    private String testPropertySource;

    @Autowired
    private AppMeta appMeta;

    @Value("${app.meta.subVersion:null}")
    private String relaxedBinding;

    @Value("${spring.application.name:null}")
    private String name;

    @Test
    public void testDevToolsConfig(){
        assertEquals("huangrongyao-pc", machineName);
    }

    @Test
    public void testTestPropertySource(){
        assertEquals("tpsV", testPropertySource);
    }

    @Test
    public void testAppMeta(){
        assertEquals(1, appMeta.getVersion());
    }

    @Test
    public void testRelaxedBinding(){
        assertEquals(1, appMeta.getSubVersion());
        assertEquals("null", relaxedBinding);
    }

    @Test
    public void testNullObject(){
        assertNull(appMeta.getTestList());
    }

    @Test
    public void testReadFromCustomizedFolder(){
        assertEquals("production application", name);
    }
}
