/**
package BLL.Utility;

import javafx.scene.control.Cell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class BulkAdd {

     public void converter() throws IOException {
     File xls = new File("C:\\BulkAdd.xls");
     File csv = new File("C:\\BulkAdd.csv");
     xls.renameTo(csv);
         FileReader fileReader = new FileReader(xls);
         FileWriter fileWriter = new FileWriter(csv);
     }



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



   /** public void converter() {
        File src = new File("C:\\BulkAdd.xls");
        File dest = new File("C:\\BulkAdd1.csv");
        src.renameTo(dest);
    }

    public static void bulkAdd123(String[] args)
    {
        File inputFile = new File("C:\\BulkAdd.xls");
        File outputFile = new File("C:\\BulkAdd1.csv");
        // For storing data into CSV files
        StringBuffer data = new StringBuffer();

        try
        {
            FileOutputStream fos = new FileOutputStream(outputFile);

            // Get the workbook object for XLS file
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(inputFile));
            // Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);
            Cell cell;
            Row row;

            // Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                row = rowIterator.next();
                // For each row, iterate through each columns
                Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext())
                {
                    cell = (Cell) cellIterator.next();

                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_BOOLEAN:
                            data.append(cell.getBooleanCellValue() + ",");
                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            data.append(cell.getNumericCellValue() + ",");
                            break;

                        case Cell.CELL_TYPE_STRING:
                            data.append(cell.getStringCellValue() + ",");
                            break;

                        case Cell.CELL_TYPE_BLANK:
                            data.append("" + ",");
                            break;

                        default:
                            data.append(cell + ",");
                    }

                    data.append('\n');
                }
            }

            fos.write(data.toString().getBytes());
            fos.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    }
}

    */


