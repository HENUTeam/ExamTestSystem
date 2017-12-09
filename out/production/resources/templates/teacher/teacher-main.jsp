<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>教师管理主页面</title>
    <link rel="stylesheet"
          href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"/>
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
                <li class="active"><a href="teachermain.jsp">首页</a></li>
                <li><a href="beforeexam.jsp">考前操作</a></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle"
                                        data-toggle="dropdown"> 考中管理 <b class="carcet"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="exammanage.jsp">考试管理</a></li>
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
        style="float: left; width: 300px; height: 300px; background-color: #C0C0C0; margin-top: 20px; margin-left: 100px">
    <h3>考前操作</h3>
    <ul style="margin-top: 40px">
        <li>可以新建考试：指定考试名称、开始时间等</li>
        <li>可以编辑自己创建的、未开始的考试，除了修改考试信息，还可以：</li>
        <li>上传试卷</li>
        <li>学生名单导入</li>
        <li>开启考试</li>
    </ul>
</div>
<div
        style="float: left; width: 300px; height: 300px; background-color: #C0C0C0; margin-top: 20px; margin-left: 30px">
    <h3>考中管理</h3>
    <ul style="margin-top: 40px">
        <li>可以查看考试情况</li>
        <li>可以管理学生信息，手动添加个别学生信息</li>
        <li>可以解除学生登录锁定</li>
        <li>可以添加或删除通知消息</li>
    </ul>
</div>
<div
        style="float: left; width: 300px; height: 300px; background-color: #C0C0C0; margin-top: 20px; margin-left: 30px">
    <h3>考后操作</h3>
    <ul style="margin-top: 40px">
        <li>主考教师可以结束考试</li>
        <li>主考教师可以打包下载学生提交文件</li>
        <li>主考教师可以导出提交信息</li>
        <li>如果管理员设置，主考教师可以清除和删除考试</li>
    </ul>
</div>
</body>

</html>