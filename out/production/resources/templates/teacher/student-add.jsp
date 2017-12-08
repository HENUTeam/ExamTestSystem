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
    <script type="text/javascript">
        function F_Open_dialog() {
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
                        <li><a href="exammanage.jsp">考试管理</a></li>
                        <li><a href="studentadmin.jsp">学生信息</a></li>
                        <li class="active"><a href="student-add.jsp">添加学生</a></li>
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
        style="height: 160px; width: 1300px; background-color: #C0C0C0; margin-left: 100px; margin-top: 20px">
    <h3 style="margin-left: 20px; margin-top: 20px">添加单个学生</h3>
    <div style="margin-top: 30px">
        <input type="text" class="form-control" placeholder="学号"
               style="margin-left: 20px; width: 200px"> <input type="text"
                                                               class="form-control" placeholder="姓名"
                                                               style="margin-left: 280px; margin-top: -35px; width: 200px">
        <input type="text" class="form-control" placeholder="班级"
               style="margin-left: 540px; margin-top: -35px; width: 200px">
    </div>
    <!-- /input-group -->
    <button type="submit" class="btn btn-default"
            style="margin-left: 760px; margin-top: -35px">添加</button>
</div>
<div class="input-group"
     style="margin-left: 100px; width: 400px; margin-top: 20px">
    <span class="input-group-addon">分页大小</span> <input type="text"
                                                       class="form-control" placeholder="30" style="width: 200px">
    <button type="submit" class="btn btn-default"
            style="margin-left: 250px; margin-top: -35px">设置</button>
</div>
<div style="width: 1300px; margin-left: 100px; margin-top: 20px">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>班级</th>
        </tr>
        </thead>
    </table>
</div>
<div
        style="height: 100px; width: 1300px; background-color: #C0C0C0; margin-left: 100px; margin-top: 20px">
    <h3 style="margin-left: 20px; margin-top: 20px">批量导入学生名单</h3>
    <div style="margin-left: 200px; margin-top: 5px">
        <input type="file" id="btn_file" style="display: none">
        <button type="button" onclick="F_Open_dialog()">浏览...</button>
        <span>未选择文件</span>
    </div>
    <button type="submit" class="btn btn-default"
            style="margin-left: 500px; margin-top: -30px">导入</button>
</div>
</div>
</body>
</html>