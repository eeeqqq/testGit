package jw.tjrac.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.opensymphony.xwork2.ActionContext;

import jw.tjrac.domain.FormTemplate;
import jw.tjrac.service.FormTemplateService;

//import jw.tjrac.utils.UploadUtils;

@Service("formTemplateService")
public class FormTemplateServiceImpl implements FormTemplateService {

    @Resource(name = "hibernateTemplate")
    public HibernateTemplate hibernateTemplate;

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public Collection<FormTemplate> getAllFormTemplate() {
        // TODO Auto-generated method stub
        return this.hibernateTemplate.find("from " + FormTemplate.class.getName());
    }

    @Transactional(readOnly = false)
    public void saveFormTemplate(FormTemplate formTemplate) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(formTemplate);
        tx.commit();
    }

    @Transactional(readOnly = false)
    public void deleteByID(long formTemplateId) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        FormTemplate formTemplate = (FormTemplate) session.get(FormTemplate.class, formTemplateId);
        session.delete(formTemplate);
        tx.commit();

    }

    @Override
    public String getformTemplateUrlByID(long formTemplateId) {
        // TODO Auto-generated method stub
        FormTemplate formTemplate = (FormTemplate) this.hibernateTemplate.get(FormTemplate.class, formTemplateId);
        return formTemplate.getFormTemplateUrl();
    }
}
