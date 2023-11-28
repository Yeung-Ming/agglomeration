package cn.deystar.CustomException.PathException;

/**
 * @author Ming Yeung Luhyun (杨名 字 露煊)
 */
public enum PathExceptionEnums {

    DIRECTORY_ERROR("The Directory Is Not Exits Or No Read Permission "),

    PATH_NULL("The path or file is null or is not exits");

    public final String message;

    PathExceptionEnums(String message){
        this.message = message;
    }
}
