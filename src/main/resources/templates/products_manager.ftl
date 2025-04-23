<#import "manager/templ_manager.ftl" as p>
<@p.pages>
    <h1> Cторінка для адміністування продукції </h1>

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>short_description</th>
            <th>full_description</th>
            <th>category</th>
            <th>price</th>
        </tr>
        </thead>
        <tbody>

        <#if products??>
            <#list products as product>
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.short_description}</td>
                    <td>${product.full_description}</td>
                    <td>${product.category.name}</td>
                    <td>${product.price?c}</td>
                </tr>
            </#list>
        </#if>

        </tbody>
    </table>


    <br>
    <hr>
    <h1> Додавання нової продукції</h1>

    <form action="/saveNewProduct" method="post">

        <label for="name">Name</label><br>
        <input type="text" name="name" placeholder="name" id="name"><br><br>

        <label for="short_description">Short description</label><br>
        <input type="text" name="short_description" placeholder="short_description" id="short_description"><br><br>

        <label for="full_description">Full description</label><br>
        <input type="text" name="full_description" placeholder="full_description" id="full_description"><br><br>

        <label for="categories">Category</label>
        <select name="categories">
            <#list categories as category>
                <option value="${category.id}">${category.name}</option>
            </#list>
        </select><br><br>

        <label for="price">Price</label><br>
        <input type="number" min="0" step="0.01" max="10000" name="price" id="price"><br><br>


        <input type="submit" value="add">


    </form>




</@p.pages>