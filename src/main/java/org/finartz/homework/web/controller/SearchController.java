package org.finartz.homework.web.controller;

import javax.validation.Valid;

import org.finartz.homework.service.SearchService;
import org.finartz.homework.web.dto.SearchDTO;
import org.finartz.homework.web.validator.SearchValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Mert
 *
 */
@RestController
@RequestMapping("/rest/api")
@Api(value = "Search Controller")
public class SearchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private SearchValidator searchValidator;

	@Autowired
	private SearchService searchService;

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		binder.addValidators(searchValidator);
	}

	@ApiOperation(value = "Returns the search result as json")
	@ApiResponses(value = { @ApiResponse(code = 401, message = "Invalid Access Token"),
			@ApiResponse(code = 200, message = "Successful Search"),
			@ApiResponse(code = 204, message = "Successful Hello World"),
			@ApiResponse(code = 400, message = "Validation failed") })
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<?> search(@Valid @RequestBody SearchDTO searchDTO, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			LOGGER.error("Validation failed: ", result.getAllErrors().toString());
			return new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		LOGGER.debug("Validation succeed. Continuing to Search Service ");
		String json = searchService.search(searchDTO);
		if (json.isEmpty()) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		if (json.contains("ERR401")) {
			return new ResponseEntity<String>("Invalid Access Token", HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

}
