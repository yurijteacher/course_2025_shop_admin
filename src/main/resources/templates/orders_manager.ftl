<#import "manager/templ_manager.ftl" as p>
<@p.pages>
    <h1> Адміністування замовлень </h1>

    <#if orders??>
        <#list orders as order>
            <ul>
                <li> Order: <a href="/manager/order/${order.id}">  Переглянути замовлення № ${order.id} </a></li>
                <li>Прізвище: ${order.customer.lastName}</li>
                <li>Ім'я: ${order.customer.firstName}</li>
                <li>Phone: ${order.customer.phone}</li>
                <li>Email: ${order.customer.email}</li>
                <li>Дата створення замовлення: ${order.dateCreated?string("yyyy:MM:dd")}</li>
                <li>Доставка: ${order.delivery.name}</li>
                <li>Оплата: ${order.payment.name}</li>
                <li>Статус замовлення: ${order.status}</li>
            </ul>

        </#list>
    </#if>

</@p.pages>