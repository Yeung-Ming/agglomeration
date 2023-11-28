package cn.deystar;

import cn.deystar.BashFile.BashFileBuilder;
import cn.deystar.Compress.Bean.FileListBean;
import cn.deystar.Compress.SevenZip.Command.ZipCommand.ZipCommandBuilder;
import cn.deystar.Compress.SevenZip.PackageRunnable.Common.ZipServiceImpl;
import cn.deystar.Compress.SevenZip.PackageRunnable.Zip.NoSuffix.NoSuffixZip;
import cn.deystar.Compress.SevenZip.PackageRunnable.Zip.Suffix.SuffixZip;
import cn.deystar.CompressArgument.CompressArgument;
import cn.deystar.Const.SystemEnums;
import cn.deystar.ScanUtil.FileScan;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ming Yeung Luhyun (杨名 字 露煊)
 */
public class WindowsTest {


    public static void main(String args[]) {
        String source = "C:\\Users\\YeungLuhyun\\Desktop\\测试";
        String zipDirectory = "C:\\Users\\YeungLuhyun\\Desktop\\测试toZip";

        CompressArgument compressArgument = new CompressArgument();
        compressArgument.setPassword("");
        compressArgument.setNameAnonymity(false);
        compressArgument.setOutPutPathAndName(zipDirectory);
        compressArgument.setEncryption(false);
        compressArgument.setSourcePath(source);
        compressArgument.setFileMaxSize((long) (1 * Math.pow(1024D, 4D)));
        List<FileListBean> fileListBeanList = new FileScan(compressArgument).getList();
        StringBuilder builder = new StringBuilder();
        for (FileListBean bean : fileListBeanList) {
            String command = new ZipCommandBuilder(SystemEnums.Windows)
                    .outPut(null, bean.getCompressPathAndName())
                    .password(null)
                    .append(bean.getFileList())
                    .build();
            String compressName = bean.getCompressName();
            File bashFile = new File(zipDirectory + "/"+compressName + ".bat");
            bashFile = BashFileBuilder.genFile(command, bashFile,SystemEnums.Windows);
            builder.append(bashFile.toString());
            if (fileListBeanList.indexOf(bean) != fileListBeanList.size()-1){
                builder.append(" && ");
            }
        }
        String finalCommand = builder.toString();
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec(finalCommand);




            int exitVal = proc.waitFor();

            System.out.println(exitVal);
        } catch (InterruptedException | IOException e) {
            System.out.println(e);
        } finally {
            if (proc != null)
                proc.destroy();
        }
    }
}
