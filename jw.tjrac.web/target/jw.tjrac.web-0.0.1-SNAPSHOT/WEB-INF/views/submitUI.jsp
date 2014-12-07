<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>提交申请</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

  <!-- 标题显示 -->
  <div id="Title_bar">
    <div id="Title_bar_Head">
      <div id="Title_Head"></div>
      <div id="Title">
        <!--页面标题-->
        <img border="0" width="13" height="13"
          src="${pageContext.request.contextPath}/css/images/title_arrow.gif" />
        提交申请
      </div>
      <div id="Title_End"></div>
    </div>
  </div>

  <!--显示表单内容-->
  <div id=MainArea>
    <form action="${pageContext.request.contextPath }/workFlow/submitUI"
      enctype="multipart/form-data" method="post">
      <input type="hidden" name="formTemplateId" value="${formTemplateId }">
      <div class="ItemBlock_Title1">
        <!-- 信息说明 -->
        <div class="ItemBlock_Title1">
          <img border="0" width="4" height="7"
            src="${pageContext.request.contextPath}/css/blue/images/item_point.gif" />
          下载文档模板
        </div>
      </div>
      <div class="ItemBlockBorder">
        <div class="ItemBlock">
          <table cellpadding="0" cellspacing="0" class="mainForm">
            <tr>
              <td><a href="${pageContext.request.contextPath }/workFlow/downloadFormTemplate/${formTemplateId }"
                style="text-decoration: underline">[点击下载文档模板文件]</a></td>
            </tr>
          </table>
        </div>
      </div>

      <div class="ItemBlock_Title1">
        <!-- 信息说明 -->
        <div class="ItemBlock_Title1">
          <img border="0" width="4" height="7"
            src="${pageContext.request.contextPath}/css/blue/images/item_point.gif" />
          申请信息
        </div>
      </div>
      <div class="ItemBlockBorder">
        <div class="ItemBlock">
          <table cellpadding="0" cellspacing="0" class="mainForm">
            <tr>
                <td width="130">请输入申请天数</td>
                <td><input type="text" name="applyDays" style="width:70px"/></td>
            </tr>
            <tr>
              <td width="130">请选择填写好的文档</td>
              
              <td><input type="file" name="resource" class="InputStyle" /></td>
            </tr>
          </table>
        </div>
      </div>

      <!-- 表单操作 -->
      <div id="InputDetailBar">
        <input type="image"
          src="${pageContext.request.contextPath}/css/blue/images/button/submit.PNG" />
        <a href="javascript:history.go(-1);"><img
          src="${pageContext.request.contextPath}/css/images/goBack.png" /></a>
      </div>
      </form>
  </div>
</body>
</html>
