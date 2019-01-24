package com.henry.upload;

import com.henry.common.BaseController;
import com.jfinal.kit.Ret;
import com.jfinal.upload.UploadFile;

public class UploadController extends BaseController {
    UploadFile uf = null;
    public UpLoadService srv = UpLoadService.me;

    public void index(){
       uf = getFile();
       String uuid =getPara("uuid");
        Ret ret = srv.UploadFile(uf,uuid);
        renderJson(ret);
    }
}
