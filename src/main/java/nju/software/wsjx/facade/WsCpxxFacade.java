package nju.software.wsjx.facade;

import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.facadeModel.WsxxModel;

/**
 * 提供给外部，抽取裁判理由、裁判依据、裁判结果
 * @author lr12
 *
 */
public interface WsCpxxFacade {
	/**
	 * 根据wsnr和文书文件名获取裁判信息List
	 * 分别为裁判理由、裁判依据、处理结果
	 * @param wsnr
	 * @param wswjm
	 * @return
	 */
	public String getCpxx(byte[] wsnr, String wswjm);
	
}
