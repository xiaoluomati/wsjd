package nju.software.wsjx.util;

import nju.software.wsjd.model.ysptWsModel.ajjbqk.FsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.SsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.ZjdsrModel;
import nju.software.wsjd.model.ysptWsModel.ssjl.FsModel;
import nju.software.wsjd.model.ysptWsModel.ssjl.SsrqydsrModel;
import nju.software.wsjx.model.Enum.ParseEnum;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.model.wsSegmentationModel.WswwModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjeModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjgnrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.QkqkModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.QzcsModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsCpjgssfModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFdlxModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcZdlxModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.XspjjgfzModel;
import nju.software.wsjx.service.model.*;
import nju.software.wsjx.service.model.xs.*;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 由wsModel生成XMl
 * @author wangzh
 *
 */
public class XmlUtil {	
	public static void BuildXMLDoc(WsModel wsModel,			
			List<WsxszjdModel> wsxszjdModellist, 
			String outputpath, String filename)
					throws IOException, JDOMException {
		if(wsModel == null)
			return;
		String parseName = wsModel.getWswsModel().getParseName();												
		// 创建根节点 全文 ,若全文为空，则将文书提取放入special文件夹
		if (wsModel.getWsqw() != null) {
			Element root = new Element("QW").setAttribute("value",
					wsModel.getWsqw());
			root.setAttribute("nameCN", "全文");
			// 将根节点添加到文档中；
			Document Doc = new Document(root);
			// 创建文首节点
			buildWs(root,wsModel);							
			// 创建诉讼参与人节点
			buildSscyr(root, wsModel);			
			// 创建诉讼记录节点
			buildSsjl(root, wsModel);			
			// 创建案件基本情况节点
			buildAjjbq(root, wsModel,wsxszjdModellist);	
			//创建裁判分析过程节点
			buildCpfxgc(root, wsModel);
			//根据是否为刑事案件，创建相应裁判结果节点
			if(parseName!=null && parseName.contains("刑事")){
				buildXspjjg(wsModel, root);
			}else{
				buildPjjg(root, wsModel);
			}
			//创建文尾节点
			buildWw(root, wsModel);
			// 创建附录节点
			buildFl(root, wsModel);
			// 使xml文件 缩进效果
			Format format = Format.getPrettyFormat();
			XMLOutputter XMLOut = new XMLOutputter(format);
			XMLOut.output(Doc, new FileOutputStream(outputpath + "\\"
					+ filename + ".xml"));
		} else {
			// fileUtil.fileCopy(path, filename, specialpath+"qwspecial",
			// filename);
		}

	}
	/**
	 * 创建文首节点
	 * @param root
	 * @param wsModel
	 */
	private static void buildWs(Element root,WsModel wsModel){
		if(wsModel == null)
			return;
		// 创建文首节点 ,若文首为空，则将文书提取放入wsspecial文件夹
		if (wsModel.getWswsSegment() != null) {
			WswsModel wswsModel = wsModel.getWswsModel();
			Element ws = new Element("WS").setAttribute("value",
					wsModel.getWswsSegment());
			ws.setAttribute("nameCN", "文首");
			root.addContent(ws);
			// 创建文书制作单位节点
			if (wswsModel.getWszzdw() != null) {
				Element wszzdw = new Element("WSZZDW").setAttribute(
						"value", wswsModel.getWszzdw());
				wszzdw.setAttribute("nameCN", "文书制作单位");
				ws.addContent(wszzdw);
			}
			// 创建经办法院节点 ,若经办法院为空，则将文书提取放入jbfyspecial文件夹
			if (wswsModel.getJbfy() != null) {
				Element jbfy = new Element("JBFY").setAttribute("value",
						wswsModel.getJbfy());
				jbfy.setAttribute("nameCN", "经办法院");
				ws.addContent(jbfy);
				if (wswsModel.getFyjb() != null) {
					Element fyjb = new Element("FYJB").setAttribute(
							"value", wswsModel.getFyjb());
					fyjb.setAttribute("nameCN", "法院级别");
					jbfy.addContent(fyjb);
				}
				if (wswsModel.getXzqhProv() != null) {
					Element xzqhProv = new Element("XZQH_P").setAttribute(
							"value", wswsModel.getXzqhProv());
					xzqhProv.setAttribute("nameCN", "行政区划(省)");
					jbfy.addContent(xzqhProv);
				}
				if (wswsModel.getXzqhCity() != null) {
					Element xzqhCity = new Element("XZQH_C").setAttribute(
							"value", wswsModel.getXzqhCity());
					xzqhCity.setAttribute("nameCN", "行政区划(市)");
					jbfy.addContent(xzqhCity);
				}
			} else {
//				fileUtil.fileCopy(path, filename, specialpath
//						+ "jbfyspecial", filename);
			}
			// 创建文书名称节点 ,若文书名称为空，则将文书提取放入wsmcspecial文件夹
			if (wswsModel.getWsmc() != null) {
				Element wsmc = new Element("WSMC").setAttribute("value",
						wswsModel.getWsmc());
				wsmc.setAttribute("nameCN", "文书名称");
				ws.addContent(wsmc);
			} else {
//				fileUtil.fileCopy(path, filename, specialpath
//						+ "wsmcspecial", filename);
			}
			// 创建案号节点 ,若案号为空，则将文书提取放入ahspecial文件夹
			if (wswsModel.getAh() != null) {
				Element ah = new Element("AH").setAttribute("value",
						wswsModel.getAh());
				ah.setAttribute("nameCN", "案号");
				ws.addContent(ah);
			} else {
//				fileUtil.fileCopy(path, filename,
//						specialpath + "ahspecial", filename);
			}
			// 创建立案年度节点 ,若立案年度为空，则将文书提取放入landspecial文件夹
			if (wswsModel.getLand() != null) {
				Element land = new Element("LAND").setAttribute("value",
						wswsModel.getLand());
				land.setAttribute("nameCN", "立案年度");
				ws.addContent(land);
			} else {
				// fileUtil.fileCopy(path, filename,
				// specialpath+"landspecial", filename);
			}
			// 创建案件性质节点
			if (wswsModel.getAjxz() != null) {
				Element ajxz = new Element("AJXZ").setAttribute("value",
						wswsModel.getAjxz());
				ajxz.setAttribute("nameCN", "案件性质");
				ws.addContent(ajxz);
			}
			// 创建文书种类节点
			if (wswsModel.getWszl() != null) {
				Element wszl = new Element("WSZL").setAttribute("value",
						wswsModel.getWszl());
				wszl.setAttribute("nameCN", "文书种类");
				ws.addContent(wszl);
			}
			// 创建审判程序节点
			if (wswsModel.getSpcx() != null) {
				Element spcx = new Element("SPCX").setAttribute("value",
						wswsModel.getSpcx());
				spcx.setAttribute("nameCN", "审判程序");
				ws.addContent(spcx);
			}
			// 创建案件类型节点
			if (wswsModel.getAjlx() != null) {
				Element ajlx = new Element("AJLX").setAttribute("value",
						wswsModel.getAjlx());
				ajlx.setAttribute("nameCN", "案件类型");
				ws.addContent(ajlx);
			}							
		} else {
			// fileUtil.fileCopy(path, filename, specialpath+"wsspecial",
			// filename);
		}		
		
	}
	private static Element buildElement(Element root, String name, String nameCN, String value){
		Element element = new Element(name);
		if(value != null)
			element.setAttribute("value", value);
		if(nameCN != null)
			element.setAttribute("nameCN", nameCN);
		root.addContent(element);
		return element;
	}

