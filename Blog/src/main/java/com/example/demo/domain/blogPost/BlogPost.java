package com.example.demo.domain.blogPost;

import com.example.demo.domain.appUser.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity(name = "blog_post")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Size(min = 10, max = 100)
    private String title;

    @NotNull
    @Size(min = 10, max = 65535)
    @Type(type = "text")
    private String content;

    @NotNull
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User author;
}
