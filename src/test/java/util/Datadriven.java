package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Datadriven {

	public ArrayList Data() throws IOException {

		ArrayList<String> list = new ArrayList<String>();

		FileInputStream file = new FileInputStream("c:\\.......");
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		int sheet = workBook.getNumberOfSheets();

		for (int i = 0; i < sheet; i++) {
			if (workBook.getSheetName(i).equalsIgnoreCase("name")) {
				XSSFSheet sheetN = workBook.getSheetAt(i);
				Iterator<Row> row = sheetN.iterator();
				Row first = row.next();
				Iterator<Cell> cells = first.cellIterator();

				while (cells.hasNext()) {
					Cell value = cells.next();
					if (value.getStringCellValue().equalsIgnoreCase("namecolumn")) {
						list.add(value.getStringCellValue());
					}
				}
			}
		}
		return list;
	}
}
