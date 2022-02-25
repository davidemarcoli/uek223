package com.noseryoung.uek223.domain.blogPost;

import com.noseryoung.uek223.domain.appUser.User;
import com.noseryoung.uek223.domain.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity(name = "blog_post")
@Getter
@Setter
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
    @Size(min = 30, max = 65535)
    @Type(type = "text")
    private String content;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "blog_post_category",
            joinColumns = @JoinColumn(name = "blog_post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<Category> category;

    @NotNull
    @CreationTimestamp
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User author;
}