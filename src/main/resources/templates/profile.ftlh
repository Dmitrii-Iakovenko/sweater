<#import "parts/common.ftl" as c>

<@c.page>
    <h1 class="mt-4 mb-5">Редактируем профиль пользователя ${username}</h1>
    <span style="color: red">${message!}</span>

    <form method="post">
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" name="password" placeholder="Password" />
            </div>
        </div>
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-6">
                <input type="email" class="form-control" name="email" placeholder="E-mail" value="${email!}" />
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Сохранить</button>
    </form>
</@c.page>
