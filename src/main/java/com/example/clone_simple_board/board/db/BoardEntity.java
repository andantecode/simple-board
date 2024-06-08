package com.example.clone_simple_board.board.db;

import com.example.clone_simple_board.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boardName;

    private String status;

    @OneToMany(mappedBy = "board")
    @Builder.Default
    @SQLRestriction("status = 'REGISTERED'")
    @OrderBy("id desc")
    private List<PostEntity> postList = List.of();

}
