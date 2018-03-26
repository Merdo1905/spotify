package org.finartz.homework.web.controller;

import org.finartz.homework.service.EnumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wrapper.spotify.enums.ModelObjectType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Mert
 *
 */

@RestController
@RequestMapping(value = "/rest/enum")
@Api(value = "Enumeration Controller")
public class EnumController {

	@Autowired
	private EnumService enumService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EnumController.class);

	@ApiOperation(value = "Returns ModelObjectType enums as Json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved enum") })
	@GetMapping("/getModelObjectTypes")
	public ResponseEntity<String> getModelObjectType() {
		LOGGER.debug("Getting Enum List");
		return new ResponseEntity<String>(enumService.getEnumList(ModelObjectType.class), HttpStatus.OK);
	}

}
