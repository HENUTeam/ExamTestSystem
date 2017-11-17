<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>学生信息</title>
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
                <li><a href="teacher-main.jsp">首页</a></li>
                <li><a href="beforeexam.jsp">考前操作</a></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle"
                                        data-toggle="dropdown"> 考中管理 <b class="caret"></b>
                </a>
                    <ul class="dropdown-menu">
                        <li><a href="exammanage.jsp">考试管理</a></li>
                        <li class="active"><a href="studentadmin.jsp">学生信息</a></li>
                        <li><a href="student-add.jsp">添加学生</a></li>
                        <li><a href="#">解除锁定</a></li>
                        <li><a href="information.jsp">通知管理</a></li>
                    </ul></li>
                <li><a href="afterexam.jsp">考后操作</a></li>
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
<div
        style="height: 130px; width: 1300px; background-color: #C0C0C0; margin-left: 100px; margin-top: 20px">
    <h3 style="margin-left: 20px; margin-top: 20px">按学生查找已登录信息</h3>
    <div style="margin-top: 30px">
        <input type="text" class="form-control" placeholder="学号"
               style="margin-left: 20px; width: 200px"> <input type="text"
                                                               class="form-control" placeholder="姓名"
                                                               style="margin-left: 280px; margin-top: -35px; width: 200px">
        <input type="text" class="form-control" placeholder="班级"
               style="margin-left: 540px; margin-top: -35px; width: 200px">
    </div>
    <!-- /input-group -->
    <button type="button" class="btn btn-primary" style="margin-left:800px;margin-top:-35px">查找</button>
</div>

<div
        style="height: 130px; width: 1300px; background-color: #C0C0C0; margin-left: 100px; margin-top: 20px">
    <h3 style="margin-left: 20px; margin-top: 20px">按ip地址查找已登录信息</h3>
    <div style="margin-top: 30px">
        <input type="text" class="form-control" placeholder="ip地址"
               style="margin-left: 20px; width: 200px">
    </div>
    <!-- /input-group -->
    <button type="button" class="btn btn-primary" style="margin-left:300px;margin-top:-35px">查找</button>
</div>

<h3 style="margin-left: 120px; margin-top: 20px">查找结果</h3>
<div style="width:1300px;margin-left: 100px;">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>班级</th>
            <th>IP地址</th>
        </tr>
        </thead>
    </table>
</div>

</body>
</html>