package com.tops001.dubbott.test.exception.service.nomapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.exception.api.nomapper.GuestbookService;
import com.tops001.dubbott.test.exception.model.nomapper.Comment;
import com.tops001.dubbott.test.exception.model.nomapper.CommentError;
import com.tops001.dubbott.test.exception.model.nomapper.GuestbookDatabase;
import com.tops001.dubbott.test.exception.model.nomapper.GuestbookException;

@Service
public class GuestbookServiceImpl implements GuestbookService {

	/**
	 * Adds a new message to the database.
	 * 
	 * @return HTTP status 200
	 */
	public Response createMessage(Comment aMessage, UriInfo uriInfo) {
		if (aMessage == null) {
			WebApplicationException webAppException = new WebApplicationException(Status.BAD_REQUEST);
			throw webAppException;
		}

		if (aMessage.getMessage() == null && aMessage.getAuthor() == null) {
			throw new WebApplicationException();
		}

		if (aMessage.getMessage() == null) {
			CommentError error = new CommentError();
			error.setErrorMessage("Missing the message in the comment.");
			Response malformedCommentResponse = Response.status(Status.BAD_REQUEST).entity(error).type("text/xml")
					.build();
			WebApplicationException webAppException = new WebApplicationException(malformedCommentResponse);
			throw webAppException;
		}

		if (aMessage.getAuthor() == null) {
			WebApplicationException webAppException = new WebApplicationException(499);
			throw webAppException;
		}

		if ("".equals(aMessage.getMessage())) {
			throw new MyWebAppException(498);
		}

		/*
		 * Set the message id to a server decided message, even if the client
		 * set it.
		 */
		int id = GuestbookDatabase.getGuestbook().getAndIncrementCounter();
		aMessage.setId(id);

		GuestbookDatabase.getGuestbook().storeComment(aMessage);
		try {
			return Response.created(new URI(uriInfo.getAbsolutePath() + "/" + aMessage.getId())).entity(aMessage)
					.type(MediaType.TEXT_XML).build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Response updateMessage(Comment aMessage, String msgId) throws GuestbookException {
		/*
		 * If no message data was sent, then return the null request.
		 */
		if (aMessage == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		if (aMessage.getId() == null || !aMessage.getId().equals(msgId)) {
			throw new GuestbookException("Unexpected ID.");
		}

		Comment existingComment = GuestbookDatabase.getGuestbook().getComment(Integer.valueOf(msgId));
		if (existingComment == null) {
			throw new GuestbookException("Cannot find existing comment to update.");
		}
		GuestbookDatabase.getGuestbook().storeComment(aMessage);
		return Response.ok(aMessage).build();
	}

	public Response readMessage(String msgId) {
		Comment msg = GuestbookDatabase.getGuestbook().getComment(Integer.valueOf(msgId));
		if (msg == null) {
			return Response.status(404).build();
		}

		return Response.ok(msg).build();
	}

	public Response deleteMessage(String msgId) {
		// NumberFormatException thrown here, most likely, when
		// ExceptionMappersTest.testRuntimeExceptionNoMappingProvider() runs
		GuestbookDatabase.getGuestbook().deleteComment(Integer.valueOf(msgId));
		return Response.noContent().build();
	}

	public Response clearMessages() {
		Collection<Integer> keys = GuestbookDatabase.getGuestbook().getCommentKeys();
		for (Integer k : keys) {
			GuestbookDatabase.getGuestbook().deleteComment(k);
		}
		GuestbookDatabase.getGuestbook().resetCounter();
		return Response.noContent().build();
	}

}
