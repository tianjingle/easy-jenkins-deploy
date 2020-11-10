package com.scaffold.easy.jenkins.server.utils;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

public class CMDExecuteUtil {

    public static String executeCommand(String cmd, File file) {
        // TODO Auto-generated method stub
        //根据运行环境判断运行命令
        String osName=System.getProperty("os.name");
        String []command={"","",cmd};
        if(osName.contains("Windows")){
            command[0]="cmd.exe";
            command[1]="/c";
        }else{
            command[0]="/bin/sh";
            command[1]="-c";
        }
        Map<String, String> map = System.getenv();
        for(Iterator<String> itr = map.keySet().iterator(); itr.hasNext();){
            String key = itr.next();
            if (key.equals(cmd)){
                cmd.replaceAll(key,map.get(key));
            }
        }
        StringBuffer output=new StringBuffer();
        Process p;
        InputStreamReader inputStreamReader=null;
        BufferedReader reader=null;
        try{
            p=Runtime.getRuntime().exec(command,null,file);
            Thread t=new Thread(new InputStreamRunnable(p.getErrorStream()));
            t.start();
            if (!cmd.startsWith("start ")) {
                inputStreamReader = new InputStreamReader(p.getInputStream(), "utf-8");
                reader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = reader.readLine()) != null) {
                    output.append(line + "\n");
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if (inputStreamReader!=null) {
                    inputStreamReader.close();
                    reader.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return output.toString();
    }


    public static String parseCommand(String commandFilePath) throws IOException {
        StringBuffer stringBuffer=new StringBuffer("");
        String[] bufstring=new String[1024];
        //打开带读取的文件
        BufferedReader br = null;
        int position=0;
        try {
            br = new BufferedReader(new FileReader(commandFilePath));
            String line=null;
            while((line=br.readLine())!=null) {
                System.out.println(line);
                stringBuffer.append(line).append("@");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();//关闭
        }
        return stringBuffer.toString();
    }
}
