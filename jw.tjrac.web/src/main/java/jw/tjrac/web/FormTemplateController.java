package jw.tjrac.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jbpm.api.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jw.tjrac.domain.FormTemplate;
import jw.tjrac.service.FormTemplateService;
import jw.tjrac.service.PDManager;

@Controller("FormTemplateControllerBean")
@RequestMapping("/formtemplate")
public class FormTemplateController {

    @Autowired
    private FormTemplateService formTemplateService;

    @Autowired
    private PDManager pdManager;

    @Autowired
    private ServletContext servletContext;

    @Resource(name = "hibernateTemplate")
    public HibernateTemplate hibernateTemplate;

    @RequestMapping("/formTemplateList")
    public String getAllFormTemplate(Map<String, Object> model) {
        Collection<FormTemplate> ftList = this.formTemplateService.getAllFormTemplate();
        model.put("ftList", ftList);
        return "formTemplateList";
    }

    @RequestMapping(value = "/addtemplateUI", method = RequestMethod.GET)
    public String addTemplateUI(Map<String, Object> model) {
        Collection<ProcessDefinition> pdList = this.pdManager.getLasterVersions();
        List<String> list = this.pdManager.selectAllProcessDefinitions();
        model.put("list", list);
        model.put("pdList", pdList);
        return "formTemplate_addUI";
    }

    @RequestMapping(value = "/addtemplateUI", method = RequestMethod.POST)
    public String addTemplate(HttpServletRequest request, @RequestParam(value = "processKey") String processKey,
            @RequestParam(value = "formTemplateName") String formTemplateName,
            @RequestParam(value = "resource", required = false) CommonsMultipartFile file) {
        try {
            long time = System.currentTimeMillis();// 获取当前时间
            FormTemplate formTemplate = new FormTemplate();
            formTemplate.setFormTemplateName(formTemplateName);
            formTemplate.setProcessKey(processKey);
            String path = request.getSession().getServletContext().getRealPath("/") + "\\upload\\";
            System.out.println(path);
            String fileName = time + "_" + formTemplateName + ".doc";
            // String fileName = formTemplateName + ".doc";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String realPath = path + fileName;
            File dest = new File(realPath);
            file.transferTo(dest);
            System.out.println(file.getOriginalFilename());
            formTemplate.setFormTemplateUrl(realPath);
            this.formTemplateService.saveFormTemplate(formTemplate);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/formtemplate/formTemplateList";
    }

    @RequestMapping("/download/{formTemplateId}")
    public ModelAndView download(@PathVariable long formTemplateId, HttpServletRequest request, HttpServletResponse response)
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

    @RequestMapping("/delete/{formTemplateId}")
    public String deleteTemplateByID(@PathVariable long formTemplateId) {
        this.formTemplateService.deleteByID(formTemplateId);
        return "redirect:/formtemplate/formTemplateList";
    }

}
