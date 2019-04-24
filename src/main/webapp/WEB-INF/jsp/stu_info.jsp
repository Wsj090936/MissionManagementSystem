<%--
  Created by IntelliJ IDEA.
  User: wushijia
  Date: 2019/4/23
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <!-- Content Header (Page header) -->
<%--        <section class="content-header">
            <h1>
                控制面板
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>--%>
        <!-- Main content -->
        <section class="content">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">个人教育信息</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                    <div class="box-body">
                        <div class="form-group">
                            <label>姓名</label>
                            <input class="form-control" readonly="readonly" name ="name" value="${stu.name}" placeholder="">

                        </div>
                        <div class="form-group">
                            <label>学号</label>
                            <input class="form-control" readonly="readonly" name ="studentId" value="${stu.studentId}" placeholder="">
                        </div>
                        <div class="form-group">
                            <label>年龄</label>
                            <input class="form-control" readonly="readonly" name ="age" value="${stu.age}" placeholder="">
                        </div>
                        <div class="form-group">
                            <label>所属班级</label>
                            <input class="form-control" readonly="readonly" name ="classId" value="${stu.classId}" placeholder="">
                        </div>
                        <div class="form-group">
                            <label>年级</label>
                            <input class="form-control" readonly="readonly" name ="grade" value="${stu.grade}" placeholder="">
                        </div>
                        <div class="form-group">
                            <label>电话</label>
                            <input class="form-control" readonly="readonly" name ="phoneNumber" value="${stu.phoneNumber}" placeholder="">
                        </div>
                        <div class="form-group">
                            <label>身份证号</label>
                            <input class="form-control" readonly="readonly" name ="idCard" value="${stu.idCard}" placeholder="">
                        </div>
                    </div>
                    <!-- /.box-body -->

<%--                    <div class="box-footer">
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>--%>
            </div>
        </section>
    </div>
    <jsp:include page="../include/copyright.jsp"/>
</div>

<jsp:include page="../include/footer.jsp"/>

</body>

</html>