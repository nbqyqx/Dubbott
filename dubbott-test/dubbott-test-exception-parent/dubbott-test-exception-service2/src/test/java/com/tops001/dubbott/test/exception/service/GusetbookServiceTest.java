package com.tops001.dubbott.test.exception.service;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.exception.api.nomapper.GuestbookService;
import com.tops001.dubbott.test.exception.model.nomapper.Comment;
import com.tops001.dubbott.test.exception.model.nomapper.CommentError;

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
    public void testRegularWorkflow_noMapped() throws Exception {
    	
        /* FIXME: this is not a repeatable test */
        // WR: What's this mean???
    	Response response = guestbookService.clearMessages();
    	assertEquals(204, response.getStatus());
    	
    	Comment comment = new Comment();
    	comment.setMessage("Hello World!");
    	comment.setAuthor("Anonymous");
        response = guestbookService.createMessage(comment, null);
        assertEquals(201, response.getStatus());
        
        String newPostURILocation = response.getHeaderString("Location");
        String[] strArray = newPostURILocation.split("/");
        if(strArray.length>0) {
        	String msgId = strArray[strArray.length-1];
        	response = guestbookService.readMessage(msgId);
        	assertEquals(200, response.getStatus());
        }

        // There are two sets of Comment* classes, in *mapped and *nomapper packages.
        // Unless there's an error, just use the classes in mapped, even for the 
        // "nomapper" tests, because they're pretty much identical.
        Comment c = response.readEntity(Comment.class);
        assertEquals("Anonymous", c.getAuthor());
        assertEquals(1, c.getId().intValue());
        assertEquals("Hello World!", c.getMessage());
        
    }
	
	/**
     * Tests a method that throws an emptily constructed
     * <code>WebApplicationException</code>.
     * 
     * @throws Exception
     */
    @Test
    public void testWebApplicationExceptionDefaultNoMappingProvider() throws Exception {

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
    public void testWebApplicationExceptionStatusCodeSetNoMappingProvider() throws Exception {

        Comment comment = new Comment();
        comment.setMessage("Suppose to fail with missing author.");
        Response response = guestbookService.createMessage(comment, null);
        assertEquals(499, response.getStatus());
        
    }
    
    /**
     * Tests a method that throws a <code>WebApplicationException</code> with a
     * Response.Status set.
     * 
     * @throws Exception
     */
    @Test
    public void testWebApplicationExceptionResponseStatusSetNoMappingProvider() throws Exception {

        Response response = guestbookService.createMessage(null, null);
        assertEquals(400, response.getStatus());
        assertEquals("", response.readEntity(String.class));
        
    }
    
    /**
     * Tests a method that throws a <code>WebApplicationException</code> with a
     * Response.
     * 
     * @throws Exception
     */
    @Test
    public void testWebApplicationExceptionResponseSetNoMappingProvider() throws Exception {

    	Comment comment = new Comment();
        comment.setAuthor("Anonymous");
        Response response = guestbookService.createMessage(comment, null);
        assertEquals(400, response.getStatus());
        CommentError c = response.readEntity(CommentError.class);
        assertEquals("Missing the message in the comment.", c.getErrorMessage());
        
    }
    
    /**
     * Tests a method that throws a subclass of
     * <code>WebApplicationException</code> with a Response.
     * 
     * @throws Exception
     */
    @Test
    public void testCustomWebApplicationExceptionNoMappingProvider() throws Exception {

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
    public void testRuntimeExceptionNoMappingProvider() throws Exception {

        // nomapped.Guestbook.deleteMessage() takes in a String param and 
        // tries to convert that an Integer; but that results in a 
        // NumberFormatException because "abcd" is invalid argument to 
        // Integer.valueOf()--this is on purpose, so don't be alarmed to see
        // that in log files.
    	/*
         * abcd is an invalid ID so a NumberFormatException will be thrown in
         * the resource
         */
    	Response response = guestbookService.deleteMessage("abcd");
        assertEquals(500, response.getStatus());

    }
    
    /**
     * Tests a method that throws a NullPointerException inside a called method.
     * 
     * @throws Exception
     */
    @Test
    public void testNullPointerExceptionNoMappingProvider() throws Exception {

    	Response response = guestbookService.deleteMessage("10000");
        assertEquals(500, response.getStatus());
        
    }
    
    /**
     * Tests a method that throws an error.
     * 
     * @throws Exception
     */
    @Test
    public void testErrorNoMappingProvider() throws Exception {

    	Response response = guestbookService.deleteMessage("-99999");
        assertEquals(500, response.getStatus());
        
    }
    
    /**
     * Tests a method that throws a checked exception.
     * 
     * @throws Exception
     */
    @Test(expected=InternalServerErrorException.class)
    public void testCheckExceptionNoMappingProvider() throws Exception {

    	Comment comment = new Comment();
        comment.setId(0);
        Response response = null;
        response = guestbookService.updateMessage(comment, "-99999");
        assertEquals(500, response.getStatus());
        
    }

}
