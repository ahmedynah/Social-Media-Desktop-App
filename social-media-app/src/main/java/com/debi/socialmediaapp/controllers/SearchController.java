package com.debi.socialmediaapp.controllers;
import com.debi.socialmediaapp.models.Post;
import com.debi.socialmediaapp.models.User;
import com.debi.socialmediaapp.repositories.PostRepository;
import com.debi.socialmediaapp.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class SearchController {
    List<User> allUsers;
    List<Post> allPosts;
    //TODO: Link with the search UI
    public void initialize(){
        UserRepository userRepository = new UserRepository();
        allUsers = userRepository.getAllUsers();
        PostRepository postRepository = new PostRepository();
        allPosts = postRepository.getAllPosts();
    }

    public List<User> findUsers(String key){
        List<User> retList = new ArrayList<User>();
        String lowerCaseKey = key.toLowerCase();

        for (User u:allUsers){
            String lowerCaseFirstName = u.getFirstName().toLowerCase();
            String lowerCaseLastName = u.getLastName().toLowerCase();
            if (lowerCaseFirstName.contains(lowerCaseKey) || lowerCaseLastName.contains(lowerCaseKey)) {
                retList.add(u);
            }
        }
        return retList;
    }

    public List<Post> findPosts(String key){
        List<Post> retList = new ArrayList<Post>();
        String lowerCaseKey = key.toLowerCase();
        for (Post p:allPosts){
            String lowerCaseText = p.getText().toLowerCase();
            if(lowerCaseText.contains(lowerCaseKey))
                retList.add(p);
        }
        return retList;
    }
}
