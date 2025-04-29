<#import "customer/templ_customer.ftl" as p>

<@p.pages>

    <h3> Д'якуємо за купівлю в нашому магазині.<br/>
    <p> Номер Вашого замовлення - №<#if orderId??> ${orderId}</#if> </p>
    </h3>


</@p.pages>