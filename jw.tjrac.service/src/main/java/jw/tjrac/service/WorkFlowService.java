package jw.tjrac.service;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import jw.tjrac.domain.ApprovalForm;
import jw.tjrac.domain.ApprovalInformation;
import jw.tjrac.domain.TaskView;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface WorkFlowService {
    public void submit(HttpServletRequest request, Long formTemplateId, CommonsMultipartFile file);

    public String getFormTempleName(long formTemplateId);

    public Collection<TaskView> getAllFormByAssignee(HttpServletRequest request);

    public void approve(String taskId, ApprovalInformation approve);

    public ApprovalForm getFormById(Long approvalFormId);

    public String getformTemplateUrlByID(Long approvalFormId);

    public Collection<ApprovalForm> getApprovalFormByName(String applicant);
    
    public Collection<ApprovalInformation> getApprovalInformationByApprovalFormId(String approvalFormId);

}
