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
            <th>image</th>
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
                    <td>${product.linkImage}</td>
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

        <label for="image">Image</label><br>
        <input type="text" name="image" placeholder="url image" id="image"><br><br>


        <input type="submit" value="add" class="btn btn-success">

    </form>

    <br>
    <hr>
    <h1> Оновлення та видалення продукції </h1>

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>short_description</th>
            <th>full_description</th>
            <th>category</th>
            <th>price</th>
            <th>image</th>
            <th> Оновлення</th>
            <th> Видалення</th>
        </tr>
        </thead>
        <tbody>

        <#if products??>
            <#list products as product>
                <form method="post" action="/updateProduct">
                <tr>
                    <td>${product.id}</td>
                    <input type="hidden" name="id" value="${product.id}">
                    <td><input type="text" name="name" value="${product.name}"></td>
                    <td><input type="text" name="short_description" value="${product.short_description}"></td>
                    <td><input type="text" name="full_description" value="${product.full_description}"></td>

                    <td>
                        <select name="categories">

                            <#if categories??>
                                <#list categories as category>
                                    <#if category.id==product.category.id>
                                        <option value="${category.id}" selected>${category.name}</option>
                                    <#else>
                                        <option value="${category.id}">${category.name}</option>
                                    </#if>
                                </#list>
                            </#if>
                        </select>

<#--                        ${product.category.name}-->
                    </td>
                    <td><input type="number" name="price" value="${product.price?c}"></td>
                    <td><input type="text" name="image" value="${product.linkImage}"></td>
                    <td> <button  class="btn btn-success" type="submit"> Update </button> </td>
                </form>
                    <form method="post" action="/deleteProduct">
                        <input type="hidden" name="id" value="${product.id}">
                        <td> <button class="btn btn-success" type="submit">Delete </button></td>
                    </form>
                </tr>
            </#list>
        </#if>

        </tbody>
    </table>

    <br>
    <hr>
    <h1> Видалення всіх даних </h1>

    <form method="post" action="/deleteAllProducts">
        <button type="submit" class="btn btn-success"> Видалення всіх видів продукції </button>
    </form>






</@p.pages>