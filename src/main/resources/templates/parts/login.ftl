<#macro login path isRegisterForm>
    <form method="post"  action="${path}">
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">User Name:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" name="username" placeholder="User name" />
            </div>
        </div>
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" name="password" placeholder="Password" />
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">
            <#if isRegisterForm>Зарегистрироваться<#else>Войти</#if>
        </button>
    </form>
    <br>
    <#if !isRegisterForm><a href="/registration">Регистрация</a></#if>
</#macro>


<#macro logout>
    <form method="post" action="/logout">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Выйти</button>
    </form>
</#macro>