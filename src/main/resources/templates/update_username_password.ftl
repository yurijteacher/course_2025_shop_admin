<#import "admin/templ_admin.ftl" as p>

<@p.pages>
    <h1> Сторінка адміністування користувачів </h1>

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>first Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>login</th>
            <th>password</th>
            <th>update</th>
        </tr>
        </thead>
        <tbody>
        <#if clients??>
            <#list clients as client>
                <tr>
                    <form method="post" action="/updateUsernameAndPassword">
                        <#if customers??>
                            <#list customers as customer>
                                <#if customer.id==client.id>
                                    <td>${customer.id}</td>
                                    <td>${customer.firstName}</td>
                                    <td>${customer.lastName}</td>
                                    <td>${customer.email}</td>
                                    <td>${customer.phone}</td>
                                </#if>
                            </#list>
                        </#if>

                        <input type="hidden" name="id" value="${client.id}">
                        <td><input type="text" name="username" value="${client.username}"></td>
                        <td><input type="text" name="password" value="${client.password}"></td>
                        <td>
                            <button type="submit"> Update</button>
                        </td>
                    </form>
                </tr>
            </#list>
        </#if>

        </tbody>
    </table>



</@p.pages>