<#import "customer/templ_customer.ftl" as p>
<@p.pages>

    <h2> Форма аутентифікації користувача у системі</h2>

    <form action="/login" method="post">

        <label for="username">Username</label><br/>
        <input type="text" name="username" placeholder="user" id="username"><br>

        <label for="password">Password</label><br>
        <input type="password" name="password" id="password"><br>

        <a href="/registration"> to form registration </a><br>

        <input type="submit" value="add">

    </form>

</@p.pages>