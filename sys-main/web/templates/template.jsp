<%--
  Created by IntelliJ IDEA.
  User:
  Date:
  Time:
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>用户登录</title>
    <style>
        body{
            margin: 0;
            padding: 0;
            background: #487eb0;
        }
        .sign-div{
            width: 300px;
            padding: 20px;
            text-align: center;
            position:absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%,-50%);
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
        .sign-text{
            margin:4px;
            background: rgba(255,255,255,5);
            border-radius: 6px;
        }
        .sign-btn{
            margin-top: 50px;
            margin-bottom: 20px;
            background: #487eb0;
            color: #fff;
            border-radius: 44px;
            cursor: pointer;
            transition: 0.8s;
            font-size: 25px;
        }
        .sign-btn:hover{
            transform:scale(0.96);
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
    </style>
</head>
<div class="sign-div">
    <form class="" action="template.jsp" method="post">
        <h1>用户登录</h1>
        <input class="sign-text" type="text" placeholder="请输入用户名" >
        <input class="sign-text" type="password" placeholder="请输入密码">
        <input class="sign-btn" type="submit" value="登录">
        <input class="sign-btn" type="submit" value="注册用户">

    </form>

</div>

<body>

</body>
</html>
