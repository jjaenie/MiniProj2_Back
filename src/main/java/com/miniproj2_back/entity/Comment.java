package com.miniproj2_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
