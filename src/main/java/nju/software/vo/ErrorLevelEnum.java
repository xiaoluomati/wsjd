package nju.software.vo;

/**
 * 错误等级
 * LV_1 轻微错误, 建议关注, 或者检测出来了但是不一定准确? 如:
 *      或者缺少 当事人职业或者工作单位和职务这种不是必要的信息
 * LV_2 中等错误, 建议修改, 如: 上下文不匹配, 缺少住所 性别, 默认错误级别
 * LV_3 严重错误, 强烈建议修改, 如: 缺少重要要素, 案号格式
 *
 * 当前归类
 * LV_1
 *  错别字
 *
 * LV_2
 *  默认
 *  sscyr
 *      缺少诉讼参与人名称
 *      缺少当事人(自然人) 性别, 生日, 民族, 住所
 *      缺少当事人(法人) 名称, 住所
 *      缺少法定代表人 姓名, 职务
 *  ssjl
 *
 *
 * LV_3
 *  案号格式
 * Created by away on 2018/4/11.
 */
public enum ErrorLevelEnum {
    LV_1(1), LV_2(2), LV_3(3);

    private int num;

    ErrorLevelEnum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
