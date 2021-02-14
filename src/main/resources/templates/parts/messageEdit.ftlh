<a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseForm" role="button" aria-expanded="false"
   aria-controls="collapseExample">
    Message editor
</a>
<div class="collapse <#if message??>show</#if>" id="collapseForm">
    <div class="form-group mt-3">
        <form method="post" action="/main" enctype="multipart/form-data">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="<#if message??>${message.id}</#if>"/>
            <div class="form-group mt-3">
                <label>
                    <input type="text" name="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                           placeholder="Введите сообщение" value="<#if message??>${message.text}</#if>"/>
                    <#if textError??>
                        <div class="invalid-feedback">${textError}</div>
                    </#if>
                </label>
            </div>
            <div class="form-group mt-3">
                <input type="file" name="file" id="customFile">
                <label class="custom-file-label" for="customFile">Choose file</label>
            </div>
            <div class="form-group mt-3">
                <label>
                    <input type="text" name="tag" class="form-control ${(tagError ??)?string('is-invalid', '')}"
                           placeholder="Тэг"
                           value="<#if message??>${message.tag}</#if>"/>
                    <#if tagError??>
                        <div class="invalid-feedback">${tagError}</div>
                    </#if>
                </label>
            </div>
            <div class="form-group mt-3">
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </div>
        </form>
    </div>
</div>