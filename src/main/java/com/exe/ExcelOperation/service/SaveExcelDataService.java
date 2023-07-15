package com.exe.ExcelOperation.service;

import com.exe.ExcelOperation.entities.ExcelEntity;
import com.exe.ExcelOperation.repository.ExcelOperationRepo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SaveExcelDataService {

    @Autowired
    ExcelOperationRepo excelOperationRepo;

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };
    static String SHEET = "Tutorials";

        public static boolean hasExcelFormat(MultipartFile file) {
            if (!TYPE.equals(file.getContentType())) {
                return false;
            }
            return true;
        }


    public void save(MultipartFile file) {
        try {
            List<ExcelEntity> excelEntityList = excelOperatrionPerform(file.getInputStream());
           List<ExcelEntity> returnData =  excelOperationRepo.saveAll(excelEntityList);
            System.out.println("Return Data  :: " + returnData);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }


        public static List<ExcelEntity> excelOperatrionPerform(InputStream is){
            try {
                Workbook workbook = new XSSFWorkbook(is);

                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rows = sheet.iterator();

                List<ExcelEntity> excelEntitiesList = new ArrayList<>();

                int rowNumber = 0;
                while (rows.hasNext()) {
                    Row currentRow = rows.next();

                    // skip header
                    if (rowNumber == 0) {
                        rowNumber++;
                        continue;
                    }

                    Iterator<Cell> cellsInRow = currentRow.iterator();
                    ExcelEntity excelEntity = new ExcelEntity();

                    int cellIdx = 0;
                    while (cellsInRow.hasNext()) {
                        Cell currentCell = cellsInRow.next();

                        switch (cellIdx) {
                            case 0:
                                excelEntity.setSerialNo(String.valueOf(currentCell.getNumericCellValue()));
                                break;

                            case 1:
                                excelEntity.setFirstName(currentCell.getStringCellValue());
                                break;

                            case 2:
                                excelEntity.setLastName(currentCell.getStringCellValue());
                                break;

                            case 3:
                                excelEntity.setGender(currentCell.getStringCellValue());
                                break;

                            case 4:
                                excelEntity.setCountry(currentCell.getStringCellValue());
                                break;

                            case 5:
                                excelEntity.setAge(String.valueOf(currentCell.getNumericCellValue()));
                                break;

                            case 6:
                                excelEntity.setDate(String.valueOf(currentCell.getStringCellValue()));
                                break;

                            case 7:
                                excelEntity.setRxId(String.valueOf(currentCell.getNumericCellValue()));
                                break;

                            default:
                                break;
                        }

                        cellIdx++;
                    }

                    excelEntitiesList.add(excelEntity);
                }

                workbook.close();

            return excelEntitiesList;

            }
            catch (IOException io)
            {
                io.printStackTrace();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

}
