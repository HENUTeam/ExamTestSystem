<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>系统首页</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" >
        $(document).ready(function(){
            $("ul li").click(function(){
                $(this).addClass("active").siblings().removeClass("active");
            });
        });
        $(document).ready(function() {
            var temp = "none";

            $("#li-one").click(function () {
                openMatter(1);
            });
            $("#li-two").click(function () {
                openMatter(2);
            });
            $("#li-three").click(function () {
                openMatter(3);
            });
            function openMatter(obj) {
                for (var i = 1; i < 4; i++) {
                    if (i == obj) {
                        temp = "block";
                    } else {
                        temp = "none";
                    }
                    document.getElementById("matter" + i).style.display = temp;

                }
            }
        });

    </script>
</head>
<body>
<h1 class="text-primary" style="text-align:center;color:#34BBEA" >上机考试管理系统</h1>
<ul id="aa" class="nav nav-pills">
    <li class="active" id="li-one" ><a href="#">学生登录</a></li>
    <li  id="li-two" ><a  id="tec1" >教师登录</a></li>
    <li id="li-three" ><a href="#">管理员登录</a></li>
</ul>
<!--  <%@ include file="stu-login.jsp" %> -->
<div class="content" >
    <div id="matter1" >
        <!--引入外部文件，即需要在下方加载的内容-->
        <%@ include file="stu-login.jsp"%>
    </div>
    <div id="matter2" style="display: none">
        <%@ include file="tec-login.jsp"%>
    </div>
    <div id="matter3" style="display: none">
        <%@ include file="mag-login.jsp"%>
    </div>
</div>
</body>
</html>