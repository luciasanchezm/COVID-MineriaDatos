import java.io.BufferedWriter;
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

public class ExcelReader {
	public static void main(String args[]) throws IOException  
	{  
		String [] attributes = {
				"Edad", 
				"Nivel", 
				"Dia", 
				"Turno", 
				"Hora", 
				"Evento", 
				"TipoLugarEvento", 
				"NoPersonas", 
				"SanaDistancia", 
				"UsoCubrebocas", 
				"Genero", 
				"SeContagio"
		};
		String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<!DOCTYPE covid SYSTEM \"./covid.dtd\">\n\n"
				+ "<covid>\n";

		// XML File to write
		File xmlFile = new File("../covid.xml");
		// if doesn't exist, create file
		if (!xmlFile.exists()) {
			xmlFile.createNewFile();
		}
		FileWriter fw = new FileWriter(xmlFile);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(text);

		// Get file
		FileInputStream fis=new FileInputStream(new File("../covid.xls"));
		HSSFWorkbook wb=new HSSFWorkbook(fis);
		HSSFSheet sheet=wb.getSheetAt(0); 
		FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();

		// Read file
		for(Row row: sheet) {
			text = "\t<Persona ";
			for(int i=0; i<12 ; i++) {  
				Cell cell = row.getCell(i);
				text += attributes[i] + "=\"";
				switch(formulaEvaluator.evaluateInCell(cell).getCellType()) {  
				case Cell.CELL_TYPE_NUMERIC:
					text += cell.getNumericCellValue();
					break;  
				case Cell.CELL_TYPE_STRING:
					text += cell.getStringCellValue();
					break;  
				}


				text += "\" ";
			}
			text += "/>\n";
			bw.write(text);
		}
		bw.write("</covid>");
		System.out.println("LISTO :D");

		bw.close();
	}
}