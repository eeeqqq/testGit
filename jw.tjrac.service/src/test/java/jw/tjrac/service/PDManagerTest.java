package jw.tjrac.service;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jw.tjrac.domain.ApprovalForm;
import jw.tjrac.service.PDManager;

public class PDManagerTest {
    @Test
    public void testPDManager() {

        ApplicationContext context = new ClassPathXmlApplicationContext("tjrac-data.xml");
        PDManager pdManager = (PDManager) context.getBean("PDManagerBean");
        pdManager.getLasterVersions();
    }

}
