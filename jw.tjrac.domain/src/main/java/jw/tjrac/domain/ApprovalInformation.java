package jw.tjrac.domain;

import java.util.Date;

public class ApprovalInformation {
    private Long approvalInformationId;
    private String approvename;
    private Date approvetime;
    private String isapprove;
    private String approvalComment;
    private ApprovalForm approvalForm;

    public ApprovalForm getApprovalForm() {
        return approvalForm;
    }

    public void setApprovalForm(ApprovalForm approvalForm) {
        this.approvalForm = approvalForm;
    }

    public String getApprovename() {
        return approvename;
    }

    public void setApprovename(String approvename) {
        this.approvename = approvename;
    }

    public Date getApprovetime() {
        return approvetime;
    }

    public void setApprovetime(Date approvetime) {
        this.approvetime = approvetime;
    }

    public String getIsapprove() {
        return isapprove;
    }

    public void setIsapprove(String isapprove) {
        this.isapprove = isapprove;
    }

    public Long getApprovalInformationId() {
        return approvalInformationId;
    }

    public void setApprovalInformationId(Long approvalInformationId) {
        this.approvalInformationId = approvalInformationId;
    }

    public String getApprovalComment() {
        return approvalComment;
    }

    public void setApprovalComment(String approvalComment) {
        this.approvalComment = approvalComment;
    }

}
