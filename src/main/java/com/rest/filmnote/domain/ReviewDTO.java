package com.rest.filmnote.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private int movieId;
    private String title;
    private String content;
    private int password;

}
