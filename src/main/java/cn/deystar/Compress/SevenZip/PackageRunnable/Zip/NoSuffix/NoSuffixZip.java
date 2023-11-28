package cn.deystar.Compress.SevenZip.PackageRunnable.Zip.NoSuffix;

import cn.deystar.Compress.Bean.FileListBean;
import cn.deystar.CompressArgument.CompressArgument;
import cn.deystar.Const.Suffix;
import cn.deystar.Compress.SevenZip.PackageRunnable.Common.ZipServiceImpl;
import cn.deystar.Const.SystemEnums;

import java.io.File;
import java.util.concurrent.Callable;

/**
 * @author Ming Yeung Luhyun (杨名 字 露煊)
 */
public class NoSuffixZip extends ZipServiceImpl implements Callable<FileListBean> {



    public NoSuffixZip(SystemEnums system, FileListBean bean, String command) {
        this.system= system;
        this.bean = bean;
        this.command = command;
    }

    @Override
    public FileListBean call() {
        FileListBean result = this.start();
        File origin = new File(bean.getCompressPathAndName());
        String renameTo = bean.getCompressPathAndName().replace(Suffix.ZIP.value, Suffix.NULL.value);
        origin.renameTo(new File(renameTo));
        return result;
    }
}
