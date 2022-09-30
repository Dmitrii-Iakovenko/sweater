<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <h1 class="mt-4 mb-5">Войти</h1>
    <span style="color: red">${message!}</span>
    <@l.login "/login" false />
</@c.page>