	/**
	 * 创建诉讼参与人节点
	 * @param root
	 * @param wsModel
	 */
	private static void buildSscyr(Element root,WsModel wsModel) {
		if(wsModel == null)
			return;
		// 创建诉讼参与人节点 ,若诉讼参与人为空，则将文书提取放入sscyrspecial文件夹
		if(wsModel.getWssscyrSegment()!=null && wsModel.getWssscyrSegment().size()>0){
			List<String> sscyr = wsModel.getWssscyrSegment();	
			List<WssscyrModel> wssscyrModellist = wsModel.getWssscyrModels();
			WscpjgModel wscpjgModel = wsModel.getWscpjgModel();
			Element sscyrqj=new Element ("SSCYRQJ").setAttribute("value",ListToString.List2String(wsModel.getWssscyrSegment()));
			sscyrqj.setAttribute("nameCN","诉讼参与人全集");
			root.addContent(sscyrqj);
			for(int i=0;i<sscyr.size();i++){
				WssscyrModel wssscyrModel=wssscyrModellist.get(i);
				Element sscyren=new Element ("SSCYR").setAttribute("value",sscyr.get(i));
				sscyren.setAttribute("nameCN","诉讼参与人");
				sscyrqj.addContent(sscyren);

				// 创建诉讼参与人名称节点
				if(wssscyrModel.getSscyr()!=null){
					Element sscyrmc=new Element ("SSCYRMC").setAttribute("value",wssscyrModel.getSscyr());
					sscyrmc.setAttribute("nameCN","诉讼参与人名称");
					sscyren.addContent(sscyrmc);
				}
				// 创建诉讼身份节点
				if(wssscyrModel.getSssf()!=null){
					Element sssf=new Element ("SSSF").setAttribute("value",wssscyrModel.getSssf());
					sssf.setAttribute("nameCN","诉讼身份");
					sscyren.addContent(sssf);
				}
				//创建诉讼地位节点
				if(wssscyrModel.getSsdw()!=null){
					Element ssdw=new Element ("SSDW").setAttribute("value",wssscyrModel.getSsdw());
					ssdw.setAttribute("nameCN","诉讼地位");
					sscyren.addContent(ssdw);
				}
				//创建原审诉讼地位节点
				if(wssscyrModel.getYsssdw()!=null){
					Element ysssdw=new Element ("YSSSDW").setAttribute("value",wssscyrModel.getYsssdw());
					ysssdw.setAttribute("nameCN","原审诉讼地位");
					sscyren.addContent(ysssdw);
				}
				//创建二审诉讼地位节点
				if(wssscyrModel.getEsssdw()!=null){
					Element esssdw=new Element ("ESSSDW").setAttribute("value",wssscyrModel.getEsssdw());
					esssdw.setAttribute("nameCN","二审诉讼地位");
					sscyren.addContent(esssdw);
				}
				//创建行政法律关系主体
				if(wssscyrModel.getXzfagxzt()!=null){
					Element xzflgxzt=new Element("XZFLGXZT").setAttribute("value",wssscyrModel.getXzfagxzt());
					xzflgxzt.setAttribute("nameCN","行政法律关系主体");
					sscyren.addContent(xzflgxzt);
				}
				//创建当事人类别节点
				if(wssscyrModel.getDsrlb()!=null){
					Element dsrlb=new Element("DSRLB").setAttribute("value", wssscyrModel.getDsrlb());
					dsrlb.setAttribute("nameCN","当事人类别");
					sscyren.addContent(dsrlb);
				}
				// 创建当事人类型节点
				if(wssscyrModel.getDsrlx()!=null){
					Element dsrlx=new Element ("DSRLX").setAttribute("value",wssscyrModel.getDsrlx());
					dsrlx.setAttribute("nameCN","当事人类型");
					sscyren.addContent(dsrlx);
				}
				// 创建诉讼性别节点
				if(wssscyrModel.getXb()!=null){
					Element xb=new Element ("XB").setAttribute("value",wssscyrModel.getXb());
					xb.setAttribute("nameCN","性别");
					sscyren.addContent(xb);
				}
				// 创建民族节点
				if(wssscyrModel.getMz()!=null){
					Element mz = new Element ("MZ").setAttribute("value",wssscyrModel.getMz());
					mz.setAttribute("nameCN", "民族");
					sscyren.addContent(mz);
				}
				// 创建出生日期节点
				if(wssscyrModel.getCsrq()!=null){
					Element csrq=new Element ("CSRQ").setAttribute("value",wssscyrModel.getCsrq());
					csrq.setAttribute("nameCN","出生日期");
					sscyren.addContent(csrq);
					//创建年月日节点
					Element year = new Element("Year").setAttribute("value",wssscyrModel.getYear());
					year.setAttribute("nameCN","年");
					csrq.addContent(year);
					Element month = new Element("Month").setAttribute("value",wssscyrModel.getMonth());
					month.setAttribute("nameCN","月");
					csrq.addContent(month);
					Element day = new Element("Day").setAttribute("value",wssscyrModel.getDay());
					day.setAttribute("nameCN","日");
					csrq.addContent(day);
				}
				//创建当事人文化程度节点
				if(wssscyrModel.getDsrwhcd()!=null){
					Element whcd = new Element ("WHCD").setAttribute("value",wssscyrModel.getDsrwhcd());
					whcd.setAttribute("nameCN","文化程度");
					sscyren.addContent(whcd);
				}
				//创建国籍节点
				if (wssscyrModel.getGj()!=null){
					Element gj = new Element("GJ").setAttribute("value",wssscyrModel.getGj());
					gj.setAttribute("nameCN","国籍");
					sscyren.addContent(gj);
				}
//				// 创建当事人职务节点
//				if(wssscyrModel.getDsrzw()!=null){
//					Element dsrzw = new Element ("DSRZW").setAttribute("value", wssscyrModel.getDsrzw());
//					dsrzw.setAttribute("nameCN","当事人职务");
//					sscyren.addContent(dsrzw);
//				}
				//创建户籍所在地节点
				if (wssscyrModel.getHjd()!=null){
					Element hjszd = new Element("HJSZD").setAttribute("value",wssscyrModel.getHjd());
					hjszd.setAttribute("nameCN","户籍所在地");
					sscyren.addContent(hjszd);
				}
				// 创建当事人地址节点
				if(wssscyrModel.getDsrdz()!=null){
					Element dsrdz=new Element ("DSRDZ").setAttribute("value",wssscyrModel.getDsrdz());
					dsrdz.setAttribute("nameCN","当事人地址");
					sscyren.addContent(dsrdz);
				}
				// 创建证件号码节点
				if (wssscyrModel.getZjhm()!=null||wssscyrModel.getZjlx()!=null){
					Element zjinfo=new Element ("ZJXX").setAttribute("nameCN","证件信息");
					if (wssscyrModel.getZjlx()!=null){
						Element zjlx=new Element("ZJLX").setAttribute("value",wssscyrModel.getZjlx());
						zjlx.setAttribute("nameCN","证件类型");
						zjinfo.addContent(zjlx);
					}
					if(wssscyrModel.getZjhm()!=null){
						Element zjhm=new Element ("ZJHM").setAttribute("value",wssscyrModel.getZjhm());
						zjhm.setAttribute("nameCN","证件号码");
						zjinfo.addContent(zjhm);
					}

				}

				if(wssscyrModel.getDtqk()!=null){
					Element dtqk=new Element ("DTQK").setAttribute("value",wssscyrModel.getDtqk());
					dtqk.setAttribute("nameCN","到庭情况");
					sscyren.addContent(dtqk);
				}
				//单位性质
				if(!StringUtil.isBlank(wssscyrModel.getDwxz())){
					Element dwxz=new Element ("DWXZ").setAttribute("value",wssscyrModel.getDwxz());
					dwxz.setAttribute("nameCN","单位性质");
					sscyren.addContent(dwxz);
				}	
				//法定代表人
				if(!StringUtil.isBlank(wssscyrModel.getFddbr())){
					Element fddbr=new Element ("FDDBR").setAttribute("value",wssscyrModel.getFddbr());
					fddbr.setAttribute("nameCN","单位法定代表人");
					sscyren.addContent(fddbr);
				}
				//单位职务分组
				if(!StringUtil.isBlank(wssscyrModel.getGzdw())||!StringUtil.isBlank(wssscyrModel.getDsrzw())){
					Element dwzefz=new Element ("DWZWFZ").setAttribute("nameCN","单位职务分组");
					sscyren.addContent(dwzefz);
					if(!StringUtil.isBlank(wssscyrModel.getDsrzw())){
						if(wssscyrModel.getDsrzw()!=null){
							Element zw=new Element ("ZW").setAttribute("value",wssscyrModel.getDsrzw());
							zw.setAttribute("nameCN","职务");
							dwzefz.addContent(zw);
						}
					}
					if(!StringUtil.isBlank(wssscyrModel.getGzdw())){
						Element dwmc =new Element ("DWMC").setAttribute("value",wssscyrModel.getGzdw());
						dwmc.setAttribute("nameCN","单位名称");
						dwzefz.addContent(dwmc);
					}
					if(!StringUtil.isBlank(wssscyrModel.getGzdwxz())){
						Element dwxz =new Element ("DWXZ").setAttribute("value",wssscyrModel.getGzdwxz());
						dwxz.setAttribute("nameCN","单位性质");
						dwzefz.addContent(dwxz);
					}
				}
				//当事人是否再婚
				if(wssscyrModel.getDsrsfzh()!=null){
					Element dsrsfzh=new Element ("DSRSFZH").setAttribute("value",wssscyrModel.getDsrsfzh());
					dsrsfzh.setAttribute("nameCN","当事人是否再婚");
					sscyren.addContent(dsrsfzh);
				}
				//特殊行业
				if(wssscyrModel.getTshy()!=null){
					Element tshy=new Element ("TSHY").setAttribute("value",wssscyrModel.getTshy());
					tshy.setAttribute("nameCN","特殊行业");
					sscyren.addContent(tshy);
				}
				//被告类型
				if(wssscyrModel.getBglx()!=null){
					Element bglx=new Element("BGLX").setAttribute("value", wssscyrModel.getBglx());
					bglx.setAttribute("nameCN", "被告类型");
					sscyren.addContent(bglx);
				}
				//交通事故 是否承担责任
				if(wssscyrModel.getJtsgzr()!=null){
					Element sfcdzr=new Element("JTSFZR").setAttribute("value", wssscyrModel.getJtsgzr());
					sfcdzr.setAttribute("nameCN", "交通事故责任");
					sscyren.addContent(sfcdzr);
				}
				//组织机构代码
				if(wssscyrModel.getZzjgdm()!=null){
					Element zzjgdm=new Element("ZZJGDM").setAttribute("value", wssscyrModel.getZzjgdm());
					zzjgdm.setAttribute("nameCN", "组织机构代码");
					sscyren.addContent(zzjgdm);
				}
				//行政管理范围
				if (wssscyrModel.getXzglfw()!=null){
					Element xzglfw=new Element("XZGLFW").setAttribute("value", wssscyrModel.getXzglfw());
					xzglfw.setAttribute("nameCN", "行政管理范围");
					sscyren.addContent(xzglfw);
				}
				//自然人身份
				if (wssscyrModel.getZrrsf()!=null){
					Element zrrsf=new Element("ZRRSF").setAttribute("value", wssscyrModel.getZrrsf());
					zrrsf.setAttribute("nameCN", "自然人身份");
					sscyren.addContent(zrrsf);
				}
				//是否被害人
				if (wssscyrModel.getIsBhr()!=null){
					Element isBhr = new Element("SFBHR").setAttribute("value",wssscyrModel.getIsBhr());
					isBhr.setAttribute("nameCN","是否被害人");
					sscyren.addContent(isBhr);
				}
				//附带民事诉讼原告人身份
				if (wssscyrModel.getMsssygrlx()!=null){
					Element msssygrlx = new Element("FDMSSSYGRLX").setAttribute("value",wssscyrModel.getMsssygrlx());
					msssygrlx.setAttribute("nameCN","附带民事诉讼原告人身份");
					sscyren.addContent(msssygrlx);
				}
				if (wssscyrModel.getXszrablity()!=null){
					Element xszrnl = new Element("XSZRNL").setAttribute("value",wssscyrModel.getXszrablity());
					xszrnl.setAttribute("nameCN","刑事责任能力");
					sscyren.addContent(xszrnl);
				}
				//缓刑期内犯罪
				if (wssscyrModel.getHxkyqfz()!=null){
					Element hxkyqfz = new Element("HXKYQNFZ").setAttribute("value",wssscyrModel.getHxkyqfz());
					hxkyqfz.setAttribute("nameCN","缓刑考验期内犯罪");
					sscyren.addContent(hxkyqfz);
				}
				//假释期内犯罪
				if (wssscyrModel.getJskyqfz()!=null){
					Element jskyqfz = new Element("JSKYQNFZ").setAttribute("value",wssscyrModel.getJskyqfz());
					jskyqfz.setAttribute("nameCN","假释考验期内犯罪");
					sscyren.addContent(jskyqfz);
				}
				//现羁押场所
				if (wssscyrModel.getXjycs()!=null){
					Element xjycs = new Element("XJYCS").setAttribute("value",wssscyrModel.getXjycs());
					xjycs.setAttribute("nameCN","现羁押场所");
					sscyren.addContent(xjycs);
				}
				//强制措施
				if (wssscyrModel.getQzcsList()!=null){
					for (QzcsModel qzcsModel:wssscyrModel.getQzcsList()) {
						Element qzcs = new Element("QZCS").setAttribute("nameCN","强制措施");
						//强制措施种类
						if (qzcsModel.getQzcsCategory()!=null){
							Element qzcszl = new Element("QZCSZL").setAttribute("value",qzcsModel.getQzcsCategory());
							qzcszl.setAttribute("nameCN","强制措施种类");
							qzcs.addContent(qzcszl);
						}
						//强制措施时间
						if (qzcsModel.getQzcsTime()!=null){
							Element qzcstime = new Element("QZCSZXSJ").setAttribute("value",qzcsModel.getQzcsTime());
							qzcstime.setAttribute("nameCN","强制措施执行时间");
							qzcs.addContent(qzcstime);
						}
						//强制措施单位
						if (qzcsModel.getQzcsDw()!=null){
							Element qzcsdw = new Element("QZCSZXDW").setAttribute("value",qzcsModel.getQzcsDw());
							qzcsdw.setAttribute("nameCN","强制措施执行单位");
							qzcs.addContent(qzcsdw);
						}
						//强制措施原因
						if (qzcsModel.getQscsReason()!=null){
							Element qzcsReasonGroup = new Element("QZCSYYFZ").setAttribute("nameCN","强制措施原因组");
							for(int j=0;j<qzcsModel.getQscsReason().size();j++) {
								if (qzcsModel.getQscsReason()!=null){
									Element qzcsyy = new Element("QZCSYY").setAttribute("value",qzcsModel.getQscsReason().get(0));
									qzcsyy.setAttribute("nameCN","强制措施原因");
									qzcsReasonGroup.addContent(qzcsyy);
									break;
								}
							}
							qzcs.addContent(qzcsReasonGroup);
						}
						sscyren.addContent(qzcs);
					}

				}
				//前科劣迹
				if (wssscyrModel.getQkqkList()!=null){
					for (QkqkModel qkqkModel:wssscyrModel.getQkqkList()){
						Element qklj = new Element("QKLJ").setAttribute("nameCN","前科劣迹");
						//前科类别
						if (qkqkModel.getQklb()!=null){
							Element qklb= new Element("QKLB").setAttribute("value",qkqkModel.getQklb());
							qklb.setAttribute("nameCN","前科类别");
							qklj.addContent(qklb);
						}
						//处罚时间
						if (qkqkModel.getCfTime()!=null){
							Element cfTime = new Element("CFSJ").setAttribute("value",qkqkModel.getCfTime());
							cfTime.setAttribute("nameCN","处罚时间");
							qklj.addContent(cfTime);
						}
						//处罚原因
						if (qkqkModel.getCfReason()!=null){
							Element cfReasonGroup = new Element("CFYYZ").setAttribute("nameCN","处罚原因组");
							for(int j=0;j<qkqkModel.getCfReason().size();j++) {
								if (qkqkModel.getCfReason()!=null){
									Element cfyy = new Element("CFYY").setAttribute("value",qkqkModel.getCfReason().get(0));
									cfyy.setAttribute("nameCN","处罚原因");
									cfReasonGroup.addContent(cfyy);
									break;
								}
							}
						}
						//处罚单位
						if (qkqkModel.getCfdw()!=null){
							Element cfdw = new Element("CFDW").setAttribute("value",qkqkModel.getCfdw());
							cfdw.setAttribute("nameCN","处罚单位");
							qklj.addContent(cfdw);
						}
						//处罚形式
						if (qkqkModel.getCfxs()!=null){
							Element cfxs = new Element("CFXS").setAttribute("value",qkqkModel.getCfxs());
							cfxs.setAttribute("nameCN","处罚形式");
							qklj.addContent(cfxs);
						}
						//处罚刑期
						if (qkqkModel.getCfxq()!=null){
							Element cfxqGroup = new Element("CFXQZ").setAttribute("nameCN", "处罚刑期组");
							for (String xq:qkqkModel.getCfxq()){
								Element cfxq = new Element("CFXQ").setAttribute("value",xq);
								cfxq.setAttribute("nameCN","处罚刑期");
								cfxqGroup.addContent(cfxq);
							}
							qklj.addContent(cfxqGroup);
						}
						//刑满释放日
						if (qkqkModel.getXmsfTime()!=null){
							Element xmsfTime = new Element("XMSFRQ").setAttribute("value",qkqkModel.getXmsfTime());
							xmsfTime.setAttribute("nameCN","刑满释放日期");
							qklj.addContent(xmsfTime);
						}
						sscyren.addContent(qklj);
					}
				}

			}
//			交通事故保险公司是否作为被告
			if(wscpjgModel!=null && wscpjgModel.getBsgssfbg()!=null){
				Element bx=new Element ("SSCYRQJ").setAttribute("value",wscpjgModel.getBsgssfbg());
				bx.setAttribute("nameCN","保险公司是否作为被告");
				sscyrqj.addContent(bx);
			}
		}else{
//			fileUtil.fileCopy(path, filename, specialpath+"sscyrspecial", filename);
		}
		
		
	}
	/**
	 * 创建诉讼记录节点
	 * @param root
	 * @param wsModel
	 */
	private static void buildSsjl(Element root,WsModel wsModel) {
		if(wsModel == null)
			return;
		// 创建诉讼记录节点
		if (wsModel.getWsssjlSegment() != null) {
			WsssjlModel wsssjlModel = wsModel.getWsssjlModel();
			Element ssjl = new Element("SSJL").setAttribute("value",
					wsModel.getWsssjlSegment());
			ssjl.setAttribute("nameCN", "诉讼记录");
			//-----18.3.22----
			//起诉状
			if(wsssjlModel.getQsz() != null){
				SsrqydsrModel qsz1 = wsssjlModel.getQsz();
				Element element = buildElement(ssjl, "QSZ", "起诉状", null);
				buildElement(element, "QSR", qsz1.getSf(), qsz1.getName());
				buildElement(element, "RQ", "日期", qsz1.getDate());
			}
			//反诉状
			if(wsssjlModel.getFsz() != null){
				SsrqydsrModel fsz1 = wsssjlModel.getFsz();
				Element element = buildElement(ssjl, "FSZ", "反诉状", null);
				buildElement(element, "FSR", fsz1.getSf(), fsz1.getName());
				buildElement(element, "RQ", "日期", fsz1.getDate());
			}
			//拒绝出庭
			if(wsssjlModel.getJjct() != null){
				SsrqydsrModel jjct1 = wsssjlModel.getJjct();
				Element element = buildElement(ssjl, "JJCT", "拒绝出庭", null);
				buildElement(element, "DSR", jjct1.getSf(), jjct1.getName());
				buildElement(element, "RQ", "日期", jjct1.getDate());
			}
			//撤诉
			if(wsssjlModel.getCs() != null){
				SsrqydsrModel cs1 = wsssjlModel.getCs();
				Element cs = buildElement(ssjl, "CS","撤诉",null);
				buildElement(cs, "CSR", cs1.getSf(), cs1.getName());
				buildElement(cs, "RQ", "日期", cs1.getDate());
			}
			//反诉
			if (wsssjlModel.getFs() != null){
				FsModel fs = wsssjlModel.getFs();
				Element element = buildElement(ssjl, "FS", "反诉", null);
				buildElement(element, "YG", "原告", fs.getYg());
				buildElement(element, "BG", "被告", fs.getBg());
				buildElement(element, "RQ", "日期", fs.getFsrq());
			}
			//-----end 18.3.22-----
			//-----18.3.8-----
			// 创建原告节点
			if (wsssjlModel.getYg() != null) {
				buildElement(ssjl, "YG", "原告", wsssjlModel.getYg());
			}
			// 创建被告节点
			if (wsssjlModel.getBg() != null) {
				buildElement(ssjl, "BG", "被告", wsssjlModel.getBg());
			}
			// 创建立案法院节点
			if (wsssjlModel.getLafy() != null) {
				buildElement(ssjl, "LAFY", "立案法院", wsssjlModel.getLafy());
			}
			// 创建上诉人节点
			if (wsssjlModel.getSsr() != null) {
				buildElement(ssjl, "SSR", "上诉人", wsssjlModel.getSsr());
			}
			// 创建上诉裁定节点
			if (wsssjlModel.getSscdah() != null && wsssjlModel.getSscdfymc() != null) {
				Element sscd = buildElement(ssjl, "SSCD", "上诉裁定", null);
				buildElement(sscd, "SSCDAH", "案号", wsssjlModel.getSscdah());
				buildElement(sscd, "SSCDFYMC", "法院名称", wsssjlModel.getSscdfymc());
			}
			//-------end 18.3.8------
			// 创建案由节点
			if (wsssjlModel.getAy() != null) {
				Element ay = new Element("AY").setAttribute("value",
						wsssjlModel.getAy());
				ay.setAttribute("nameCN", "案由");
				ssjl.addContent(ay);
			}
			// 创建案件来源节点
			if (wsssjlModel.getAjly() != null) {
				Element ajly = new Element("AJLY").setAttribute("value",
						wsssjlModel.getAjly());
				ajly.setAttribute("nameCN", "案件来源");
				ssjl.addContent(ajly);
			}
			// 创建案件涉及节点
			if (wsssjlModel.getAjsj() != null) {
				Element ajsj = new Element("AJSJ").setAttribute("value",
						wsssjlModel.getAjsj());
				ajsj.setAttribute("nameCN", "案件涉及");
				ssjl.addContent(ajsj);
			}
			// 创建开庭审理节点
			if (wsssjlModel.getKtsl() != null) {
				Element ktsl = new Element("KTSL").setAttribute("value",
						wsssjlModel.getKtsl());
				ktsl.setAttribute("nameCN", "开庭审理");
				ssjl.addContent(ktsl);
			}
			// 创建开庭日期节点
			if (wsssjlModel.getKtrq() != null) {
				for (int i = 0; i < wsssjlModel.getKtrq().size(); i++) {
					if (wsssjlModel.getKtrq().get(i) != null) {
						Element ktrq = new Element("KTRQ").setAttribute(
								"value", wsssjlModel.getKtrq().get(i));
						ktrq.setAttribute("nameCN", "开庭日期");
					}
				}
			}
			// 创建前审案号节点
			if (wsssjlModel.getQsah() != null) {
				for (int i = 0; i < wsssjlModel.getQsah().size(); i++) {
					Element qsah = new Element("QSAH").setAttribute(
							"value", wsssjlModel.getQsah().get(i));
					qsah.setAttribute("nameCN", "前审案号");
					ssjl.addContent(qsah);
				}
			}
			// 创建开庭审理信息节点
			if (wsssjlModel.getKtslxx() != null) {
				Element ktslxx = new Element("KTSLXX").setAttribute(
						"value", wsssjlModel.getKtslxx());
				// 创建不公开审理原因节点
				if (wsssjlModel.getKtslxx().contains("不公开")) {
					if (wsssjlModel.getBgkslyy() != null) {
						Element bgkslyy = new Element("BGKSLYY")
								.setAttribute("value",
										wsssjlModel.getBgkslyy());
						bgkslyy.setAttribute("nameCN", "不公开审理原因");
						ktslxx.addContent(bgkslyy);
					}
				}
				ktslxx.setAttribute("nameCN", "开庭审理信息");
				ssjl.addContent(ktslxx);
			}
			// 创建完整案由节点
			if (wsssjlModel.getWzay() != null) {
				Element wzay = new Element("WZAY").setAttribute("value",
						wsssjlModel.getWzay());
				wzay.setAttribute("nameCN", "完整案由");
				ssjl.addContent(wzay);
			}
			// 创建出庭检察员相关节点
			if (wsssjlModel.getJcy() != null) {
				Element ctspy = new Element("CTSPY").setAttribute("nameCN",
						"出庭审判员");
				Element jcyfz = new Element("JCYFZ").setAttribute("nameCN",
						"检察员分组");
				Element js = new Element("JS").setAttribute("value",
						wsssjlModel.getJs());
				js.setAttribute("nameCN", "角色");
				Element jcy = new Element("JCY").setAttribute("value",
						wsssjlModel.getJcy());
				jcy.setAttribute("nameCN", "检察员");
				jcyfz.addContent(js);
				jcyfz.addContent(jcy);
				ctspy.addContent(jcyfz);
				ssjl.addContent(ctspy);
			}
			// 创建诉讼性质节点
			if (wsssjlModel.getSsxz() != null) {
				Element ssxz = new Element("SSXZ").setAttribute("value",
						wsssjlModel.getSsxz());
				ssxz.setAttribute("nameCN", "诉讼性质");
				ssjl.addContent(ssxz);
			}
			// 创建检察院建议延期审理
			if (wsssjlModel.getJcyjyyqsl() != null) {
				Element jcyjyyqsl = new Element("JCYJYYQSL").setAttribute(
						"value", wsssjlModel.getJcyjyyqsl());
				jcyjyyqsl.setAttribute("nameCN", "检察院建议延期审理");
				ssjl.addContent(jcyjyyqsl);
			}
			// 创建少年法庭节点
			if (wsssjlModel.getSnft() != null) {
				Element snft = new Element("SNFT").setAttribute("value",
						wsssjlModel.getSnft());
				snft.setAttribute("nameCN", "少年法庭");
				ssjl.addContent(snft);
			}
			// 创建指控信息相关节点
			if (wsssjlModel.getWsssjlZkjl() != null) {
				Element zkxx = new Element("ZKXX").setAttribute("nameCN",
						"指控信息");
				for (int i = 0; i < wsssjlModel.getWsssjlZkjl().size(); i++) {
					Element zkjl = new Element("ZKJL").setAttribute(
							"nameCN", "指控记录");
					ArrayList<WsssjlZkzmModel> zkzmModellist = wsssjlModel
							.getWsssjlZkjl().get(i).getZkzmModelist();
					ArrayList<String> xgr = wsssjlModel.getWsssjlZkjl()
							.get(i).getXgr();
					for (int j = 0; j < zkzmModellist.size(); j++) {
						if (zkzmModellist != null
								&& zkzmModellist.get(j).getWzzm() != null) {
							Element zkzm = new Element("ZKZM")
									.setAttribute("value", zkzmModellist
											.get(j).getZkzm());
							zkzm.setAttribute("nameCN", "指控罪名");
							Element wzzm = new Element("WZZM")
									.setAttribute("value", zkzmModellist
											.get(j).getWzzm());
							wzzm.setAttribute("nameCN", "完整罪名");
							if (zkzmModellist.get(j).getZmdm() != null) {
								Element zmdm = new Element("ZMDM")
										.setAttribute("value",
												zkzmModellist.get(j)
														.getZmdm());
								zmdm.setAttribute("nameCN", "罪名代码");
								zkzm.addContent(zmdm);
							}
							zkzm.addContent(wzzm);
							zkjl.addContent(zkzm);
						}
					}
					for (int j = 0; j < xgr.size(); j++) {
						if (xgr != null && !xgr.get(j).contains("检察院")) {
							Element xgrlist = new Element("XGR")
									.setAttribute("value", xgr.get(j));
							xgrlist.setAttribute("nameCN", "相关人");
							zkjl.addContent(xgrlist);
						}
					}
					zkxx.addContent(zkjl);
				}
				if (zkxx.getChild("ZKJL") != null) {
					ssjl.addContent(zkxx);
				}
			}
			// 创建起诉主案由节点
			if (wsssjlModel.getQszay() != null) {
				Element qszay = new Element("QSZAY").setAttribute("value",
						wsssjlModel.getQszay());
				qszay.setAttribute("nameCN", "起诉主案由");
				ssjl.addContent(qszay);
			}
			// 创建建议适用简易程序节点
			if (wsssjlModel.getJysyjycx() != null) {
				Element jysyjycx = new Element("JYSYJYCX").setAttribute(
						"value", wsssjlModel.getJysyjycx());
				jysyjycx.setAttribute("nameCN", "建议适用简易程序");
				ssjl.addContent(jysyjycx);
			}
			// 创建案由代码节点
			if (wsssjlModel.getAydm() != null) {
				Element aydm = new Element("AYDM").setAttribute("value",
						wsssjlModel.getAydm());
				aydm.setAttribute("nameCN", "案由代码");
				ssjl.addContent(aydm);
			}
			// 创建行政行为种类节点
			if (wsssjlModel.getXzxwzl() != null) {
				Element xzxwzl = new Element("XZXWZL").setAttribute(
						"value", wsssjlModel.getXzxwzl());
				xzxwzl.setAttribute("nameCN", "行政行为种类");
				ssjl.addContent(xzxwzl);
			}// 创建行政侵权行为种类节点
			if (wsssjlModel.getXzqqxwzl() != null) {
				Element xzqqxwzl = new Element("XZQQXWZL").setAttribute(
						"value", wsssjlModel.getXzqqxwzl());
				xzqqxwzl.setAttribute("nameCN", "行政侵权行为种类");
				ssjl.addContent(xzqqxwzl);
			}
			// 创建行政二审前审案号相关节点
			if (wsssjlModel.getXzesqsah() != null) {
				Element xzesqsah = new Element("QSAH").setAttribute(
						"value", wsssjlModel.getXzesqsah());
				xzesqsah.setAttribute("nameCN", "前审案号");
				if (wsssjlModel.getQsland() != null) {
					Element qsland = new Element("QSLAND").setAttribute(
							"value", wsssjlModel.getQsland());
					qsland.setAttribute("nameCN", "前审立案年度");
					xzesqsah.addContent(qsland);
				}
				if (wsssjlModel.getQsfyjc() != null) {
					Element qsfyjc = new Element("QSFYJC").setAttribute(
							"value", wsssjlModel.getQsfyjc());
					qsfyjc.setAttribute("nameCN", "前审法院简称");
					xzesqsah.addContent(qsfyjc);
				}
				if (wsssjlModel.getQsahsxh() != null) {
					Element qsahsxh = new Element("QSAHSXH").setAttribute(
							"value", wsssjlModel.getQsahsxh());
					qsahsxh.setAttribute("nameCN", "前审案号顺序号");
					xzesqsah.addContent(qsahsxh);
				}
				ssjl.addContent(xzesqsah);
			}
			// 创建案件由来与审理经过段节点
			if (wsssjlModel.getXsesqsah() != null
					|| wsssjlModel.getQsfy() != null
					|| wsssjlModel.getQscpsj() != null) {
				Element ajylysljgd = new Element("AJYLYSLJGD").setAttribute("nameCN","案件由来与审理经过段");
				// 创建刑事二审前审案号相关节点
				if (wsssjlModel.getXsesqsah() != null) {
					Element xsesqsah = new Element("QSAH").setAttribute(
							"value", wsssjlModel.getXsesqsah());
					xsesqsah.setAttribute("nameCN", "前审案号");
					if (wsssjlModel.getQsland() != null) {
						Element qsland = new Element("QSLAND").setAttribute(
								"value", wsssjlModel.getQsland());
						qsland.setAttribute("nameCN", "前审立案年度");
						xsesqsah.addContent(qsland);
					}
					if (wsssjlModel.getQsfyjc() != null) {
						Element qsfyjc = new Element("QSFYJC").setAttribute(
								"value", wsssjlModel.getQsfyjc());
						qsfyjc.setAttribute("nameCN", "前审法院简称");
						xsesqsah.addContent(qsfyjc);
					}
					if (wsssjlModel.getQsahsxh() != null) {
						Element qsahsxh = new Element("QSAHSXH").setAttribute(
								"value", wsssjlModel.getQsahsxh());
						qsahsxh.setAttribute("nameCN", "前审案号顺序号");
						xsesqsah.addContent(qsahsxh);
					}
					ajylysljgd.addContent(xsesqsah);
				}
				// 创建二审前审法院相关节点
				if (wsssjlModel.getQsfy() != null) {
					Element qsfy = new Element("QSFY").setAttribute("value",
							wsssjlModel.getQsfy());
					qsfy.setAttribute("nameCN", "前审法院");
					if (wsssjlModel.getFyjb() != null) {
						Element fyjb = new Element("FYJB").setAttribute(
								"value", wsssjlModel.getFyjb());
						fyjb.setAttribute("nameCN", "法院级别");
						qsfy.addContent(fyjb);
					}
					if (wsssjlModel.getBzfymc() != null) {
						Element bzfymc = new Element("BZFYMC").setAttribute(
								"value", wsssjlModel.getBzfymc());
						bzfymc.setAttribute("nameCN", "标准法院名称");
						qsfy.addContent(bzfymc);
					}
					ajylysljgd.addContent(qsfy);
				}
				// 创建前审裁判时间节点
				if (wsssjlModel.getQscpsj() != null) {
					Element qscpsj = new Element("QSCPSJ").setAttribute(
							"value", wsssjlModel.getQscpsj());
					qscpsj.setAttribute("nameCN", "前审裁判时间");
					ajylysljgd.addContent(qscpsj);
				}
				// 创建前审判决节点
				if (wsssjlModel.getQspj() != null) {
					Element qspj = new Element("QSPJ").setAttribute("value",
							wsssjlModel.getQspj());
					qspj.setAttribute("nameCN", "前审判决");
					ajylysljgd.addContent(qspj);
				}
				// 创建前审文书种类节点
				if (wsssjlModel.getQswszl() != null) {
					Element qswszl = new Element("QSWSZL").setAttribute(
							"value", wsssjlModel.getQswszl());
					qswszl.setAttribute("nameCN", "前审文书种类");
					ajylysljgd.addContent(qswszl);
				}
				// 创建前审公诉机关
				if (wsssjlModel.getQsgsjg() != null) {
					Element qsgsjg = new Element("QSGSJG").setAttribute(
							"value", wsssjlModel.getQsgsjg());
					qsgsjg.setAttribute("nameCN", "前审公诉机关");
					ajylysljgd.addContent(qsgsjg);
				}
				// 创建前审审级节点
				if (wsssjlModel.getQssj() != null) {
					Element qssjl = new Element("QSSJ").setAttribute("value",
							wsssjlModel.getQssj());
					qssjl.setAttribute("nameCN", "前审审级");
					ajylysljgd.addContent(qssjl);
				}
				// 创建前审案件由来节点
				if (wsssjlModel.getQsajyl() != null) {
					Element qsajyl = new Element("QSAJYL").setAttribute(
							"value", wsssjlModel.getQsajyl());
					qsajyl.setAttribute("nameCN", "前审案件由来");
					ajylysljgd.addContent(qsajyl);
				}
				// 创建前审结案方式节点
				if (wsssjlModel.getQsjafs() != null) {
					Element qsjafs = new Element("QSJAFS").setAttribute(
							"value", wsssjlModel.getQsjafs());
					qsjafs.setAttribute("nameCN", "前审结案方式");
					ajylysljgd.addContent(qsjafs);
				}
				ssjl.addContent(ajylysljgd);
			}
			// 创建上诉或抗诉范围节点
			if (wsssjlModel.getSshksfw() != null) {
				Element sshksfw = new Element("SSHKSFW").setAttribute(
						"value", wsssjlModel.getSshksfw());
				sshksfw.setAttribute("nameCN", "上诉或抗诉范围");
				ssjl.addContent(sshksfw);
			}
			// 创建立案日期节点
			if (wsssjlModel.getLarq() != null) {
				Element larq = new Element("LARQ").setAttribute("value",
						wsssjlModel.getLarq());
				larq.setAttribute("nameCN", "立案日期");
				ssjl.addContent(larq);
			}
			// 创建一审案件适用程序节点
			if (wsssjlModel.getYsajsycx() != null) {
				Element ysajsycx = new Element("YSAJSYCX").setAttribute(
						"value", wsssjlModel.getYsajsycx());
				ysajsycx.setAttribute("nameCN", "一审案件适用程序");
				ssjl.addContent(ysajsycx);
			}
			// 创建简易转普通节点
			if (wsssjlModel.getJyzpt() != null) {
				Element jyzpt = new Element("JYZPT").setAttribute("value",
						wsssjlModel.getJyzpt());
				jyzpt.setAttribute("nameCN", "简易转普通");
				ssjl.addContent(jyzpt);
			}
			// 创建审判组织节点
			if (wsssjlModel.getSpzz() != null) {
				Element spzz = new Element("SPZZ").setAttribute("value",
						wsssjlModel.getSpzz());
				spzz.setAttribute("nameCN", "审判组织");
				ssjl.addContent(spzz);
			}
			// 创建一审案件来源节点
			if (wsssjlModel.getYsajly() != null) {
				Element ysajly = new Element("YSAJLY").setAttribute(
						"value", wsssjlModel.getYsajly());
				ysajly.setAttribute("nameCN", "一审案件来源");
				ssjl.addContent(ysajly);
			}
			// 创建公诉机关节点
			if (wsssjlModel.getGsjg() != null) {
				Element gsjg = new Element("GSJG").setAttribute("value",
						wsssjlModel.getGsjg());
				gsjg.setAttribute("nameCN", "公诉机关");
				ssjl.addContent(gsjg);
			}
			// 创建公诉案号节点
			if (wsssjlModel.getGsah() != null) {
				Element gsah = new Element("GSAH").setAttribute("value",
						wsssjlModel.getGsah());
				gsah.setAttribute("nameCN", "公诉案号");
				ssjl.addContent(gsah);
			}
			// 创建受理日期节点
			if (wsssjlModel.getSlrq() != null) {
				Element slrq = new Element("SLRQ").setAttribute("value",
						wsssjlModel.getSlrq());
				slrq.setAttribute("nameCN", "受理日期");
				ssjl.addContent(slrq);
			}
			// 创建起诉日期节点
			if (wsssjlModel.getQsrq() != null) {
				Element qsrq = new Element("QSRQ").setAttribute("value",
						wsssjlModel.getQsrq());
				qsrq.setAttribute("nameCN", "起诉日期");
				ssjl.addContent(qsrq);
			}
			// 创建申请撤诉日期节点
			if (wsssjlModel.getSqcsrq() != null) {
				Element sqcsrq = new Element("SQCSRQ").setAttribute(
						"value", wsssjlModel.getSqcsrq());
				sqcsrq.setAttribute("nameCN", "申请撤诉日期");
				ssjl.addContent(sqcsrq);
			}
			// 创建被告主要领导出庭节点
			if (wsssjlModel.getBgzyldct() != null) {
				Element bgzyldct = new Element("BGZYLDCT").setAttribute(
						"value", wsssjlModel.getBgzyldct());
				bgzyldct.setAttribute("nameCN", "被告主要领导出庭");
				ssjl.addContent(bgzyldct);
			}
			// 创建当事人缺席、缺席人姓名、缺席人诉讼地位节点
			if (wsssjlModel.getQxrxx()!=null && !wsssjlModel.getQxrxx().isEmpty()) {
				Element dsrqx = new Element("DXRQX");
				dsrqx.setAttribute("nameCN", "当事人缺席");
				HashMap<String, String> map = wsssjlModel.getQxrxx();
				for (Map.Entry<String, String> entry : map.entrySet()) {
					if (entry.getKey() != null) {
						Element qxrxm = new Element("XM").setAttribute(
								"value", entry.getKey());
						qxrxm.setAttribute("nameCN", "姓名");
						if (entry.getValue() != null) {
							Element qxrssdw = new Element("SSDW")
									.setAttribute("value", entry.getValue());
							qxrssdw.setAttribute("nameCN", "诉讼地位");
							qxrxm.addContent(qxrssdw);
						}
						dsrqx.addContent(qxrxm);
					}
				}
				ssjl.addContent(dsrqx);
			}
			// 创建出庭当事人信息、出庭人姓名、出庭人诉讼身份节点
			if (wsssjlModel.getCtrxx()!=null && !wsssjlModel.getCtrxx().isEmpty()) {
				Element dsrqx = new Element("CTDSRXX");
				dsrqx.setAttribute("nameCN", "出庭当事人信息");
				HashMap<String, String> map = wsssjlModel.getCtrxx();
				for (Map.Entry<String, String> entry : map.entrySet()) {
					if (entry.getKey() != null) {
						Element ctrxm = new Element("CTRXM").setAttribute(
								"value", entry.getKey());
						ctrxm.setAttribute("nameCN", "出庭人姓名");
						if (entry.getValue() != null) {
							Element ctrssdw = new Element("SSDW")
									.setAttribute("value", entry.getValue());
							ctrssdw.setAttribute("nameCN", "诉讼地位");
							ctrxm.addContent(ctrssdw);
						}
						dsrqx.addContent(ctrxm);
					}
				}
				ssjl.addContent(dsrqx);
			}
			root.addContent(ssjl);
		} else {
			// fileUtil.fileCopy(path, filename, specialpath+"ssjlspecial",
			// filename);
		}
		
	}
	/**
	 * 创建案件基本情况节点
	 * @param root
	 * @param wsModel
	 * @param wsxszjdModellist
	 */
	private static void buildAjjbq(Element root,WsModel wsModel,List<WsxszjdModel> wsxszjdModellist){
		if(wsModel == null)
			return;
		// 创建案件基本情况节点 ,若为空，则将文书提取放入ajjbqkspecial文件夹		
		if (wsModel.getWsajjbqSegment() != null) {
			if (wsModel.getWsajjbqSegment() != null) {
				WsajjbqkModel wsajjbqkModel = wsModel.getWsajjbqkModel();
				Element ajjbqk = new Element("AJJBQK").setAttribute(
						"value", wsModel.getWsajjbqSegment());
				ajjbqk.setAttribute("nameCN", "案件基本情况");
				root.addContent(ajjbqk);
				if (wsajjbqkModel.getQysdl() != null) {
					Element qysdl = new Element("QYSDL").setAttribute(
							"value", wsajjbqkModel.getQysdl());
					qysdl.setAttribute("nameCN", "前一审段落");
					ajjbqk.addContent(qysdl);
					if (wsajjbqkModel.getQyspjd() != null) {
						Element qyspjd = new Element("QYSPJD")
						.setAttribute("value",
								wsajjbqkModel.getQyspjd());
						qyspjd.setAttribute("nameCN", "前一审判决段");
						qysdl.addContent(qyspjd);
					}
				}
				if (wsajjbqkModel.getQsdl() != null) {
					Element qsdl = new Element("QSDL").setAttribute(
							"value", wsajjbqkModel.getQsdl());
					qsdl.setAttribute("nameCN", "前审段落");
					ajjbqk.addContent(qsdl);
					if (wsajjbqkModel.getQsygscd() != null) {
						Element qsygscd = new Element("QSYGSCD")
						.setAttribute("value",
								wsajjbqkModel.getQsygscd());
						qsygscd.setAttribute("nameCN", "前审原告诉称段");
						qsdl.addContent(qsygscd);
					}
					if (wsajjbqkModel.getQsbgbcd() != null) {
						Element qsbgbcd = new Element("QSBGBCD")
						.setAttribute("value",
								wsajjbqkModel.getQsbgbcd());
						qsbgbcd.setAttribute("nameCN", "前审被告辩称段");
						qsdl.addContent(qsbgbcd);
					}
					if (wsajjbqkModel.getQsfsscd() != null) {
						Element qsfsscd = new Element("QSFSSCD")
						.setAttribute("value",
								wsajjbqkModel.getQsfsscd());
						qsfsscd.setAttribute("nameCN", "前审反诉诉称段");
						qsdl.addContent(qsfsscd);
					}
					if (wsajjbqkModel.getQspjd() != null) {
						Element qspjd = new Element("QSPJD").setAttribute(
								"value", wsajjbqkModel.getQspjd());
						qspjd.setAttribute("nameCN", "前审判决段");
						qsdl.addContent(qspjd);
					}
					if (wsajjbqkModel.getQssld() != null) {
						List<String> qssldlist = wsajjbqkModel.getQssld();
						for (int i = 0; i < qssldlist.size(); i++) {
							Element qssld = new Element("QSSLD")
							.setAttribute("value", qssldlist.get(i));
							qssld.setAttribute("nameCN", "前审审理段");
							qsdl.addContent(qssld);
						}
					}
				}
				//-----18.3.23-----
				//反诉请求段
				if (wsajjbqkModel.getFsqqModel() != null){
					FsqqModel fsqqModel = wsajjbqkModel.getFsqqModel();
					Element e_fs = new Element("FSQQD").setAttribute("nameCN", "反诉请求段");
					Element fsr = new Element("FSR").setAttribute("nameCN", "反诉人");
					fsr.setAttribute("value", fsqqModel.getFsr());
					Element fsqq = new Element("FSQQ").setAttribute("nameCN", "反诉请求");
					fsqq.setAttribute("value", fsqqModel.getFsqq());
					Element sshly = new Element("SSHLY").setAttribute("nameCN", "事实和理由");
					sshly.setAttribute("value", fsqqModel.getSshly());
					e_fs.addContent(fsr);
					e_fs.addContent(fsqq);
					e_fs.addContent(sshly);
					ajjbqk.addContent(e_fs);
				}
				//诉讼请求段
				if (wsajjbqkModel.getSsqqModel() != null){
					SsqqModel ssqqModel = wsajjbqkModel.getSsqqModel();
					Element e_ssqq = new Element("SSQQD").setAttribute("nameCN", "诉讼请求段");
					Element qsr = new Element("QSR").setAttribute("nameCN", "起诉人");
					qsr.setAttribute("value", ssqqModel.getQsr());
					Element ssqq = new Element("FSQQ").setAttribute("nameCN", "诉讼请求");
					ssqq.setAttribute("value", ssqqModel.getSsqq());
					Element sshly = new Element("SSHLY").setAttribute("nameCN", "事实和理由");
					sshly.setAttribute("value", ssqqModel.getSshly());
					e_ssqq.addContent(qsr);
					e_ssqq.addContent(ssqq);
					e_ssqq.addContent(sshly);
					ajjbqk.addContent(e_ssqq);
				}
				//追加当事人段
				if (wsajjbqkModel.getZjdsrModel() != null){
					ZjdsrModel zjdsrModel = wsajjbqkModel.getZjdsrModel();
					Element e_zjdsr = new Element("ZJDSRD").setAttribute("nameCN", "追加当事人段");
					Element dsr = new Element("DSR").setAttribute("nameCN", "当事人");
					dsr.setAttribute("value", zjdsrModel.getDsr());
					Element sqr = new Element("SQR").setAttribute("nameCN", "申请人");
					sqr.setAttribute("value", zjdsrModel.getSqr());
					Element sqsj = new Element("SQSJ").setAttribute("nameCN", "申请时间");
					sqsj.setAttribute("value", zjdsrModel.getSqsj());
					Element sshly = new Element("SSHLY").setAttribute("nameCN", "事实和理由");
					sshly.setAttribute("value", zjdsrModel.getSshly());
					e_zjdsr.addContent(dsr);
					e_zjdsr.addContent(sqr);
					e_zjdsr.addContent(sqsj);
					e_zjdsr.addContent(sshly);
					ajjbqk.addContent(e_zjdsr);
				}
				//-----end 18.3.23-----
				//-----18.3-------
				if(wsajjbqkModel.getYsfylyd() != null){
					Element ysfyly = new Element("YSFYLYD").setAttribute(
							"value", wsajjbqkModel.getYsfylyd());
					ysfyly.setAttribute("nameCN", "移送法院理由段");
					ajjbqk.addContent(ysfyly);
				}
				if(wsajjbqkModel.getBqfylyd() != null){
					Element bqfylyd = new Element("BQFYLYD").setAttribute(
							"value", wsajjbqkModel.getBqfylyd());
					bqfylyd.setAttribute("nameCN", "报请法院理由段");
					ajjbqk.addContent(bqfylyd);
				}
				//----end 18.3----
				if (wsajjbqkModel.getYgscd() != null) {
					Element ygscd = new Element("YGSCD").setAttribute(
							"value", wsajjbqkModel.getYgscd());
					ygscd.setAttribute("nameCN", "原告诉称段");
					ajjbqk.addContent(ygscd);
					//交通事故原告诉称段 诉讼金额
					if(wsajjbqkModel.getSsqqjeList()!=null && wsajjbqkModel.getSsqqjeList().size()>0){
						Element jeele = new Element("SSJE").setAttribute("nameCN", "交通事故原告诉称诉讼金额");
						ygscd.addContent(jeele);
						for(PjjeModel je:wsajjbqkModel.getSsqqjeList()){
							Element jesl =  new Element("SSJESL").setAttribute(
									"value", je.getValue());
							jesl.setAttribute("nameCN", "金额");
							jeele.addContent(jesl);
							if(je.getCategorys()!=null && je.getCategorys().size()>0){
								for(String lx:je.getCategorys()){
									Element lxing= new Element("SSJELX").setAttribute(
											"value", lx);
									lxing.setAttribute("nameCN", "类型");
									jesl.addContent(lxing);
								}
							}
							if(je.getJsfs()!=null){
								Element jsff= new Element("JSFF").setAttribute(
										"value",je.getJsfs());
								jsff.setAttribute("nameCN", "计算方法");
								jesl.addContent(jsff);
							}
							if(je.getKssj()!=null){
								Element jsff= new Element("KSSJ").setAttribute(
										"value",je.getKssj());
								jsff.setAttribute("nameCN", "开始时间");
								jesl.addContent(jsff);
							}
							if(je.getJssj()!=null){
								Element jsff= new Element("JSSJ").setAttribute(
										"value",je.getJssj());
								jsff.setAttribute("nameCN", "结束时间");
								jesl.addContent(jsff);
							}
						}
					}
				}
				if (wsajjbqkModel.getBgbcd() != null) {
					Element bgbcd = new Element("BGBCD").setAttribute(
							"value", wsajjbqkModel.getBgbcd());
					bgbcd.setAttribute("nameCN", "被告辩称段");
					ajjbqk.addContent(bgbcd);
				}
				if (wsajjbqkModel.getFsscd() != null) {
					Element fsscd = new Element("FSSCD").setAttribute(
							"value", wsajjbqkModel.getFsscd());
					fsscd.setAttribute("nameCN", "反诉诉称段");
					ajjbqk.addContent(fsscd);
				}
				if (wsajjbqkModel.getFsbcd() != null) {
					Element fsbcd = new Element("FSBCD").setAttribute(
							"value", wsajjbqkModel.getFsbcd());
					fsbcd.setAttribute("nameCN", "反诉辩称段");
					ajjbqk.addContent(fsbcd);
				}
				if (wsajjbqkModel.getDsryjd() != null) {
					Element dsryjd = new Element("DSRYJD").setAttribute(
							"value", wsajjbqkModel.getDsryjd());
					dsryjd.setAttribute("nameCN", "第三人意见段");
					ajjbqk.addContent(dsryjd);
				}
				if (wsajjbqkModel.getZjd() != null) {
					List<String> zjdlist = wsajjbqkModel.getZjd();
					for (int i = 0; i < zjdlist.size(); i++) {
						Element zjd = new Element("ZJD").setAttribute(
								"value", zjdlist.get(i));
						zjd.setAttribute("nameCN", "证据段");
						ajjbqk.addContent(zjd);
					}
				}
				if (wsajjbqkModel.getCmssd() != null) {
					List<String> cmssdlist = wsajjbqkModel.getCmssd();
					for (int i = 0; i < cmssdlist.size(); i++) {
						Element cmssd = new Element("CMSSD").setAttribute(
								"value", cmssdlist.get(i));
						cmssd.setAttribute("nameCN", "查明事实段");
						if (wsajjbqkModel.getSgxq() != null) {
							Element sgxq = new Element("SGXQ").setAttribute(
									"value", wsajjbqkModel.getSgxq());
							sgxq.setAttribute("nameCN", "事故详情");
							cmssd.addContent(sgxq);
							if (wsajjbqkModel.getSgsj() != null) {
								Element sgsj = new Element("SGSJ").setAttribute(
										"value", wsajjbqkModel.getSgsj());
								sgsj.setAttribute("nameCN", "事故时间");
								sgxq.addContent(sgsj);
							}
							if (wsajjbqkModel.getSgdd() != null) {
								Element sgdd = new Element("SGDD").setAttribute(
										"value", wsajjbqkModel.getSgdd());
								sgdd.setAttribute("nameCN", "事故地点");
								sgxq.addContent(sgdd);
							}
						}
						if (wsajjbqkModel.getJdcglr() != null) {
							Element jdcglr = new Element("JDCGLR").setAttribute(
									"value", wsajjbqkModel.getJdcglr());
							jdcglr.setAttribute("nameCN", "机动车管理人");
							cmssd.addContent(jdcglr);
						}
						if (wsajjbqkModel.getJdcsyr() != null) {
							Element jdcsyr = new Element("JDCSYR").setAttribute(
									"value", wsajjbqkModel.getJdcsyr());
							jdcsyr.setAttribute("nameCN", "机动车所有人");
							cmssd.addContent(jdcsyr);
						}
						if (wsajjbqkModel.getGajgrdyj() != null) {
							Element gajgrdyj = new Element("GAJGRDYJ").setAttribute(
									"value", wsajjbqkModel.getGajgrdyj());
							gajgrdyj.setAttribute("nameCN", "公安机关认定意见");
							cmssd.addContent(gajgrdyj);
						}
						if (wsajjbqkModel.getShrjzd() != null) {
							Element shrjzd = new Element("SHRJZD").setAttribute(
									"value", wsajjbqkModel.getShrjzd());
							shrjzd.setAttribute("nameCN", "受害人居住地");
							cmssd.addContent(shrjzd);
						}
						if (wsajjbqkModel.getShrzy() != null) {
							Element shrzy = new Element("SHRZY").setAttribute(
									"value", wsajjbqkModel.getShrzy());
							shrzy.setAttribute("nameCN", "受害人职业");
							cmssd.addContent(shrzy);
						}
						if (wsajjbqkModel.getSftb() != null) {
							Element sftb = new Element("SFTB").setAttribute(
									"value", wsajjbqkModel.getSftb());
							sftb.setAttribute("nameCN", "是否投保");
							cmssd.addContent(sftb);
						}
						if (wsajjbqkModel.getTbxz() != null) {
							Element tbxz = new Element("TBXZ").setAttribute(
									"value", wsajjbqkModel.getTbxz());
							tbxz.setAttribute("nameCN", "投保险种");
							cmssd.addContent(tbxz);
						}
						if (wsajjbqkModel.getSfzbxqn() != null) {
							Element sfzbxqn = new Element("SFZBXQN").setAttribute(
									"value", wsajjbqkModel.getSfzbxqn());
							sfzbxqn.setAttribute("nameCN", "是否在保险期内");
							cmssd.addContent(sfzbxqn);
						}
						if (wsajjbqkModel.getSfxqpf() != null) {
							Element sfxqpf = new Element("SFXQPF").setAttribute(
									"value", wsajjbqkModel.getSfxqpf());
							sfxqpf.setAttribute("nameCN", "是否先期赔付");
							cmssd.addContent(sfxqpf);
						}
						if (wsajjbqkModel.getShangQing() != null) {
							Element shangQing = new Element("SQ").setAttribute(
									"value", wsajjbqkModel.getShangQing());
							shangQing.setAttribute("nameCN", "伤情");
							cmssd.addContent(shangQing);
						}
						if (wsajjbqkModel.getRealPay() != null) {
							Element sjzcqk = new Element("SJZCQK").setAttribute(
									"value", wsajjbqkModel.getRealPay());
							sjzcqk.setAttribute("nameCN", "实际支出情况");
							cmssd.addContent(sjzcqk);
						}
						if (wsajjbqkModel.getIdentifyContent() != null) {
							Element xgjd = new Element("XGJD").setAttribute(
									"value", wsajjbqkModel.getIdentifyContent());
							xgjd.setAttribute("nameCN", "相关鉴定");
							cmssd.addContent(xgjd);
						}
						if (wsajjbqkModel.getJdsfkk() != null) {
							Element jdsfkk = new Element("JDSFKK").setAttribute(
									"value", wsajjbqkModel.getJdsfkk());
							jdsfkk.setAttribute("nameCN", "鉴定是否可靠");
							cmssd.addContent(jdsfkk);
						}
						ajjbqk.addContent(cmssd);
					}
				}
				if (wsajjbqkModel.getXzsszyd() != null) {
					Element xzsszyd = new Element("XZSSZYD").setAttribute(
							"value", wsajjbqkModel.getXzsszyd());
					xzsszyd.setAttribute("nameCN", "行政诉讼争议段");
					ajjbqk.addContent(xzsszyd);
				}
				if(wsajjbqkModel.getBsdl()!=null){
					Element bsdl=new Element ("BSDL").setAttribute("value",wsajjbqkModel.getBsdl()); 
					bsdl.setAttribute("nameCN","本审段落");
					ajjbqk.addContent(bsdl);
					if(wsajjbqkModel.getSsrscd()!=null){
						Element ssrscd=new Element ("SSRSCD").setAttribute("value",wsajjbqkModel.getSsrscd()); 
						ssrscd.setAttribute("nameCN","上诉人诉称段");
						bsdl.addContent(ssrscd);
					}
					if(wsajjbqkModel.getBssrbcd()!=null){
						List<String> bssrbcdlist=wsajjbqkModel.getBssrbcd();
						for(int i=0;i<bssrbcdlist.size();i++){
							Element bssrbcd=new Element ("BSSRBCD").setAttribute("value",bssrbcdlist.get(i)); 
							bssrbcd.setAttribute("nameCN","被上诉人辩称段");
							bsdl.addContent(bssrbcd);
						}
					}
					if(wsajjbqkModel.getBszjd()!=null){
						List<String> bszjdlist=wsajjbqkModel.getBszjd();
						for(int i=0;i<bszjdlist.size();i++){
							Element bszjd=new Element ("BSZJD").setAttribute("value",bszjdlist.get(i)); 
							bszjd.setAttribute("nameCN","本审证据段");
							bsdl.addContent(bszjd);
						}
					}
					if(wsajjbqkModel.getBssld()!=null){
						List<String> bssldlist=wsajjbqkModel.getBssld();
						for(int i=0;i<bssldlist.size();i++){
							Element bssld=new Element ("BSSLD").setAttribute("value",bssldlist.get(i)); 
							bssld.setAttribute("nameCN","本审审理段");
							bsdl.addContent(bssld);
						}
					}
				}
				if(wsajjbqkModel.getZkdl()!=null){
					Element zkdl=new Element ("ZKDL").setAttribute("value",wsajjbqkModel.getZkdl()); 
					zkdl.setAttribute("nameCN","指控段落");
					ajjbqk.addContent(zkdl);
					if(wsajjbqkModel.getZkss()!=null){
						Element zkss=new Element ("ZKSS").setAttribute("value",wsajjbqkModel.getZkss()); 
						zkss.setAttribute("nameCN","指控事实");
						zkdl.addContent(zkss);
					}
					if(wsajjbqkModel.getZkzj()!=null){
						Element zkzj=new Element ("ZKZJ").setAttribute("value",wsajjbqkModel.getZkzj()); 
						zkzj.setAttribute("nameCN","指控证据");
						zkdl.addContent(zkzj);
					}
					if(wsajjbqkModel.getZkyj()!=null){
						Element zkyj=new Element ("ZKYJ").setAttribute("value",wsajjbqkModel.getZkyj()); 
						zkyj.setAttribute("nameCN","指控意见");
						zkdl.addContent(zkyj);
					}
				}
				if(wsajjbqkModel.getBhdl()!=null){
					Element bhdl=new Element ("BHDL").setAttribute("value",wsajjbqkModel.getBhdl()); 
					bhdl.setAttribute("nameCN","辩护段落");
					ajjbqk.addContent(bhdl);
					if(wsajjbqkModel.getBgrbc()!=null){
						List<String> bgrbc=wsajjbqkModel.getBgrbc();
						for(int i=0;i<bgrbc.size();i++){
							Element bgr=new Element ("BGRBC").setAttribute("value",bgrbc.get(i)); 
							bgr.setAttribute("nameCN","被告人辩称");
							bhdl.addContent(bgr);
						}
					}
					if(wsajjbqkModel.getBhrbh()!=null){
						List<String> bhrbh=wsajjbqkModel.getBhrbh();
						for(int i=0;i<bhrbh.size();i++){
							Element bhr=new Element ("BHRBH").setAttribute("value",bhrbh.get(i)); 
							bhr.setAttribute("nameCN","辩护人辩护");
							bhdl.addContent(bhr);
						}
					}
				}
				if(wsajjbqkModel.getFdmsssqqd()!=null){
					Element fdmsssqqd=new Element ("FDMSSSQQD").setAttribute("value",wsajjbqkModel.getFdmsssqqd()); 
					fdmsssqqd.setAttribute("nameCN","附带民事诉讼请求段");
					ajjbqk.addContent(fdmsssqqd);
				}
				if(wsajjbqkModel.getXsbssld()!=null){
					Element xsbssld=new Element ("BSSLD").setAttribute("value",wsajjbqkModel.getXsbssld()); 
					xsbssld.setAttribute("nameCN","本审审理段");
					ajjbqk.addContent(xsbssld);
					if(wsxszjdModellist!=null){
						for(int i=0;i<wsxszjdModellist.size();i++){
							WsxszjdModel wsxszjdmodel=wsxszjdModellist.get(i);
							Element zjfz=new Element ("ZJFZ"); 
							zjfz.setAttribute("nameCN","证据分组");
							xsbssld.addContent(zjfz);
							if(wsxszjdmodel.getRdss()!=null){
								Element rdss=new Element ("RDSS").setAttribute("value",wsxszjdmodel.getRdss()); 
								rdss.setAttribute("nameCN","认定事实");
								zjfz.addContent(rdss);
							}
							if(wsxszjdmodel.getZjjl()!=null){
								List<String>zjjllist=wsxszjdmodel.getZjjl();
								for(int j=0;j<zjjllist.size();j++){
									Element zjjl=new Element ("ZJJL").setAttribute("value",zjjllist.get(j)); 
									zjjl.setAttribute("nameCN","证据记录");
									zjfz.addContent(zjjl);
								}

							}
						}
					}
				}
				if(wsajjbqkModel.getXsqssld()!=null){
					Element xsqssld=new Element ("QSSLD").setAttribute("value",wsajjbqkModel.getXsqssld()); 
					xsqssld.setAttribute("nameCN","前审审理段");
					ajjbqk.addContent(xsqssld);
					if(wsajjbqkModel.getQscmssyzj()!=null){
						Element qscmssyzj=new Element ("QSCMSSYZJ").setAttribute("value",wsajjbqkModel.getQscmssyzj()); 
						qscmssyzj.setAttribute("nameCN","前审查明事实与证据");
						xsqssld.addContent(qscmssyzj);						
					}
					if(wsajjbqkModel.getQscpyzypjjg()!=null){
						Element qscpyzypjjg=new Element ("QSCPYZYPJJG").setAttribute("value",wsajjbqkModel.getQscpyzypjjg()); 
						qscpyzypjjg.setAttribute("nameCN","前审裁判要旨与判决结果");
						xsqssld.addContent(qscpyzypjjg);						
					}
				}
				if(wsajjbqkModel.getSsssbhyj()!=null){
					Element ssssbhyj=new Element ("SSSSBHYJ").setAttribute("value",wsajjbqkModel.getSsssbhyj()); 
					ssssbhyj.setAttribute("nameCN","上诉申诉辩护意见");
					ajjbqk.addContent(ssssbhyj);						
				}
				if(wsajjbqkModel.getGsjgctyj()!=null){
					Element jcjgyj=new Element ("JCJGYJ").setAttribute("value",wsajjbqkModel.getGsjgctyj()); 
					jcjgyj.setAttribute("nameCN","检察机关意见");
					ajjbqk.addContent(jcjgyj);						
				}
			}
		} else {
			// fileUtil.fileCopy(path, filename,
			// specialpath+"ajjbqkspecial", filename);
		}		
	}
	/**
	 * 创建裁判分析过程节点
	 * @param root
	 * @param wsModel
	 */
	private static void buildCpfxgc(Element root,WsModel wsModel) {
		if(wsModel == null)
			return;
		// 创建裁判分析过程节点 ,若为空，则将文书提取放入cpfxgcspecial文件夹
		if (wsModel.getWscpfxgcSegment() != null) {
			WscpfxgcModel wscpfxgcModel = wsModel.getWscpfxgcModel();
			Element cpfxgc = new Element("CPFXGC").setAttribute("value",
					wsModel.getWscpfxgcSegment());
			cpfxgc.setAttribute("nameCN", "裁判分析过程");
			root.addContent(cpfxgc);
			if (wscpfxgcModel != null) {
				// 创建结案方式类型节点
				if (wscpfxgcModel.getJafslx() != null) {
					Element jafslx = new Element("JAFSLX").setAttribute(
							"value", wscpfxgcModel.getJafslx());
					jafslx.setAttribute("nameCN", "结案方式类型");
					cpfxgc.addContent(jafslx);
				}
				// 创建是否经过行政复议节点
				if (wscpfxgcModel.getSfjgxzfy() != null) {
					Element sfjgxzfy = new Element("SFJGXZFY")
							.setAttribute("value",
									wscpfxgcModel.getSfjgxzfy());
					sfjgxzfy.setAttribute("nameCN", "是否经过行政复议");
					cpfxgc.addContent(sfjgxzfy);
				}
				// 创建行政行为违法补救节点
				if (wscpfxgcModel.getXzxwwfbj() != null) {
					Element xzxwwfbj = new Element("XZXWWFBJ")
							.setAttribute("value",
									wscpfxgcModel.getXzxwwfbj());
					xzxwwfbj.setAttribute("nameCN", "行政行为违法补救");
					cpfxgc.addContent(xzxwwfbj);
				}
				// 创建是否提起行政赔偿节点
				if (wscpfxgcModel.getXzpc() != null) {
					Element sftqxzpc = new Element("SFTQXZPC")
							.setAttribute("value", wscpfxgcModel.getXzpc());
					sftqxzpc.setAttribute("nameCN", "是否提起行政赔偿");
					cpfxgc.addContent(sftqxzpc);
				}
				// 创建开庭前申请撤回上诉节点
				if (wscpfxgcModel.getKtqsqchss() != null) {
					Element ktqsqchss = new Element("KTQSQCHSS")
							.setAttribute("value",
									wscpfxgcModel.getKtqsqchss());
					ktqsqchss.setAttribute("nameCN", "开庭前申请撤回上诉");
					cpfxgc.addContent(ktqsqchss);
				}
				// 创建结案方式节点
				// 创建法律法条名称，条目，款目节点
				if (wscpfxgcModel.getFtModellist() != null) {
					ArrayList<WscpfxgcFtModel> ftModellist = wscpfxgcModel
							.getFtModellist();
					for (int i = 0; i < ftModellist.size(); i++) {
						Element flftmc = new Element("FLFTMC")
								.setAttribute("value", ftModellist.get(i)
										.getFlftmc());
						flftmc.setAttribute("nameCN", "法律法条名称");
						if (ftModellist.get(i).getFtMap() != null) {
							Map<String, String> ftMap = ftModellist.get(i)
									.getFtMap();
							for (Map.Entry<String, String> entry : ftMap
									.entrySet()) {
								Element tm = new Element("TM")
										.setAttribute("value",
												entry.getKey());
								tm.setAttribute("nameCN", "条目");
								if (entry.getValue() != "没有款目") {
									Element km = new Element("KM")
											.setAttribute("value",
													entry.getValue());
									km.setAttribute("nameCN", "款目");
									tm.addContent(km);
								}
								flftmc.addContent(tm);
							}
						}
						Element sfcxf = new Element("SFCXF")
								.setAttribute("value", ftModellist.get(i)
										.getSfcxf());
						sfcxf.setAttribute("nameCN", "是否是程序法");
						flftmc.addContent(sfcxf);
						cpfxgc.addContent(flftmc);
					}
				}
				// 创建法定量刑情况相关节点

				if (wscpfxgcModel.getFdlxModel() != null
						|| wscpfxgcModel.getFdlxModel() != null) {
					Element lxqk = new Element("LXQK").setAttribute(
							"nameCN", "量刑情况");
					if (wscpfxgcModel.getFdlxModel() != null) {
						ArrayList<WscpfxgcFdlxModel> fdlxModel = wscpfxgcModel
								.getFdlxModel();
						for (int j = 0; j < fdlxModel.size(); j++) {
							// 创建法定量刑情节节点
							Element fdlxqj = new Element("FDLXQJ")
									.setAttribute("nameCN", "法定量刑情节");
							if (wscpfxgcModel.getFdlxModel().get(j)
									.getXgr() != null) {
								ArrayList<String> xgrList = wscpfxgcModel
										.getFdlxModel().get(j).getXgr();
								for (int k = 0; k < xgrList.size(); k++) {
									// 创建相关人节点
									Element xgr = new Element("XGR")
											.setAttribute("value",
													xgrList.get(k));
									xgr.setAttribute("nameCN", "相关人");
									fdlxqj.addContent(xgr);
								}
							}
							// 创建情节节点
							Element qj = new Element("QJ").setAttribute(
									"value", fdlxModel.get(j).getQj());
							qj.setAttribute("nameCN", "情节");
							fdlxqj.addContent(qj);
							// 创建量刑情节类型节点
							ArrayList<String> lxqjlbList = fdlxModel.get(j)
									.getLxqjlb();
							for (int k = 0; k < lxqjlbList.size(); k++) {
								Element lxqjlb = new Element("LXQJLB")
										.setAttribute("value",
												lxqjlbList.get(k));
								lxqjlb.setAttribute("nameCN", "量刑情节类型");
								fdlxqj.addContent(lxqjlb);
							}
							lxqk.addContent(fdlxqj);
						}
					}
					if (wscpfxgcModel.getZdlxModel() != null) {
						ArrayList<WscpfxgcZdlxModel> zdlxModel = wscpfxgcModel
								.getZdlxModel();
						for (int j = 0; j < zdlxModel.size(); j++) {
							// 创建酌定量刑情节节点
							Element zdlxqj = new Element("ZDLXQJ")
									.setAttribute("nameCN", "酌定量刑情节");
							ArrayList<String> xgrList = wscpfxgcModel
									.getZdlxModel().get(j).getXgr();
							for (int k = 0; k < xgrList.size(); k++) {
								// 创建相关人节点
								Element xgr = new Element("XGR")
										.setAttribute("value",
												xgrList.get(k));
								xgr.setAttribute("nameCN", "相关人");
								zdlxqj.addContent(xgr);
							}
							// 创建酌定情节节点
							Element qj = new Element("QJ").setAttribute(
									"value", zdlxModel.get(j).getQj());
							qj.setAttribute("nameCN", "情节");
							zdlxqj.addContent(qj);
							// 创建量刑情节类型节点
							ArrayList<String> lxqjlbList = zdlxModel.get(j)
									.getLxqjlb();
							for (int k = 0; k < lxqjlbList.size(); k++) {
								Element lxqjlb = new Element("LXQJLB")
										.setAttribute("value",
												lxqjlbList.get(k));
								lxqjlb.setAttribute("nameCN", "量刑情节类型");
								zdlxqj.addContent(lxqjlb);
							}
							lxqk.addContent(zdlxqj);
						}
					}
					if (lxqk.getChild("ZDLXQJ") != null) {
						cpfxgc.addContent(lxqk);
					}
				}
				// 创建共同犯罪节点
				if (wscpfxgcModel.getGtfz() != null) {
					Element gtfz = new Element("GTFZ").setAttribute(
							"value", wscpfxgcModel.getGtfz());
					gtfz.setAttribute("nameCN", "共同犯罪");
					cpfxgc.addContent(gtfz);
				}
				// 创建被告人同意认罪程序节点
				if (wscpfxgcModel.getBgrtyrzcx() != null) {
					Element bgrtyrzcx = new Element("BGRTYRZCX")
							.setAttribute("value",
									wscpfxgcModel.getBgrtyrzcx());
					bgrtyrzcx.setAttribute("nameCN", "被告人同意认罪程序");
					cpfxgc.addContent(bgrtyrzcx);
				}
			}
		} else {
			// fileUtil.fileCopy(path, filename,
			// specialpath+"cpfxgcspecial", filename);
		}		
	}
	/**
	 * 刑事之外的案件类型 判决结果
	 * @param root
	 * @param wsModel
	 */
	private static void buildPjjg(Element root,WsModel wsModel){
		if(wsModel == null)
			return;
		WscpjgModel wscpjgModel = wsModel.getWscpjgModel();
		if (wsModel.getWscpjgSegment() != null) {
			Element cpjg = new Element("CPJG").setAttribute("value",
					wsModel.getWscpjgSegment());
			cpjg.setAttribute("nameCN", "裁判结果");
			root.addContent(cpjg);
			// 创建结案方式节点
			if (wscpjgModel.getJafs() != null) {
				Element jafs = new Element("JAFS").setAttribute("value",
						wscpjgModel.getJafs());
				jafs.setAttribute("nameCN", "结案方式");
				cpjg.addContent(jafs);
			}
			//交通事故 同时起诉侵权人与保险公司的赔偿顺序
			if(wscpjgModel.getPcsxsfzq()!=null){
				Element pcsxsfzq = new Element("PCSFSXZQ").setAttribute("value",
						wscpjgModel.getPcsxsfzq());
				pcsxsfzq.setAttribute("nameCN", "同时起诉侵权人与保险公司的赔偿顺序");
				cpjg.addContent(pcsxsfzq);
			}
			// 创建裁判结果内容节点
			if (wscpjgModel.getPjjgList() != null
					&& wscpjgModel.getPjjgList().size() > 0) {
				for (PjjgnrModel pjjgnrModel : wscpjgModel.getPjjgList()) {
					Element pjjg = new Element("JTPJJG").setAttribute(
							"value", pjjgnrModel.getPjjgnr());
					pjjg.setAttribute("nameCN", "具体裁判段");
					cpjg.addContent(pjjg);
					// 权利人
					if (pjjgnrModel.getQlr() != null) {
						for (Map.Entry<String, String> entry : pjjgnrModel
								.getQlr().entrySet()) {
							if (!StringUtil.isBlank(entry.getKey())) {
								Element qlr = new Element("QLR")
								.setAttribute("value",
										entry.getKey());
								qlr.setAttribute("nameCN", "权利人");
								pjjg.addContent(qlr);
								// 诉讼地位
								if (!StringUtil.isBlank(entry.getValue())) {
									Element qlrdw = new Element("QLRSSDW")
									.setAttribute("value",
											entry.getValue());
									qlrdw.setAttribute("nameCN", "诉讼地位");
									qlr.addContent(qlrdw);
								}
							}

						}
					}
					// 义务人
					if (pjjgnrModel.getYwr() != null) {
						for (Map.Entry<String, String> entry : pjjgnrModel
								.getYwr().entrySet()) {
							if (!StringUtil.isBlank(entry.getKey())) {
								Element ywr = new Element("YWR")
								.setAttribute("value",
										entry.getKey());
								ywr.setAttribute("nameCN", "义务人");
								pjjg.addContent(ywr);
								// 诉讼地位
								if (!StringUtil.isBlank(entry.getValue())) {
									Element ywrdw = new Element("YWRSSDW")
									.setAttribute("value",
											entry.getValue());
									ywrdw.setAttribute("nameCN", "诉讼地位");
									ywr.addContent(ywrdw);
								}
							}
						}
					}
					// 判决执行期限
					if (!StringUtil.isBlank(pjjgnrModel.getPjzxqx())) {
						Element qx = new Element("PJZXQX").setAttribute(
								"value", pjjgnrModel.getPjzxqx());
						qx.setAttribute("nameCN", "判决执行期限");
						pjjg.addContent(qx);
					}
					// 判决金额
					if (pjjgnrModel.getPjjeList() != null
							&& pjjgnrModel.getPjjeList().size() > 0) {
						for (PjjeModel jemodel : pjjgnrModel.getPjjeList()) {
							if (jemodel != null
									&& !StringUtil.isBlank(jemodel
											.getValue())) {
								Element pjje = new Element("PJJE")
								.setAttribute("nameCN", "判决金额");
								pjjg.addContent(pjje);
								Element je = new Element("JE")
								.setAttribute("value",
										jemodel.getValue());
								je.setAttribute("nameCN", "金额");
								pjje.addContent(je);
								if (jemodel.getCategorys() != null
										&& jemodel.getCategorys().size() > 0) {
									for (String s : jemodel.getCategorys()) {
										if (!StringUtil.isBlank(s)) {
											Element jezl = new Element(
													"JELX").setAttribute(
															"value", s);
											jezl.setAttribute("nameCN",
													"金额类型");
											pjje.addContent(jezl);
										}
									}
								}
							}
						}
					}
				}
			}
			// 创建案件受理费节点
			if (wscpjgModel.getSsfModel() != null
					&& !StringUtil.isBlank(wscpjgModel.getSsfModel()
							.getSsfjl())) {
				WsCpjgssfModel ssfModel = wscpjgModel.getSsfModel();
				Element ajslf = new Element("SSFCD").setAttribute("nameCN",
						"诉讼费承担");
				cpjg.addContent(ajslf);
				Element cljl = new Element("SSFCDJL").setAttribute("value",
						wscpjgModel.getSsfModel().getSsfjl());
				cljl.setAttribute("nameCN", "诉讼费承担记录");
				ajslf.addContent(cljl);
				// 诉讼费金额
				if (ssfModel.getSsfjeModels() != null
						&& ssfModel.getSsfjeModels().size() > 0) {
					for (WsCpjgssfjeModel jeModel : ssfModel
							.getSsfjeModels()) {
						if (!StringUtil.isBlank(jeModel.getValue())) {
							Element sfzcssqq = new Element("SSFJE")
							.setAttribute("value",
									jeModel.getValue());
							sfzcssqq.setAttribute("nameCN", "诉讼费金额");
							cljl.addContent(sfzcssqq);
							if (!StringUtil.isBlank(jeModel.getCategory())) {
								Element zl = new Element("SSFZL")
								.setAttribute("value",
										jeModel.getCategory());
								zl.setAttribute("nameCN", "诉讼费种类");
								cljl.addContent(zl);
							}
						}
					}
				}
				// 诉讼费承担
				if (ssfModel.getSsfcdModels() != null
						&& ssfModel.getSsfcdModels().size() > 0) {
					for (WscpjgssfcdModel cdModel : ssfModel
							.getSsfcdModels()) {
						if (!StringUtil.isBlank(cdModel.getCdr())) {
							Element cdr = new Element("SSFCDR")
							.setAttribute("value", cdModel.getCdr());
							cdr.setAttribute("nameCN", "承担人");
							cljl.addContent(cdr);
							if (!StringUtil.isBlank(cdModel.getCdje())) {
								Element cdje = new Element("SSFCDJE")
								.setAttribute("value",
										cdModel.getCdje());
								cdje.setAttribute("nameCN", "承担金额");
								cdr.addContent(cdje);
							}
							if (!StringUtil.isBlank(cdModel.getCdfs())) {
								Element cdfs = new Element("SSFCDFS")
								.setAttribute("value",
										cdModel.getCdfs());
								cdfs.setAttribute("nameCN", "承担方式");
								cdr.addContent(cdfs);
							}
							if (!StringUtil.isBlank(cdModel.getCdrdw())) {
								Element cddw = new Element("SSFCDDW")
								.setAttribute("value",
										cdModel.getCdrdw());
								cddw.setAttribute("nameCN", "承担人地位");
								cdr.addContent(cddw);
							}
						}

					}
				}
			}

			// 创建是否支持诉讼请求节点
			if (wscpjgModel.getSfzcssqq() != null) {
				Element sfzcssqq = new Element("SFZCSSQQ").setAttribute(
						"value", wscpjgModel.getSfzcssqq());
				sfzcssqq.setAttribute("nameCN", "是否支持诉讼请求");
				cpjg.addContent(sfzcssqq);
			}
			// 创建胜诉方节点
			if (wscpjgModel.getSbsf() != null) {
				Element sbsf = new Element("SBSF").setAttribute("value",
						wscpjgModel.getSbsf());
				sbsf.setAttribute("nameCN", "胜诉方");
				cpjg.addContent(sbsf);
			}
			if (wscpjgModel.getSffhcs() != null) {
				Element fhcs = new Element("SFFHCS").setAttribute("value",
						wscpjgModel.getSffhcs());
				fhcs.setAttribute("nameCN", "是否发回重审");
				cpjg.addContent(fhcs);
			}
			if (wscpjgModel.getFhcsyy() != null) {
				Element yy = new Element("FHCSYY").setAttribute("value",
						wscpjgModel.getFhcsyy());
				yy.setAttribute("nameCN", "发回重审原因");
				cpjg.addContent(yy);
			}
			// 结案标的额
			if (wscpjgModel.getJabde() != null) {
				Element jabde = new Element("JABDE").setAttribute("nameCN",
						"结案标的金额");
				cpjg.addContent(jabde);
				for (String je : wscpjgModel.getJabde()) {
					Element jaje = new Element("JABDE_JE").setAttribute(
							"value", je + "元");
					jaje.setAttribute("nameCN", "金额");
					jabde.addContent(jaje);
				}
			}
			// 结案标的总额
			if (wscpjgModel.getJabdze() != null) {
				Element jazje = new Element("JABDZE").setAttribute("value",
						wscpjgModel.getJabdze());
				jazje.setAttribute("nameCN", "结案标的总额");
				cpjg.addContent(jazje);
			}
			// 可上诉至
			if (!StringUtil.isBlank(wscpjgModel.getKssz())) {
				Element kssz = new Element("KSSZ").setAttribute("value",
						wscpjgModel.getKssz());
				kssz.setAttribute("nameCN", "可上诉至");
				cpjg.addContent(kssz);
			}
			// 上诉提交材料
			if (!StringUtil.isBlank(wscpjgModel.getSstjcl())) {
				Element sstjcl = new Element("SSTJCL").setAttribute(
						"value", wscpjgModel.getSstjcl());
				sstjcl.setAttribute("nameCN", "上诉提交材料");
				cpjg.addContent(sstjcl);
			}
			// 上诉期限
			if (!StringUtil.isBlank(wscpjgModel.getSsqx())) {
				Element ssqx = new Element("SSQX").setAttribute("value",
						wscpjgModel.getSsqx());
				ssqx.setAttribute("nameCN", "上诉期限");
				cpjg.addContent(ssqx);
			}
			// 撤诉人集合
			if (wscpjgModel.getCsrjh() != null
					&& wscpjgModel.getCsrjh().size() > 0) {
				Element csrjh = new Element("CSRJH").setAttribute("nameCN",
						"撤诉人集合");
				cpjg.addContent(csrjh);
				for (String s : wscpjgModel.getCsrjh()) {
					if (!StringUtil.isBlank(s)) {
						Element ssqx = new Element("CSR").setAttribute(
								"value", s);
						ssqx.setAttribute("nameCN", "撤诉人");
						csrjh.addContent(ssqx);
					}
				}
			}
			// 撤诉类型
			if (!StringUtil.isBlank(wscpjgModel.getCslx())) {
				Element cslx = new Element("CSLX").setAttribute("value",
						wscpjgModel.getCslx());
				cslx.setAttribute("nameCN", "撤诉类型");
				cpjg.addContent(cslx);
			}
			// 提出管辖权异议
			if (!StringUtil.isBlank(wscpjgModel.getSftcgxyy())) {
				Element cslx = new Element("TCGXQYY").setAttribute("value",
						wscpjgModel.getSftcgxyy());
				cslx.setAttribute("nameCN", "是否提出管辖权异议");
				cpjg.addContent(cslx);
			}
		} else {
			// fileUtil.fileCopy(path, filename, specialpath+"cpjgspecial",
			// filename);
		}
	}
	/**
	 * 刑事判决结果
	 * @param wsModel
	 * @param root
	 */
	private static void buildXspjjg(WsModel wsModel,Element root){
		if(wsModel == null)
			return;
		WscpjgModel cpjgModel = wsModel.getWscpjgModel();
		if(cpjgModel == null)
			return;
		if(wsModel.getWscpjgSegment()!=null){
			Element pjjg=new Element ("PJJG").setAttribute("value",wsModel.getWscpjgSegment()); 
			pjjg.setAttribute("nameCN","判决结果");
			root.addContent(pjjg);
			//				是否提出管辖权异议
			if(!StringUtil.isBlank(cpjgModel.getTcgxyy())){
				Element sftcggxqyy=new Element ("SFTCGXQYY").setAttribute("value",cpjgModel.getTcgxyy()); 
				sftcggxqyy.setAttribute("nameCN","提出管辖权异议");
				pjjg.addContent(sftcggxqyy);
			}
			//				结案方式
			if(!StringUtil.isBlank(cpjgModel.getJafs())){
				Element jafs=new Element ("JAFS").setAttribute("value",cpjgModel.getJafs()); 
				jafs.setAttribute("nameCN","结案方式");
				pjjg.addContent(jafs);
			}
			//				一审刑事部分判决结果
			if(!StringUtil.isBlank(cpjgModel.getYsxsbfpjjg())){
				Element ysxsbf=new Element ("YSXSBFPJJG").setAttribute("value",cpjgModel.getYsxsbfpjjg()); 
				ysxsbf.setAttribute("nameCN","一审刑事部分判决解雇");
				pjjg.addContent(ysxsbf);
			}
			//				附带民事部分裁判结果
			if(!StringUtil.isBlank(cpjgModel.getFdmscpjg())){
				Element ysxsbf=new Element ("FDMSBFCPJG").setAttribute("value",cpjgModel.getFdmscpjg()); 
				ysxsbf.setAttribute("nameCN","附带民事部分裁判结果");
				pjjg.addContent(ysxsbf);
			}
			//				刑事判决结果分组
			if(cpjgModel.getPjjgfzModels()!=null && cpjgModel.getPjjgfzModels().size()>0){
				for(XspjjgfzModel fzModel:cpjgModel.getPjjgfzModels()){
					if(!StringUtil.isBlank(fzModel.getEslxjg()) || !StringUtil.isBlank(fzModel.getEslxjg())){
						Element fz =new Element ("XSPJJGFZ").setAttribute("nameCN","刑事判决结果分组");
						pjjg.addContent(fz);
						
						if(fzModel.getSscyr()!=null){
							Element sscyr=new Element ("SSCYR").setAttribute("value",fzModel.getSscyr()); 
							sscyr.setAttribute("nameCN","诉讼参与人");
							fz.addContent(sscyr);
						}
						
						Element sscyr=new Element ("BSPJJG").setAttribute("nameCN","本审判决结果");
						fz.addContent(sscyr);
						
						if(!StringUtil.isBlank(fzModel.getEslxjg())){
							Element e=new Element ("ESLXJG").setAttribute("value",fzModel.getEslxjg()); 
							e.setAttribute("nameCN","二审量刑结果");
							sscyr.addContent(e);
						}
						if(!StringUtil.isBlank(fzModel.getEslxjg())){
							Element e=new Element ("ESSLJG").setAttribute("value",fzModel.getEssljg()); 
							e.setAttribute("nameCN","二审审理结果");
							sscyr.addContent(e);
						}
					}else{
						Element fz =new Element ("XSPJJGFZ").setAttribute("nameCN","刑事判决结果分组");
						pjjg.addContent(fz);
						if(fzModel.getSscyr()!=null){
							Element sscyr=new Element ("SSCYR").setAttribute("value",fzModel.getSscyr()); 
							sscyr.setAttribute("nameCN","诉讼参与人");
							fz.addContent(sscyr);
						}
						if(fzModel.getDzpf()!=null){
							for(PfModel pf:fzModel.getDzpf()){
								Element dzpf=new Element ("DZPF").setAttribute("value",pf.getPfnr()); 
								dzpf.setAttribute("nameCN","单罪判罚");
								fz.addContent(dzpf);
								if(pf.getZm()!=null){
									Element zm=new Element ("ZM").setAttribute("value",pf.getZm().getZm()); 
									zm.setAttribute("nameCN","罪名");
									dzpf.addContent(zm);
								}
								if(pf.getZx()!=null){
									Element zx=new Element ("ZX").setAttribute("nameCN","主刑");
									dzpf.addContent(zx);
									if(pf.getZx().getZxlb()!=null){
										Element zxlb=new Element ("ZXLB").setAttribute("value",pf.getZx().getZxlb()); 
										zxlb.setAttribute("nameCN","主刑类别");
										zx.addContent(zxlb);
									}
									if(pf.getZx().getZxxq()!=null){
										Element zxlb=new Element ("ZXQX").setAttribute("value",pf.getZx().getZxxq()); 
										zxlb.setAttribute("nameCN","主刑期限");
										zx.addContent(zxlb);
									}
								}
								if(pf.getFjxList()!=null){
									for(FjxModel fjx:pf.getFjxList()){
										Element fj=new Element ("FJX").setAttribute("nameCN","附加刑");
										dzpf.addContent(fj);
										if(fjx.getLb()!=null){
											Element lb=new Element ("FJXLB").setAttribute("value",fjx.getLb()); 
											lb.setAttribute("nameCN","附加刑类别");
											fj.addContent(lb);
											if(StringUtil.equals(fjx.getLb(), "罚金")||StringUtil.equals(fjx.getLb(), "没收个人部分财产")){
												if(fjx.getSe()!=null){
													Element se=new Element ("SE").setAttribute("value",fjx.getSe()); 
													se.setAttribute("nameCN","数额");
													lb.addContent(se);
												}
												if(fjx.getBz()!=null){
													Element bz=new Element ("BZ").setAttribute("value",fjx.getBz()); 
													bz.setAttribute("nameCN","币种");
													lb.addContent(bz);
												}
											}else if(StringUtil.equals(fjx.getLb(), "剥夺政治权利")){
												if(fjx.getQx()!=null){
													Element qx=new Element ("QX").setAttribute("value",fjx.getQx()); 
													qx.setAttribute("nameCN","期限");
													lb.addContent(qx);
												}
											}
										}
									}
								}
								if(pf.getHx()!=null){
									Element hx=new Element ("HX").setAttribute("nameCN","缓刑");
									dzpf.addContent(hx);
									if(pf.getHx().getZxlb()!=null){
										Element lb=new Element ("HXLB").setAttribute("value",pf.getHx().getZxlb()); 
										lb.setAttribute("nameCN","缓刑类别");
										hx.addContent(lb);
									}
									if(pf.getHx().getZxxq()!=null){
										Element lb=new Element ("QX").setAttribute("value",pf.getHx().getZxxq()); 
										lb.setAttribute("nameCN","缓刑期限");
										hx.addContent(lb);
									}
								}
								if(pf.getPjjglx()!=null){
									Element pjjglx=new Element ("PJJGLX").setAttribute("value",pf.getPjjglx()); 
									pjjglx.setAttribute("nameCN","判决结果类型");
									dzpf.addContent(pjjglx);
								}
							}
						}

						if(fzModel.getZxpf()!=null){
							Element zxpf=new Element ("ZXPF").setAttribute("value",fzModel.getZxpf().getPfnr()); 
							zxpf.setAttribute("nameCN","执行判罚");
							fz.addContent(zxpf);
							if(fzModel.getZxpf().getZx()!=null){
								Element zx=new Element ("ZX").setAttribute("nameCN","主刑");
								zxpf.addContent(zx);
								if(fzModel.getZxpf().getZx().getZxlb()!=null){
									Element zxlb=new Element ("ZXLB").setAttribute("value",fzModel.getZxpf().getZx().getZxlb()); 
									zxlb.setAttribute("nameCN","主刑类别");
									zx.addContent(zxlb);
								}
								if(fzModel.getZxpf().getZx().getZxxq()!=null){
									Element zxlb=new Element ("ZXQX").setAttribute("value",fzModel.getZxpf().getZx().getZxxq()); 
									zxlb.setAttribute("nameCN","主刑期限");
									zx.addContent(zxlb);
								}
							}
							if(fzModel.getZxpf().getFjxList()!=null){
								for(FjxModel fjx:fzModel.getZxpf().getFjxList()){
									Element fj=new Element ("FJX").setAttribute("nameCN","附加刑");
									zxpf.addContent(fj);
									if(fjx.getLb()!=null){
										Element lb=new Element ("FJXLB").setAttribute("value",fjx.getLb()); 
										lb.setAttribute("nameCN","附加刑类别");
										fj.addContent(lb);
										if(StringUtil.equals(fjx.getLb(), "罚金")||StringUtil.equals(fjx.getLb(), "没收个人部分财产")){
											if(fjx.getSe()!=null){
												Element se=new Element ("SE").setAttribute("value",fjx.getSe()); 
												se.setAttribute("nameCN","数额");
												lb.addContent(se);
											}
											if(fjx.getBz()!=null){
												Element bz=new Element ("BZ").setAttribute("value",fjx.getBz()); 
												bz.setAttribute("nameCN","币种");
												lb.addContent(bz);
											}
										}else if(StringUtil.equals(fjx.getLb(), "剥夺政治权利")){
											if(fjx.getQx()!=null){
												Element qx=new Element ("QX").setAttribute("value",fjx.getQx()); 
												qx.setAttribute("nameCN","期限");
												lb.addContent(qx);
											}
										}
									}
								}
							}
							if(fzModel.getZxpf().getHx()!=null){
								Element hx=new Element ("HX").setAttribute("nameCN","缓刑");
								zxpf.addContent(hx);
								if(fzModel.getZxpf().getHx().getZxlb()!=null){
									Element lb=new Element ("HXLB").setAttribute("value",fzModel.getZxpf().getHx().getZxlb()); 
									lb.setAttribute("nameCN","缓刑类别");
									hx.addContent(lb);
								}
								if(fzModel.getZxpf().getHx().getZxxq()!=null){
									Element lb=new Element ("QX").setAttribute("value",fzModel.getZxpf().getHx().getZxxq()); 
									lb.setAttribute("nameCN","缓刑期限");
									hx.addContent(lb);
								}
							}
							if(fzModel.getZxpf().getPjjglx()!=null){
								Element pjjglx=new Element ("PJJGLX").setAttribute("value",fzModel.getZxpf().getPjjglx()); 
								pjjglx.setAttribute("nameCN","判决结果类型");
								zxpf.addContent(pjjglx);
							}
						}
						if(fzModel.getPjzzm()!=null){
							ZmModel pjzzmModel = fzModel.getPjzzm();
							if(pjzzmModel.getZm()!=null){
								Element pjzzm=new Element ("PJZZM").setAttribute("value",pjzzmModel.getZm()); 
								pjzzm.setAttribute("nameCN","判决主罪名");
								fz.addContent(pjzzm);
							}
						}
						if(fzModel.getXqksrq()!=null || fzModel.getXqjsrq()!=null){
							Element xqqzrq=new Element ("XQQZRQ").setAttribute("nameCN","刑期起止日期");
							fz.addContent(xqqzrq);
							if(fzModel.getXqksrq()!=null){
								Element xqksrq=new Element ("XQKSRQ").setAttribute("value",fzModel.getXqksrq()); 
								xqksrq.setAttribute("nameCN","刑期开始日期");
								xqqzrq.addContent(xqksrq);
							}
							if(fzModel.getXqjsrq()!=null){
								Element xqjsrq=new Element ("XQJSRQ").setAttribute("value",fzModel.getXqjsrq()); 
								xqjsrq.setAttribute("nameCN","刑期结束日期");
								xqqzrq.addContent(xqjsrq);
							}
						}
						if(fzModel.getXqzdbf()!=null){
							Element xqzdbf=new Element ("XQZDBF").setAttribute("value",fzModel.getXqzdbf()); 
							xqzdbf.setAttribute("nameCN","刑期折抵办法");
							fz.addContent(xqzdbf);
						}
						if(fzModel.getMzhwzsf()!=null){
							Element xqzdbf=new Element ("MZHWZSF").setAttribute("value",fzModel.getMzhwzsf()); 
							xqzdbf.setAttribute("nameCN","免罪或无罪释放");
							fz.addContent(xqzdbf);
						}
						if(fzModel.getSzbf()!=null){
							Element xqzdbf=new Element ("SZBF").setAttribute("value",fzModel.getSzbf()); 
							xqzdbf.setAttribute("nameCN","数罪并罚");
							fz.addContent(xqzdbf);
						}
						if(fzModel.getHblx()!=null){
							Element xqzdbf=new Element ("HBLX").setAttribute("value",fzModel.getHblx()); 
							xqzdbf.setAttribute("nameCN","合并量刑");
							fz.addContent(xqzdbf);
						}
						//原罪
						if(fzModel.getYzpf()!=null &&fzModel.getYzpf().size()>0 ){
							Element yzpjjg=new Element ("YZPJJG").setAttribute("nameCN","原罪判决结果");
							fz.addContent(yzpjjg);
							for(PfModel yzpf:fzModel.getYzpf()){

								Element yz=new Element ("YZPF").setAttribute("value",yzpf.getPfnr()); 
								yz.setAttribute("nameCN","原罪判罚");
								yzpjjg.addContent(yz);


								if(yzpf.getZm()!=null){
									Element zm=new Element ("YZZM").setAttribute("value",yzpf.getZm().getZm()); 
									zm.setAttribute("nameCN","罪名");
									yz.addContent(zm);
								}
								if(yzpf.getZx()!=null){
									Element zx=new Element ("ZX").setAttribute("nameCN","主刑");
									yz.addContent(zx);
									if(yzpf.getZx().getZxlb()!=null){
										Element zxlb=new Element ("ZXLB").setAttribute("value",yzpf.getZx().getZxlb()); 
										zxlb.setAttribute("nameCN","主刑类别");
										zx.addContent(zxlb);
									}
									if(yzpf.getZx().getZxxq()!=null){
										Element zxlb=new Element ("ZXQX").setAttribute("value",yzpf.getZx().getZxxq()); 
										zxlb.setAttribute("nameCN","主刑期限");
										zx.addContent(zxlb);
									}
								}
								if(yzpf.getFjxList()!=null){
									for(FjxModel fjx:yzpf.getFjxList()){
										Element fj=new Element ("FJX").setAttribute("nameCN","附加刑");
										yz.addContent(fj);
										if(fjx.getLb()!=null){
											Element lb=new Element ("FJXLB").setAttribute("value",fjx.getLb()); 
											lb.setAttribute("nameCN","附加刑类别");
											fj.addContent(lb);
											if(StringUtil.equals(fjx.getLb(), "罚金")||StringUtil.equals(fjx.getLb(), "没收个人部分财产")){
												if(fjx.getSe()!=null){
													Element se=new Element ("SE").setAttribute("value",fjx.getSe()); 
													se.setAttribute("nameCN","数额");
													lb.addContent(se);
												}
												if(fjx.getBz()!=null){
													Element bz=new Element ("BZ").setAttribute("value",fjx.getBz()); 
													bz.setAttribute("nameCN","币种");
													lb.addContent(bz);
												}
											}else if(StringUtil.equals(fjx.getLb(), "剥夺政治权利")){
												if(fjx.getQx()!=null){
													Element qx=new Element ("QX").setAttribute("value",fjx.getQx()); 
													qx.setAttribute("nameCN","期限");
													lb.addContent(qx);
												}
											}
										}
									}
								}
								if(yzpf.getHx()!=null){
									Element hx=new Element ("HX").setAttribute("nameCN","缓刑");
									yz.addContent(hx);
									if(yzpf.getHx().getZxlb()!=null){
										Element lb=new Element ("HXLB").setAttribute("value",yzpf.getHx().getZxlb()); 
										lb.setAttribute("nameCN","缓刑类别");
										hx.addContent(lb);
									}
									if(yzpf.getHx().getZxxq()!=null){
										Element lb=new Element ("QX").setAttribute("value",yzpf.getHx().getZxxq()); 
										lb.setAttribute("nameCN","缓刑期限");
										hx.addContent(lb);
									}
								}
							}
						}
					}
				}
			}
			//分组结束
			if(!StringUtil.isBlank(cpjgModel.getDfdmspccl())){
				Element ysxsbf=new Element ("DFDMSPCDCL").setAttribute("value",cpjgModel.getDfdmspccl()); 
				ysxsbf.setAttribute("nameCN","对附带民事赔偿的处理");
				pjjg.addContent(ysxsbf);
			}
			if(cpjgModel.getFdmspjfzModel()!=null && !StringUtil.isBlank(cpjgModel.getFdmspjfzModel().getNr())){
				Element ysxsbf=new Element ("FDMSPJJGFZ").setAttribute("value",cpjgModel.getFdmspjfzModel().getNr()); 
				ysxsbf.setAttribute("nameCN","附带民事判决结果分组");
				pjjg.addContent(ysxsbf);
				if(cpjgModel.getFdmspjfzModel().getBcje()!=null){
					for(String s:cpjgModel.getFdmspjfzModel().getBcje()){
						Element je=new Element ("PCJE").setAttribute("value",s); 
						je.setAttribute("nameCN","赔偿金额");
						ysxsbf.addContent(je);
					}
				}
				if(cpjgModel.getFdmspjfzModel().getBpcr()!=null){
					for(String s:cpjgModel.getFdmspjfzModel().getBpcr()){
						Element je=new Element ("BPCR").setAttribute("value",s); 
						je.setAttribute("nameCN","被赔偿人");
						ysxsbf.addContent(je);
					}
				}
				if(cpjgModel.getFdmspjfzModel().getPcr()!=null){
					for(String s:cpjgModel.getFdmspjfzModel().getPcr()){
						Element je=new Element ("PCR").setAttribute("value",s); 
						je.setAttribute("nameCN","赔偿人");
						ysxsbf.addContent(je);
					}
				}
			}
			//				民事分组结束
			if(!StringUtil.isBlank(cpjgModel.getKssz())){
				Element ysxsbf=new Element ("KSSZ").setAttribute("value",cpjgModel.getKssz()); 
				ysxsbf.setAttribute("nameCN","可上诉至");
				pjjg.addContent(ysxsbf);
			}
			if(!StringUtil.isBlank(cpjgModel.getSsqx())){
				Element ysxsbf=new Element ("SSQX").setAttribute("value",cpjgModel.getSsqx()); 
				ysxsbf.setAttribute("nameCN","上诉期限");
				pjjg.addContent(ysxsbf);
			}
			if(!StringUtil.isBlank(cpjgModel.getSstjcl())){
				Element ysxsbf=new Element ("SSTJCL").setAttribute("value",cpjgModel.getSstjcl()); 
				ysxsbf.setAttribute("nameCN","上诉提交材料");
				pjjg.addContent(ysxsbf);
			}
			if(cpjgModel.getCsrjh()!=null && cpjgModel.getCsrjh().size()>0){
				Element ysxsbf=new Element ("CSRJH").setAttribute("nameCN","撤诉人集合");
				pjjg.addContent(ysxsbf);
				for(String s:cpjgModel.getCsrjh()){
					Element csr=new Element ("CSR").setAttribute("value",s); 
					csr.setAttribute("nameCN","撤诉人");
					ysxsbf.addContent(csr);
				}
			}
			if(!StringUtil.isBlank(cpjgModel.getCslx())){
				Element ysxsbf=new Element ("CSLX").setAttribute("value",cpjgModel.getCslx()); 
				ysxsbf.setAttribute("nameCN","撤诉类型");
				pjjg.addContent(ysxsbf);
			}
		}
	}
	/**
	 * 创建文尾节点
	 * @param root
	 * @param wsModel
	 */
	private static void buildWw(Element root,WsModel wsModel) {
		if(wsModel == null)
			return;
		// 创建文尾节点 ,若为空，则将文书提取放入wwspecial文件夹
		if (wsModel.getWswwSegment() != null) {
			WswwModel wswwModel = wsModel.getWswwModel();
			Element ww = new Element("WW").setAttribute("value",
					wsModel.getWswwSegment());
			ww.setAttribute("nameCN", "文尾");
			root.addContent(ww);
			// 创建文尾信息节点
			// 审判组织成员节点
			if (wswwModel.getSpzzcyMap() != null) {
				Map<String, String> spzzcyMap = wswwModel.getSpzzcyMap();
				for (Map.Entry<String, String> entry : spzzcyMap.entrySet()) {
					Element spzzcy = new Element("SPZZCY").setAttribute(
							"nameCN", "审判组织成员");
					Element spryxm = new Element("SPRYXM").setAttribute(
							"value", entry.getKey());
					spryxm.setAttribute("nameCN", "审判人员姓名");
					spzzcy.addContent(spryxm);
					Element spryjs = new Element("SPRYJS").setAttribute(
							"value", entry.getValue());
					spryjs.setAttribute("nameCN", "审判人员角色");
					spzzcy.addContent(spryjs);
					ww.addContent(spzzcy);
				}
			}
			// 文书日期节点
			if (wswwModel.getWsrq() != null) {
				Element wsrq = new Element("CPSJ").setAttribute("value",
						wswwModel.getWsrq());
				wsrq.setAttribute("nameCN", "裁判时间");
				ww.addContent(wsrq);
				// 创建年月日节点
				if (wswwModel.getYear() != null) {
					Element year = new Element("Year").setAttribute(
							"value", wswwModel.getYear());
					year.setAttribute("nameCN", "结案年度");
					wsrq.addContent(year);
				}
				if (wswwModel.getWsrq() != null) {
					Element time = new Element("JANYR").setAttribute(
							"value", wswwModel.getWsrq());
					time.setAttribute("nameCN", "结案年月日");
					wsrq.addContent(time);
				}
				if (wswwModel.getYearAndMonth() != null) {
					Element yearmonth = new Element("JANY").setAttribute(
							"value", wswwModel.getYearAndMonth());
					yearmonth.setAttribute("nameCN", "结案年月");
					wsrq.addContent(yearmonth);
				}
				if (wswwModel.getMonth() != null) {
					Element month = new Element("Month").setAttribute(
							"value", wswwModel.getMonth());
					month.setAttribute("nameCN", "月");
					wsrq.addContent(month);
				}
				if (wswwModel.getDay() != null) {
					Element day = new Element("Day").setAttribute("value",
							wswwModel.getDay());
					day.setAttribute("nameCN", "日");
					wsrq.addContent(day);
				}
			}
		} else {
			// fileUtil.fileCopy(path, filename, specialpath+"wwspecial",
			// filename);
		}
		
	}
	/**
	 * 创建附录节点
	 * @param root
	 * @param wsModel
	 */
	private static void buildFl(Element root,WsModel wsModel){
		if(wsModel == null)
			return;
		if (wsModel.getWsfl() != null) {
			Element fl = new Element("FL").setAttribute("value",
					wsModel.getWsfl());
			fl.setAttribute("nameCN", "附录");
			root.addContent(fl);
		}
	}
}
