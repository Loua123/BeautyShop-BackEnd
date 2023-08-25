package com.example.ecommercelouabackend.exceptions;

import java.time.LocalDateTime;

public record ExceptionResponse
        (
                String path,
                String message,
                int statusCode,
                LocalDateTime localDateTime

        ) {


}
