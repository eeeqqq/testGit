<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>我的申请查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet"
  href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
<script type="text/javascript">
    
</script>
</head>
<body>

  <div id="Title_bar">
    <div id="Title_bar_Head">
      <div id="Title_Head"></div>
      <div id="Title">
        <!--页面标题-->
        <img border="0" width="13" height="13"
          src="${pageContext.request.contextPath}/style/images/title_arrow.gif" /> 我的申请查询
      </div>
      <div id="Title_End"></div>
    </div>
  </div>



  <div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
      <!-- 表头-->
      <thead>
        <tr align="CENTER" valign="MIDDLE" id="TableTitle">
          <td width="250px">标题</td>
          <td width="115px">申请人</td>
          <td width="115px">申请日期</td>
          <td width="115px">申请天数</td>
          <td width="115px">当前状态</td>
          <td >相关操作</td>
        </tr>
      </thead>


      <!--显示数据列表：正在审批或审批完成的表单显示示例-->
      <tbody id="TableData" class="dataContainer" datakey="formList">
        <!-- 正在审批或审批完成的表单显示示例 -->
        <c:forEach items="${list }" var="approvalForm">
        <tr class="TableDetail1 template">
          <td><a href="${pageContext.request.contextPath}/workFlow/showForm/${approvalForm.approvalFormId}" >${approvalForm.approvalFormTitle}</a></td>
          <td>${approvalForm.applicant}&nbsp;</td>
          <td>${approvalForm.approvalTime}&nbsp;</td>
          <td>${approvalForm.applyDays }&nbsp;</td>
          <td>${approvalForm.approvalState}&nbsp;</td>
          <td><a href="${pageContext.request.contextPath}/workFlow/showForm/${approvalForm.approvalFormId}">查看申请信息</a>
          	<a href="${pageContext.request.contextPath }/workFlow/approvedHistory/${approvalForm.approvalFormId}">查看流转记录</a>
          </td>
        </tr>
        </c:forEach>
      </tbody>
    </table>

    <!-- 其他功能超链接 -->
    <div id="TableTail">
      <div id="TableTail_inside"></div>
    </div>
  </div>



</body>
</html>
