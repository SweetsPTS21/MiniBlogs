package com.dogoo.miniblogs.handler;

import com.dogoo.miniblogs.advice.NotFoundExceptionHandler;
import com.dogoo.miniblogs.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestNotFoundExceptionHandler {

    @InjectMocks
    NotFoundExceptionHandler notFoundExceptionHandler;

    @Mock
    NotFoundException ex;

    @Test
    public void testHandleNotFoundException() {
        assertEquals(404, notFoundExceptionHandler.handleNotFoundException(ex).getStatusCodeValue());
    }
}
