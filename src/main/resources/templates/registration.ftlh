<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<h1>Добавить нового пользователя</h1>
<#if message??><span style="color: red">${message}</span></#if>
<@l.login "/registration" />
</@c.page>




