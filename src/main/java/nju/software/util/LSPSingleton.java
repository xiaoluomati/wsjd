package nju.software.util;

import nju.software.preProcess.DataProcess;
import nju.software.preProcess.LabeledSentenceProcess;

public class LSPSingleton {

    private static final LabeledSentenceProcess instance = DataProcess.readModel();

    public static LabeledSentenceProcess getInstance(){
        return instance;
    }

}
