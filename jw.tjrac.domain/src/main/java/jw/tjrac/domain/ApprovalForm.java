package jw.tjrac.domain;

import java.util.Date;
import java.util.Set;

public class ApprovalForm {
    private Long approvalFormId;
    private String approvalFormTitle;
    private String applicant;
    private Date approvalTime;
    private String approvalState;
    private String approvalFormUrl;
    private String applyDays;

    public String getApplyDays() {
        return applyDays;
    }

    public void setApplyDays(String applyDays) {
        this.applyDays = applyDays;
    }

    public String getApprovalFormUrl() {
        return approvalFormUrl;
    }

    public void setApprovalFormUrl(String approvalFormUrl) {
        this.approvalFormUrl = approvalFormUrl;
    }

    private FormTemplate formTemplate;
    private Set<ApprovalInformation> approvalInformations;

    public FormTemplate getFormTemplate() {
        return formTemplate;
    }

    public void setFormTemplate(FormTemplate formTemplate) {
        this.formTemplate = formTemplate;
    }

    public Set<ApprovalInformation> getApprovalInformations() {
        return approvalInformations;
    }

    public void setApprovalInformations(Set<ApprovalInformation> approvalInformations) {
        this.approvalInformations = approvalInformations;
    }

    public Long getApprovalFormId() {
        return approvalFormId;
    }

    public void setApprovalFormId(Long approvalFormId) {
        this.approvalFormId = approvalFormId;
    }

    public String getApprovalFormTitle() {
        return approvalFormTitle;
    }

    public void setApprovalFormTitle(String approvalFormTitle) {
        this.approvalFormTitle = approvalFormTitle;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getApprovalState() {
        return approvalState;
    }

    public void setApprovalState(String approvalState) {
        this.approvalState = approvalState;
    }

}
