package org.example;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.util.*;

public class Apoidate {
    public static void main(String[] args) throws Exception {
        createWorkBook();
        List<ValCurs> valCurs=new ArrayList<>();
        List<String> listOfDates= Readfilecsv.parserCSV();
        for (String str : listOfDates) {
            valCurs.add(Bazinreg.sendGet(str));
        }

        for (ValCurs temp : valCurs
        ) {
            openWorkBook(temp);
        }
    }
    private static void createWorkBook() throws IOException {
        XSSFWorkbook workbook=new XSSFWorkbook();
        FileOutputStream out=new FileOutputStream(new File("salveaza.xlsx"));
        workbook.write(out);
        out.close();

    }

    private static void openWorkBook(ValCurs valCurs) throws Exception {
        File file=new File("salveaza.xlsx");
        FileInputStream fIP=new FileInputStream(file);
        XSSFWorkbook workbook=new XSSFWorkbook(fIP);
        XSSFSheet spreadsheet=workbook.createSheet(valCurs.getDate());
        XSSFRow row=spreadsheet.createRow((short) 1);

        Map<String, Object[]> valutes=
                new TreeMap<String, Object[]>();
        valutes.put("*", new Object[]{"Name", "Date"});

        valutes.put("+", new Object[]{valCurs.getName(), valCurs.getDate()});

        valutes.put("/", new Object[]{"NumCode", "CharCode", "Nominal",
                "Name", "Value"});

        for (Valute valute : valCurs.getList()) {
            valutes.put(valute.getId(), new Object[]{
                    valute.getNumCode(),
                    valute.getСharCode(),
                    valute.getNominal() + "",
                    valute.getName(),
                    Double.toString(valute.getValue())});
        }

        Set<String> keyid=valutes.keySet();
        int rowid=0;

        for (String key : keyid) {
            row=spreadsheet.createRow(rowid++);
            Object[] objectArr=valutes.get(key);
            int cellid=0;

            for (Object obj : objectArr) {
                Cell cell=row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }

        FileOutputStream out=new FileOutputStream(new File("salveaza.xlsx"));
        workbook.write(out);
        out.close();
    }


}