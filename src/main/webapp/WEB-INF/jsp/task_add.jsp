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
    <title>发布任务</title>
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
                    <h3 class="box-title">新增任务</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                    <div class="box-body">

                        <form id= "form1" enctype ="multipart/form-data" action= "/task/addTask" method ="post" target= "hframe">
                            <div class="form-group">
                                <label>标题</label>
                                <input class="form-control"  name ="title"  placeholder="任务标题">

                            </div>
                            <div class="form-group">
                                <label>班级</label>
                                <input class="form-control"  name ="classId"  placeholder="发布班级">
                            </div>
                            <div class="form-group">
                                <label>任务内容</label><br/>
<%--
                                <input class="form-control" type="" name ="detail"  placeholder="请输入任务内容">
--%>
                                <textarea rows="20" cols="168" name="detail" placeholder="请输入任务内容"></textarea>
                            </div>
                            附件： <input type ="file" name="file" value="file"/><br/>
                            <input type ="submit" value="确定"/>
                            <iframe name ="hframe" id="hframe" style=" display: none" ></iframe >
                        </form>
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
<script>
    var isSuccess = ${isSuccess};
    if(isSuccess){
        alert("添加成功");
    }
</script>
</html>