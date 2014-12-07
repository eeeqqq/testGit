package jw.tjrac.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("LoginControllerBean")
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginUI(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String loginForm(HttpServletRequest request) {
        // 处理用户登录信息
        String loginflag = request.getParameter("loginflag");
        if (loginflag != null && loginflag.equals("ok")) {
            String username = request.getParameter("username");
            String userpwd = request.getParameter("password");
            if (username.equals("cailaoshi") && userpwd.equals("cailaoshi") || username.equals("zhangzhuren")
                    && userpwd.equals("zhangzhuren") || username.equals("wuyuanzhang") && userpwd.equals("wuyuanzhang")) {
                request.getSession().setAttribute("loginuser", username);
                return "index";
            } else if (username.equals("majinghe") && userpwd.equals("majinghe")) {
                request.getSession().setAttribute(("loginuser"), username);
                return "indexForStudent";
            } else {
                return "redirect:/login";
            }

        }
        return "redirect:/login";

    }
}
