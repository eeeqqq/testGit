<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<html>
<head>
<title>审批处理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
    $().ready(function() {
        $("#agree").unbind("click");
        $("#agree").bind("click", function() {
            $("#isapprove").val("同意");
        });

        $("#disagree").unbind("click");
        $("#disagree").bind("click", function() {
            $("#isapprove").val("不同意");
        });
    });
</script>
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
        审批处理
      </div>
      <div id="Title_End"></div>
    </div>
  </div>

  <!--显示表单内容-->
  <div id=MainArea>
    <form action="${pageContext.request.contextPath}/workFlow/approveUI"
      method="post">
      <input type="hidden" name="approvalFormId"
        value="${approvalFormId }"> <input type="hidden"
        name="taskId" value="${taskId }"> <input type="hidden"
        name="isapprove" id="isapprove">
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
              <td><a href="${pageContext.request.contextPath }/workFlow/downloadApprovalForm/${approvalFormId }"
                style="text-decoration: underline">[点击下载申请的文档]</a></td>
            </tr>
          </table>
        </div>
      </div>

      <div class="ItemBlock_Title1">
        <!-- 信息说明 -->
        <div class="ItemBlock_Title1">
          <img border="0" width="4" height="7"
            src="${pageContext.request.contextPath}/css/blue/images/item_point.gif" />
          审批信息
        </div>
      </div>
      <div class="ItemBlockBorder">
        <div class="ItemBlock">
          <table cellpadding="0" cellspacing="0" class="mainForm">
            <tr>
              <td>审批意见</td>
              <td><textarea rows="3" cols="20" name="comment" class="TextareaStyle"></textarea>
              </td>
            </tr>
          </table>
        </div>
      </div>

      <!-- 表单操作 -->
      <div id="InputDetailBar" style="float: none">
        <!--onclick事件在submit之前触发-->
        <input type="image" id="agree"
          src="${pageContext.request.contextPath}/css/blue/images/button/agree.png" />
        <input type="image" id="disagree"
          src="${pageContext.request.contextPath}/css/blue/images/button/disagree.png" />
        <!-- <img src="${pageContext.request.contextPath}/css/blue/images/button/sendBack.png" onClick="selectToNode();" /> -->
        <a href="javascript:history.go(-1);"><img
          src="${pageContext.request.contextPath}/css/images/goBack.png" /></a>
      </div>

    </form>
  </div>
</body>
</html>
