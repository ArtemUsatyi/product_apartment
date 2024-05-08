package com.example.product_apartment.constans;

public class ProductApartmentConst {

    /**
     * PathProductConstant
     */
    public static final String BASE_URL = "/api";
    public static final String PRODUCT = BASE_URL + "/product";
    public static final String SEND_MESSAGE = BASE_URL + "/send_message";

    /**
     * MessageException
     */
    public static final String TOKEN_EXCEPTION = "Токен не валидный";
    public static final String PRODUCT_EXCEPTION = "Текущее бронирование не обнаружено";

    /**
     * MessageInfoProduct
     */
    public static final String PRODUCT_SEARCH_DONE = "Квартира забронирована, информация об бронировании и подобранной скидки отправлена вам на почту";
    public static final String BOOKING_MESSAGE = "Квартира забронирована, по адресу город - %s, улица - %s, дата бронирования - %s" +
            ", сумма - %s, с учетом скидки к оплате - %s";
}
