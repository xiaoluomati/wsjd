package nju.software.service;

import nju.software.vo.LawItemVO;

import java.util.List;

/**
 * Created by away on 2018/3/28.
 */
public interface LawManagerService {

    /**
     *
     * @param lawItemVOS 输入为一系列法律以及其中的法条, LawItemVO 中的 lawList 由数个 map 组成,
     *                   key 为要拿的法条, value 为 ""
     * @return 将 value 填成法条内容
     */
    List<LawItemVO> getLaw(List<LawItemVO> lawItemVOS);
}
