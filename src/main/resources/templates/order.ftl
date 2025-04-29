<#import "customer/templ_customer.ftl" as p>

<@p.pages>

    <h2> Order </h2>

    <hr/>

    <h3> Інформація про користувача </h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <td>First Name</td>
            <td>Last Name</td>
            <td>Phone</td>
            <td>Email</td>
        </tr>
        </thead>
        <tr>
            <td>${customer.firstName}</td>
            <td>${customer.lastName}</td>
            <td>${customer.phone}</td>
            <td>${customer.email}</td>
        </tr>
    </table>


    <hr/>
    <h3> Інформація про продукцію </h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Image</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Value</th>
        </tr>
        </thead>
        <tbody>
        <#if cart??>
            <#list cart as item>
                <tr>
                    <td> ${item.product.name} </td>
                    <td> <img src="${item.product.linkImage}" height="25" alt="${item.product.name}"> </td>
                    <td> ${item.quantity} </td>
                    <td> ${item.product.price} </td>
                    <td> ${item.product.price * item.quantity}</td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
    <hr>

    <form action="/order" method="post">
    <h3> Delivery </h3>
        <select name="delivery">
            <option value="1">Самовивіз</option>
            <option value="2">Нова пошта</option>
            <option value="3">Укрпошта</option>
            <option value="4">Кур'єром</option>
        </select>
    <hr>

    <h3> Payment </h3>
        <select name="payment">
            <option value="1">Готівка</option>
            <option value="2">Банківська карта</option>
        </select>

    <button class="button"> Buy </button>
    </form>


</@p.pages>