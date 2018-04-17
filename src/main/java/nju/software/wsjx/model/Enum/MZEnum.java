package nju.software.wsjx.model.Enum;

import java.util.ArrayList;
import java.util.List;

public enum MZEnum  {
	HZ("汉族"),
	MZ("满族"),
	HUIZ("回族"),
    MGZ("蒙古族"),
    ZZ("藏族"),
    WWEZ("维吾尔族"),
    MIAOZ("苗族"),
    YZ("彝族"),
    BYZ("布依族"),
    ZHZ("壮族"),
    CXZ("朝鲜族"),
    DZ("侗族"),
    YAOZ("瑶族"),
    BZ("白族"),
    TJZ("土家族"),
    HSKZ("哈萨克族"),
    DAIZ("傣族"),
    SSZ("僳僳族"),
    WZ("佤族"),
    SZ("畲族"),
    GSZ("高山族"),
    QZ("羌族"),
    TZ("土族"),
    SHUIZ("水族"),
    ELSZ("俄罗斯族"),
    BAZ("保安族"),
    TJKZ("塔吉克族"),
    NZ("怒族"),
    HNZ("哈尼族"),
	LZ("黎族");
	//拉祜族、东乡族、纳西族、景颇族、柯尔克孜族、达斡尔族、仫佬族、布朗族、撒拉族、
	//毛南族、仡佬族、锡伯族、阿昌族、普米族、乌孜别克族、鄂温克族、德昂族、
	//裕固族、京族、塔塔尔族、独龙族、鄂伦春族、赫哲族、门巴族、珞巴族、基诺族
    
	MZEnum(){
		
	}
	private MZEnum(String content){
		this.content = content;
	}
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static List<String> getMZList() {
		List<String> mzList = new ArrayList<String>();
		for (MZEnum mzEnum : MZEnum.values()) {
			mzList.add(mzEnum.getContent());
		}
		return mzList;
	}
	
	public static boolean HasMZ(String mz){
		List<String> list=getMZList();
		for(int i=0;i<list.size();i++){
	    
			if(mz.indexOf(list.get(i))==0){
				return true;
			}
		}
		return false;
	}
	
	public static String getMZ(String mz){
		List<String> list=getMZList();
		for(int i=0;i<list.size();i++){
	    
			if(mz.indexOf(list.get(i))!=-1){
//				System.out.println(mz);
				return list.get(i);
			}
		}
		return null;
	}
}
