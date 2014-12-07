<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>表单模板列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

  <div id="Title_bar">
    <div id="Title_bar_Head">
      <div id="Title_Head"></div>
      <div id="Title">
        <!--页面标题-->
        <img border="0" width="13" height="13"
          src="${pageContext.request.contextPath}/css/images/title_arrow.gif" />
        模板管理
      </div>
      <div id="Title_End"></div>
    </div>
  </div>

  <div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">

      <!-- 表头-->
      <thead>
        <tr align=center valign=middle id=TableTitle>
          <td width="250px">模板名称</td>
          <td width="250px">所用流程</td>
          <td>相关操作</td>
        </tr>
      </thead>

      <!--显示数据列表-->
      <tbody id="TableData" class="dataContainer"
        datakey="documentTemplateList">
        <c:forEach items="${ftList }" var="template">
          <tr class="TableDetail1 template">
            <td>${template.formTemplateName }</td>
            <td>${template.processKey }</td>
            <td><a onClick="return delConfirm()"
              href="${pageContext.request.contextPath }/formtemplate/delete/${template.formTemplateId}">删除</a>
              <a
              href="${pageContext.request.contextPath}/formtemplate/download/${template.formTemplateId}">下载</a></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <!-- 其他功能超链接 -->
    <div id="TableTail">
      <div id="TableTail_inside">
        <a
          href="${pageContext.request.contextPath}/formtemplate/addtemplateUI"><img
          src="${pageContext.request.contextPath}/css/blue/images/button/addNew.PNG" /></a>
      </div>
    </div>
  </div>
</body>
</html>
