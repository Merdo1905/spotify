package org.finartz.homework.service;

import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.finartz.homework.utils.GsonUtils;
import org.springframework.stereotype.Service;

import com.wrapper.spotify.enums.ModelObjectType;

@Service
public class EnumService {

	public String getEnumList(Class<?> clazz) {
		List<ModelObjectType> enumValues = EnumUtils.getEnumList(ModelObjectType.class);
		String json = GsonUtils.jSonify(enumValues);

		return json;

	}

}
