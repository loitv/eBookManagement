package reference;
import java.util.Iterator;
import java.util.Map;

//import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfReader;

public class demo1 {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception {

		/// ===Example2====/// Read and show metadata
		 PdfReader reader = new PdfReader("F:\\Books\\Computers & Technology\\Operating Systems\\Virtualization Slide\\m_05srv.pdf");
		 Map info = reader.getInfo();
		 for (Iterator i = info.keySet().iterator(); i.hasNext();) {
		 String key = (String) i.next();
		 String value = (String) info.get(key);
		 System.out.println(key + ": " + value);
		 }
		 if (reader.getMetadata() == null) {
		 System.out.println("No XML Metadata.");
		 } else {
		 System.out.println("XML Metadata: " + new
		 String(reader.getMetadata()));
		 }
		//
		// ///// Write XML to textFile/////////////
		// try (FileWriter wr = new FileWriter("D://test.xml");) {
		// wr.write(new String(reader.getMetadata()));
		// } catch (FileNotFoundException e) {
		// System.out.println(e.getMessage());
		// } catch (IOException e) {
		// System.out.println(e.getMessage());
		// }

		// ===Example 3===// set metadata for pdf file
//		int a = 0;
//		try {
//			PdfReader reader1 = new PdfReader("D://Patricia Neal_ An Unquiet Life - Stephen Michael Shearer.pdf");
//			System.out.println("Tampered? " + reader1.isTampered());
//			PdfStamper stamper = new PdfStamper(reader1, new FileOutputStream("D://new_Patricia Neal_ An Unquiet Life - Stephen Michael Shearer.pdf"));
//			HashMap<String, String> info = reader1.getInfo();
//			info.put("Subject", "Arts & Literature");
//			info.put("Author", "Stephen Michael Shearer");
////			info.put("Keywords", "Patricia Neal");
//			info.put("Title", "Patricia Neal: An Unquiet Life");
//			info.put("Language", "English");
//			info.put("Publisher", "University Press of Kentucky (August 5, 2011)");
//			info.put("ISBN-10", "0813129710");
//			stamper.setMoreInfo(info);
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			XmpWriter xmp = new XmpWriter(baos, info);
//			xmp.close();
//			stamper.setXmpMetadata(baos.toByteArray());
//			stamper.close();
//			reader1.close(); // close process around oldFile
//			a = 1;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
////		System.out.println(a);
//		if (a == 1) {
//			
//			File oldFile = new File("D://Patricia Neal_ An Unquiet Life - Stephen Michael Shearer.pdf");
//			oldFile.delete();
//			File newFile = new File("D://new_Patricia Neal_ An Unquiet Life - Stephen Michael Shearer.pdf");
//			newFile.renameTo(new File("D://Patricia Neal_ An Unquiet Life - Stephen Michael Shearer.pdf"));
//		}
	}
}
