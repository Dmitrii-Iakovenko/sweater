<#import "parts/common.ftl" as c>

<@c.page>
    <h1>User editor</h1>
    <form action="/users" method="post">
        <input type="hidden" value="${user.id}" name="id" />
        <label>User Name: <input type="text" value="${user.username}" name="username" /></label>
        <h2>Roles:</h2>
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}"
                            ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${_csrf.token}" name="_csrf" />
        <br>
        <button type="submit">Сохранить</button>
    </form>
</@c.page>