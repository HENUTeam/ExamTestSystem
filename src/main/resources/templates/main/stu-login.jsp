<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>学生登录页面</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<form  class="form-horizontal"  role="form">
    <div class="form-group" >
        <label for="firstname" class="col-sm-offset-2 col-sm-2 control-label">学号：</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" id="firstname"
                   placeholder="请输入学号">
        </div>
    </div>
    <div class="form-group" >
        <label for="lastname" class="col-sm-offset-2 col-sm-2 control-label">姓名：</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" id="lastname"
                   placeholder="请输入姓名">
        </div>
    </div>
    <div class="form-group" >
        <div class=" col-sm-offset-5 col-sm-10">
            <button type="submit" class="btn btn-default">登录</button>
        </div>
    </div>
</form>
</body>
</html>