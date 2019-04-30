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
            <div class="box-body table-responsive no-padding">
                <div class="box-header with-border">
                    <h3 class="box-title">任务详情</h3>
                </div>
                <h4 align="center">${task.title}</h4><br/>
                <textarea rows="3" cols="20">
                ${task.detail}
                </textarea>
                <form id= "form1" enctype ="multipart/form-data" action= "/task/uploadHomeWork" method ="post" target= "hframe">
                    <input type="hidden" name="taskId" value="${task.id}" />
                    上传作业： <input type ="file" name="file" value="file"/><br/>
                    <input type ="submit" value="提交作业"/>
                    <iframe name ="hframe" id="hframe" style=" display: none" ></iframe >
                </form>
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
        alert("上传成功");
    }

</script>
</html>