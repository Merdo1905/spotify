package org.finartz.homework.model;

public class TrackModel {

	private String albumImageUrl;

	private String albumName;

	private String trackName;

	private String artistName;

	public String getAlbumImageUrl() {
		return albumImageUrl;
	}

	public void setAlbumImageUrl(String albumImageUrl) {
		this.albumImageUrl = albumImageUrl;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	@Override
	public String toString() {
		return "TrackModel [albumImageUrl=" + albumImageUrl + ", albumName=" + albumName + ", trackName=" + trackName
				+ ", artistName=" + artistName + "]";
	}

}
