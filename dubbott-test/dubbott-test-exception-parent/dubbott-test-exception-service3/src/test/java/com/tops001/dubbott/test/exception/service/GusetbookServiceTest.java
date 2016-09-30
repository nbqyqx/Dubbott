package com.tops001.dubbott.test.exception.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.exception.api.mapped.GuestbookService;
import com.tops001.dubbott.test.exception.model.mapped.Comment;
import com.tops001.dubbott.test.exception.model.mapped.CommentError;
import com.tops001.dubbott.test.exception.model.mapped.GuestbookException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-exception-client.xml")
public class GusetbookServiceTest {

	@Autowired
	private GuestbookService guestbookService;
	
	/**
     * Test the positive workflow where a comment with a message and author is
     * successfully posted to the Guestbook.
     * 
     * @throws Exception
     */
    @Test
    public void testRegularWorkflow() throws Exception {

        // Clear messages first
        Response response = guestbookService.clearMessages();
        assertEquals(204, response.getStatus());

        Comment message = new Comment();
        message.setMessage("Hello World!");
        message.setAuthor("Anonymous");
        response = guestbookService.createMessage(message, null);
        assertEquals(201, response.getStatus());
        
        String newPostURILocation = response.getHeaderString("Location");
        String[] strArray = newPostURILocation.split("/");
        if(strArray.length>0) {
        	String msgId = strArray[strArray.length-1];
        	response = guestbookService.readMessage(msgId);
        	assertEquals(200, response.getStatus());
        }

        Comment c = response.readEntity(Comment.class);
        assertEquals("Anonymous", c.getAuthor());
        assertEquals(1, c.getId().intValue());
        assertEquals("Hello World!", c.getMessage());
        
    }
    
    @Test
    public void testWebApplicationExceptionResponseStatusSetMappedProvider() throws Exception {
    	
    	Response response = guestbookService.createMessage(null, null);
        assertEquals(496, response.getStatus()); // 496表示请求报文实体为空
        
    }
    
    @Test
    public void testWebApplicationException() throws Exception {
    	
    	Response response = guestbookService.createMessage(new Comment(), null);
        assertEquals(500, response.getStatus());
        
    }
    
    /**
     * Tests a method that throws a <code>WebApplicationException</code> with an
     * integer status code.
     * 
     * @throws Exception
     */
    @Test
    public void testWebApplicationExceptionStatusCodeSetMappedProvider() throws Exception {

        Comment comment = new Comment();
        comment.setMessage("Suppose to fail with missing author.");
        Response response = guestbookService.createMessage(comment, null);
        assertEquals(497, response.getStatus());
        
    }
    
    /**
     * Tests a method that throws a <code>WebApplicationException</code> with a
     * Response with an entity (which will not get mapped via an exception
     * mapper).
     * 
     * @throws Exception
     */
    @Test
    public void testWebApplicationExceptionResponseWithEntitySetMappedProvider() throws Exception {

    	Comment comment = new Comment();
        comment.setAuthor("Anonymous");
        Response response = guestbookService.createMessage(comment, null);
        assertEquals(400, response.getStatus());

        CommentError c = response.readEntity(CommentError.class);
        assertEquals("Missing the message in the comment.", c.getErrorMessage());
        
    }
    
    /**
     * Tests a method that throws a <code>WebApplicationException</code> with a
     * Response with no entity (which will not get mapped via an exception
     * mapper).
     * 
     * @throws Exception
     */
    @Test
    public void testWebApplicationExceptionResponseWithNoEntitySetMappedProvider() throws Exception {

    	Comment comment = new Comment();
    	comment.setMessage("throwemptywebappexception");
        comment.setAuthor("Anonymous");
        Response response = guestbookService.createMessage(comment, null);
        assertEquals(491, response.getStatus());
        assertEquals("Some message", response.getHeaderString("throwemptyentitywebappexception"));

        CommentError c = response.readEntity(CommentError.class);
        assertEquals("WebApplicationExceptionMapProvider set message", c.getErrorMessage());
        
    }
    
    /**
     * Tests a method that throws a subclass of
     * <code>WebApplicationException</code> with a Response.
     * 
     * @throws Exception
     */
    @Test
    public void testCustomWebApplicationExceptionMappedProvider() throws Exception {

    	Comment comment = new Comment();
    	comment.setMessage("");
        comment.setAuthor("");
        Response response = guestbookService.createMessage(comment, null);
        assertEquals(498, response.getStatus());

        CommentError c = response.readEntity(CommentError.class);
        assertEquals("Cannot post an invalid message.", c.getErrorMessage());
        
    }
    
    /**
     * Tests a method that throws a runtime exception.
     * 
     * @throws Exception
     */
    @Test
    public void testRuntimeExceptionMappedProvider() throws Exception {

        /*
         * abcd is an invalid ID so a NumberFormatException will be thrown in
         * the resource
         */
    	Response response = guestbookService.deleteMessage("abcd");
        assertEquals(450, response.getStatus());

        CommentError c = response.readEntity(CommentError.class);
        assertEquals("For input string: \"abcd\"", c.getErrorMessage());
        
    }
    
    /**
     * Tests a method that throws a NullPointerException inside a called method.
     * 
     * @throws Exception
     */
    @Test
    public void testNullPointerExceptionMappedProvider() throws Exception {

    	Response response = guestbookService.deleteMessage("10000");
        assertEquals(451, response.getStatus());

        CommentError c = response.readEntity(CommentError.class);
        assertEquals("The comment did not previously exist.", c.getErrorMessage());
        
    }
    
    /**
     * Tests a method that throws an error.
     * 
     * @throws Exception
     */
    @Test
    public void testErrorMappedProvider() throws Exception {

    	Response response = guestbookService.deleteMessage("-99999");
        assertEquals(453, response.getStatus());

        CommentError c = response.readEntity(CommentError.class);
        assertEquals("Simulated error", c.getErrorMessage());
        
    }
    
    /**
     * Tests a method that throws a checked exception.
     * 
     * @throws Exception
     */
    @Test(expected=GuestbookException.class)
    public void testCheckExceptionMappedProvider() throws Exception {

        Comment comment = new Comment();
        comment.setId(0);
        
        Response response = null;
        response = guestbookService.updateMessage(comment, "-99999");
        
        Assert.assertTrue(Optional.of(response).isPresent());
	    assertEquals(454, response.getStatus());
	
	    CommentError c = response.readEntity(CommentError.class);
	    assertEquals("Unexpected ID.", c.getErrorMessage());
	    
    }
	
}
