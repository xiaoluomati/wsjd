package nju.software.wsjx.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

public class FileUtil {
	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	private String s;

	public void writeToFile(@SuppressWarnings("rawtypes") ArrayList list) {
		String path = s;
		File file = new File(path);
		if (!file.exists()) {
			String str = null;
			try {
				file.createNewFile();
				FileWriter fw = new FileWriter(file);
				BufferedWriter bufw = new BufferedWriter(fw);
				for (int i = 0; i < list.size(); i++) {
					if ((str = (String) list.get(i)) != null) {
						bufw.write(str);
						bufw.newLine();
					}
				}
				bufw.close();
				fw.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String str = null;
			try {
				FileWriter fw = new FileWriter(file);
				BufferedWriter bufw = new BufferedWriter(fw);
				for (int i = 0; i < list.size(); i++) {
					if ((str = (String) list.get(i)) != null) {
						bufw.write(str);
						bufw.newLine();
					}
				}
				bufw.close();
				fw.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<String> readFromFile() {
		File file = new File(s);
		String str = null;
		ArrayList<String> list = new ArrayList<String>();
		FileReader fr = null;
		if (file.exists()) {
			try {
				fr = new FileReader(file);
				BufferedReader bufr = new BufferedReader(fr);
				while ((str = bufr.readLine()) != null) {

					list.add(str);
				}
				bufr.close();
				fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list = new ArrayList<String>();
		}
		return list;
	}
	 public static String readDoc(String path,String filename) throws Exception {
		  // 创建输入流读取doc文件
		 String text2003=null;
		 String wsnr="";
		  FileInputStream in = new FileInputStream(new File(path+"\\"+filename));
		  try {  
			  if(filename.contains("docx")){

		            OPCPackage opcPackage = POIXMLDocument.openPackage(path+"\\"+filename);  
		            POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);  
	            wsnr = extractor.getText();  

			  }else{
				  WordExtractor ex = new WordExtractor(in);  
		           text2003 = ex.getText();  
		           ArrayList<String> wsnrlist=new ArrayList<String>();
		   		FileUtil fileUtil = new FileUtil();
		   		FcUtil fcUtil=new FcUtil();
		   		String[]content=text2003.split("\n");
		   		for(int i=0;i<content.length;i++){
		   			ArrayList<String> str=(ArrayList<String>) FcUtil.getWholeToken(content[i]);
		   			if(str.size()>0){
		   				wsnrlist.add(content[i]);
		   			}
		   		}
		   		for(int i=0;i<wsnrlist.size()-1;i++){
		   			wsnr+=wsnrlist.get(i)+"\n";
		   		}
		   		wsnr+=wsnrlist.get(wsnrlist.size()-1);

			  }
	           
	              
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		  return wsnr;
	    }  
	public static void fileCopy(String path1,String file1,String path2,String file2) throws IOException{
		File file = new File(path2);
		if(!file.exists()&& !file.isDirectory()){
			file.mkdir();
		}
        FileInputStream fis = new FileInputStream(path1+"\\"+file1);
        FileOutputStream fos = new FileOutputStream(path2+"\\"+file2);
 
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = fis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        fis.close();
        fos.close();
	}
	public void append(String str) {
		String path = s;
		File file = new File(path);

		try {

			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bufw = new BufferedWriter(fw);

			bufw.write(str);
			bufw.newLine();

			bufw.close();
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    //将byte数组写入文件  
  public  static void createFile(String path, byte[] content) throws IOException {  

      FileOutputStream fos = new FileOutputStream(path);  

      fos.write(content);  
      fos.close();  
  }  
	public static byte[] getContent(String filePath) throws IOException {  
        File file = new File(filePath);  
        long fileSize = file.length();  
        if (fileSize > Integer.MAX_VALUE) {  
            System.out.println("file too big...");  
            return null;  
        }  
        FileInputStream fi = new FileInputStream(file);  
        byte[] buffer = new byte[(int) fileSize];  
        int offset = 0;  
        int numRead = 0;  
        while (offset < buffer.length  
        && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {  
            offset += numRead;  
        }  
        // 确保所有数据均被读取  
        if (offset != buffer.length) {  
        throw new IOException("Could not completely read file "  
                    + file.getName());  
        }  
        fi.close();  
        return buffer;  
    }  
	public static void writeToFile(List<String> list,String path) {
		File file = new File(path);
		if (!file.exists()) {
			String str = null;
			try {
				file.createNewFile();
				FileWriter fw = new FileWriter(file);
				BufferedWriter bufw = new BufferedWriter(fw);
				for (int i = 0; i < list.size(); i++) {
					if ((str = (String) list.get(i)) != null) {
						bufw.write(str);
						bufw.newLine();
					}
				}
				bufw.close();
				fw.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String str = null;
			try {
				FileWriter fw = new FileWriter(file);
				BufferedWriter bufw = new BufferedWriter(fw);
				for (int i = 0; i < list.size(); i++) {
					if ((str = (String) list.get(i)) != null) {
						bufw.write(str);
						bufw.newLine();
					}
				}
				bufw.close();
				fw.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
