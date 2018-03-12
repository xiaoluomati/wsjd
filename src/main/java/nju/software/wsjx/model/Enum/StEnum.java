package nju.software.wsjx.model.Enum;

/**
 *  实体主体enum
 * @author lr12
 *
 */
public enum StEnum {

	YG("原告"),
	BG("被告"),
	SSR("上诉人"),
	BSSR("被上诉人"),
	SQR("申请人"),
	BSQR("被申请人"),
	DSR("第三人");
	
	private String stzt;

	private StEnum() {
	}

	private StEnum(String stzt) {
		this.stzt = stzt;
	}
	
	public String getStzt() {
		return stzt;
	}

	public void setStzt(String stzt) {
		this.stzt = stzt;
	}

	public static  boolean hasStzt(String content){
		
		for(StEnum stEnum:StEnum.values()){
			if(stEnum.getStzt().equals(content))
				return true;
		}
		return false;
	}
	
}
