<%--
  Created by IntelliJ IDEA.
  User: vorbote
  Date: 2021/5/19
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
    <style>
        body{
            margin: 0;
            padding: 0;
            background: #487eb0;
        }
        .sign-div{
            width: 400px;
            padding: 20px;
            position:absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%,-100%);
            overflow: hidden;
        }
        .sign-div h1{
            margin-top: 100px;
            color: #fff;
            font-size: 40px;
        }
        .sign-div input{
            display: block;
            width: 100%;
            padding: 0 16px;
            height: 44px;
            text-align: center;
            box-sizing: border-box;
            outline: none;
            border: none;
            font-family: "montserrat",sans-serif;
        }

        .sign-div a{
            text-decoration: none;
            color: #fff;
            font-family: "montserrat", sans-serif;
            font-size: 14px;
            padding: 10px;
            transition: 0.8s;
            display: block;
        }
        .sign-div a:hover{
            background: rgba(0,0,0,.3);
        }
        .sign-a{
            text-align: center;
            font-size: 50px;
        }
    </style>
</head>

<body>
<div class="sign-div">
    <form class="" action="" method="post">
        <h1>欢迎来到图书管理系统</h1>
        <a class="sign-a" href="login.jsp">立即进入</a>
    </form>

</div>
</body>
</html>
