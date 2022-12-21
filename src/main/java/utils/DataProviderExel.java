package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

public class DataProviderExel {

    public static boolean useDataProvider;

    @DataProvider(name = "col-provider")
    public static Object[][] col_exelData(Method method) throws IOException {
        int startRow = 2, lastRow = -1;//If you need all the lines after the title in row 1
        if (GetProperties.skipInvalid_DataProvider())
            startRow = 3;
        String startcolumn = "B", lastColumn = "B"; //If you just need column B but if you need all column not empty enter "All"
        String sheetName = "";
        switch (method.getName()) {
            case "register_valid_Only_MailAdress", "login_valid_enterOnlyMailAdress" -> sheetName = "email address valid";
            case "register_invalid_enterOnlyMailAdress", "login_invalid_enterOnlyMailAdress" -> sheetName = "email address invalid";
            default -> System.out.println("\n****This method has no seted data to take from DataProvider no case in switch****\n");
        }
        return get_exelData(sheetName, startRow, lastRow, startcolumn, lastColumn);
    }


    /**
     * @param startRow    Enter the first row number in the workbook from which you want to start receiving data.
     *                    If equals -1 will start from line 2 in the brochure and skip the title.
     * @param lastRow     Enter the line number to be read we will receive data.
     *                    if  equals -1 Will give the last row not empty in the sheet.
     * @param startColumn Enter the column char in the workbook from which you want to start receiving data.
     * @param lastColumn  Enter the column letter up to which we will receive data.
     *                    if  equals "ALL" Will give the last column not empty in the sheet.
     *                    if you want one column enter the character you entered in startColumn
     */
    private synchronized static Object[][] get_exelData(String sheetName, int startRow, int lastRow, String startColumn, String lastColumn) throws IOException {
        XSSFWorkbook workbook = get_workbook();
        XSSFSheet sheet = workbook.getSheet(sheetName);
        startRow = startRow != -1 ? startRow - 1 : 1;
        lastRow = lastRow != -1 ? lastRow : (sheet.getLastRowNum());

        int limit = GetProperties.getLimit_DataProvider();

        lastRow = lastRow - startRow < limit || limit < 1 ? lastRow : startRow + limit;
        System.out.println("\nlastRow:" + lastRow +
                "\nlimit : " + limit);
        int int_StartColumn = startColumn.toUpperCase().charAt(0) - 65;
        int int_lastColumn = lastColumn.toUpperCase().contains("ALL") ?
                sheet.getRow(startRow).getPhysicalNumberOfCells() :
                lastColumn.toUpperCase().charAt(0) - ('A') + 1;
        int lengthRow = lastRow - startRow, lengthCol = int_lastColumn - int_StartColumn;

        Object[][] exelData = new Object[lengthRow][lengthCol];
        int row_exelData = 0, col_exelData = 0;

        for (int row = startRow; row < lastRow; row++) {
            for (int col = int_StartColumn; col < int_lastColumn; col++) {
                exelData[row_exelData][col_exelData] = sheet.getRow(row).getCell(col).toString();
                col_exelData++;
            }
            col_exelData = 0;
            row_exelData++;
        }
        return exelData;
    }

    private synchronized static XSSFWorkbook get_workbook() {
        FileInputStream workbookLocation = null;
        try {
            workbookLocation = new FileInputStream(System.getProperty("user.dir") +
                    "\\src\\main\\resources\\DataProviderWithExcel_001.xlsx");
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");
            e.printStackTrace();
        }
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(workbookLocation);
        } catch (IOException e) {
            System.out.println("Unable to set in EXCEL file");
            e.printStackTrace();
        }
        return workbook;
    }
}
