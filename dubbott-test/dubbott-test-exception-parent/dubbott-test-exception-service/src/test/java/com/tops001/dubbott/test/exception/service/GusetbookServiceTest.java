package com.tops001.dubbott.test.exception.service;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.exception.api.nullconditions.GuestbookService;
import com.tops001.dubbott.test.exception.model.nullconditions.GuestbookThrowable;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-exception-client.xml")
public class GusetbookServiceTest {

	@Autowired
	private GuestbookService guestbookService;
	
	/**
     * Tests that an empty constructor constructed
     * <code>WebApplicationException</code> will return status 500 and no
     * response body by default.
     * 
     * @throws Exception
     */
    @Test
    public void testEmptyWebException() throws Exception {
    	
    	Response response = guestbookService.exception();
    	Assert.assertEquals(500, response.getStatus());
        
    }
    
    /**
     * Tests that a <code>WebApplicationException</code> constructed with a
     * cause will return status 500 and no response body by default.
     * 
     * @throws Exception
     */
    @Test
    public void testWebExceptionWithCause() throws Exception {

    	Response response = guestbookService.exceptionWithCause();
    	Assert.assertEquals(500, response.getStatus());
        
    }
    
    /**
     * Tests that a <code>WebApplicationException</code> constructed with a
     * cause and status will return status and no response body by default.
     * 
     * @throws Exception
     */
    @Test
    public void testWebExceptionWithCauseAndStatus() throws Exception {

    	Response response = guestbookService.exceptionWithCauseAndStatus();
        assertEquals(499, response.getStatus());
        
    }
    
    /**
     * Tests that a <code>WebApplicationException</code> constructed with a
     * cause and response will return the Response entity by default.
     * 
     * @throws Exception
     */
    @Test
    public void testWebExceptionWithCauseAndResponse() throws Exception {

    	Response response = guestbookService.exceptionWithCauseAndResponse();
        assertEquals(406, response.getStatus());
    	
    }
    
    /**
     * Tests that a <code>WebApplicationException</code> constructed with a
     * cause and response status will return the response status and empty
     * response body by default.
     * 
     * @throws Exception
     */
    @Test
    public void testWebExceptionWithCauseAndResponseStatus() throws Exception {

    	Response response = guestbookService.exceptionWithCauseAndResponseStatus();
        assertEquals(400, response.getStatus());
        
    }
	
    /**
     * Tests that a <code>ExceptionMapper</code> that returns null should see a
     * HTTP 204 status.
     * 
     * @throws Exception
     */
    //comment out this case out as this is not supported by CXF
//    @Test
    public void testExceptionMapperReturnNull() throws Exception {

    	Response response = guestbookService.exceptionMapperReturnNull();
        assertEquals(204, response.getStatus());

    }
    
    /**
     * Tests that a <code>ExceptionMapper</code> that throws an exception or
     * error should see a HTTP 500 status error and empty response.
     * 
     * @throws Exception
     */
    @Test(expected=InternalServerErrorException.class)
    public void testExceptionMapperThrowsException() throws Exception {

    	Response response = guestbookService.exceptionMapperThrowsException();
        assertEquals(500, response.getStatus());
        
    }
    
    /**
     * Tests that a <code>ExceptionMapper</code> that throws an error should see
     * a HTTP 500 status error and unknown response.
     * 
     * @throws Exception
     */
    @Test(expected=InternalServerErrorException.class)
    public void testExceptionMapperThrowsError() throws Exception {

    	Response response = guestbookService.exceptionMapperThrowsError();
        assertEquals(500, response.getStatus());

    }
    
    /**
     * Tests that a <code>ExceptionMapper</code> can catch a generic Throwable.
     * 
     * @throws Exception
     * @throws GuestbookThrowable 
     */
    @Test(expected=InternalServerErrorException.class)
    public void testExceptionMapperForSpecificThrowable() throws Throwable {

    	Response response = guestbookService.throwableExceptionMapper();
        assertEquals(500, response.getStatus());
        
    }
    

    /**
     * Tests that a Throwable can propagate throughout the code.
     * 
     * @throws Exception
     */
    @Test(expected=InternalServerErrorException.class)
    public void testThrowableCanBeThrown() throws Throwable {

    	Response response = guestbookService.throwThrowable();
        assertEquals(500, response.getStatus());
        
    }
    
}
