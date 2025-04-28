<#import "manager/templ_manager.ftl" as p>
<@p.pages>
    <h1> Адміністування замовлень </h1>

    <hr>
    <h2> Замовлення № ${order.id} </h2>

    <hr>
    <table class="table">
        <tr>
            <td>${order.customer.firstName}</td>
            <td>${order.customer.lastName}</td>
            <td>${order.customer.phone}</td>
            <td>${order.customer.email}</td>
            <td>${order.payment.name}</td>
            <td>${order.delivery.name}</td>
        </tr>
    </table>

    <hr>

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>price</th>
            <th>quantity</th>
            <th>value(price * quantity)</th>
        </tr>
        </thead>
        <tbody>


        <#if productHasOrderList??>
            <#list productHasOrderList as pho>
                <tr>
                    <td>${pho.product.id}</td>
                    <td>${pho.product.name}</td>
                    <td>${pho.product.price}</td>
                    <td>${pho.quantity}</td>
                    <td>${pho.product.price*pho.quantity}</td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>

    <hr>

    Варість замовлення: <b>${value}</b>

</@p.pages>