package nju.software.wsjx.facade;

import java.io.InputStream;

import nju.software.wsjx.model.caseinfo.BaseCaseInfo;

/**
 * 暴露给外部，通过传递输入流，byte【】或者转好的字符串获取对应的文档
 * 使用重载
 * @author lr12
 *
 */
public interface CaseInfoFacade {

	/**
	 * 通过传入输入流解析成一篇文档
	 * @param inputStream
	 * @param wswjm
	 * @return
	 */
	public BaseCaseInfo jxDocument(InputStream inputStream, String wswjm);
	
	/**
	 * 通过传入bytes解析成一篇文档
	 * @param bytes
	 * @param wswjm
	 * @return
	 */
	public BaseCaseInfo jxDocument(byte[] bytes, String wswjm);
	
	/**
	 * 通过传入文档字符串解析成一篇文档
	 * @param wsnr
	 * @param wswjm
	 * @return
	 */
	public BaseCaseInfo jxDocument(String wsnr, String wswjm);
}
