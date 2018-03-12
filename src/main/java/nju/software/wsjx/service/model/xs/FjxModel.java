package nju.software.wsjx.service.model.xs;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.util.MoneyUtil;
import nju.software.wsjx.util.NumberConverter;
import nju.software.wsjx.util.StringUtil;

/**
 * 刑事判决结果 附加刑
 * @author 服兰
 *
 */
public class FjxModel {
	private String lb;//附加刑类别
	private String se;//数额
	private String bz;//币种
	private String qx;//期限
	
	public FjxModel() {
		super();
	}
	
	public FjxModel(XsFjxEnum fjxlb,String content){
		this.lb = fjxlb.getFjx();
		if(fjxlb==XsFjxEnum.BDZZQL){
			this.qx = content.substring(content.indexOf(fjxlb.getFjx()));
		}else if(fjxlb==XsFjxEnum.MSGRBFCC || fjxlb==XsFjxEnum.FJ){
//			this.se = getJeStr(content);
			if(fjxlb==XsFjxEnum.MSGRBFCC){
				this.se = content.substring(content.indexOf("财产")+2);
			}
			if(fjxlb==XsFjxEnum.FJ){
				this.se = content.substring(content.indexOf("罚金")+2);
			}
			if(StringUtil.contains(content, "人民币")){
				this.bz = "人民币";
				this.se = content.substring(content.indexOf("人民币")+3);
			}
			if(StringUtil.contains(this.se, "元")){
				this.bz = "人民币";
				this.se = this.se.substring(0,this.se.indexOf("元")+1);
				if(!StringUtil.isNum(this.se.substring(0,this.se.length()-1))){
					this.se = NumberConverter.convertFromChinese(this.se)+"元";
				}
			}
//			汉字转化成阿拉伯数字
		}
	}
	
	public static String getJeStr(String content){
		String reg = "[一二三四五六七八九十][^x00-xff]*?元";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(content);
		if(m.find()){
			String je = m.group();
			if(je.endsWith("元")){
				je=je.substring(0, je.length()-1);
//				不是数字
				if(!StringUtil.isNum(je)){
					int jevalue = NumberConverter.convertFromChinese(je);
					je = jevalue+"";
				}
				je=je+"元";
			}
			
			return je;
		}
		return null;
	}
	public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
	}
	public String getSe() {
		return se;
	}
	public void setSe(String se) {
		this.se = se;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getQx() {
		return qx;
	}
	public void setQx(String qx) {
		this.qx = qx;
	}
	

}
