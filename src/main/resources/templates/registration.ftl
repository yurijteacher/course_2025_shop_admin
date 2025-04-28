<#import "customer/templ_customer.ftl" as p>
<#import "/spring.ftl" as s>

<@p.pages>

    <h2> Registration </h2>

    <form action="/registration" method="post">

    <@s.bind "clients"/>

    <label for="username">Username</label><br>
    <@s.formInput "clients.username"/><br>
    <@s.showErrors "<br>"/><br>

    <label for="password">Password</label><br>
    <@s.formInput "clients.password"/><br>
    <@s.showErrors "<br>"/><br>

    <@s.bind "customers"/>
    <label for="firstName">First Name</label><br>
    <@s.formInput "customers.firstName"/><br>
    <@s.showErrors "<br>"/><br>

    <label for="lastName">Last Name</label><br>
    <@s.formInput "customers.lastName"/><br>
    <@s.showErrors "<br>"/><br>

    <label for="email">Email</label><br>
    <@s.formInput "customers.email"/><br>
    <@s.showErrors "<br>"/><br>

    <label for="phone">Phone</label><br>
    <@s.formInput "customers.phone"/><br>
    <@s.showErrors "<br>"/><br>

        <input type="submit" value="add New User">

    </form>

</@p.pages>