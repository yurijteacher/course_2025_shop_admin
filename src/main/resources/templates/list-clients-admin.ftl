<#import "admin/templ_admin.ftl" as p>

<@p.pages>
    <h1> Сторінка адміністування користувачів </h1>

    <table class="table">
        <thead>
        <tr>
            <th>login</th>
            <th>password</th>
            <th>first Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone</th>
        </tr>
        </thead>
        <tbody>
        <#if clients??>
            <#list clients as client>
                <tr>
                    <td>${client.username}</td>
                    <td>${client.password}</td>

                    <#if customers??>
                        <#list customers as customer>
                            <#if customer.id==client.id>
                                <td>${customer.firstName}</td>
                                <td>${customer.lastName}</td>
                                <td>${customer.email}</td>
                                <td>${customer.phone}</td>
                            </#if>
                        </#list>
                    </#if>
                </tr>
            </#list>
        </#if>

        </tbody>
    </table>



</@p.pages>