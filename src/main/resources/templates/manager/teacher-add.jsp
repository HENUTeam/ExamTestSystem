<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="henu.utils.DbUtil,java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加教师用户</title>
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
                <li><a href="manager-main.jsp">首页</a></li>
                <li  class="active"><a href="teacher-add.jsp">教师管理</a></li>
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
<h2 class="text-primary" style="margin-left: 200px; color: #E5185E">添加教师用户</h2>
<form id="teacher_Form" name="user" method="post" action="tec_add.jsp">
    <div class="input-group" style="margin-left:300px;margin-top:60px">
        <div style="width:200px;float:left">
            <span class="input-group-addon">用户名</span>
            <input type="text" class="form-control" style="width:200px" ></div>
        <div style="width:200px;float:left"><span class="input-group-addon">密码</span>
            <input type="password" class="form-control" style="width:200px" ></div>
        <div style="width:200px;float:left"><span class="input-group-addon">真实姓名</span>
            <input type="text" class="form-control" style="width:200px" ></div>
        <div style="width:200px;float:left"><span class="input-group-addon">是否添加管理员</span>
            <input type="checkbox" class="form-control" style="width:200px" ></div>
        <input type="submit" class="btn btn-default"
               style="margin-left: 20px;margin-top:15px; width: 100px; line-height: 23px"
               value="添加" />
    </div>
    <!--  <table style="border: 5; border-color: #9E46AB; margin-left: 300px">
        <tr>
            <td>用户名：<input type="text" name="tec_username" value="" /></td>
            <td>密码：<input type="password" name="tec_password" value="" /></td>
            <td>真实姓名：<input type="text" name="trc_realname" value="" /></td>
            <td>添加管理员：<input type="checkbox" value="" /></td>
            <td><input type="submit" class="btn btn-default"
                style="margin-left: 20px; width: 100px; line-height: 23px"
                value="添加" /></td>
        </tr>
    </table> -->
</form>
<div style="width:950px;margin-left: 300px;">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>用户名</th>
            <th>全名</th>
            <th>是否管理员</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>