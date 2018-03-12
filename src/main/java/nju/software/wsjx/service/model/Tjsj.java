package nju.software.wsjx.service.model;

public class Tjsj {

	private String fymc;
	private int xs;
	private int jc;
	private int ja;
	private String bd;

	public String getFymc() {
		return fymc;
	}

	public void setFymc(String fymc) {
		this.fymc = fymc;
	}

	public int getXs() {
		return xs;
	}

	public void setXs(int xs) {
		this.xs = xs;
	}

	public int getJc() {
		return jc;
	}

	public void setJc(int jc) {
		this.jc = jc;
	}

	public int getJa() {
		return ja;
	}

	public void setJa(int ja) {
		this.ja = ja;
	}

	public String getBd() {
		return bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
	}

	@Override
	public String toString() {
		return fymc + "\t" + xs + "\t" + jc + "\t" + ja + "\t" + bd;
	}

}
