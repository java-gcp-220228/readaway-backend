package com.revature.readawaybackend.service;

import com.revature.readawaybackend.dao.CommentRepository;
import com.revature.readawaybackend.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepo;

    public void addCommentAsReply(String parentId, Comment reply) {
        int id = validateId(parentId);
        Comment parent = commentRepo.findById(id).get();
        reply.setPostTime(new Timestamp(System.currentTimeMillis()));
        parent.getReplies().add(reply);
        commentRepo.save(parent);
    }

    private int validateId(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Illegal Argument provided for path. Path parameter must be an integer.");
        }
    }
}
