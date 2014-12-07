package jw.tjrac.domain;

import org.jbpm.api.task.Task;

public class TaskView {
    private ApprovalForm approvalForm;

    public ApprovalForm getApprovalForm() {
        return approvalForm;
    }

    public void setApprovalForm(ApprovalForm approvalForm) {
        this.approvalForm = approvalForm;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    private Task task;
}
