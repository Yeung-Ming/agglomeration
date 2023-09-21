package com.deystar.Zip.ZipFourJ;

import com.deystar.Result.ResultState;
import com.deystar.ZipArgument.ZipArgument;
import com.deystar.Zip.Entity.FileListBean;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;

import java.io.IOException;


/**
 * @author Yeung Ming Luhyun 杨名 字露煊
 * zip4j
 */
public class FileToZip {


    public synchronized static void zip(FileListBean bean, ZipArgument userTyper) {
        ZipFile zipFile = null;
        try {
            ZipParameters zipParameters = new ZipParameters();
            zipParameters.setEncryptFiles(userTyper.getEncryption() && userTyper.getPassword() != null && !userTyper.getPassword().trim().isEmpty());
            zipFile = new ZipFile(bean.getZipName());
            if (userTyper.getEncryption() && userTyper.getPassword() != null && !userTyper.getPassword().trim().isEmpty()) {
                zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
                zipFile.setPassword(userTyper.getPassword().toCharArray());
            }
            zipFile.addFiles(bean.getFileLit(), zipParameters);
            ResultState.success(bean);
        } catch (IOException e) {
            ResultState.error(bean);
            throw new RuntimeException(e);
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}