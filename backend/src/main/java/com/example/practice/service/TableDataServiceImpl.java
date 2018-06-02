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
		try {
			InputStream inputStream = new ClassPathResource("jobs.json").getInputStream();
			Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name());
			String positionStr = scanner.useDelimiter("\\Z").next();
			scanner.close();
			Gson gson = new Gson();
			TableData tableData = gson.fromJson(positionStr, TableData.class);

			filter = filter.toLowerCase();
			TableData filteredTableData = new TableData();
			for (Job job : tableData.getData()) {
				String title = job.getTitle().toLowerCase();
				String location = job.getLocation().toLowerCase();
				String type = job.getType().toLowerCase();
				String company = job.getCompany().toLowerCase();
				if (title.contains(filter) || location.contains(filter) ||
					type.contains(filter) || company.contains(filter))
				filteredTableData.addData(job);
			}
			return filteredTableData;
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
