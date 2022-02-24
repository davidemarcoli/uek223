package com.example.demo;

import com.example.demo.domain.appUser.User;
import com.example.demo.domain.appUser.UserService;
import com.example.demo.domain.authority.Authority;
import com.example.demo.domain.authority.AuthorityRepository;
import com.example.demo.domain.blogPost.BlogPost;
import com.example.demo.domain.blogPost.BlogPostService;
import com.example.demo.domain.role.Role;
import com.example.demo.domain.role.RoleRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
@RequiredArgsConstructor
//ApplicationListener used to run commands after startup
class AppStartupRunner implements ApplicationRunner {
    @Autowired
    private final UserService userService;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final BlogPostService blogPostService;
    @Autowired
    private final AuthorityRepository authorityRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
//        RUN YOUR STARTUP CODE HERE
//        e.g. to add a user or role to the DB (only for testing)

//        Authorities


//        Authority canRetrieveAllUsers_auth = new Authority(null, "CAN_RETRIEVE_ALL_USERS");
//        authorityRepository.save(canRetrieveAllUsers_auth);
//        Authority canRetrieveUser_auth = new Authority(null, "CAN_RETRIEVE_USER");
//        authorityRepository.save(canRetrieveUser_auth);
//        Authority canRetrieveOwnUser_auth = new Authority(null, "CAN_RETRIEVE_OWN_USER");
//        authorityRepository.save(canRetrieveOwnUser_auth);
//        Authority canCreateUser_auth = new Authority(null, "CAN_CREATE_USER");
//        authorityRepository.save(canCreateUser_auth);
//        Authority canManageUser_auth = new Authority(null, "CAN_MANAGE_USER");
//        authorityRepository.save(canManageUser_auth);
//        Authority canManageOwnUser_auth = new Authority(null, "CAN_MANAGE_OWN_USER");
//        authorityRepository.save(canManageOwnUser_auth);
//
//        Authority canManageBlog_auth = new Authority(null, "CAN_MANAGE_BLOG");
//        authorityRepository.save(canManageBlog_auth);
//        Authority canManageOwnBlog_auth = new Authority(null, "CAN_MANAGE_OWN_BLOG");
//        authorityRepository.save(canManageOwnBlog_auth);
//        Authority canRetrieveAllBlogs_auth = new Authority(null, "CAN_RETRIEVE_ALL_BLOGS");
//        authorityRepository.save(canRetrieveAllBlogs_auth);
//        Authority canRetrieveBlog_auth = new Authority(null, "CAN_RETRIEVE_BLOG");
//        authorityRepository.save(canRetrieveBlog_auth);
//        Authority canCreateBlog_auth = new Authority(null, "CAN_CREATE_BLOG");
//        authorityRepository.save(canCreateBlog_auth);
//
////        Roles
//        Role default_role = new Role(null, "DEFAULT", Arrays.asList(canRetrieveBlog_auth, canRetrieveAllBlogs_auth, canCreateBlog_auth, canManageOwnUser_auth));
//        roleRepository.save(default_role);
//        Role admin_role = new Role(null, "ADMIN", authorityRepository.findAll());
//        roleRepository.save(admin_role);
//        Role author_role = new Role(null, "AUTHOR", Stream.concat(authorityRepository.findOwnAuthorities().stream(), Stream.of(canRetrieveOwnUser_auth, canRetrieveBlog_auth, canRetrieveAllBlogs_auth, canCreateBlog_auth)).collect(Collectors.toList()));
//        roleRepository.save(author_role);
//
//        User default_user = new User(null, "james", "james.bond@mi6.com", "bond", Set.of(default_role));
//        userService.saveUser(default_user);
//        User andrin_user = new User(null, "andrin", "andrin.klarer@gmail.com", "klarer", Set.of(admin_role));
//        userService.saveUser(andrin_user);
//        User davide_user = new User(null, "davide", "davide@marcoli.ch", "marcoli", Set.of(author_role));
//        userService.saveUser(davide_user);
//
//        BlogPost blogPost = new BlogPost(null, "Climate Change", "Climate Change get's worse, here is what to do:", "Environment", andrin_user);
//        blogPostService.create(blogPost);
//        BlogPost blogPost2 = new BlogPost(null, "30km/h in the city", "What are the benefits and the disbenefits of this drastic change?", "traffic", davide_user);
//        blogPostService.create(blogPost2);
//
//        userService.addRoleToUser(default_user.getUsername(), default_role.getName());
//        userService.addRoleToUser(andrin_user.getUsername(), admin_role.getName());
//        userService.addRoleToUser(davide_user.getUsername(), author_role.getName());

    }
}

