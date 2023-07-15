package com.exe.ExcelOperation.controllers;

//import com.exe.ExcelOperation.service.SaveExcelDataService;
import com.exe.ExcelOperation.service.SaveExcelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/excel")
public class ExcelOperation {

    @Autowired
    SaveExcelDataService saveExcelDataService;



    @PostMapping("/operationExcel")
    public String excelOperation(@RequestParam("file") MultipartFile excel)
    {
        System.out.println("Running Start...");
        try {
                    saveExcelDataService.save(excel);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Running...");
        return "DONE";
    }

}
