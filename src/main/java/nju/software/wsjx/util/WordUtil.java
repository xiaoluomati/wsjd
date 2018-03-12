package nju.software.wsjx.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordUtil {
    
	public static void BytesToFile(String filePath, byte[] wsnr){
		FileOutputStream fop = null;
		
		File file;
		try {
			file = new File(filePath);
			fop = new FileOutputStream(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			fop.write(wsnr);
			fop.flush();
			fop.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
}
