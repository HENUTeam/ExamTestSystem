<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>系统配置</title>
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
                <li class="active"><a href="systemconf.jsp">系统配置</a></li>
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
        style="height: 600px; width: 1300px; background-color: #C0C0C0; margin-left: 100px; margin-top: 20px">
    <form action="">
        <h3 style="margin-left: 20px; margin-top: 20px">修改系统配置</h3>
        <h4 style="margin-left: 160px; margin-top: 40px">后台任务间隔时间：</h4>
        <div style="width: 200px; float: left">
            <input type="text" class="form-control"
                   style="width: 200px; margin-left: 330px;margin-top:-35px" value="30">
        </div>
        <span style="margin-left: 200px">指定扫描考试信息的时间间隔，单位为 分钟</span>
        <h4 style="margin-left: 160px; margin-top: 40px">分页查询记录条数：</h4>
        <div style="width: 200px; float: left">
            <input type="text" class="form-control"
                   style="width: 200px; margin-left: 330px;margin-top:-35px" value="30">
        </div>
        <span style="margin-left: 200px">指定分页查询时每页显示记录数的默认值（查询页面中可以修改）。</span>
        <h4 style="margin-left: 125px; margin-top: 40px">手动开始考试时间阈值：</h4>
        <div style="width: 200px; float: left">
            <input type="text" class="form-control"
                   style="width: 200px; margin-left: 330px;margin-top:-35px" value="30">
        </div>
        <span style="margin-left: 200px">指定手工开启考试时允许的最大提前量，单位为 分钟</span>
        <h4 style="margin-left: 145px; margin-top: 40px">上传文件字节数下限：</h4>
        <div style="width: 200px; float: left">
            <input type="text" class="form-control"
                   style="width: 200px; margin-left: 330px;margin-top:-35px" value="30">
        </div>
        <span style="margin-left: 200px">指定上传文件的长度下限（字节），低于此值发出警告</span>
        <h4 style="margin-left: 145px; margin-top: 40px">上传文件字节数上限：</h4>
        <div style="width: 200px; float: left">
            <input type="text" class="form-control"
                   style="width: 200px; margin-left: 330px;margin-top:-35px" value="30">
        </div>
        <div style="margin-left:250px;margin-top:30px;float:left">
            <input type="checkbox"><span>教师可以清理和删除考试</span></div>
        <button type="button" class="btn btn-primary" style="margin-left:-90px;margin-top:100px">修改</button>

    </form>
</div>

</body>
</html>