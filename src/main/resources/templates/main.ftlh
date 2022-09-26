<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <h1>Добавить нового пользователя</h1>
    <div>
        <@l.logout />
    </div>


    <div>
        <h2>Добавить сообщение</h2>
        <form method="post" action="/main">
            <input type="text" name="text" placeholder="Введите сообщение"/>
            <input type="text" name="tag" placeholder="Тэг"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>
        <h2>Список сообщений</h2>
        <form method="get" action="/main">
            <input type="text" name="filter" value="${filter!}"/>
            <button type="submit">Найти</button>
        </form>
        <#list messages as message>
            <div>
                <b>${message.id}</b>
                <span>${message.text}</span>
                <i>${message.tag}</i>
                <strong>${message.author}</strong>
            </div>
        <#else>
            No messages
        </#list>
    </div>
</@c.page>