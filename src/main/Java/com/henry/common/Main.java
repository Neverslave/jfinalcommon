package com.henry.common;

import com.jfinal.core.JFinal;
import com.jfinal.kit.HashKit;

public class Main {
    public static void main(String[] args) {
        JFinal.start("src/main/webapp",80,"/",5);
        //System.out.println(HashKit.sha256(""));
    }
}
