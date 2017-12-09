<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>考试管理</title>
    <link rel="stylesheet"
          href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script
            src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script
            src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function F_Open_dialog()
        {
            document.getElementById("btn_file").click();
        }
    </script>
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
                <li class="dropdown" class="active"><a href="onexam.jsp"
                                                       class="dropdown-toggle" data-toggle="dropdown"> 考中管理 <b
                        class="caret"></b>
                </a>
                    <ul class="dropdown-menu">
                        <li class="active"><a href="exammanage.jsp">考试管理</a></li>
                        <li><a href="studentadmin.jsp">学生信息</a></li>
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
        style="height: 170px; width: 1300px; background-color: #C0C0C0; margin-left: 100px; margin-top: 20px">
    <h3 style="margin-left: 20px; margin-top: 20px">编辑考试信息</h3>
    <form class="bs-example bs-example-form" role="form">
        <div class="input-group" style="margin-left: 200px">
            <span class="input-group-addon">考试名称：</span> <input type="text"
                                                                class="form-control" style="width: 150px"> <span
                class="input-group-addon">考试时间：</span> <input type="text"
                                                              class="form-control" style="width: 150px">
        </div>
        <div class="checkbox" style="margin-left:500px">
            <label> <input type="checkbox">自动开始
            </label>
        </div>
        <button type="submit" class="btn btn-default" style="margin-left:500px">提交</button>
    </form>
</div>
<div
        style="height: 100px; width: 1300px; background-color: #C0C0C0; margin-left: 100px; margin-top: 20px">
    <h3 style="margin-left: 20px; margin-top: 20px">上传试卷</h3>
    <div style="margin-left:200px;margin-top:5px">
        <input type="file" id="btn_file" style="display:none">
        <button type="button" onclick="F_Open_dialog()">浏览...</button>
        <span>未选择文件</span>
    </div>
    <button type="submit" class="btn btn-default" style="margin-left:500px;margin-top:-30px">上传</button>
</div>
<div
        style="height: 100px; width: 1300px; background-color: #C0C0C0; margin-left: 100px; margin-top: 20px">
    <h3 style="margin-left: 20px; margin-top: 20px">导入学生名单</h3>
    <span style="margin-left: 20px">目前设定参加考试的学生人数：</span><span style="color:red">0</span>
    <button type="submit" class="btn btn-default" style="margin-left:500px">继续导入</button>
</div>
<div
        style="height: 100px; width: 1300px; background-color: #C0C0C0; margin-left: 100px; margin-top: 20px">
    <h3 style="margin-left: 20px; margin-top: 20px">开启考试</h3>
    <span style="color:red;margin-left: 20px">尚未上传试卷！</span>
    <button type="submit" class="btn btn-default" style="margin-left:500px">开启</button>
</div>
</body>
</html>