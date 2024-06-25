package com.sds.weatherstory.model.member;

import com.sds.weatherstory.domain.Sns;

public interface SnsService {
	public Sns selectByName(String sns_name);
}
