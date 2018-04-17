package nju.software.wsjx.model.wsSegmentationModel;

import java.io.IOException;
import java.util.List;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;
import org.jdom.JDOMException;

import nju.software.wsjx.util.XmlUtil;


/**
 * 包含文书各段落model
 * @author lr12
 *
 */
public class WsModel {

	private List<WscpfxgcFtModel> PreWscpfxgcFtModels;

	public List<WscpfxgcFtModel> getPreWscpfxgcFtModels() {
		return PreWscpfxgcFtModels;
	}

	public void setPreWscpfxgcFtModels(List<WscpfxgcFtModel> preWscpfxgcFtModels) {
		PreWscpfxgcFtModels = preWscpfxgcFtModels;
	}

	private DocType docType;

	public DocType getDocType() {
		return docType;
	}

	public void setDocType(DocType docType) {
		this.docType = docType;
	}

	private WswsModel wswsModel;//文书文首model
	
	private List<WssscyrModel> wssscyrModels;//文书诉讼参与人models
	
	private WsajjbqkModel wsajjbqkModel;//文书案件基本情况模型
	
	private WsssjlModel wsssjlModel;//文书诉讼记录模型
	
	private WscpfxgcModel wscpfxgcModel;//文书裁判分析过程模型
	
	private WscpjgModel wscpjgModel;//文书裁判结果模型
	
	private WswwModel wswwModel;//文书文尾模型
	
	private String wsqw;//全文
	private String wswsSegment;//文首段落
	private List<String> wssscyrSegment;//诉讼参与人段落
	private String wsajjbqSegment;//案件基本情况段落
	private String wsssjlSegment;//诉讼记录段落
	private String wscpfxgcSegment;//裁判分析过程段落
	private String wscpjgSegment;//裁判结果段落
	private String wswwSegment;//文尾段落
	private String wsfl;//附录
	
	public String getWsqw() {
		return wsqw;
	}

	public void setWsqw(String wsqw) {
		this.wsqw = wsqw;
	}

	public String getWsfl() {
		return wsfl;
	}

	public void setWsfl(String wsfl) {
		this.wsfl = wsfl;
	}

	public WsModel() {
		super();
	}

	public WsModel(WswsModel wswsModel, List<WssscyrModel> wssscyrModels,
			WsajjbqkModel wsajjbqkModel, WsssjlModel wsssjlModel,
			WscpfxgcModel wscpfxgcModel, WscpjgModel wscpjgModel,
			WswwModel wswwModel) {
		super();
		this.wswsModel = wswsModel;
		this.wssscyrModels = wssscyrModels;
		this.wsajjbqkModel = wsajjbqkModel;
		this.wsssjlModel = wsssjlModel;
		this.wscpfxgcModel = wscpfxgcModel;
		this.wscpjgModel = wscpjgModel;
		this.wswwModel = wswwModel;
	}

	public WswsModel getWswsModel() {
		return wswsModel;
	}

	public void setWswsModel(WswsModel wswsModel) {
		this.wswsModel = wswsModel;
	}

	public List<WssscyrModel> getWssscyrModels() {
		return wssscyrModels;
	}

	public void setWssscyrModels(List<WssscyrModel> wssscyrModels) {
		this.wssscyrModels = wssscyrModels;
	}

	public WsajjbqkModel getWsajjbqkModel() {
		return wsajjbqkModel;
	}

	public void setWsajjbqkModel(WsajjbqkModel wsajjbqkModel) {
		this.wsajjbqkModel = wsajjbqkModel;
	}

	public WsssjlModel getWsssjlModel() {
		return wsssjlModel;
	}

	public void setWsssjlModel(WsssjlModel wsssjlModel) {
		this.wsssjlModel = wsssjlModel;
	}

	public WscpfxgcModel getWscpfxgcModel() {
		return wscpfxgcModel;
	}

	public void setWscpfxgcModel(WscpfxgcModel wscpfxgcModel) {
		this.wscpfxgcModel = wscpfxgcModel;
	}

	public WscpjgModel getWscpjgModel() {
		return wscpjgModel;
	}

	public void setWscpjgModel(WscpjgModel wscpjgModel) {
		this.wscpjgModel = wscpjgModel;
	}

	public WswwModel getWswwModel() {
		return wswwModel;
	}

	public void setWswwModel(WswwModel wswwModel) {
		this.wswwModel = wswwModel;
	}

	public String getWswsSegment() {
		return wswsSegment;
	}

	public void setWswsSegment(String wswsSegment) {
		this.wswsSegment = wswsSegment;
	}

	public List<String> getWssscyrSegment() {
		return wssscyrSegment;
	}

	public void setWssscyrSegment(List<String> wssscyrSegment) {
		this.wssscyrSegment = wssscyrSegment;
	}

	public String getWsajjbqSegment() {
		return wsajjbqSegment;
	}

	public void setWsajjbqSegment(String wsajjbqSegment) {
		this.wsajjbqSegment = wsajjbqSegment;
	}

	public String getWsssjlSegment() {
		return wsssjlSegment;
	}

	public void setWsssjlSegment(String wsssjlSegment) {
		this.wsssjlSegment = wsssjlSegment;
	}

	public String getWscpfxgcSegment() {
		return wscpfxgcSegment;
	}

	public void setWscpfxgcSegment(String wscpfxgcSegment) {
		this.wscpfxgcSegment = wscpfxgcSegment;
	}

	public String getWscpjgSegment() {
		return wscpjgSegment;
	}

	public void setWscpjgSegment(String wscpjgSegment) {
		this.wscpjgSegment = wscpjgSegment;
	}

	public String getWswwSegment() {
		return wswwSegment;
	}

	public void setWswwSegment(String wswwSegment) {
		this.wswwSegment = wswwSegment;
	}
	/**
	 * 将wsModel转化为Xml
	 * @param outputPath
	 * @param outputName
	 * @throws IOException
	 * @throws JDOMException
	 */
	public void transformToXml(String outputPath,String outputName) throws IOException, JDOMException{
		XmlUtil.BuildXMLDoc(this, null, outputPath, outputName);
	}
	
	
}
