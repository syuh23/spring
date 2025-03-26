package org.example.jpa_spring.error;

import org.springframework.http.ResponseEntity;

public class DataResponseType<T> extends ResponseType {
    private final T data;

    public DataResponseType(ResponseEnum responseEnum, T data) {
        super(responseEnum);
        this.data = data;
    }

    public static <T> ResponseEntity<DataResponseType<T>> entity(ResponseEnum responseEnum, T data) {
        return ResponseEntity.status(responseEnum.getHttpStatus()).body(new DataResponseType<>(responseEnum, data));
    }
}
