package org.example.jpa_spring.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class ResponseType {
    private final LocalDateTime timestamp;
    private final HttpStatus statusCode;
    private final String message;

    public ResponseType(ResponseEnum responseEnum) {
        this.timestamp = LocalDateTime.now();
        this.statusCode = responseEnum.getHttpStatus();
        this.message = responseEnum.getMessage();
    }

    public static ResponseEntity<ResponseType> entity (ResponseEnum responseEnum) {
        return ResponseEntity.status(responseEnum.getHttpStatus()).body(new ResponseType(responseEnum));
    }
}
