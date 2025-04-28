<#import "admin/templ_admin.ftl" as p>
<@p.pages>

    <#list clients as client>

        ${client}

        <form action="/saveNewRoleForClient" method="post">
        <input type="hidden" name="id" value="${client.id}">
        <select name="roles">
            <option value="1">ROLE_user</option>
            <option value="2">ROLE_manager</option>
            <option value="3">ROLE_admin</option>
        </select>

        <button type="submit">Add</button>
        </form>


    </#list>



</@p.pages>