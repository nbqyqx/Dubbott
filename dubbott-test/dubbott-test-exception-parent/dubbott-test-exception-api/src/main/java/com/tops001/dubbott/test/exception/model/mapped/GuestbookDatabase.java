package com.tops001.dubbott.test.exception.model.mapped;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple in-memory data store.
 */
public class GuestbookDatabase {

    private static GuestbookDatabase guestbook = new GuestbookDatabase();

    private final Map<Integer, Comment> comments =
                                                   Collections
                                                                   .synchronizedMap(new HashMap<Integer, Comment>());

    private int counter = 0;

    private GuestbookDatabase() {
        /* private singleton constructor */
    }

    public static GuestbookDatabase getGuestbook() {
        return guestbook;
    }

    public Comment getComment(Integer id) {
        return comments.get(id);
    }

    public void storeComment(Comment c) {
        comments.put(c.getId(), c);
    }

    public Collection<Integer> getCommentKeys() {
        return comments.keySet();
    }

    public void deleteComment(Integer id) {
        if (id == -99999) {
            throw new GuestbookError("Simulated error");
        }

        if (comments.remove(id) == null) {
            throw new NullPointerException("The comment did not previously exist.");
        }
    }

    public synchronized int getAndIncrementCounter() {
        ++counter;
        return counter;
    }

    public synchronized int resetCounter() {
        counter = 0;
        return counter;
    }
}
