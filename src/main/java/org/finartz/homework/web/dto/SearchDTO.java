package org.finartz.homework.web.dto;

/**
 * @author Mert
 *
 */
public class SearchDTO {

	private String query;
	private String type;
	private Integer limit;
	private Integer offSet;
	private String accessToken;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffSet() {
		return offSet;
	}

	public void setOffSet(Integer offSet) {
		this.offSet = offSet;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "SearchDTO [query=" + query + ", type=" + type + ", limit=" + limit + ", offSet=" + offSet
				+ ", accessToken=" + accessToken + "]";
	}
	

}
