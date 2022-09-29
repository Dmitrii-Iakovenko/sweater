<#import "parts/common.ftl" as c>

<@c.page>
    <div class="container px-4 text-center mt-4 mb-5">
        <#-- Форма фильтрации -->
        <div class="row gx-5">
            <form method="get" class="col d-flex p-3 border bg-light" action="/main">
                <input type="text" class="form-control" name="filter" value="${filter!}" placeholder="Фильтр"/>
                <button class="btn btn-primary ms-3" type="submit">Найти</button>
            </form>
        </div>

        <#-- Форма добавления сообщения -->
        <div class="row gx-5">
            <div class="col p-3 border bg-light">
                <div class="d-flex justify-content-end">
                    <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                        Добавить сообщение
                    </button>
                </div>
                <div class="collapse" id="collapseExample">
                    <form class="col mt-3" method="post" action="/main" enctype="multipart/form-data">
                        <div class="input-group mb-3">
                            <input class="form-control"type="text" name="text" placeholder="Введите сообщение"/>
                            <input class="form-control"type="text" name="tag" placeholder="Тэг"/>
                        </div>
                        <div class="input-group">
                            <input type="file" name="file" class="form-control" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload">
                            <button class="btn btn-primary" type="submit" id="inputGroupFileAddon04">Добавить</button>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    </form>
                </div>
            </div>
        </div>

        <#-- Список карточек -->
        <div class="row gx-5">
            <div class="col p-3 border">
                <div class="row row-cols-1 row-cols-md-3 g-3">
                    <#list messages as message>
                        <div class="col">
                            <div class="card">
                                <#if message.filename??>
                                    <img class="card-img-top" src="/img/${message.filename}" />
                                </#if>
                                <div class="card-body">
                                    <h5 class="card-title">${message.tag}</h5>
                                    <p class="card-text">${message.text}</p>
                                </div>
                                <div class="card-footer">
                                    ${message.author}
                                </div>
                            </div>
                        </div>
                    <#else>
                        No messages
                    </#list>
                </div>
            </div>
        </div>
    </div>
</@c.page>
