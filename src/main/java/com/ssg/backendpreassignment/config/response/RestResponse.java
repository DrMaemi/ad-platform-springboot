package com.ssg.backendpreassignment.config.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * RESTful API의 Response 객체 구조를 정의하는 클래스
 * timestamp, code, status, result 필드를 가짐
 * timestamp는 LocalDateTime 타입의 객체가 생성된 시간
 * @param <T>
 */
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
