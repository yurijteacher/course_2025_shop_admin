package ua.com.kneu.course.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.com.kneu.course.entity.Products;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        List<Products> products = new ArrayList<>();


        Document doc = null;


        try {

            doc = Jsoup.connect("https://rozetka.com.ua/ua/notebooks/c80004/producer=apple/")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();

            Elements elements = doc.select("[class='tile']");
            String name = "";
            String price = "";
            String description = "";
            String image = "";

            for (Element element : elements) {
//                System.out.println(element);
//                System.out.println("--------");
//            }

              name  = element.select("[class='tile-title black-link text-base']").text();
              if(name.equals(""))
                  name  = element.select("[class='tile-title black-link text-base']").text();


              if(!name.equals("")) {

//                  System.out.println(name);

                  price = element.select("[class='price color-red']").text();
                  if (price.equals("")) price = element.select("[class='price']").text();

                  price = price.replaceAll("[^0-9\\.]+", "");

//                  System.out.println(price);

                  description = element.select("[class='green']").text();
//                  System.out.println(description);

                  image = element.select("img.tile-image").attr("src");
//                  System.out.println(image);

                  Products product = new Products();
                  product.setName(name);
                  product.setShort_description(name);
                  product.setFull_description(name + ", " + description);
                  product.setPrice(new BigDecimal(Double.parseDouble(price)));
                  product.setLinkImage(image);

                  products.add(product);
              }
            }

        } catch (Exception e){
            e.printStackTrace();
        }


        for (Products product : products) {
            System.out.println(product);
        }


    }

}
