<#import "customer/templ_customer.ftl" as p>

<@p.pages>
    <h3> Cart </h3>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th> Image</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Value</th>
            <th> update</th>
            <th> delete</th>
        </tr>
        </thead>
        <tbody>

        <#if cart??>
            <#list cart as item>
                <tr>
                    <td> ${item.product.name} </td>
                    <td>
                        <img src="${item.product.linkImage}" height="25" alt="${item.product.name}">
                    </td>

                    <form method="post" action="/updateItemFromCart">
                        <td>
                            <input type="number" name="quantity" value="${item.quantity}" min="1" max="1000" step="1">

                        </td>
                        <td> ${item.product.price} </td>
                        <td> ${item.product.price * item.quantity}</td>
                        <td>
                            <input type="hidden" name="id" value="${item.product.id}">
                            <button> Update</button>
                        </td>
                    </form>
                    <td>
                        <form method="post" action="/deleteItemFromCart">
                            <input type="hidden" name="id" value="${item.product.id}">
                            <button> Delete</button>
                        </form>

                    </td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>

    <hr>

    <h4> Вартість купівлі: ${totalValue} </h4>
    <h4> Загальна кількість елементів у кошику: ${sumEl}</h4>


    <hr>
    <h4> Очистити кошик і повернутись до каталогу </h4>

    <form method="post" action="/deleteAllItemFromCart">
        <button>Delete All</button>
    </form>

    <hr>
    <h4> Перехід на форму оформлення замовлення </h4>
    <form method="get" action="/order">
        <button>Order</button>
    </form>

</@p.pages>