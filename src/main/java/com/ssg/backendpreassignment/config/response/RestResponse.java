package com.ssg.backendpreassignment.config.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RestResponse<T> {
    private LocalDateTime timestamp;
    private Integer code;
    private HttpStatus status;
    private T result;

    @Builder
    public RestResponse(final Integer code, final HttpStatus status, final T result) {
        this.timestamp = LocalDateTime.now();
        this.code = code;
        this.status = status;
        this.result = result;
    }
}
