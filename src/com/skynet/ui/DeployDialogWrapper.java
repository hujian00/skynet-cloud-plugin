package com.skynet.ui;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.skynet.dto.SkynetConfigDto;
import com.skynet.dto.SkynetResult;
import com.skynet.ui.deploy.SkynetDeployForm;
import com.skynet.until.HttpClientUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.concurrent.*;

/**
 * @Author: jianhu5
 * @Date: 2020/11/19 16:18
 */
public class DeployDialogWrapper extends DialogWrapper {
    final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    final static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private  Project project;
    private SkynetDeployForm panel;
    final String newLine = "\r\n";
    public DeployDialogWrapper(@Nullable Project project) {
        super(project);
        this.project = project;
        init();
    }

    @Nullable
    @Override
    public JComponent createCenterPanel() {
        panel = new SkynetDeployForm();
        panel.initComponents();
        synThread();
        return panel.getContentPane();
    }

    public void synThread(){
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new Runnable() {
            @Override
            public void run() {
               execPackage();
                try {
                    String token = login();
                    uploadPackage(token);
                    restartServer(token);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }


    public Project getProject() {
        return project;
    }

    public SkynetDeployForm getPanel() {
        return panel;
    }

    public void setPanel(SkynetDeployForm panel) {
        this.panel = panel;
    }
    /**
     * 登录
     */
    private String login() {
        SkynetConfigDto config = SkynetConfigDto.getInstance(project);
        String userJson = "{\"username\":\""+config.getUserName()+"\",\"password\":\""+config.getPassword()+"\"}";
        String token = HttpClientUtil.sendPostByJson(config.getAddress()+"/skynet/auth/login", userJson ,0,null);
        if (StringUtils.isBlank(token)){
            //登录异常
            return null;
        }
        //返回token
        return token;
    }

    /**
     * 重启服务
     */
    private void restartServer(String token) throws InterruptedException {
        SkynetConfigDto config = SkynetConfigDto.getInstance(project);
        String url = config.getAddress()+"/skynet/api/v3/actions/status/"+config.getHostIp()+"/"+config.getServerCode()+"@"+config.getPlugin();
        String tips = "服务（"+config.getServerCode()+")：";
        String offResponse = HttpClientUtil.sendPutByJson(url,"{\"enabled\":false}",0,token);
        String tipsOff = tips+"停止";
        if (resultBoolean(offResponse)){
           tipsOff = tipsOff +"成功";
        }else {
            tipsOff = tipsOff +"失败，错误返回："+offResponse;
        }
        panel.getStopContent().setText(tipsOff);
        Thread.sleep(4*1000);
        String onResponse = HttpClientUtil.sendPutByJson(url,"{\"enabled\":true}",0,token);
        String tipsOn = tips+"开启";
        if (resultBoolean(onResponse)){
            tipsOn = tipsOn +"成功";
        }else {
            tipsOn = tipsOn +"失败，错误返回："+offResponse;
        }
        panel.getStartContent().setText(tipsOn);
    }

    private boolean resultBoolean(String reponse){
        if (StringUtils.isNotBlank(reponse)){
            SkynetResult result = JSON.parseObject(reponse,SkynetResult.class);
            if (result.getCode() == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * 上传版本包
     * 对接skynet接口 进行上传
     */
    private void uploadPackage(String token) throws Exception {
        SkynetConfigDto config = SkynetConfigDto.getInstance(project);
        StringBuffer text = new StringBuffer();
        text.append("开始上传文件 (1/2)   ...   ");
        panel.getUpload().setText(text.toString());
        String url = config.getAddress()+"/skynet/api/v3/repo/files/"+config.getPlugin();
        String message = HttpClientUtil.upload(token,url,config.getPath()+"\\"+config.getPackageName());
        String tips = "服务（"+config.getServerCode()+")上传：";
        if (resultBoolean(message)){
            tips = tips +"成功!";
        }else {
            tips = tips +"失败，错误返回："+message;
        }
        text.append(tips).append("(2/2)");
        panel.getUpload().setText(text.toString());

    }

    /**
     * 执行打包命令(支持自定义)
     */
    private void execPackage() {
        try{
            SkynetConfigDto config = SkynetConfigDto.getInstance(project);
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("cmd /k cd "+config.getPath()+"\\.. && "+config.getCommand()+" && exit");
            while (p.isAlive()){
                p.waitFor(2, TimeUnit.SECONDS);
                BufferedReader buffer = new BufferedReader (new InputStreamReader(p.getInputStream()));
                String line = null;
                while ((line = buffer.readLine())!= null){
                    panel.appendBuildLog(line);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
