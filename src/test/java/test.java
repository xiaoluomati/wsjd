import nju.software.wsjx.facade.impl.WsModelFacadeImpl;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.util.FileUtil;
import org.jdom.JDOMException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class test {
	public static void main(String[] args) throws IOException, JDOMException{
		FileUtil fileUtil=new FileUtil();
		
		File file = new File("E:\\毕业设计\\文书");
		File[] files = file.listFiles();
		for(File f:files){
			System.out.println(f.getAbsolutePath());
			String name = f.getAbsolutePath();
			byte[] wsnr=fileUtil.getContent(name);
			InputStream is=new ByteArrayInputStream(wsnr);
			WsModelFacadeImpl wsModelFacadeImpl=new WsModelFacadeImpl();
			WsModel wsModel=wsModelFacadeImpl.jxDocument(is, name);
			int posa = name.lastIndexOf('.');
			int posb = name.lastIndexOf('\\');
			String fname = name.substring(posb+1,posa);
			if(wsModel!=null)
				wsModel.transformToXml("E:\\毕业设计\\xml",fname);
			else {
				System.out.println("error");
			}
		}				
	}
	
}
