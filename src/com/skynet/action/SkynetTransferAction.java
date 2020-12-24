package com.skynet.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.skynet.ui.DeployDialogWrapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * @Author: jianhu5
 * @Date: 2020/11/10 19:08
 */
public class SkynetTransferAction extends AnAction {

    // 如果通过Java代码来注册，这个构造函数会被调用，传给父类的字符串会被作为菜单项的名称
    // 如果你通过plugin.xml来注册，可以忽略这个构造函数
    public SkynetTransferAction() {
        super("版本部署");
    }

    @Override
    public void actionPerformed(AnActionEvent event) {
        //画图
        Project project = event.getProject();
        DeployDialogWrapper deployDialogWrapper = new DeployDialogWrapper(project);
        deployDialogWrapper.show();
        //代码上传
        //服务停止
        //服务器启动

      /*  Project project = event.getData(PlatformDataKeys.PROJECT);
        String txt= Messages.showInputDialog(project, "输入您的skynet服务地址?", "输入您的skynet服务地址", Messages.getQuestionIcon());
        String username= Messages.showInputDialog(project, "用户名 ", "用户名 ", Messages.getQuestionIcon());
        String password= Messages.showInputDialog(project, "密码", "密码", Messages.getQuestionIcon());
        Messages.showMessageDialog(project, "登录skynet完成, " +username+":"+password+"@"+txt+ "!\n I am glad to see you.", "Information", Messages.getInformationIcon());*/
    }
}

/**
 //本地代码构建
 //获取Popup工程
 JBPopupFactory factory = JBPopupFactory.getInstance();


 // 获取 JBPopupFactory
 JBPopupFactory instance = JBPopupFactory.getInstance();

 // 创建需要执行的任务
 Runnable runnable = new Runnable() {
@Override
public void run() {
Messages.showMessageDialog("aaa", "hello", Messages.getInformationIcon());
}
};
 ListPopup popup = instance.createConfirmation("hello", runnable, 1);
 popup.showInBestPositionFor(event.getDataContext());
 **/