package nju.software.wsjx.facade;

import java.io.InputStream;

import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WsModelWrapper;

/**
 * 暴露给外部，通过传递输入流，byte【】或者转好的字符串获取对应的文书model
 * 使用重载
 * @author lr12
 *
 */
public interface WsModelFacade {

	/**
	 * 通过传入输入流解析成文书模型
	 * @param inputStream
	 * @param wswjm
	 * @return
	 */
	public WsModel jxDocument(InputStream inputStream, String wswjm);
	
	/**
	 * 通过传入bytes解析成文书模型
	 * @param bytes
	 * @param wswjm
	 * @return
	 */
	public WsModel jxDocument(byte[] bytes, String wswjm);
	
	/**
	 * 通过传入文档字符串解析成文书模型
	 * @param wsnr
	 * @param wswjm
	 * @return
	 */
	public WsModel jxDocument(String wsnr, String wswjm);
	
	/**
	 * 通过传入输入流解析成文书模型包装类，若解析出错，则包含错误信息
	 * @param inputStream
	 * @param wswjm
	 * @return
	 */
	public WsModelWrapper jxDocumentWrapper(InputStream inputStream, String wswjm);
}
