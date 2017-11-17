<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理员主页面</title>
    <link rel="stylesheet"
          href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script
            src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script
            src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<h1 class="text-primary" style="text-align: center; color: #178DFF">
    <img src="image/logo.png" style="margin-right: 30px" />上机考试管理系统
</h1>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">管理员管理</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="manager-main.jsp">首页</a></li>
                <li><a href="teacher-add.jsp">教师管理</a></li>
                <!-- <li class="dropdown"><a href="#" class="dropdown-toggle"
                    data-toggle="dropdown"> 考中管理 <b class="caret"></b>
                </a>
                    <ul class="dropdown-menu">
                        <li><a href="exammanage.jsp">考试管理</a></li>
                        <li><a href="studentadmin.jsp">学生信息</a></li>
                        <li><a href="student-add.jsp">添加学生</a></li>
                        <li><a href="#">解除锁定</a></li>
                        <li><a href="information.jsp">通知管理</a></li>
                    </ul></li> -->
                <li><a href="delectexam.jsp">考试清理</a></li>
                <li><a href="systemconf.jsp">系统配置</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">修改口令</a></li>
                <li><a href="#">退出</a></li>
            </ul>
        </div>
    </div>
</nav>
<img src="image/teacher.png"
     style="width: 60px; height: 60px; margin-left: 100px; float: left" />
<h2 class="text-primary"
    style="margin-left: 200px; margin-top: 30px; color: #E5185E">系统管理</h2>
<div
        style="float: left; width: 300px; height: 300px; background-color: #C0C0C0; margin-top: 20px; margin-left: 100px">
    <h3>教师管理</h3>
    <ul style="margin-top: 40px">
        <li>可以对教师用户进行增删查改操作，并可以指定特定教师作为系统管理员</li>
        <li>系统可以有多个管理员</li>
        <li>如果没有任何一个教师具有管理员身份，则默认管理员登录信息有效</li>
    </ul>
</div>
<div
        style="float: left; width: 300px; height: 300px; background-color: #C0C0C0; margin-top: 20px; margin-left: 30px">
    <h3>考试清理</h3>
    <ul style="margin-top: 40px">
        <li>清楚考试的相关数据，包括数据库中的信息，文件系统中的提交文件</li>
        <li>只有在主考教师已经打包下载学生提交文件后才可以进行</li>
        <li>清理后的考试可以删除</li>
    </ul>
</div>
<div
        style="float: left; width: 300px; height: 300px; background-color: #C0C0C0; margin-top: 20px; margin-left: 30px">
    <h3>系统配置</h3>
    <ul style="margin-top: 40px">
        <li>设置一些全局性的系统选项，包括后台任务的时间周期、分页查询时的每页记录数、手动开启考试的事件阈值、学生上传文件字节数的有效范围等</li>
        <li>可以指定是否允许主考教师清理和删除考试</li>
    </ul>
</div>

</body>
</html>