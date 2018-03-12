package nju.software.wsjx.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 数值相关的工具类
 * @author zym
 *
 */
public class NumberUtil {
	/**
	 * 数值保留两位小数
	 * 
	 * @param convert
	 *            转换前数值
	 * @return 转换后字符串
	 */
	public static String changeNumberType(BigDecimal big) {

		big.setScale(2, RoundingMode.HALF_UP);
		DecimalFormat format = new DecimalFormat("##0.00");
		return format.format(big);
	}
	
	/**
	 * 将double 最多保留place位数
	 * @param value
	 * @param place 保留位数
	 * @return
	 */
	public static String changeNumber(double value, int place){
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(place);
		nf.setGroupingUsed(false);
		return nf.format(value);
	}
}
