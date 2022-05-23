
package BLL.Utility;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.util.Iterator;

public class BulkAdd {


    /**public File converter() throws IOException {
     File xls = new File("C:\\BulkAdd.xls");

     File csv = new File("C:\\BulkAdd.csv");

     xls.renameTo(csv);
         FileReader fileReader = new FileReader(xls);
         FileWriter fileWriter = new FileWriter(csv);

         csv.setReadOnly();

         return csv;
     } */

/**
    private static Row row;

    public BulkAdd() throws IOException {
    }

    public static void readExcel(String filePath) throws IOException {
        File file = new File("\"C:\\BulkAdd.xlsx\"");
        try {
            FileInputStream inputStream = new FileInputStream(file);

            Workbook WorkBook = new XSSFWorkbook(inputStream);
            for (Sheet sheet : WorkBook) {
                int firstRow = sheet.getFirstRowNum();
                int lastRow = sheet.getLastRowNum();
                for (int index = firstRow + 1; index <= lastRow; index++) {
                    Row row = sheet.getRow(index);
                }
                for (int cellIndex = row.getFirstCellNum(); cellIndex < row.getLastCellNum(); cellIndex++) {
                    Cell cell = (Cell) row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                }
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

*/

        File csv = new File(".idea/BulkAdd.csv");

    public static void bulkAdd123()
    {
        File inputFile = new File(".idea/BulkAdd.csv" );
        File outputFile = new File(".idea/BulkAdd.csv");
        // For storing data into CSV files
        StringBuffer csvData = new StringBuffer();

        System.out.println(outputFile.canWrite());

        try
        {
            FileOutputStream fos = new FileOutputStream(outputFile);

            //under here is not working properly

            // Get the workbook object for XLS file
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(inputFile));
            // Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            Cell cell;
            Row row;



            // Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                row = rowIterator.next();
                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext())
                {
                    System.out.println(cellIterator.next().toString());
                    cell = cellIterator.next();

                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_BOOLEAN:
                            csvData.append(cell.getBooleanCellValue() + ",");
                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            csvData.append(cell.getNumericCellValue() + ",");
                            break;

                        case Cell.CELL_TYPE_STRING:
                            csvData.append(cell.getStringCellValue() + ",");
                            break;

                        case Cell.CELL_TYPE_BLANK:
                            csvData.append("" + ",");
                            break;

                        default:
                            csvData.append(cell + ",");
                    }

                    csvData.append('\n');
                }
            }

            fos.write(csvData.toString().getBytes());

            fos.flush();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("did not work");

            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}


