package com.sds.weatherstory.domain;

import lombok.Data;

@Data
public class Likey {
    private int like_idx;
    private Story story;
    private Member member;
}
