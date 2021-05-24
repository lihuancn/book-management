<%--
  Created by IntelliJ IDEA.
  User: 李欢
  Date: 2021/5/23
  Time: 21:02
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
    <title>注册页面</title>
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
            transform: translate(-50%,-70%);
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
<body>
<div class="sign-div">
    <form class="" action="" method="post">
        <h1>用户注册</h1>
        <label>
            <input class="sign-text" type="text" placeholder="请输入用户名" >
        </label>
        <label>
            <input class="sign-text" type="password" placeholder="请输入密码">
        </label>
        <input class="sign-btn" type="submit" value="立即注册">
    </form>
</div>
</body>
</html>
