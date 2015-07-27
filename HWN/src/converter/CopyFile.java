package converter;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CopyFile {
	   {
		      FileReader in = null;
		      FileWriter out = null;

		      try {
		         in = new FileReader("input.txt");
		         out = new FileWriter("output.txt");
		         
		         int c;
		         while ((c = in.read()) != -1) {
		            out.write(c);
		         }
		      } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
		         if (in != null) {
		            try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		         }
		         if (out != null) {
		            try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		         }
		      }
		   }
		}

