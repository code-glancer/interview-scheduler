package com.code.glancer.interview.scheduler.exception.handler;

import com.code.glancer.interview.scheduler.dto.ResponseDto;
import com.code.glancer.interview.scheduler.exception.ResourceNotFoundException;
import com.code.glancer.interview.scheduler.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class InterviewSchedulerExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ResponseDto> notFoundException(ResourceNotFoundException notFoundException) {
        log.info( "No resource found");
        return new ResponseEntity<>( ResponseUtil.getFailureResponse( notFoundException.getMessage() ), notFoundException.getCode() );
    }


}
