package converter;

import java.io.*;

class InFile implements Closeable {
	int i;
	String fileName;
	private byte[] content;
	private FileInputStream inFile;

	byte[] content(String fileName) throws IOException {
		try {
			inFile = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			return null;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Unknown command");
			return null;
		}
		// read characters until EOF is reached
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		do {
			i = inFile.read();
			if (i != -1) {
				// System.out.print((char) i);
				// TODO schreibe in out ...!
				out.write(i);
			}
		} while (i != -1);
		inFile.close();
		content = out.toByteArray();
		return content;
	}

	@Override
	public void close() throws IOException {
		content = null;

	}

}