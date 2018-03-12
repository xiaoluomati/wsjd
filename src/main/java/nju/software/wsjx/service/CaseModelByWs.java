package nju.software.wsjx.service;

import java.util.List;




import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.model.wsSegmentationModel.WswwModel;
import nju.software.wsjx.service.model.WsfdModel;


/**
 * 文书抽取的案件信息模型
 * @author lr12
 *
 */
public interface CaseModelByWs {

	
	
	/**
	 * 是否完整文书
	 * @return
	 */
	public boolean isWzws();

	/**
	 * 解析文书分段内容
	 * @param wsnr
	 * @return
	 */
	public WsfdModel jxWsfdModel(String wsnr, List<String> ws, List<String> sscyr,
                                 String ssjl, List<String> ajjbqk, List<String> cpfxgc
            , List<String> cpjg, List<String> ww, List<String> fl);
	
	/**
	 * 解析文书文首内容
	 * @param ws
	 * @return
	 */
	public WswsModel jxWswsModel(List<String> ws);
	
	/**
	 * 解析文书诉讼参与人内容
	 * @param sscyr
	 * @return
	 */
	public List<WssscyrModel> jxWssscyrModelList(List<String> sscyr, List<String> ajjbqk);
	
	/**
	 * 解析文书案件基本情况内容
	 * @param ajjbqk
	 * @return
	 */
	public List<WsajjbqkModel> jxWsajjbqkModel(List<String> ajjbqk);
	
	/**
	 * 解析裁判分析过程内容
	 * @param cpfxgc
	 * @return
	 */
	public WscpfxgcModel jxWscpfxgcModel(List<String> cpfxgc);
	
	/**
	 * 解析裁判结果内容
	 * @param cpjg
	 * @return
	 */
	public WscpjgModel jxWscpjgModel(List<String> cpjg);
	
	/**
	 * 解析文书文尾内容
	 * @param wsww
	 * @return
	 */
	public WswwModel jxWswwModel(List<String> wsww);
	
}
