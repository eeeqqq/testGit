package jw.tjrac.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jw.tjrac.service.PDManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller("PDManagerControllerBean")
@RequestMapping("/pdmanager")
public class PDManagerController {

    @Autowired
    private PDManager pdManager;

    @RequestMapping("/versionslist")
    public String getLasterVersions(Map<String, Object> model) {
        model.put("pdList", this.pdManager.getLasterVersions());
        return "versionslist";
    }

    @RequestMapping(value = "/deploy", method = RequestMethod.GET)
    public String deployUI() {
        return "deployUI";
    }

    @RequestMapping(value = "/deploy", method = RequestMethod.POST)
    public String deployFolwChart(@RequestParam(value = "resource", required = false) CommonsMultipartFile file) throws Exception {

        try {
            // 判断文件是否存在
            String path = "D:/" + file.getOriginalFilename();
            File localFile = new File(path);
            this.pdManager.deploy(localFile);
            file.transferTo(localFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/pdmanager/versionslist";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam String key) {
        this.pdManager.deletePDKEY(key);
        return "redirect:/pdmanager/versionslist";
    }

    @RequestMapping("/showImage")
    public String showImage(@RequestParam String deploymentId, HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = this.pdManager.showImage(deploymentId);
        byte[] b = new byte[256];
        int len = -1;
        try {
            while ((len = inputStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }

            inputStream.close();
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
