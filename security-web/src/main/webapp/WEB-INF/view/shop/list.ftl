<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>店铺列表</title>
</head>
<body>
<table border="1">
    <tr>
        <th>id</th>
        <th>店铺名称</th>
        <th>店铺链接</th>
        <th>状态</th>
        <th>创建人</th>
    </tr>

<#list shopList as shop>
    <tr>
        <td>${shop.id}</td>
        <td>${shop.shopName}</td>
        <td>${shop.shopUrl}</td>
        <td><#if shop.state> 在线 <#else> 下线 </#if></td>
        <td>${shop.username}</td>
    </tr>
</#list>


</table>
</body>
</html>