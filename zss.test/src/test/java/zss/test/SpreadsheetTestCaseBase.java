package zss.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.zkoss.poi.ss.usermodel.Cell;
import org.zkoss.zats.mimic.Zats;
import org.zkoss.zss.api.Ranges;
import org.zkoss.zss.api.model.CellStyle;
import org.zkoss.zss.api.model.Sheet;
import org.zkoss.zss.model.sys.XSheet;
import org.zkoss.zss.ui.impl.XUtils;

public class SpreadsheetTestCaseBase {
	@BeforeClass
	public static void init() {
		Zats.init("./src/main/webapp"); 
	}

	@AfterClass
	public static void end() {
		Zats.end();
	}

	@After
	public void after() {
		Zats.cleanup();
	}
	
	//encapsulate access methods for future API change
	protected Cell getCell(XSheet sheet, int row, int col) {
		return XUtils.getCell(sheet, row, col);
	}

	protected boolean isHiddenColumn(XSheet sheet, int columnIndex) {
		return sheet.getColumnWidth(columnIndex)==0;
	}

	protected boolean isHiddenRow(XSheet sheet, int rowIndex) {
		return sheet.getRow(rowIndex).getZeroHeight();
	}
	
	protected CellStyle getCellStyle(Sheet sheet, int row, int col) {
		return Ranges.range(sheet, row, col).getCellStyle();
	}

	/*
	protected XSSFColor getCellFontColor(Spreadsheet spreadsheet, int row, int column) {
		//FIXME do not use BookHelper
		return (XSSFColor)BookHelper.getFontColor(spreadsheet.getBook(),
			spreadsheet.getBook().getFontAt(Utils.getCell(spreadsheet.getSelectedSheet(), row, column).getCellStyle().getFontIndex()));
	}
	*/

}