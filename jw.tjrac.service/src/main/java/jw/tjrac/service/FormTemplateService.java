package jw.tjrac.service;

import java.util.Collection;

import jw.tjrac.domain.FormTemplate;

public interface FormTemplateService {
    public Collection<FormTemplate> getAllFormTemplate();

    public void saveFormTemplate(FormTemplate formTemplate);

    public void deleteByID(long formTemplateId);

    public String getformTemplateUrlByID(long formTemplateID);

}
