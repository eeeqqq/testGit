package jw.tjrac.service;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FormTemplateTest {

    @Test
    public void testFormTemplate() {
        ApplicationContext context = new ClassPathXmlApplicationContext("tjrac-data.xml");
        FormTemplateService formTemplateService = (FormTemplateService) context.getBean("formTemplateService");
        String st = "12";
        Long id = Long.parseLong(st);
        String url = formTemplateService.getformTemplateUrlByID(id);
        String a[] = url.split("_");
        String fileName1 = a[1];
        String b[] = fileName1.split("\\.");
        String realFileName = b[0];
        System.out.println(realFileName);
    }
}
