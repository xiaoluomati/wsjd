package nju.software.vo;

/**
 *
 * - 结构错误
     - 结构缺失
        - 缺少诉讼记录, 判决结构, 案号
     - 要素缺失
        - 缺少 "申请人", "申请日期" 等
   - 内容错误
     - 要素错误
        - "申请人" 应为 原告...
     - 上下文不统一
        - 原告 xxx, 名字写错了
 * Created by away on 2018/3/28.
 */

public enum ErrorType {
    JGQS("结构缺失"), YSQS("要素缺失"), YSCW("要素错误"), SSWBTY("上下文不统一");

    private String name;

    ErrorType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
