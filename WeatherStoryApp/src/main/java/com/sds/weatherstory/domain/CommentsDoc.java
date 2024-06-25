package com.sds.weatherstory.domain;

import lombok.Data;

@Data
public class CommentsDoc {
	private int member_idx;
	private int food_category_idx;
	private String comments;
	private double score;
}