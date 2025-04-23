<#import "manager/templ_manager.ftl" as p>
<@p.pages>

    <h1> Сторінка для адміністування каталогів продукції </h1>

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>description</th>
            <th>images</th>
        </tr>
        </thead>
        <tbody>
        <#if categories??>
        <#list categories as c>
        <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td>${c.description}</td>
            <td>${c.linkImages}</td>
        </tr>
        </#list>
        </#if>
        </tbody>
    </table>

    <br>
    <hr>
    <h1> Додавання нового каталогу продукції </h1>

    <form method="post" action="/saveNewCategory">

        <label for="name">Name</label><br>
        <input type="text" id="name" name="name" placeholder="name"><br>
        <br>

        <label for="description">Description</label><br>
        <input type="text" id="description" name="description" placeholder="description"><br>
        <br>

        <label for="images">Image</label><br>
        <input type="text" id="image" name="image" placeholder="image"><br>
        <br>


        <input type="submit" value="add">

    </form>

    <br>
    <hr>
    <h1> Оновлення та видалення категорій </h1>

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>description</th>
            <th>images</th>
            <th>Оновлення</th>
            <th>Видалення</th>
        </tr>
        </thead>
        <tbody>
        <#if categories??>
            <#list categories as c>
                <tr>
                    <form method="post" action="/updateCategory">
                    <td>
                        <input type="hidden" name="id1" value="${c.id}">
                        ${c.id}
                    </td>
                    <td>
                        <input type="text" name="name" value="${c.name}">
                    </td>
                    <td>
                        <input type="text" name="description" value="${c.description}">
                    </td>
                    <td>
                        <input type="text" name="image" value="${c.linkImages}">
                    </td>
                    <td>
                        <input type="submit" value="update">
                    </td>
                    </form>
                    <form method="post" action="/deleteCategoryFromList">
                    <td>
                        <input type="hidden" name="id1" value="${c.id}">
                        <input type="submit" value="delete">
                    </td>
                    </form>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>

    <br>
    <hr>
    <h1> Видалення всіх категорій </h1>

    <form method="post" action="/deleteAllCategory">
        <input type="submit" value="deleteAll">
    </form>




</@p.pages>