package com.skynet.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.skynet.dto.SkynetConfigDto;
import com.skynet.ui.codeSettingsForm.CodeSettingsForm;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: jianhu5
 * @Date: 2020/11/19 16:18
 */
public class CodeGenerateDialogWrapper extends DialogWrapper {
    final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    final static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final Project project;
    private CodeSettingsForm contentPanel;

    public CodeGenerateDialogWrapper(@Nullable Project project) {
        super(project);
        this.project = project;

        setOKButtonText("保存");
        setCancelButtonText("取消");
        //this.setSize((int) (width * 0.6f), (int) (height * 0.5f));

        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        contentPanel = new CodeSettingsForm();
        contentPanel.initComponents();
        contentPanel.preInitOperate(project);
        //页面的后置操作 主要用于组件事件绑定
        contentPanel.postInitOperate(project);
        return contentPanel.getContentPane();
    }

    public CodeSettingsForm getCodeSettingsForm(){
        return this.contentPanel;
    }

}
