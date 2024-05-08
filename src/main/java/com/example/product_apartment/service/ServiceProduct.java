package com.example.product_apartment.service;

import com.example.product_apartment.exception.ProductException;
import com.example.product_apartment.exception.TokenException;
import com.example.product_apartment.model.entity.*;
import com.example.product_apartment.repository.BookingRepository;
import com.example.product_apartment.repository.ProductRepository;
import com.example.product_apartment.service.serviceImpl.ServiceProductImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.example.product_apartment.constans.ProductApartmentConst.*;

@Service
@RequiredArgsConstructor
public class ServiceProduct implements ServiceProductImpl {

    private final BookingRepository bookingRepository;
    private final ProductRepository productRepository;
    private final JavaMailSender javaMailSender;


    @Value("${cors.allowed-origins}")
    private String productKey;

    @Override
    public void checkValidKey(String key) {
        if (!new String(Base64ProductService.decod(productKey)).equals(key)) {
            throw new TokenException(TOKEN_EXCEPTION);
        }
    }

    @Override
    public String prepareProduct(Long id) {
        BookingEntity bookingProduct = bookingRepository.findById(id).orElseThrow(() -> new ProductException(PRODUCT_EXCEPTION));
        ApartmentEntity apartment = bookingProduct.getApartment();
        ClientApplicationEntity client = bookingProduct.getClient();

        List<ProductEntity> listProduct = prepareProduct(client, apartment);
        List<ProductEntity> resultProduct = listProduct.stream()
                .max(Comparator.comparingInt(ProductEntity::getValue))
                .map(p -> List.of(p))
                .orElse(List.of());

        bookingProduct.setProduct(resultProduct.get(0));
        bookingRepository.save(bookingProduct);

        sendMail(bookingProduct.getClient().getEmail(), prepareMessageFromSend(bookingProduct));
        return PRODUCT_SEARCH_DONE;
    }

    private String prepareMessageFromSend(BookingEntity booking) {
        AddressEntity address = booking.getApartment().getAddressEntity();
        String sum = calculateSumWithOutDiscount(booking);
        return String.format(BOOKING_MESSAGE, address.getNameCity(),
                address.getNameStreet(), booking.getStartDate(),
                sum,
                calculateWithDiscount(sum, booking)
        );
    }

    private String calculateSumWithOutDiscount(BookingEntity booking) {
        long days = Duration.between(booking.getStartDate(), booking.getEndDate()).toDays();
        long amount = Long.parseLong(booking.getApartment().getAmountApartment());
        long result = days * amount;
        return String.valueOf(result);
    }

    private String calculateWithDiscount(String sum, BookingEntity booking) {
        float discount = (float) booking.getProduct().getValue() / 100;
        float resultDisc = Float.parseFloat(sum) * discount;
        return String.valueOf(Integer.parseInt(sum) - resultDisc);
    }

    private List<ProductEntity> prepareProduct(ClientApplicationEntity client, ApartmentEntity apartment) {
        List<ProductEntity> allProduct = productRepository.findAll();
        List<ProductEntity> resultDiscountList = new ArrayList<>();
        for (ProductEntity product : allProduct) {
            if (product.getId() == 1) {
                resultDiscountList.add(product);
            }
            if (product.getId() == 3) {
                LocalDate dateNow = LocalDate.now();
                LocalDate dateOfDiscount = LocalDate.of(dateNow.getYear(), Month.SEPTEMBER, 1);
                if (dateOfDiscount.equals(dateNow)) {
                    resultDiscountList.add(product);
                }
            }
            if (product.getId() == 5) {
                LocalDate dateNow = LocalDate.now();
                LocalDate dateOfDiscount = LocalDate.of(dateNow.getYear(), Month.MAY, 9);
                if (dateOfDiscount.equals(dateNow)) {
                    resultDiscountList.add(product);
                }
            }
            if (product.getId() == 6) {
                DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
                if (dayOfWeek.equals(DayOfWeek.MONDAY)) {
                    resultDiscountList.add(product);
                }
            }
        }
        return resultDiscountList;
    }

    public void sendMail(String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("artem180791@mail.ru");
        msg.setFrom("usatii.art@yandex.ru");
        msg.setSubject("Заголовок сообщения");
        msg.setText(text);
        javaMailSender.send(msg);
    }

    public void sendMail(String email, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setFrom("usatii.art@yandex.ru");
        msg.setSubject("Заголовок сообщения");
        msg.setText(text);
        javaMailSender.send(msg);
    }
}
