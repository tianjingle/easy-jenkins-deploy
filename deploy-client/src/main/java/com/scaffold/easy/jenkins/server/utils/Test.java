//package com.scaffold.easy.jenkins.server.utils;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.InputStreamReader;
//import java.util.Iterator;
//import java.util.Map;
//
///**
// * @Author: tianjl
// * @Date: 2020/11/12 19:32
// * @Eamil: 2695062879@qq.com
// */
//public class Test {
//
//    public static void main(String[] args) {
//        String osName=System.getProperty("os.name");
//        String []command={"","","start D:\\github\\xmleditorserver\\execute.bat"};
//        if(osName.contains("Windows")){
//            command[0]="cmd.exe";
//            command[1]="/c";
//        }else{
//            command[0]="/bin/sh";
//            command[1]="-c";
//        }
//        Map<String, String> map = System.getenv();
//        String[] evn=new String[100];
//        int i=0;
//        for(Iterator<String> itr = map.keySet().iterator(); itr.hasNext();){
//            String key = itr.next();
//            evn[i++]=key+"="+map.get(key);
//        }
//        Process p;
//        try {
//            p = Runtime.getRuntime().exec(command, null,new File("D:\\github\\jenkinsdeployeasy\\deploy-client\\target"));
//            Thread t = new Thread(new InputStreamRunnable(p.getErrorStream()));
//            t.start();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
