package nju.software.preProcess;

import com.sun.jna.Native;

import java.io.UnsupportedEncodingException;

public class NLPIR {

    private CLibrary Instance = (CLibrary) Native.loadLibrary(System.getProperty("user.dir")+"\\source\\NLPIR", CLibrary.class);

    private boolean initflag = false;

    public boolean init() throws UnsupportedEncodingException {
        String argu = "";
        //         String system_charset = "GBK";//GBK----0
        String system_charset = "UTF-8";
        int charset_type = 1;
//         int charset_type = 0;
        // 调用printf打印信息


        boolean init_flag = Instance.NLPIR_Init(argu.getBytes(system_charset),
                charset_type, "0".getBytes(system_charset));
        String nativeBytes = null;
        if (!init_flag){
            nativeBytes = Instance.NLPIR_GetLastErrorMsg();
            System.err.println("初始化失败！fail reason is "+nativeBytes);
        }
        initflag = true;
        return true;

    }

    public boolean unInit(){
        try {
            Instance.NLPIR_Exit();
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        initflag = false;
        return true;
    }

    public String parseSeq(String sInput) {
        String nativeBytes = null;
        try {
            nativeBytes = Instance.NLPIR_ParagraphProcess(sInput, 3);
//             String nativeStr = new String(nativeBytes, 0,
//             nativeBytes.length(),"utf-8");
//             + transString(nativeBytes, system_charset, "UTF-8"));
            // + transString(nativeBytes, "gb2312", "utf-8"));
//            int nCountKey = 0;
//            String nativeByte = CLibrary.Instance.NLPIR_GetKeyWords(sInput, 10,true);
//            String nativeBytenew  = CLibrary.Instance.NLPIR_GetNewWords(sInput,10,true);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return nativeBytes;
    }

    public CLibrary getInstance() {
        return Instance;
    }

    public boolean isInitflag() {
        return initflag;
    }
}
