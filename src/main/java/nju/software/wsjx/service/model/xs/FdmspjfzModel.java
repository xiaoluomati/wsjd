package nju.software.wsjx.service.model.xs;

import java.util.List;

public class FdmspjfzModel {

	private String nr;
	private List<String> pcr;
	private List<String> bpcr;
	private List<String> bcje;
	
	public FdmspjfzModel() {
		super();
	}
	public List<String> getPcr() {
		return pcr;
	}
	public void setPcr(List<String> pcr) {
		this.pcr = pcr;
	}
	public List<String> getBpcr() {
		return bpcr;
	}
	public void setBpcr(List<String> bpcr) {
		this.bpcr = bpcr;
	}
	public List<String> getBcje() {
		return bcje;
	}
	public void setBcje(List<String> bcje) {
		this.bcje = bcje;
	}
	public String getNr() {
		return nr;
	}
	public void setNr(String nr) {
		this.nr = nr;
	}
	
}
