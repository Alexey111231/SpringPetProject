<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <@l.logout/>
    <div>
        <form method="post">
            <label>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="text" name="text" placeholder="Введите сообщение"/>
            </label>
            <label>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="text" name="tag" placeholder="Тэг"/>
            </label>
            <button type="submit">Добавить</button>
        </form>
    </div>

    <div> Список сообщений</div>
    <form method="get" action="/main">
        <label>
            <input type="text" name="filter" value="${filter!}"/>
        </label>
        <button type="submit">Найти</button>
    </form>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
        </div>
    <#else>
        No messages
    </#list>
</@c.page>