package com.bartlot.Server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartlot.Server.entity.WorkTableEntity;
import com.bartlot.Server.repository.WorkTableRepository;

@Service
public class WorkTableService {

    @Autowired
    private WorkTableRepository workTableRepository;

    public List<WorkTableEntity> getListWorkTable() {

        return workTableRepository.findAll();

    }

}
