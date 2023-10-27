package com.dogoo.miniblogs.handler;

import com.dogoo.miniblogs.advice.BadRequestExceptionHandler;
import com.dogoo.miniblogs.exception.BadRequestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestBadRequestExceptionHandler {

    @InjectMocks
    BadRequestExceptionHandler badRequestExceptionHandler;

    @Mock
    BadRequestException ex;
    @Test
    public void testHandleBadRequestException() {
        assertEquals(400, badRequestExceptionHandler.handleBadRequestException(ex).getStatusCodeValue());
    }
}
