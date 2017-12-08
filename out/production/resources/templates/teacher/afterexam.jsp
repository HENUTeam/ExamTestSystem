<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>考前操作</title>
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
            <a class="navbar-brand" href="#">教师管理</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li ><a href="teacher-main.jsp">首页</a></li>
                <li ><a href="beforeexam.jsp">考前操作</a></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle"
                                        data-toggle="dropdown"> 考中管理 <b class="caret"></b>
                </a>
                    <ul class="dropdown-menu">
                        <li><a href="exammanage.jsp">考试管理</a></li>
                        <li><a href="studentadmin.jsp">学生信息</a></li>
                        <li><a href="student-add.jsp">添加学生</a></li>
                        <li><a href="#">解除锁定</a></li>
                        <li><a href="information.jsp">通知管理</a></li>
                    </ul></li>
                <li class="active"><a href="afterexam.jsp">考后操作</a></li>
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
    style="margin-left: 200px; margin-top: 30px; color: #E5185E">上机考试管理</h2>
<div style="width:1300px;margin-left: 100px;">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>考试名称</th>
            <th>考试时间</th>
            <th>创建人</th>
            <th>上传试卷</th>
            <th>自动开始</th>
            <th>进行中</th>
            <th>已结束</th>
            <th>已归档</th>
            <th>已清理</th>
        </tr>
        </thead>
    </table>
</div>

</body>
</html>