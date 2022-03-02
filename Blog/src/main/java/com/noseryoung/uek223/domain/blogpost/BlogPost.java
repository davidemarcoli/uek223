package com.noseryoung.uek223.domain.blogpost;

import com.noseryoung.uek223.domain.appuser.User;
import com.noseryoung.uek223.domain.category.Category;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity(name = "blog_post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "blog_post_category",
            joinColumns = @JoinColumn(name = "blog_post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<Category> category;

//    @CreationTimestamp
    private Date createdAt;

//    @UpdateTimestamp
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User author;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
