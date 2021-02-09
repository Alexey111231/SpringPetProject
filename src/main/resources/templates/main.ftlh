<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main">
                <label>
                    <input type="text" name="filter" value="${filter!}" class="form-control" placeholder="Search"/>
                </label>
                <button class="btn btn-primary" type="submit">Найти</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseForm" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new message
    </a>
    <div class="collapse" id="collapseForm">
        <div class="form-group mt-3">
            <form method="post" action="/main" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group mt-3">
                    <label>
                        <input type="text" name="text" class="form-control" placeholder="Введите сообщение"/>
                    </label>
                </div>
                <div class="form-group mt-3">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
                <div class="form-group mt-3">
                    <label>
                        <input type="text" name="tag" class="form-control" placeholder="Тэг"/>
                    </label>
                </div>
                <div class="form-group mt-3">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <#list messages as message>
            <div class="col">
                <div class="card">
                    <#if message.filename??>
                        <img src="/img/${message.filename}" class="card-img-top">
                    </#if>
                    <div class="m-2">
                        <span>${message.text}</span>
                        <i>${message.tag}</i>
                    </div>
                    <div class="card-footer text-muted">
                        ${message.authorName}
                    </div>
                </div>
            </div>
        <#else>
            <div class="mt=3">
                No message
            </div>
        </#list>
    </div>
</@c.page>