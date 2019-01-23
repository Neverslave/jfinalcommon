package com.henry.upload;

import com.henry.common.model.Upload;
import com.jfinal.upload.UploadFile;

import java.io.File;

/**
 * 上传文件
 * */
public class UpLoadService {
    public static  final  UpLoadService me =new UpLoadService();
    private Upload dao = new Upload().dao();

    public File UploadFile(UploadFile uf){
        File file = uf.getFile();
        F


    }






}
