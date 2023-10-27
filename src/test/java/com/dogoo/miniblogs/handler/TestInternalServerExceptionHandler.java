package com.dogoo.miniblogs.handler;

import com.dogoo.miniblogs.advice.InternalServerExceptionHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpServerErrorException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestInternalServerExceptionHandler {

    @InjectMocks
    InternalServerExceptionHandler internalServerExceptionHandler;

    @Mock
    Exception ex;

    @Test
    public void testHandleInternalServerException() {
        assertEquals(500, internalServerExceptionHandler.handleInternalServerException(ex).getStatusCodeValue());
    }
}
