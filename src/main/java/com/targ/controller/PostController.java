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

import com.targ.domain.PostDO;
import com.targ.domain.UserDO;
import com.targ.model.Post;
import com.targ.model.User;
import com.targ.service.PostService;
import com.targ.service.UserService;

@Controller
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.GET)
    public String newPost(UserDO user,
                          Model model) {

        Optional<User> users = userService.findByUsername(user.getUsername());
        ModelMapper modelMapper = new ModelMapper();
        if (users.isPresent()) {
            PostDO post = new PostDO();
            User use=  users.get();
            UserDO userDO =  modelMapper.map(use, UserDO.class);
            post.setUser(userDO);

            model.addAttribute("post", post);

            return "/postForm";

        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.POST)
    public String createNewPost(@Valid PostDO post,
                                BindingResult bindingResult) {
    	
    	ModelMapper  mapper = new ModelMapper();
    	
        if (bindingResult.hasErrors()) {
            return "/postForm";
        } else {
        	Post postSer = mapper.map(post, Post.class);
            postService.save(postSer);
            return "redirect:/blog/" + post.getUser().getUsername();
        }
    }

    @RequestMapping(value = "/editPost/{id}", method = RequestMethod.GET)
    public String editPostWithId(@PathVariable Long id,
                                 User user,
                                 Model model) {

        Optional<Post> optionalPost = postService.findForId(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            if (user != null ) {
                model.addAttribute("post", post);
                return "/postForm";
            } else {
                return "/403";
            }

        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String getPostWithId(@PathVariable Long id,
                                UserDO user,
                                Model model) {

        Optional<Post> optionalPost = postService.findForId(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            model.addAttribute("post", post);
            if (user != null) {
                model.addAttribute("username", user.getUsername());
            }

            return "/post";

        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public String deletePostWithId(@PathVariable Long id,
                                   User principal) {

        Optional<Post> optionalPost = postService.findForId(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            if (principal != null) {
                postService.delete(post);
                return "redirect:/home";
            } else {
                return "/403";
            }

        } else {
            return "/error";
        }
    }

//    private boolean isPrincipalOwnerOfPost(Principal principal, Post post) {
//        return principal != null && principal.getName().equals(post.getUser().getUsername());
//    }
}
