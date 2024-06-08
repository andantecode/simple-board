package com.example.clone_simple_board.common;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {

    private Integer page;
    private Integer size;

    private Integer currentElements;
    private Long totalElements;

    private Integer totalPage;
}
