package com.crimson.app.crimson.service;


import com.crimson.app.crimson.dto.ComplaintCategoryDto;
import com.crimson.app.crimson.model.MdlComplaintCategory;
import com.crimson.app.crimson.repository.ComplaintCategoryRepository;
import com.crimson.app.crimson.service.imp.ComplaintCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintCategoryServiceImp implements ComplaintCategoryService {

    @Autowired
    private final ComplaintCategoryRepository complaintCategoryRepository;

    public ComplaintCategoryServiceImp(ComplaintCategoryRepository complaintCategoryRepository) {
        this.complaintCategoryRepository = complaintCategoryRepository;
    }

    @Override
    public List<ComplaintCategoryDto> findALl() {
        return complaintCategoryRepository.findAllCategory();
    }
}
