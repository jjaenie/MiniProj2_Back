package com.miniproj2_back.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "email")
    //@Email
    private String email;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "password")
    private String password;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Follow> following;

    @OneToMany(mappedBy = "following",cascade = CascadeType.ALL)
    Set<Follow> followers;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Post> posts;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Like> likes;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<UserImage> images;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Comment>comments;

}
