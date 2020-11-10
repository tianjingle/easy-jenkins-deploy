package com.scaffold.easy.jenkins.server;

import com.scaffold.easy.jenkins.server.infrastration.router.ro.SendPackRo;
import com.scaffold.easy.jenkins.server.infrastration.router.service.SendPackService;
import com.sun.glass.ui.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class LabApplicationTests {


    @Autowired
    private SendPackService sendPackService;


    @Test
    public void executeTask(){
        SendPackRo ro=new SendPackRo();
        ro.setCommandName("D:\\github\\cosin\\contact-center\\appserver\\target\\appserver.war");
        ro.setCommandName("D:\\github\\cosin\\contact-center\\appserver\\execute.sh");
        ro.setServerUrl("http://127.0.0.1:8082/Jdeploy/upload");
        ro.setSave(true);
        sendPackService.upload2Serer(ro);
    }

}
