package nju.software.wsjx.model;

import java.util.Map;

public interface ModelInterface {

	/**
	 * 所有的model只需要实现该接口则可以返回
	 * @return
	 */
	public Map<String,Object> TransformToDataMap();
}
