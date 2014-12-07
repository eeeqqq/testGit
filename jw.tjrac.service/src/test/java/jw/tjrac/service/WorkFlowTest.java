package jw.tjrac.service;

import java.util.List;

import jw.tjrac.domain.ApprovalForm;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WorkFlowTest {

    @Test
    public void TestselectAllProcessDefinitions() {
        ApplicationContext context = new ClassPathXmlApplicationContext("tjrac-data.xml");
        WorkFlowService workFlowService = (WorkFlowService) context.getBean("workFlowService");
        List<ApprovalForm> list = (List<ApprovalForm>) workFlowService.getApprovalFormByName("马靖和");
        System.out.println(list.size());
    }

}
