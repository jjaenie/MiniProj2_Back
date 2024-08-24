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
@Table(name = "likes")
public class Like {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private  int id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;
}
