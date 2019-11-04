package resources;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Data_Driven_Demo {

	public Properties prop=new Properties();
	
	public ArrayList<String> getData(String testcaseName) throws IOException {

		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//Test_Data_Rest_Api.xlsx");		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		ArrayList<String> a = new ArrayList<String>();

		int sheets = wb.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			// System.out.println(wb.getSheetName(i));
			if (wb.getSheetName(i).equalsIgnoreCase("Rest_Assured")) {
				XSSFSheet sheet = wb.getSheetAt(i);

				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> cell = firstRow.cellIterator();

				int k = 0;
				int column = 0;
				while (cell.hasNext()) {
					Cell cellValue = cell.next();
					// System.out.println(cellValue);
					if (cellValue.getStringCellValue().equalsIgnoreCase("Testcases")) {
						column = k;
						// System.out.println(column);
					}
					k++;

					while (rows.hasNext()) {
						Row r = rows.next();
						if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
							Iterator<Cell> cv = r.cellIterator();
							while (cv.hasNext()) {
								Cell c = cv.next();
								if (c.getCellTypeEnum()==CellType.STRING) {
									a.add(c.getStringCellValue());
								} else {
									a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
								}

							}

						}

					}
				}

			}

		}
		return a;

	}

}
