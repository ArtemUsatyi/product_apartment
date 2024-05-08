package com.example.product_apartment.cotroller;

import com.example.product_apartment.service.ServiceProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.product_apartment.constans.ProductApartmentConst.PRODUCT;
import static com.example.product_apartment.constans.ProductApartmentConst.SEND_MESSAGE;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ServiceProduct serviceProduct;

    @GetMapping(PRODUCT)
    public String getProduct(@RequestParam Long id,
                             @RequestParam String key) {
        serviceProduct.checkValidKey(key);
        return serviceProduct.prepareProduct(id);
    }

    @GetMapping(SEND_MESSAGE)
    public String getEmailTest(@RequestParam String text){
        serviceProduct.sendMail(text);
        return "Message send";
    }

}
