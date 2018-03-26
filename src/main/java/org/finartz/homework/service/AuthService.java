package org.finartz.homework.service;

import java.io.IOException;

import org.finartz.homework.utils.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

/**
 * @author Mert
 *
 */
@Service
public class AuthService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

	@Value("${homeWork.spotify.clientId}")
	private String clientId = "";

	@Value("${homeWork.spotify.clientSecret}")
	private String clientSecret = "";

	private String accessToken = "";

	//TODO Token active check will be added
	public String getAccessToken() {
		return this.accessToken;
	}

	public String createAccessToken() {
		ClientCredentials clientCredentials = null;
		try {
			SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(clientId).setClientSecret(clientSecret)
					.build();
			ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();

			clientCredentials = clientCredentialsRequest.execute();
			accessToken = clientCredentials.getAccessToken();
			LOGGER.debug("Expires in: {0}", clientCredentials.getExpiresIn());
		} catch (IOException | SpotifyWebApiException | NullPointerException e) {
			LOGGER.error(e.getMessage(), e);
			return "";
		}
		return GsonUtils.jSonify(clientCredentials);
	}

}
