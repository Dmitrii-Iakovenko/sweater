<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<h1 class="mt-4 mb-5">Добавить нового пользователя</h1>
<span style="color: red">${message!}</span>
<@l.login "/registration" true />
</@c.page>




