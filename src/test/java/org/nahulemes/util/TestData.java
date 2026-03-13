package org.nahulemes.util;

import org.nahulemes.models.UserData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TestData {

    public static final String BASE_URL = "https://petstore.swagger.io/v2";

    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private TestData() {
    }

    public static UserData initialUser() {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        long userId = Long.parseLong(timestamp);
        String username = "user-auto-" + timestamp;

        return UserData.builder()
                .id(userId)
                .username(username)
                .firstName("Nahu")
                .lastName("Lemes")
                .email("nahu." + timestamp + "@mailinator.com")
                .password("Pass@" + timestamp)
                .phone("300" + timestamp.substring(4, 10))
                .userStatus(1)
                .build();
    }

    public static UserData updatedUserFrom(UserData originalUser) {
        return UserData.builder()
                .id(originalUser.getId())
                .username(originalUser.getUsername())
                .firstName("Nahuel")
                .lastName("LemesActualizado")
                .email("actualizado." + originalUser.getUsername() + "@mailinator.com")
                .password("New" + originalUser.getPassword())
                .phone("3110009999")
                .userStatus(2)
                .build();
    }
}
