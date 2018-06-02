package com.example.practice.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.practice.model.Job;
import com.example.practice.model.TableData;
import com.google.gson.Gson;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;


@Service
public class TableDataServiceImpl implements TableDataService {
	public TableData serveTableData(int page, String filter) {
			TableData tableData = getJobData();

			TableData filteredData = filterData(tableData, filter);
			TableData paginatedData = paginateData(filteredData, page, filter);

			paginatedData.setFrom((page - 1) * 6 + 1);
			paginatedData.setTo((page - 1) * 6 + paginatedData.getData().size());
			paginatedData.setTotal(filteredData.getTotal());

			return paginatedData;
	}

	private TableData getJobData() {
		try {
			InputStream inputStream = new ClassPathResource("jobs.json").getInputStream();
			Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name());
			String positionStr = scanner.useDelimiter("\\Z").next();
			scanner.close();
			
			return new Gson().fromJson(positionStr, TableData.class);
		} catch(IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private TableData filterData(TableData tableData, String filter) {
		TableData filteredData = new TableData();
		filter = filter.toLowerCase();

		for (Job job : tableData.getData()) {
			String title = job.getTitle().toLowerCase();
			String location = job.getLocation().toLowerCase();
			String type = job.getType().toLowerCase();
			String company = job.getCompany().toLowerCase();
			if (title.contains(filter) || location.contains(filter) ||
					type.contains(filter) || company.contains(filter))
				filteredData.addData(job);
		}

		return filteredData;
	}

	private TableData paginateData(TableData filteredData, int page, String filter) {
		TableData paginatedData = new TableData();

		for (int i = 0; i < 6; i++) {
			if (filteredData.getData().size() == (page - 1) * 6 + i)
				break;
			paginatedData.addData(filteredData.getData().get((page - 1) * 6 + i));
		}
		paginatedData.setCurrent_page(page);
		paginatedData.setLast_page(filteredData.getTotal() / 6 + 1);
		if (page + 1 <= paginatedData.getLast_page()) {
			String next_page_url = String.format(
				"http://localhost:8088/api/jobs?page=%d&filter=%s", 
				page + 1, filter
			);
			paginatedData.setNext_page_url(next_page_url);
		}
		if (page - 1 > 0) {
			String prev_page_url = String.format(
				"http://localhost:8088/api/jobs?page=%d&filter=%s", 
				page - 1, filter
			);
			paginatedData.setPrev_page_url(prev_page_url);
		}

		return paginatedData;
	}
}
