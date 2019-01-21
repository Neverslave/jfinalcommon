package com.henry.upload;

import com.henry.common.BaseController;
import com.jfinal.upload.UploadFile;

public class UploadController extends BaseController {
    UploadFile uf = null;

    public void index(){
       uf = getFile();


    }
}
