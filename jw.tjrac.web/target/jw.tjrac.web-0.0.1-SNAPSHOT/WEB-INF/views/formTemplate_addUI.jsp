<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>文档模板信息</title>
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
    文档模板信息
   </div>
   <div id="Title_End"></div>
  </div>
 </div>

 <!--显示表单内容-->
 <div id=MainArea>
  <form action="${pageContext.request.contextPath }/formtemplate/addtemplateUI"
   enctype="multipart/form-data" method="post">
   <div class="ItemBlock_Title1">
    <!-- 信息说明 -->
    <div class="ItemBlock_Title1">
     <img border="0" width="4" height="7"
      src="${pageContext.request.contextPath}/css/blue/images/item_point.gif" />
     模板基本信息
    </div>
   </div>

   <!-- 表单内容显示 -->
   <div class="ItemBlockBorder">
    <div class="ItemBlock">
     <table cellpadding="0" cellspacing="0" class="mainForm">
      <tr>
       <td>模板名称</td>
       <td><input type="text" name="formTemplateName" class="InputStyle" /></td>
      </tr>
      <tr>
       <td>所用流程</td>

       <td><select name="processKey">
        <c:forEach var="name" items="${list}">
            <option value="${name}"><c:out value="${name}"/></option>
        </c:forEach>
       </select>
      </td>
      </tr>
     </table>
    </div>
   </div>

   <div class="ItemBlock_Title1">
    <!-- 信息说明 -->
    <div class="ItemBlock_Title1">
     <img border="0" width="4" height="7"
      src="${pageContext.request.contextPath}/css/blue/images/item_point.gif" />
     模板文件
    </div>
   </div>

   <!-- 表单内容显示 -->
   <div class="ItemBlockBorder">
    <div class="ItemBlock">
     <table cellpadding="0" cellspacing="0" class="mainForm">
      <tr>
       <td width="120px;">请选择文件(doc格式)</td>
       <td><input type="file" name="resource" class="InputStyle" /></td>
      </tr>
     </table>
    </div>
   </div>

   <!-- 表单操作 -->
   <div id="InputDetailBar">
    <input type="image"
     src="${pageContext.request.contextPath}/css/images/save.png" /> <a
     href="javascript:history.go(-1);"><img
     src="${pageContext.request.contextPath}/css/images/goBack.png" /></a>
   </div>
  </form>
 </div>
</body>
</html>
