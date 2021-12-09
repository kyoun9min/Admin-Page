package com.fastcampus.java.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Header<T> {

    private LocalDateTime transactionTime;

    private String resultCode;

    private String descrption;

    private T data;

    public static <T> Header<T> OK() {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .descrption("OK")
                .build();
    }

    public static <T> Header<T> OK(T data) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .descrption("OK")
                .data(data)
                .build();
    }

    public static <T> Header<T> ERROR(String descrption) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .descrption(descrption)
                .build();
    }
}
