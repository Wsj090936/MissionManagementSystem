<%--
  Created by IntelliJ IDEA.
  User: wushijia
  Date: 2019/4/25
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>作业提交情况</title>
    <jsp:include page="../include/header.jsp"/>
</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../include/nav.jsp"/>
    <jsp:include page="../include/leftMenue.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">
            <div class="box-body table-responsive no-padding">
                <div class="box-header with-border">
                    <h3 class="box-title">学生列表</h3>
                </div>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>班级编号</th>
                        <th>姓名</th>
                        <th>学号</th>
                        <th>是否提交</th>
                        <th>附件</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${homeWorkAccountDtos}" var="account">
                        <tr>
                            <td>${account.classId}</td>
                            <td>${account.name}</td>
                            <td>${account.studentId}</td>
                            <c:if test="${account.isUpload==1}"><td>已提交</td></c:if>
                            <c:if test="${account.isUpload==2}"><td>未提交</td></c:if>
                            <td>
                                <a href="/task/downloadHomeWork?studentId=${account.studentId}&taskId=${account.taskId}" type="button" class="btn  btn-sm btn-primary"><i class="fa fa-edit"></i>下载附件</a>&nbsp;
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </section>
    </div>
    <jsp:include page="../include/copyright.jsp"/>
</div>

<jsp:include page="../include/footer.jsp"/>

</body>

</html>
