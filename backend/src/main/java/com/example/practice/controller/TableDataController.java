package com.example.practice.controller;

import com.example.practice.model.TableData;
import com.example.practice.service.TableDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TableDataController {
    private final TableDataService tableDataService;

	@Autowired
	TableDataController(TableDataService tableDataService) {
		this.tableDataService = tableDataService;
	}

	@GetMapping("/jobs")
    public TableData createMail(@RequestParam(value = "page") int page,
								@RequestParam(value = "filter") String filter) {
		return tableDataService.serveTableData(page, filter); 
    }
}
