package org.finartz.homework.web.utils;

import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;

import com.wrapper.spotify.enums.ModelObjectType;

/**
 * @author Mert
 *
 */
public class SearchValidationUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchValidationUtils.class);

	public static void checktType(Errors errors, String type) {

		LOGGER.debug("Type validation Started");
		if (!EnumUtils.isValidEnum(ModelObjectType.class, type)) {
			errors.reject("ERRS001", "Type is not supported");
		}
		LOGGER.debug("Type validation Ended");
	}

}
