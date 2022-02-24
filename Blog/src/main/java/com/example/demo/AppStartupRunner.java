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
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;


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
        Authority read_auth = new Authority(null, "READ");
        authorityRepository.save(read_auth);
        Authority write_auth = new Authority(null, "WRITE");
        authorityRepository.save(write_auth);
        Authority userManage_auth = new Authority(null, "CAN_MANAGE_USERS");
        authorityRepository.save(userManage_auth);

//        Roles
        Role default_role = new Role(null, "DEFAULT", Arrays.asList(read_auth));
        roleRepository.save(default_role);
        Role admin_role = new Role(null, "ADMIN", Arrays.asList(read_auth, write_auth, userManage_auth));
        roleRepository.save(admin_role);
        Role author_role = new Role(null, "AUTHOR", Arrays.asList(read_auth, write_auth));
        roleRepository.save(author_role);

        User default_user = new User(null, "james", "james.bond@mi6.com", "bond", Set.of(default_role));
        userService.saveUser(default_user);
        User andrin_user = new User(null, "andrin", "andrin.klarer@gmail.com", "klarer", Set.of(admin_role));
        userService.saveUser(andrin_user);
        User davide_user = new User(null, "davide", "davide@marcoli.ch", "marcoli", Set.of(author_role));
        userService.saveUser(davide_user);

        BlogPost blogPost = new BlogPost(null, "Climate Change", "Climate Change get's worse, here is what to do:", "Environment", andrin_user);
        blogPostService.create(blogPost);
        BlogPost blogPost2 = new BlogPost(null, "30km/h in the city", "What are the benefits and the disbenefits of this drastic change?", "traffic", davide_user);
        blogPostService.create(blogPost2);

        userService.addRoleToUser(default_user.getUsername(), default_role.getName());
        userService.addRoleToUser(andrin_user.getUsername(), admin_role.getName());
        userService.addRoleToUser(davide_user.getUsername(), default_role.getName());
    }
}

