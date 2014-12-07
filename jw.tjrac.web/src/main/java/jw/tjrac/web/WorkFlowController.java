package jw.tjrac.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jw.tjrac.domain.ApprovalForm;
import jw.tjrac.domain.ApprovalInformation;
import jw.tjrac.domain.FormTemplate;
import jw.tjrac.domain.TaskView;
import jw.tjrac.service.FormTemplateService;
import jw.tjrac.service.WorkFlowService;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller("WorkFlowControllerBean")
@RequestMapping("/workFlow")
public class WorkFlowController {
    @Resource(name = "formTemplateService")
    private FormTemplateService formTemplateService;

    @Resource(name = "workFlowService")
    private WorkFlowService workFlowService;

    @RequestMapping("/formTemplateList")
    public String getAllFormTemplate(Map<String, Object> model) {
        Collection<FormTemplate> ftList = this.formTemplateService.getAllFormTemplate();
        model.put("ftList", ftList);
        return "workFlow_formTemplateList";
    }

    @RequestMapping(value = "/submitUI/{formTemplateId}/{formTemplateName}", method = RequestMethod.GET)
    public String submitUI(@PathVariable long formTemplateId, @PathVariable String formTemplateName) {
        return "submitUI";
    }

    @RequestMapping(value = "/submitUI", method = RequestMethod.POST)
    public String submit(HttpServletRequest request, @RequestParam(value = "resource", required = false) CommonsMultipartFile file) {
        String st = request.getParameter("formTemplateId");
        long formTemplateId = Long.parseLong(st);
        System.out.println(formTemplateId);
        this.workFlowService.submit(request, formTemplateId, file);
        return "forward:/workFlow/mySubmittedList";

    }

    @RequestMapping("/downloadFormTemplate/{formTemplateId}")
    public ModelAndView download(HttpServletRequest request, HttpServletResponse response, @PathVariable long formTemplateId)
            throws Exception {
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null; // 从文件中读取内容
        BufferedOutputStream bos = null; // 向文件中写入内容
        // 获得服务器上存放下载资源的地址
        String ctxPath = request.getSession().getServletContext().getRealPath("/") + "\\upload\\";
        System.out.println(ctxPath);
        String realFileName = this.formTemplateService.getformTemplateUrlByID(formTemplateId);
        String a[] = realFileName.split("_");
        String fileName1 = a[1];
        String b[] = fileName1.split("\\.");
        String fileRealName = b[0];
        System.out.println(fileRealName);

        // 如果文件不存在,退出
        File file = new File(realFileName);
        if (!file.exists()) {
            return null;
        }
        try {
            response.setContentType("application/msword;charset=utf-8"); // 设置相应类型和编码
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(fileRealName.getBytes("utf-8"), "ISO8859-1"));

            bis = new BufferedInputStream(new FileInputStream(realFileName));
            bos = new BufferedOutputStream(response.getOutputStream());
            // 定义文件读取缓冲区
            byte[] buff = new byte[2048];
            int bytesRead;
            // 把下载资源写入到输出流
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bos.flush();
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        return null;
    }

    @RequestMapping("/myTaskList")
    public String myTaskList(Map<String, Object> model, HttpServletRequest request) {
        Collection<TaskView> taskViewList = this.workFlowService.getAllFormByAssignee(request);
        Hibernate.initialize(this.workFlowService.getAllFormByAssignee(request));
        model.put("taskViewList", taskViewList);
        return "myTaskList";
    }

    @RequestMapping(value = "/approveUI/{approvalFormId}/{taskId}", method = RequestMethod.GET)
    public String approveUI(@PathVariable long approvalFormId, @PathVariable long taskId) {
        return "approveUI";
    }

    @RequestMapping(value = "/approveUI", method = RequestMethod.POST)
    public String approve(HttpServletRequest request, @RequestParam(value = "comment") String comment) {
        String loginuser = (String) request.getSession().getAttribute("loginuser");
        String st = request.getParameter("approvalFormId");
        long approvalFormId = Long.parseLong(st);
        String taskId = request.getParameter("taskId");
        String isapprove = request.getParameter("isapprove");
        System.out.println(isapprove);
        ApprovalInformation approve = new ApprovalInformation();
        approve.setApprovalComment(comment);
        approve.setIsapprove(isapprove);
        ApprovalForm form = this.workFlowService.getFormById(approvalFormId);
        approve.setApprovename(loginuser);
        approve.setApprovetime(new Date());
        approve.setApprovalForm(form);
        this.workFlowService.approve(taskId, approve);
        return "myTaskList";
    }

    @RequestMapping("/downloadApprovalForm/{approvalFormId}")
    public ModelAndView download(@PathVariable long approvalFormId, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null; // 从文件中读取内容
        BufferedOutputStream bos = null; // 向文件中写入内容
        // 获得服务器上存放下载资源的地址
        String ctxPath = request.getSession().getServletContext().getRealPath("/") + "\\upload\\";
        System.out.println(ctxPath);
        String realFileName = this.workFlowService.getformTemplateUrlByID(approvalFormId);
        String a[] = realFileName.split("_");
        String fileName1 = a[1];
        String b[] = fileName1.split("\\.");
        String fileRealName = b[0];
        System.out.println(fileRealName);

        // 如果文件不存在,退出
        File file = new File(realFileName);
        if (!file.exists()) {
            return null;
        }
        try {
            response.setContentType("application/msword;charset=utf-8"); // 设置相应类型和编码
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(fileRealName.getBytes("utf-8"), "ISO8859-1"));

            bis = new BufferedInputStream(new FileInputStream(realFileName));
            bos = new BufferedOutputStream(response.getOutputStream());
            // 定义文件读取缓冲区
            byte[] buff = new byte[2048];
            int bytesRead;
            // 把下载资源写入到输出流
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bos.flush();
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        return null;
    }

    @RequestMapping("/mySubmittedList")
    public String mySubmittedListUI(HttpServletRequest request, Map<String, Object> model) {
        String loginuser = (String) request.getSession().getAttribute("loginuser");
        System.out.println(loginuser);
        model.put("list", this.workFlowService.getApprovalFormByName(loginuser));
        return "mySubmittedList";
    }
    
    @RequestMapping("/showForm/{approvalFormId}")
    public String showForm(@PathVariable long approvalFormId) {
        return "showForm";
    }
    
    @RequestMapping("/approvedHistory/{approvalFormId}")
    public String approvedHistory(Map<String, Object> model,@PathVariable long approvalFormId) {
    	String approvalFormId1 = String.valueOf(approvalFormId);
    	model.put("list", this.workFlowService.getApprovalInformationByApprovalFormId(approvalFormId1));
        return "approvedHistory";
    }
}
