<%--
  Created by IntelliJ IDEA.
  User: wushijia
  Date: 2019/4/22
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- Sidebar user panel (optional) -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="/adminLte/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>Alexander Pierce</p><%--这里可以加上用户的姓名--%>
                <!-- Status -->
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>

        <!-- search form (Optional) -->
<%--        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
              <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
              </button>
            </span>
            </div>
        </form>--%>
        <!-- /.search form -->

        <!-- Sidebar Menu -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">功能列表</li>
            <!-- Optionally, you can add icons to the links -->
            <li class="active treeview">
                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>学生服务</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/information/studentInformation?studentId=${sessionScope.studentId}"><i class="fa fa-circle-o"></i>个人信息</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i>我的班级</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i>任务列表</a></li>

                </ul>
            </li>

            <li class="active treeview">
                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>教师服务</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/research/paper"><i class="fa fa-circle-o"></i>任务列表</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i>发布任务</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i>班级管理</a></li>
                </ul>
            </li>
        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>
