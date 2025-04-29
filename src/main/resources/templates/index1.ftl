<#import "customer/templ_customer.ftl" as p>
<@p.pages>

    <h3 pt-3> Categories </h3>


    <div class="row">
        <div class="col"></div>
        <div class="col"></div>
        <div class="col"></div>
        <div class="col">

            <form action="/list_card" method="get">
                <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                    <button type="submit" class="btn btn-outline-primary" name="list1" value="true">
                        <i class="bi bi-card-checklist"> list</i>
                    </button>
                    <button type="submit" class="btn btn-outline-primary" name="list1" value="false">
                        <i class="bi bi-table"> table</i>
                    </button>
                </div>
            </form>

        </div>
    </div>

    <#if list1??>
        <#if list1=="true">
            <hr>
            <table class="table table table-striped">
                <thead>
                <tr>
                    <td>img</td>
                    <td>name</td>
                    <td>description</td>
                </tr>

                </thead>
                <tbody>
                <#if categories??>
                    <#list categories as category>
                        <tr>
                            <td>
                                <a href="/category/${category.id}">
                                    <img src="${category.linkImages}"
                                         alt="${category.name}" width="50px">
                                </a>
                            </td>
                            <td>${category.name}</td>
                            <td>${category.description}</td>
                        </tr>
                    </#list>
                </#if>
                </tbody>

            </table>
        <#else>

            <hr>
            <div class="row row-cols-2 row-cols-md-3 g-4">
                <#if categories??>
                    <#list categories as category>
                        <div class="col">
                            <div class="card">
                                <a href="/category/${category.id}">
                                    <img src="${category.linkImages}" class="card-img-top card-img-bottom"
                                         alt="${category.name}" height="150px">
                                </a>
                                <div class="card-body">
                                    <h5 class="card-title">${category.name}</h5>
                                    <p class="card-text">${category.description}</p>
                                </div>
                            </div>
                        </div>
                    </#list>
                </#if>
            </div>
        </#if>
    </#if>

    <br>

</@p.pages>
