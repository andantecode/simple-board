package com.example.clone_simple_board.common;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Api<T> {

    private T body;

    private Pagination pagination;
}
