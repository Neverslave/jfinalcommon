package com.henry.upload;

import com.henry.common.model.Upload;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.upload.UploadFile;

import java.io.File;

/**
 * 上传文件
 * */
public class UpLoadService {
    public static  final  UpLoadService me =new UpLoadService();
    private Upload dao = new Upload().dao();

    public Ret UploadFile(UploadFile uf , String groupId){
        File file = uf.getFile();
        Ret ret = new Ret();
        if(file ==null){
            return ret.setFail().set("code","400");
        }

        String fileName = uf.getFileName();
        String type = uf.getContentType();
        double  fileSize = file.length();
        Upload upload =new Upload();
        String Id = StrKit.getRandomUUID();//唯一Id
        upload.setId(Id);
        upload.setType(type);
        upload.setGroupId(groupId);
        upload.setFileName(fileName);
        upload.setFileSize(fileSize);
        upload.setUrl(file.getPath());
        if(upload.save()){
          return   ret.set("code",0);
        }
        return ret.set("code",400);

    }






}
