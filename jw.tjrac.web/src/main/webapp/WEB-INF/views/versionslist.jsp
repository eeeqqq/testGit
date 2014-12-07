<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<html>
<head>
<title>审批流程列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
    function showProcessImage(deploymentId) {
        var url = "${pageContext.request.contextPath}/pdmanager/showImage?deploymentId="
                + deploymentId;
        window.open(url, 300, 200);
    }
</script>
</head>
<body>

 <div id="Title_bar">
  <div id="Title_bar_Head">
   <div id="Title_Head"></div>
   <div id="Title">
    <!--页面标题-->
    <img border="0" width="13" height="13"
     src="${pageContext.request.contextPath}/css/images/title_arrow.gif" />
    审批流程管理
   </div>
   <div id="Title_End"></div>
  </div>
 </div>

 <div id="MainArea">
  <table cellspacing="0" cellpadding="0" class="TableStyle">

   <!-- 表头-->
   <thead>
    <tr align=center valign=middle id=TableTitle>
     <td width="200px">流程名称</td>
     <td width="80px">最新版本</td>
     <td>相关操作</td>
    </tr>
   </thead>

   <!--显示数据列表-->
   <tbody id="TableData" class="dataContainer" datakey="processDefList">
    <c:forEach items="${pdList}" var="pdversion">
     <tr class="TableDetail1 template">
      <td>${pdversion.name }</td>
      <td align="CENTER">${pdversion.version }</td>
      <td><a href="${pageContext.request.contextPath}/pdmanager/delete?key=${pdversion.key}">删除</a>
       <a href="javascript: showProcessImage('${pdversion.deploymentId}')">查看流程图</a>
      </td>
     </tr>
    </c:forEach>
   </tbody>
  </table>

  <!-- 其他功能超链接 -->
  <div id="TableTail">
   <div id="TableTail_inside">
    <table border="0" cellspacing="0" cellpadding="10" align="left">
     <tr>
      <td><div class="FuncBtn">
        <div class=FuncBtnHead></div>
        <div class=FuncBtnMemo>
         <a href="${pageContext.request.contextPath}/pdmanager/deploy">部署流程定义文档</a>
        </div>
        <div class=FuncBtnTail></div>
       </div></td>
     </tr>
    </table>
   </div>
  </div>
 </div>

</body>
</html>
