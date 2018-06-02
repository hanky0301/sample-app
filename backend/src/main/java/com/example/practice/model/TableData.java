package com.example.practice.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TableData {
	private int total;
	private int per_page = 6;
	private int current_page;
	private int last_page;
	private String next_page_url;
	private String prev_page_url;
	private int from;
	private int to;
	private List<Job> data;

	public TableData() {
		data = new ArrayList<Job>();
	}

	public void addData(Job job) {
		data.add(job);
		total++;
	}
}
