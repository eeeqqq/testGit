package jw.tjrac.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import jw.tjrac.domain.ApprovalInformation;
import jw.tjrac.domain.FormTemplate;
import jw.tjrac.domain.ApprovalForm;
import jw.tjrac.domain.TaskView;
import jw.tjrac.service.WorkFlowService;

@Service("workFlowService")
public class WorkFlowServiceImpl implements WorkFlowService {

	@Resource(name = "processEngine")
	private ProcessEngine processEngine;

	@Resource(name = "hibernateTemplate")
	public HibernateTemplate hibernateTemplate;

	@Resource(name = "sessionFactory")
	public SessionFactory sessionFactory;

	public void submit(HttpServletRequest request, Long formTemplateId,
			CommonsMultipartFile file) {
		/**
		 * * 上传表单 往form表中插入一行数据 applicatetime 当前时间 applicator 就是登入系统的人 state 审批中
		 * url 上传表单以后可以生成该值 ftid 表单模板ID(在页面上应该用隐藏域) jbpm 启动流程实例
		 * 根据pdkey启动流程实例，因为页面上传递到后台的参数只有pdkey 把form作为流程变量，保存到流程实例中 完成请假申请的任务
		 * completeTask(String taskId);
		 */

		/**
		 * 上传
		 */

		try {
			String applyDays = request.getParameter("applyDays");
			String loginuser = (String) request.getSession().getAttribute(
					"loginuser");
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			FormTemplate formTemplate = (FormTemplate) session.get(
					FormTemplate.class, formTemplateId);
			long time = System.currentTimeMillis();// 获取当前时间
			String fileName = time + "_" + formTemplate.getFormTemplateName()
					+ ".doc";
			String path = request.getSession().getServletContext()
					.getRealPath("/")
					+ "\\upload\\";
			System.out.println(path);
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String realPath = path + fileName;
			File dest = new File(realPath);
			file.transferTo(dest);
			System.out.println(file.getOriginalFilename());
			/**
			 * 保存form
			 */
			// 从session中提取user
			// 往form表中插入一行数据
			java.sql.Date sqlDate = new java.sql.Date(
					new java.util.Date().getTime());
			ApprovalForm form = new ApprovalForm();
			form.setApprovalTime(sqlDate);
			form.setApplicant(loginuser);
			form.setApprovalState("申请中");
			form.setApplyDays(applyDays);

			// 把formTempalte提取出来
			// 建立form与formTemplate之间的关系
			form.setFormTemplate(formTemplate);

			String approvalFormTitle = formTemplate.getFormTemplateName() + "_"
					+ loginuser + "_" + sqlDate;
			form.setApprovalFormTitle(approvalFormTitle);
			form.setApprovalFormUrl(realPath);
			session.save(form);
			tx.commit();
			/********************************************************************************************/
			/**
			 * jbpm的事情
			 */
			// 启动流程实例,设置流程变量
			Map<String, ApprovalForm> variables = new HashMap<String, ApprovalForm>();
			variables.put("form", form);
			ProcessInstance pi = this.processEngine.getExecutionService()
					.startProcessInstanceByKey(formTemplate.getProcessKey(),
							variables);
			/**
			 * 根据当前正在执行的实例获取正在执行的任务
			 */
			Task task = this.processEngine.getTaskService().createTaskQuery()
					.executionId(pi.getId()).uniqueResult();
			// 完成请假申请的任务
			this.processEngine.getTaskService().completeTask(task.getId());
			/********************************************************************************************/
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getFormTempleName(long formTemplateId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		FormTemplate formTemplate = (FormTemplate) session.get(
				FormTemplate.class, formTemplateId);
		tx.commit();
		return formTemplate.getFormTemplateName();

	}

	public Collection<TaskView> getAllFormByAssignee(HttpServletRequest request) {
		// TODO Auto-generated method stub
		/**
		 * 1、根据登录人查询当前执行的所有的任务 2、遍历所有的任务，得到executionId
		 * 3、通过executionId和"form"的值把流程变量form提取出来 4、form和task共同组成taskView
		 */
		String loginuser = (String) request.getSession().getAttribute(
				"loginuser");
		List<Task> taskList = this.processEngine.getTaskService()
				.createTaskQuery().assignee(loginuser).list();
		List<TaskView> taskViewList = new ArrayList<TaskView>();
		for (Task task : taskList) {
			TaskView taskView = new TaskView();
			ApprovalForm form = (ApprovalForm) this.processEngine
					.getExecutionService().getVariable(task.getExecutionId(),
							"form");
			taskView.setApprovalForm(form);
			taskView.setTask(task);
			taskViewList.add(taskView);
		}
		return taskViewList;
	}

	public void approve(String taskId, ApprovalInformation approve) {
		// TODO Auto-generated method stub
		/**
		 * 1、插入一行数据到approve中 2、如果页面点击的是不同意 则流程实例直接结束 把相应的form表中的状态变成"未通过"
		 * 如果页面点击的是同意 * 完成任务 * 判断该流程实例是否结束 如果结束，把相应的form表的状态变成"已完成"
		 */
		Task task = this.processEngine.getTaskService().getTask(taskId);
		if ("不同意".equals(approve.getIsapprove())) {
			this.processEngine.getExecutionService().endProcessInstance(
					task.getExecutionId(), "end1");
			this.updateApprovalForm("未通过", approve.getApprovalForm()
					.getApprovalFormId());
		} else {
			// 完成任务
			this.processEngine.getTaskService().completeTask(taskId);
			// 得到流程实例
			ProcessInstance pi = this.processEngine.getExecutionService()
					.createProcessInstanceQuery()
					.processInstanceId(task.getExecutionId()).uniqueResult();
			if (pi == null) {// 该流程实例已经结束了
				this.updateApprovalForm("通过", approve.getApprovalForm()
						.getApprovalFormId());
			}
		}
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(approve);
		tx.commit();
	}

	public ApprovalForm getFormById(Long approvalFormId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		ApprovalForm approvalForm = (ApprovalForm) session.get(
				ApprovalForm.class, approvalFormId);
		tx.commit();
		return approvalForm;
	}

	@Override
	public String getformTemplateUrlByID(Long approvalFormId) {
		// TODO Auto-generated method stub
		ApprovalForm approvalForm = (ApprovalForm) this.hibernateTemplate.get(
				ApprovalForm.class, approvalFormId);
		return approvalForm.getApprovalFormUrl();
	}

	@Override
	public Collection<ApprovalForm> getApprovalFormByName(String applicant) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		String sql = " from ApprovalForm  where applicant=?";
		Query query = session.createQuery(sql);
		query.setParameter(0, applicant, Hibernate.STRING);
		List<ApprovalForm> list = query.list();
		return list;
	}

	private void updateApprovalForm(String isapprove, long approvalFormId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session
				.createQuery("update ApprovalForm  set approvalState = '"
						+ isapprove + "' where approvalFormId = "
						+ approvalFormId + "");
		query.executeUpdate();
		tx.commit();
	}

	@Override
	public Collection<ApprovalInformation> getApprovalInformationByApprovalFormId(
			String approvalFormId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		String sql = " from ApprovalInformation  where approvalFormId=?";
		Query query = session.createQuery(sql);
		query.setParameter(0, approvalFormId, Hibernate.STRING);
		List<ApprovalInformation> list = query.list();
		return list;
	}
}
