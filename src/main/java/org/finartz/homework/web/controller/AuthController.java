package org.finartz.homework.web.controller;

import org.finartz.homework.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/rest/auth")
@Api(value = "Authentication Controller")
public class AuthController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthService authService;

	/**
	 * Retrieves the access token if there is an active one If not return an ERROR
	 */
	@ApiOperation(value = "Returns the currently available access token")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Search"),
			@ApiResponse(code = 204, message = "No available token ATM") })
	@GetMapping("/getToken")
	public ResponseEntity<String> getToken() {
		if (authService.getAccessToken().isEmpty()) {
			LOGGER.error("No available token ATM");
			return new ResponseEntity<String>("No available Token ATM: ", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(authService.getAccessToken(), HttpStatus.OK);
	}

	/**
	 * Create and return access token If something went wrong return an ERROR
	 */
	@ApiOperation(value = "Creates Spotify access token and returns is")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Search"),
			@ApiResponse(code = 500, message = "Something went wrong. Please try again...") })
	@GetMapping("/createToken")
	public ResponseEntity<String> createToken() {
		String accessToken = authService.createAccessToken();
		if (accessToken.isEmpty()) {
			return new ResponseEntity<String>(
					"Something went wrong. Please try again. If the problem continues, contact tos system admin ",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(accessToken, HttpStatus.OK);
	}
}
