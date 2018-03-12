package nju.software.wsjx.service.model;

import java.io.Serializable;
import java.util.List;

/**
 * ·Ö×émodel
 * 
 * @author lr12
 * 
 */
@SuppressWarnings("serial")
public class FzModel implements Serializable{
	private int zh;
	private List<String> cluster;

	public int getZh() {
		return zh;
	}

	public void setZh(int zh) {
		this.zh = zh;
	}

	public List<String> getCluster() {
		return cluster;
	}

	public void setCluster(List<String> cluster) {
		this.cluster = cluster;
	}

}
