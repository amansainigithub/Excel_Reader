package com.exe.ExcelOperation.repository;

import com.exe.ExcelOperation.controllers.ExcelOperation;
import com.exe.ExcelOperation.entities.ExcelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExcelOperationRepo extends JpaRepository<ExcelEntity, Long> {

//    Optional<?> saveAll(List<?> example);
}
