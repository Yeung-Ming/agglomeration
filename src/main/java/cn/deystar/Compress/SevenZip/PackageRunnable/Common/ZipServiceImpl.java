package cn.deystar.Compress.SevenZip.PackageRunnable.Common;

import cn.deystar.CompressArgument.CompressArgument;
import cn.deystar.Const.CompressStatus;
import cn.deystar.Compress.Bean.FileListBean;
import cn.deystar.Compress.ZipFourJ.FileToZip;
import cn.deystar.Const.SystemEnums;
import cn.hutool.core.io.resource.ResourceUtil;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ming Yeung Luhyun (杨名 字 露煊)
 * 公共
 */
public class ZipServiceImpl {


    protected FileListBean bean;

    protected SystemEnums system;

    protected String command;


    protected FileListBean start() {

        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec(command);
            InputStream stdIn = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(stdIn, encoder());
            BufferedReader br = new BufferedReader(isr);
            String line = null;

            while ((line = br.readLine()) != null)
                System.out.println(line);
            int exitVal = proc.waitFor();
            if (exitVal != 0) {
                beforeError();
            }
            System.out.println(exitVal);
        } catch (InterruptedException | IOException e) {
            beforeError();
        } finally {
            if (proc != null)
                proc.destroy();
        }
        return bean;
    }


    private void beforeError() {
        FileListBean bean = (FileListBean) this.bean;
        File file = new File(bean.getCompressPathAndName());
        if (file.exists()) file.deleteOnExit();
        bean.setStatus(CompressStatus.ERROR);

    }

    private Charset encoder() {
        return system.equals(SystemEnums.Windows) ? Charset.forName("GBK") : StandardCharsets.UTF_8;
    }



}
