<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <h1>Необходимо залогиниться</h1>
    <@l.login "/login" />
    <a href="/registration">Регистрация</a>
</@c.page>


