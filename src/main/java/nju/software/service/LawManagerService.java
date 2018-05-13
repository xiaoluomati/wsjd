package nju.software.service;

import nju.software.preProcess.LabeledSentenceProcess;
import nju.software.vo.LawItemVO;

import java.io.UnsupportedEncodingException;
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

    /**
     *
     * @param content 输入为文书的案件基本情况
     * @param labeledSentenceProcess 输入为已经加载好的模型
     * @return 输出为预测的一组法条
     */
    List<LawItemVO> lawRecommend(String content, LabeledSentenceProcess labeledSentenceProcess) throws UnsupportedEncodingException;

    List<LawItemVO> deduplication(List<LawItemVO> lawItemVOList, List<LawItemVO> recommendlawItemVOList);
}
