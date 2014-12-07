<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<HTML>
<HEAD>
<META http-equiv=Content-Type CONTENT="text/html; charset=UTF-8" />
<TITLE>登录</TITLE>
<LINK HREF="${pageContext.request.contextPath}/css/blue/login.css" type=text/css rel=stylesheet />
</HEAD>

<BODY LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0
  CLASS=PageBody>
  <form action="${pageContext.request.contextPath}/main" method="post">
    <input type="hidden" name="loginflag" value="ok">
    <DIV ID="CenterAreaBg">
      <DIV ID="CenterArea">
        <DIV ID="LogoImg">
          <IMG BORDER="0" SRC="${pageContext.request.contextPath}/css/blue/images/logo.png" />
        </DIV>
        <DIV ID="LoginInfo">
          <TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 width=100%>
            <TR>
              <TD width=45 CLASS="Subject"><IMG BORDER="0"
                SRC="${pageContext.request.contextPath}/css/blue/images/login/userId.gif" /></TD>

              <TD><input name="username" type="text"
                class="TextField"></TD>
              <TD ROWSPAN="2" STYLE="padding-left: 10px;"><INPUT
                TYPE="image"
                SRC="${pageContext.request.contextPath}/css/blue/images/login/userLogin_button.gif" /></TD>
            </TR>
            <TR>
              <TD CLASS="Subject"><IMG BORDER="0"
                SRC="${pageContext.request.contextPath}/css/blue/images/login/password.gif" /></TD>
              <TD><input name="password" type="password"
                class="TextField"></TD>
            </TR>
          </TABLE>
        </DIV>
        <DIV ID="CopyRight">
          <A HREF="javascript:void(0)">&copy; 2014 版权所有 马靖和</A>
        </DIV>
      </DIV>
    </DIV>
  </form>
</BODY>

</HTML>
