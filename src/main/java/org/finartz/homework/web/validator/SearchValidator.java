package org.finartz.homework.web.validator;

import org.finartz.homework.web.dto.SearchDTO;
import org.finartz.homework.web.utils.SearchValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Mert
 *
 */
@Component
public class SearchValidator implements Validator {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		LOGGER.debug("Checking DTO Support");
		return SearchDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		SearchDTO searchDTO = (SearchDTO) target;

		LOGGER.debug("Empty or Whitespace validaiton Started");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "query", "Query cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "Type cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "offSet", "offSet cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "limit", "limit cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accessToken", "accessToken cannot be empty");
		LOGGER.debug("Empty or Whitespace validaiton Ended");
		SearchValidationUtils.checktType(errors, searchDTO.getType());
		LOGGER.debug("Exitting Validation");
	}

}
