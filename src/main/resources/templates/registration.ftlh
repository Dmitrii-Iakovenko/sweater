<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<h1>Добавить нового пользователя</h1>
<span style="color: red">${message!}</span>
<@l.login "/registration" />
</@c.page>




