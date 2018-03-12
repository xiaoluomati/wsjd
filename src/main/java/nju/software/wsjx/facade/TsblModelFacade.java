package nju.software.wsjx.facade;

import java.io.InputStream;
import java.util.List;

import nju.software.wsjx.model.tsblModel.TsblModel;



/**
 * 庭审笔录facade
 * @author lr12
 *
 */
public interface TsblModelFacade {

	/**
	 * 通过传入输入流解析成庭审笔录模型
	 * @param inputStream
	 * @param wswjm
	 * @return
	 */
	public TsblModel jxDocument(InputStream inputStream, String wswjm);
	
	/**
	 * 通过传入bytes解析成庭审笔录模型
	 * @param bytes
	 * @param wswjm
	 * @return
	 */
	public TsblModel jxDocument(byte[] bytes, String wswjm);
	
	/**
	 * 通过传入文档字符串解析成庭审笔录模型
	 * @param wsnr
	 * @param wswjm
	 * @return
	 */
	public TsblModel jxDocument(String wsnr);
	
	/**
	 * 根据分好段的获取相应model
	 * @param paragraps
	 * @return
	 */
	public TsblModel jxDocument(List<String> paragraps);
}