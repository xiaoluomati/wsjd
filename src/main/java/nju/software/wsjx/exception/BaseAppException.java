/**   
* @date 2012-7-3 by YU
* @version V1.0   
*/
package nju.software.wsjx.exception;

/**
 * @author YU
 *说明:系统中所有unchecked异常的基类
 */
public class BaseAppException extends RuntimeException{

	/**
	* @Fields serialVersionUID
	*/
	private static final long serialVersionUID = 896610542617189773L;
	
	/**
     * BaseAppException中包含的异常消息
     */
    private String exceptionMessage;

    /**
     * 无参构造方法
     *  
     */
    public BaseAppException() {
    	
    }

    /**
     * 有参构造方法
     * <p>
     * 
     * @param msg
     *            异常消息
     *  
     */
    public BaseAppException(String msg) {
        this.exceptionMessage = msg;
    }

    /**
     * 有参构造方法
     * 
     * @param msg
     *            异常消息
     * @param e
     *            异常产生的根源,Throwable object.
     *  
     */
    public BaseAppException(String msg, Throwable e) {
        this.exceptionMessage = msg;
        this.initCause(e);
    }

    /**
     * 设置异常产生的根源
     * 
     * @param e
     *            异常产生的根源,Throwable object.
     *  
     */
    public void setCause(Throwable e) {
        this.initCause(e);
    }

    /**
     * 
     * @see Object#toString()
     */
    public String toString() {
        String s = getClass().getName();
        return s + ": " + exceptionMessage;
    }

    /**
     * 获得异常消息. 等价于Exception.getMessage().
     * 
     * @see Throwable#getMessage()
     */
    public String getMessage() {
        return exceptionMessage;
    }

}
