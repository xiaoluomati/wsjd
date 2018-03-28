package nju.software.service;

import nju.software.vo.CheckInfoVO;
import nju.software.vo.DocInfoVO;

/**
 * Created by zhuding on 2018/3/28.
 */
public interface ErrorCheckService {

    CheckInfoVO checkError(DocInfoVO docInfoVO);

}
