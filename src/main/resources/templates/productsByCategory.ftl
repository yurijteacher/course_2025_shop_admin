<#import "customer/templ_customer.ftl" as p>
<@p.pages>

    <h3 pt-3> Categories </h3>
    <br>
    <hr>

    <div class="row row-cols-2 row-cols-md-3 g-4">
        <#if products??>
            <#list products as product>
                <div class="col">
                    <div class="card">
                        <form method="post" action="/addToCart">
                            <input type="hidden" name="id" value="${product.id}">
<#--                        <a href="/category/${product.id}">-->

<#--                        </a>-->
                            <a href="#" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                <img src="${product.linkImage}" class="card-img-top" alt="${product.name}">
                            </a>

                        <div class="card-body text-center">

<#--                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">-->
<#--                                Переглянути повну інформацію про товар-->
<#--                            </button>-->

                            <h5 class="card-title"><b>${product.name}</b></h5>
                            <hr>
                            <p class="card-text">Category: ${product.category.name}</p>
                            <p class="cart-text">Manufacturer: ${product.manufacturer.name}</p>
                            <p class="card-text">${product.short_description}</p>

                            <p>Кількість <input type="number" min="1" max="1000" value="1" name="quantity"></p>
                            <p class="cart-text"> Ціна: <b> ${product.price} грн.</b></p>
                            <hr>
                            <div class="">
                                <input type="submit" value="addToCart" class="btn btn-success">
                            </div>
                        </div>
                        </form>
                    </div>
                </div>


                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">${product.name}</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">

                                <div class="row">
                                    <div class="col">

                                        <p>Band: <b>${product.brand.name}</b></p>
                                        <p>Manufacturer: <i>${product.manufacturer.name}</i></p>

                                    </div>
                                    <div class="col">
                                        <img src="${product.linkImage}">
                                        <p></p>
                                        <p class="text-center"><i class="bi bi-star"></i><i class="bi bi-star"></i><i class="bi bi-star"></i><i class="bi bi-star"></i><i class="bi bi-star"></i> Rating: 0 </p>

                                    </div>

                                </div>

                                <div class="row">
                                    <div class="col">
                                        ${product.full_description}

                                        <hr>

                                        <p class="card-text">${product.short_description}</p>

                                        <form method="post" action="/addToCart">
                                            <input type="hidden" name="id" value="${product.id}">
                                        <p>Кількість <input type="number" min="1" max="1000" value="1" name="quantity"></p>
                                        <p class="cart-text"> Ціна: <b> ${product.price} грн.</b></p>
                                        <hr>
                                        <div class="">
                                            <input type="submit" value="addToCart" class="btn btn-success">
                                        </div>
                                        </form>
                                    </div>


                                </div>





                            </div>
                            <div class="modal-footer">
<#--                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>-->
<#--                                <button type="button" class="btn btn-primary">Save changes</button>-->
                            </div>
                        </div>
                    </div>
                </div>

            </#list>
        </#if>
    </div>
    <br>





</@p.pages>