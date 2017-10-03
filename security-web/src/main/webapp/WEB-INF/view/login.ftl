
<!DOCTYPE html>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>

<div id="global">
    <form name="loginForm" action="/login" method="POST">
        <fieldset>
            <legend>登陆</legend>
            <p>
                <label for="name">用户名:</label>
                <input type="text" name="username" "/>
            </p>

            <p>
                <label for="name">密码:</label>
                <input type="password" name="password" />
            </p>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <p id="buttons">
                <input id="reset" type="reset" tabindex="4"
                       value="重置">
                <input id="submit" type="submit" tabindex="5"
                       value="登陆">
            </p>
            错误信息:${error}
        </fieldset>
    </form>
</div>
</body>
</html>
