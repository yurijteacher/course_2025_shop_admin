package ua.com.kneu.course.bl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.kneu.course.entity.Products;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCart {

    private Products product;
    private int quantity;

}
