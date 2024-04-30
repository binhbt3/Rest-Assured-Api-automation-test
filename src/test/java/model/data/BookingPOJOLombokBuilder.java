package model.data;

import net.datafaker.Faker;

import java.util.*;

public class BookingPOJOLombokBuilder {
    public static BookingPOJOLombok getBookingData(){
        Faker faker = new Faker(new Locale("en", "US"));
        String name = faker.book().title().toString() + faker.random().nextInt(0, 1000);
        int price = faker.number().numberBetween(1, 10000);
        Date dateStart = new Date(new Random().nextInt());
        Date dateStop = new Date();
        String releaseDate = faker.date().between(dateStart, dateStop, "yyyy/MM/dd").toString();
        System.out.println("12: " + releaseDate);
        List<Integer> image_ids = new ArrayList();
        boolean status = faker.bool().bool();
        System.out.println("18: " + status);
        image_ids.add(1);
        return BookingPOJOLombok.builder()
                .name(name)
                .category_id(1)
                .price(price)
                .release_date(releaseDate)
                .image_ids(image_ids)
                .status(status)
                .build();
    }
}
