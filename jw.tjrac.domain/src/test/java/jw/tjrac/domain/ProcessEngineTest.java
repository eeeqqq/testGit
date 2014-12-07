package jw.tjrac.domain;

import org.jbpm.api.ProcessEngine;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProcessEngineTest {
    @Test
    public void testProcessEngine() {
        ApplicationContext context = new ClassPathXmlApplicationContext("tjrac-data.xml");
        ProcessEngine processEngine = (ProcessEngine) context.getBean("processEngine");
        System.out.println(processEngine);
    }
}
