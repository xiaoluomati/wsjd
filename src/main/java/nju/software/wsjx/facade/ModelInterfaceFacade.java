package nju.software.wsjx.facade;

import java.io.InputStream;

import nju.software.wsjx.model.ModelInterface;


/**
 * modelInterfaceFacade
 * @author lr12
 *
 */
public interface ModelInterfaceFacade {



	/**
	 * 通过传入输入流解析成modelInterface
	 * @param inputStream
	 * @param wswjm
	 * @return
	 */
	public ModelInterface jxDocument(InputStream inputStream, String wswjm);
	
	/**
	 * 通过传入bytes解析成modelInterface
	 * @param bytes
	 * @param wswjm
	 * @return
	 */
	public ModelInterface jxDocument(byte[] bytes, String wswjm);
	
	/**
	 * 通过传入文档字符串解析成modelInterface
	 * @param wsnr
	 * @param wswjm
	 * @return
	 */
	public ModelInterface jxDocument(String wsnr);
	
}
