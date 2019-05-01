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
    <title>班级信息</title>
    <jsp:include page="../include/header.jsp"/>
</head>
<script>
    var success = ${success};
    if(success){
        alert("修改成功");
    }

</script>
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
                    <h3 class="box-title">班级成员</h3>
                </div>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>班级</th>
                        <th>操作</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${accountList}" var="account">
                        <tr>
                            <td>${account.classId}</td>

                            <td>
                                <a href="/class/classDetail?classId=${account.classId}" type="button" class="btn  btn-sm btn-primary"><i class="fa fa-edit"></i>班级详情</a>&nbsp;
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