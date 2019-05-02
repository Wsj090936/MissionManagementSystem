<%--
  Created by IntelliJ IDEA.
  User: wushijia
  Date: 2019/4/28
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>任务详情</title>
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
                <div class="panel-heading">任务详情</div>
                <form id= "form1" enctype ="multipart/form-data" action= "/task/editTask" method ="post" target= "hframe">
                    <input type="hidden" name="id" value="${task.id}"/>
                    <div class="form-group">
                        <label>标题</label>
                        <input class="form-control" value="${task.title}"  name ="title"  placeholder="任务标题">

                    </div>
                    <div class="form-group">
                        <label>班级</label>
                        <input class="form-control" readonly="readonly" value="${task.classId}"  name ="classId"  placeholder="发布班级">
                    </div>
                    <div class="form-group">
                        <label>任务内容</label><br/>
                        <textarea rows="20" cols="168"  name="detail" placeholder="请输入任务内容">${task.detail}</textarea>
                    </div>
                    附件： <input type ="file" name="file" value="file"/><br/>
                    <button type="submit" onclick="tiaozhuan()"  class="btn btn-primary">提交修改</button>
                    <iframe name ="hframe" id="hframe" style=" display: none" ></iframe >
                </form>
            </div>

        </section>
    </div>
    <jsp:include page="../include/copyright.jsp"/>
</div>

<jsp:include page="../include/footer.jsp"/>

</body>
<script type="text/javascript">

    function tiaozhuan()
    {
        alert("操作成功");
        window.location.href="http://localhost:8003/task/getTaskList?userId=${sessionScope.teacherId}";
    }
</script>
</html>