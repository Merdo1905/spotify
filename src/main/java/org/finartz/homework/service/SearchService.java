package org.finartz.homework.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.finartz.homework.model.TrackModel;
import org.finartz.homework.utils.GsonUtils;
import org.finartz.homework.web.dto.SearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SearchResult;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.SearchItemRequest;

/**
 * @author Mert
 *
 */
@Service
public class SearchService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

	private static Map<String, String> cacheMap = new HashMap<String, String>();

	@Cacheable("search")
	public String search(SearchDTO searchDTO) {
		String encodedString = DigestUtils.sha1Hex(searchDTO.toString());
		LOGGER.debug("Entering Search Service");
		SearchResult searchResult = null;
		if (cacheMap.containsKey(encodedString)) {
			return cacheMap.get(encodedString);
		}
		try {

			LOGGER.info("Setting Access Token");
			SpotifyApi spotifyApi = new SpotifyApi.Builder().setAccessToken(searchDTO.getAccessToken()).build();
			SearchItemRequest searchItemRequest = spotifyApi.searchItem(searchDTO.getQuery(), searchDTO.getType())
					.market(CountryCode.TR).limit(searchDTO.getLimit()).offset(searchDTO.getOffSet()).build();
			searchResult = searchItemRequest.execute();
		} catch (IOException | NullPointerException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (SpotifyWebApiException e) {
			LOGGER.error(e.getMessage(), e);
			return "ERR401";
		}
		cacheMap.put(encodedString, GsonUtils.jSonify(searchResult));
		return GsonUtils.jSonify(searchResult);

	}

	/* For thymeleaf example */
	public List<TrackModel> getTracks(SearchDTO searchDTO) {
		SpotifyApi spotifyApi = new SpotifyApi.Builder().setAccessToken(searchDTO.getAccessToken()).build();
		SearchItemRequest searchItemRequest = spotifyApi.searchItem(searchDTO.getQuery(), searchDTO.getType())
				.market(CountryCode.TR).limit(searchDTO.getLimit()).offset(searchDTO.getOffSet()).build();

		Track[] tracksArray = null;
		try {
			SearchResult searchResult = searchItemRequest.execute();
			Paging<Track> pagingList = searchResult.getTracks();
			tracksArray = pagingList.getItems();
		} catch (SpotifyWebApiException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fillTrackModelList(tracksArray);

	}

	/*
	 * Extract necessary fields which will be returned to front-end and return
	 * created list as TrackModelList
	 */
	private List<TrackModel> fillTrackModelList(Track[] tracksArray) {
		List<TrackModel> trackModelList = new ArrayList<TrackModel>();
		for (Track track : tracksArray) {
			TrackModel trackModel = new TrackModel();
			trackModel.setAlbumName(track.getAlbum().getName());
			trackModel.setTrackName(track.getName());
			trackModel.setArtistName(track.getArtists()[0].getName());
			trackModel.setAlbumImageUrl(track.getAlbum().getImages()[0].getUrl());
			trackModelList.add(trackModel);
		}
		return trackModelList;
	}
}
