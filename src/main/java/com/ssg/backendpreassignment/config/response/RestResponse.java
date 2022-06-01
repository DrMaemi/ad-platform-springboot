package com.ssg.backendpreassignment.config.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class RestResponse<T> {
    private HttpStatus status;
    private T result;

    @Builder
    public RestResponse(final HttpStatus status, final T result) {
        this.status = status;
        this.result = result;
    }
}
