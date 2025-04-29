package ua.com.kneu.course.bl;

import lombok.Getter;
import lombok.Setter;
import ua.com.kneu.course.entity.Products;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter

public class Cart {

    List<ItemCart> cart;
    public double totalValue;
    public double sumElFromCart;


    public Cart() {
        cart = new ArrayList<>();
        totalValue = 0;
        sumElFromCart = 0;
    }

    // addProductToCart
    public synchronized void addNewItemToCart(Products product, int quantity) {
        // v.1
//        boolean logic = false;
//
//        for (ItemCart itemCart : cart) {
//            if (itemCart.getProduct().getId()==product.getId()) {
//                logic = true;
//                itemCart.setQuantity(itemCart.getQuantity()+quantity);
//            }
//        }
//
//        if(!logic) cart.add(new ItemCart(product, quantity));

//        boolean logic = false;

        for (ItemCart itemCart : cart) {
            if (itemCart.getProduct().getId() == product.getId()) {
                itemCart.setQuantity(itemCart.getQuantity() + quantity);
                return;
            }
        }
        cart.add(new ItemCart(product, quantity));
    }
    // updateProductFromCart

    public synchronized void updateItemFromCart(Products product, int quantity) {
//  v.1
//        if (quantity <= 0) {
//            for (ItemCart itemCart : cart) {
//                if (itemCart.getProduct().getId() == product.getId()) {
//                    cart.remove(itemCart);
//                    break;
//                }
//            }
//        } else {
//            for (ItemCart itemCart : cart) {
//                if (itemCart.getProduct().getId() == product.getId()) {
//                    itemCart.setQuantity(quantity);
//                }
//            }
//        }

        Iterator<ItemCart> iterator = cart.iterator();

        while (iterator.hasNext()) {
            ItemCart itemCart = iterator.next();

            if (itemCart.getProduct().getId() == product.getId()) {
                if (quantity <= 0) {
                    iterator.remove();
                } else {
                    itemCart.setQuantity(quantity);
                }
            }
        }
    }


    // deleteProductFromCart
    public synchronized void deleteItemFromCart(Products product) {

        for (ItemCart itemCart : cart) {
            if (itemCart.getProduct().getId() == product.getId()) {
                cart.remove(itemCart);
                break;
            }
        }
    }


    // deleteAllCart

    public synchronized void deleteAllItemsFromCart() {
        cart.clear();
        totalValue = 0;
        sumElFromCart = 0;
    }


    // getTotalValue

    public synchronized double getTotalValue() {
        totalValue = 0;

        for (ItemCart itemCart : cart) {
            totalValue += itemCart.getQuantity() * itemCart.getProduct().getPrice().doubleValue();
        }
        return totalValue;
    }

    // getSumElFromCart
    public synchronized double getSumElFromCart() {
        return cart.size();
    }


}
