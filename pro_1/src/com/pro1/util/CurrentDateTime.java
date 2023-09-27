package com.pro1.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentDateTime {
    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final String time = localDateTime.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

    public String getTime() {
        return time;
    }

}
