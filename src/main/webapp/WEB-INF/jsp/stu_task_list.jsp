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
    <title>任务列表</title>
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
                <div class="panel-heading">任务列表</div>
                <!-- Table -->
                <table class="table">
                    <thead>
                    <tr>
                        <th>班级编号</th>
                        <th>创建时间</th>
                        <th>标题</th>
                        <th>发布人</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${taskDtoList}" var="task">
                        <tr>
                            <td>${task.classId}</td>
                            <td>${task.created}</td>
                            <td>${task.title}</td>
                            <td>${task.teacherName}</td>
                            <td>
                                <a href="/student/taskDetail?id=${task.id}" type="button" class="btn  btn-sm btn-primary"><i class="fa fa-edit"></i>查看详情</a>&nbsp;
                            </td>
                            <td>
                                <a href="/student/downloadTaskFile?id=${task.id}" type="button" class="btn  btn-sm btn-primary"><i class="fa fa-edit"></i>下载附件</a>&nbsp;
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
<script>
    var haveFile = ${haveFile};
    if(!haveFile){
        alert("当前任务没有附件");
    }

</script>
</html>
