package com.rgsinfotech.inventory.service;

import org.springframework.data.domain.Page;

import com.rgsinfotech.model.Part;

public interface PartService {
	
	public Page<Part> findAll(int pageNum, int defaultPageSize);

	Page<Part> findByNameLike(String name, int page, int size);

	Part findById(Integer id);

	Part insert(Part part);

	Part update(Part part);

	void deleteById(Integer id);
}
