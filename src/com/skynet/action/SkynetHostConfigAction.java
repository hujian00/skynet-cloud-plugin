package com.skynet.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.skynet.dto.SkynetConfigDto;
import com.skynet.ui.CodeGenerateDialogWrapper;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: jianhu5
 * @Date: 2020/11/10 19:08
 */
public class SkynetHostConfigAction extends AnAction {
    // 如果通过Java代码来注册，这个构造函数会被调用，传给父类的字符串会被作为菜单项的名称
    // 如果你通过plugin.xml来注册，可以忽略这个构造函数
    public SkynetHostConfigAction() {
        super("服务器配置");
    }

    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();
        CodeGenerateDialogWrapper wrapper = new CodeGenerateDialogWrapper(project);
        boolean result = wrapper.showAndGet();
        if (result) {
            wrapper.getCodeSettingsForm().apply();
            SkynetConfigDto config = SkynetConfigDto.getInstance(project);
            Messages.showInfoMessage(project, "保存成功", "Success");
        }
    }
}
