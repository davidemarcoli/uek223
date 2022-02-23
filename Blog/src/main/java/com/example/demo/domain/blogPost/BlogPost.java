package com.example.demo.domain.blogPost;

import com.example.demo.domain.appUser.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "blog_post")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String content;
    private String category;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
}
