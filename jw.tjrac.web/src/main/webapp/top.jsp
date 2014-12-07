<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>Top</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="css/blue/top.css" />
</head>

<body class="PageBody" style="margin: 0">

  <div id="Head1">
    <div id="Logo">
      <a id="msgLink" href="javascript:void(0)"></a> <font
        color="#0000CC"
        style="color: #F1F9FE; font-size: 28px; font-family: Arial Black, Arial">天津大学仁爱学院请假系统</font>
      <!--<img border="0" src="style/blue/images/logo.png" />-->
    </div>

    <div id="Head1Right">
      <div id="Head1Right_UserName">
        <img border="0" width="13" height="14"
          src="css/images/top/user.gif" /> 您好，<b>${loginuser} </b> <input
          type="hidden" id="hidden" value="${loginuser }" />
      </div>
      <div id="Head1Right_UserDept"></div>
      <div id="Head1Right_Time"></div>
    </div>

    <div id="Head1Right_SystemButton">
      <a target="_parent"
        href="${pageContext.request.contextPath}/login"> <img
        width="78" height="20" alt="退出系统"
        src="css/blue/images/top/logout.gif" />
      </a>
    </div>

  </div>

  <div id="Head2">
    <div id="Head2_Awoke">
      <ul id="AwokeNum">
        <li class="Line"></li>
        <!-- 是否有待审批文档的提示1，数量 -->
        <li><a
          href="${pageContext.request.contextPath}/workFlow/myTaskList"
          target="right"> <img border="0" width="12" height="14"
            src="css/images/top/wait.gif" /> 待办事项
        </a></li>

        <!-- 是否有待审批文档的提示2，提示审批 -->
        <li id="messageArea">您有 待审批文档，请及时审批！★★★★★</li>
      </ul>
    </div>

    <div id="Head2_FunctionList">
      <marquee style="WIDTH: 100%;" onMouseOver="this.stop()"
        onMouseOut="this.start()" scrollamount=1 scrolldelay=30
        direction=center>
        <b>天津大学仁爱学院请假系统</b>
      </marquee>
    </div>
  </div>

</body>
</html>
