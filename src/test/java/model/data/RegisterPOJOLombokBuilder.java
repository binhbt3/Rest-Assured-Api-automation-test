package model.data;

import net.datafaker.Faker;

import java.util.Locale;

public class RegisterPOJOLombokBuilder {

    public static RegisterPOJOLombok getRegisterData(){
        Faker faker = new Faker(new Locale("en"));
        String userName = faker.internet().username();
        String email = faker.internet().emailAddress();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String password = faker.internet().password(6,10, true, true);
        String phone = faker.number().digits(10);
        int userStatus = faker.number().numberBetween(0,1);
        return RegisterPOJOLombok.builder()
                .username(userName)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .phone(phone)
                .userStatus(userStatus).build();
    }
}


