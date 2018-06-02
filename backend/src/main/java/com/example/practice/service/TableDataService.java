package com.example.practice.service;

import com.example.practice.model.TableData;

import org.springframework.stereotype.Service;

@Service
public interface TableDataService {
	public TableData serveTableData(int page, String filter);
}
