package com.targ.controller;

 
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.targ.domain.CommentDO;
import com.targ.domain.UserDO;
import com.targ.model.Comment;
import com.targ.model.Post;
import com.targ.model.User;
import com.targ.service.CommentService;
import com.targ.service.PostService;
import com.targ.service.UserService;

@Controller
public class CommentController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public CommentController(PostService postService, UserService userService, CommentService commentService) {
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @RequestMapping(value = "/createComment", method = RequestMethod.POST)
    public String createNewPost(@Valid CommentDO comment,
                                BindingResult bindingResult) {
    	ModelMapper mapper =new ModelMapper();
    	
        if (bindingResult.hasErrors()) {
            return "/commentForm";

        } else {
        	Comment commentServeice =  mapper.map(comment, Comment.class);
            commentService.save(commentServeice);
            return "redirect:/post/" + comment.getPost().getId();
        }
    }

    @RequestMapping(value = "/commentPost/{id}", method = RequestMethod.GET)
    public String commentPostWithId(@PathVariable Long id,
                                    UserDO user,
                                    Model model) {
    	 

        Optional<Post> post = postService.findForId(id);

        if (post.isPresent()) {
            Optional<User> use = userService.findByUsername(user.getUsername());

            if (use.isPresent()) {
                Comment comment = new Comment();
                
                comment.setUser(use.get());
                comment.setPost(post.get());

                model.addAttribute("comment", comment);

                return "/commentForm";

            } else {
                return "/error";
            }

        } else {
            return "/error";
        }
    }

}
