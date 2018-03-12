package nju.software.wsjx.model.Enum;

import java.util.ArrayList;
import java.util.List;

public enum   ZWEnum {

	LS("律师"), FWZY("法务专员"), TXGR("退休工人"),GR("工人"), NM("农民"), GCS("工程师"), MS("秘书"), FLGW("法律顾问"),GW("顾问"), JS("教师"),
	SJS("设计师"),SJ("司机"),JSY("驾驶员"),JIS("技师"),MDS("面点师"),CS("厨师"),CSZ("厨师长"),MJ("民警"),FZZ("副站长"),KJ("会计"),CN("出纳"),
	ZZ("站长"),DZ("店长"),GS("干事 "),FZR("副主任"),BGSZW("办公室主任"),ZR("主任"),FZJ("副总监"),CWZJ("财务总监"),ZJ("总监"),ZL("助理"),ZC("总裁"),ZXDS("执行董事"),DSZ("董事长"),
	DS("董事"),ZY("职员"),YG("员工"),FBZ("副部长"),BZ("部长"),FZJL("副总经理"),ZJL("总经理"),FJL("副经理"),JL("经理"),DWWW("党委委员"),DWSJ("党委书记"),DWFSJ("党委副书记"),
	FCZ("副厂长"),CZ("厂长"),FHZ("副行长"),HZ("行长"),FZG("副主管"),ZG("主管"),FDDBR("法定代表人"),YTX("已退休"),ZYZY("自由职业"),
	WZY("无职业"),WGDZY("无固定职业"),GZRY("工作人员"),WY("无业"),NTZG("内退职工"),TXZG("退休职工"),ZHG("职工"),GTJYZ("个体经营者"),TXGB("退休干部"),
	JZ("局长"),GB("干部"),ZHZ("镇长"),WN("务农"),WG("务工"),GT("个体");

	ZWEnum(){

	}
	private String content;
	private ZWEnum(String content){
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public static List<String> getZWList(){
		List<String> zwlist = new ArrayList<String>();
		for(ZWEnum zwEnum : ZWEnum.values()){
			zwlist.add(zwEnum.getContent());
		}
		return zwlist;
	}
	public static boolean HasZW(String zw){
		List<String> list = getZWList();
		for(int i=0;i<list.size();i++){
			if(zw.indexOf(list.get(i))==0){
				return true;
			}
		}
		return false;

	}
	public static String getZW(String zw){
		List<String> list = getZWList();
		for(int i=0;i<list.size();i++){
			if(zw.indexOf(list.get(i))!=-1){
				return list.get(i);
			}
		}
		return null;
	}
}
