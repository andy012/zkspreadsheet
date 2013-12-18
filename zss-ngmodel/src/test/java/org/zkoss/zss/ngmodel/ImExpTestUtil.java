package org.zkoss.zss.ngmodel;

import static org.junit.Assert.fail;

import java.io.*;
import java.net.URL;

import org.zkoss.zss.ngapi.NImporter;
import org.zkoss.zss.ngapi.impl.imexp.*;

/**
 * Utility for write and load book
 * @author kuro
 *
 */
public class ImExpTestUtil {
	
	static private String impoortFileName = "import.xlsx";
	static private String exportFileName = "exported.xlsx";
	static private NExcelXlsxExporter xlsxExporter = (NExcelXlsxExporter) new ExcelExportFactory(ExcelExportFactory.Type.XLSX).createExporter();
	
	public static File writeBookToFile(NBook book) {
		return writeBookToFile(book, null);
	}
	
	public static File writeBookToFile(NBook book, File outFile) {
		try {
			if(outFile == null) {
				outFile = new File(ImExpTestUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/org/zkoss/zss/ngmodel/book/" + exportFileName);
				outFile.createNewFile();
			}
			xlsxExporter.export(book, outFile);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
		return outFile;
	}
	
	/**
	 * Utility to load book from inputstream
	 * @param is
	 * @return
	 */
	public static NBook loadBook(InputStream is) {
		NImporter importer = new ExcelImportFactory().createImporter();
		NBook book = null;
		try {
			book = importer.imports(is, "XSSFBook");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return book;
	}
	
	/**
	 * Utility to load book from file
	 * @param file
	 * @return
	 */
	public static NBook loadBook(File file) {
		try {
			return loadBook(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static NBook loadBook(URL fileUrl, String bookName) {
		try {
			return new ExcelImportFactory().createImporter().imports(fileUrl, bookName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
