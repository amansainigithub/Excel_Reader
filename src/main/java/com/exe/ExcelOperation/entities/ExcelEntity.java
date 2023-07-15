package com.exe.ExcelOperation.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class ExcelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String serialNo;

    private String firstName;

    private String lastName;

    private String gender;

    private String country;

    private String age;

    private String date;

    private String rxId;


}
