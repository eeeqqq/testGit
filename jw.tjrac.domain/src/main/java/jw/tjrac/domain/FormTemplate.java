package jw.tjrac.domain;

import java.util.Set;

public class FormTemplate {
    private Long formTemplateId;
    private String formTemplateName;
    private String processKey;
    private String formTemplateUrl;

    private Set<ApprovalForm> approveForms;

    public Long getFormTemplateId() {
        return formTemplateId;
    }

    public void setFormTemplateId(Long formTemplateId) {
        this.formTemplateId = formTemplateId;
    }

    public String getFormTemplateName() {
        return formTemplateName;
    }

    public void setFormTemplateName(String formTemplateName) {
        this.formTemplateName = formTemplateName;
    }

    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String processKey) {
        this.processKey = processKey;
    }

    public String getFormTemplateUrl() {
        return formTemplateUrl;
    }

    public void setFormTemplateUrl(String formTemplateUrl) {
        this.formTemplateUrl = formTemplateUrl;
    }

    public Set<ApprovalForm> getApproveForms() {
        return approveForms;
    }

    public void setApproveForms(Set<ApprovalForm> approveForms) {
        this.approveForms = approveForms;
    }

}
