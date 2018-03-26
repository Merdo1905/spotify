package org.finartz.homework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * @author Mert
 *
 */
public class GsonUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(GsonUtils.class);
	private static Gson gson = new Gson();

	public static String jSonify(Object object) {
		LOGGER.debug("Converting object to json");
		return gson.toJson(object);

	}

}
