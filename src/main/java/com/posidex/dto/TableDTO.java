package com.posidex.dto;

import java.util.Arrays;
import java.util.List;

public class TableDTO {
	private String[] headers;
	private List<String[]> records;
	@Override
	public String toString() {
		return "TableDTO [headers=" + Arrays.toString(headers) + ", records=" + records + "]";
	}
	public String[] getHeaders() {
		return headers;
	}
	public void setHeaders(String[] headers) {
		this.headers = headers;
	}
	public List<String[]> getRecords() {
		return records;
	}
	public void setRecords(List<String[]> records) {
		this.records = records;
	}
	public TableDTO(String[] headers, List<String[]> records) {
		super();
		this.headers = headers;
		this.records = records;
	}
	public TableDTO() {
		super();
	}
	
}
