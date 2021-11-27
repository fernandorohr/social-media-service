package com.fernando.socialmediaservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InternalServerErrorExceptionDetails {

    private String title;
    private Integer status;
    private String details;
    private LocalDateTime timestamp;
}
