package cn.deystar.BashFile;

import cn.deystar.Const.SystemEnums;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ming Yeung Luhyun (杨名 字 露煊)
 */
public class BashFileBuilder {



    public static File genFile(String command, File file,SystemEnums system){
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            stream.write(command.getBytes(encoder(system)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if (stream!= null){
                try {
                    stream.flush();
                    stream.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return file;
    }


    private static Charset encoder(SystemEnums systemEnums){
        return systemEnums.equals(SystemEnums.Windows) ? Charset.forName("GBK") : StandardCharsets.UTF_8;
    }


}
