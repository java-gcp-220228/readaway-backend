package com.revature.readawaybackend.controller;

import com.revature.readawaybackend.models.Comment;
import com.revature.readawaybackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comments/{comment_id}/replies")
    public ResponseEntity<?> addCommentAsReply(@PathVariable("comment_id") String id, @RequestBody Comment comment, @RequestHeader("Authorization") String headerValue) {
        String jwt = headerValue.split(" ")[1];
        commentService.addCommentAsReply(id, comment);
        return ResponseEntity.ok("");
    }
}
