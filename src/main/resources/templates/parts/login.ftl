<#macro login path isRegisterForm>
    <form method="post"  action="${path}">
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">User Name:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control<#if usernameError??> is-invalid</#if>"
                       value="<#if user??>${user.username}</#if>"
                       name="username" placeholder="User name" />
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" class="form-control<#if passwordError??> is-invalid</#if>"
                       name="password" placeholder="Password" />
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegisterForm>
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" class="form-control<#if password2Error??> is-invalid</#if>"
                       name="password2" placeholder="Retype password" />
                <#if password2Error??>
                    <div class="invalid-feedback">
                        ${password2Error}
                    </div>
                </#if>
            </div>
        </div>
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-6">
                <input type="email" class="form-control<#if usernameError??> is-invalid</#if>"
                       value="<#if user??>${user.email}</#if>"
                       name="email" placeholder="E-mail" />
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="g-recaptcha" data-sitekey="${captchaClient}"></div>
            <#if captchaError??>
                <div class="alert alert-danger" role="alert">
                    ${captchaError}
                </div>
            </#if>
        </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">
            <#if isRegisterForm>????????????????????????????????????<#else>??????????</#if>
        </button>
    </form>
    <br>
    <#if !isRegisterForm><a href="/registration">??????????????????????</a></#if>
</#macro>


<#macro logout>
    <form method="post" action="/logout">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">??????????</button>
    </form>
</#macro>