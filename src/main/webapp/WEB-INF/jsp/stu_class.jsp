<%--
  Created by IntelliJ IDEA.
  User: wushijia
  Date: 2019/4/23
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>个人信息</title>
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
            <div class="panel panel-info">
                <!-- Default panel contents -->
                <div class="panel-heading">我的班级</div>
                <!-- Table -->
                <table class="table">
                    <thead>
                    <tr>
                        <th>班级编号</th>
                        <th>班级人数</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--                    <c:forEach items="${papers}" var="paper">--%>
                    <tr>
                        <%--
                                                    <td><a href="${paper.url}" target="view_window">${paper.paperName}</a></td>
                        --%>
                        <td>${stu.classId}</td>
                        <td>${stuCount}</td>
                        <td>
                            <a href="/student/getStudentList?studentId=${stu.studentId}" type="button" class="btn  btn-sm btn-primary"><i class="fa fa-edit"></i>查看详情</a>&nbsp;
                        </td>
                    </tr>
                    <%--                    </c:forEach>--%>

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