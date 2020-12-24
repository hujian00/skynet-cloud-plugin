/*
 * Copyright (c) 2018. CK. All rights reserved.
 */

package com.skynet.ui.codeSettingsForm;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.*;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.uiDesigner.core.*;
import com.skynet.dto.SkynetConfigDto;
import com.skynet.ui.FileChooseUi;
import com.skynet.until.HttpClientUtil;
import com.skynet.until.ValidateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.codehaus.jettison.json.JSONObject;

/**
 * @ClassName: CodeSettingsForm
 * @Author: jianhu5
 * @Date: 2019-12-05 15:26
 * @Description: 表单
 * @Version: 1.0
 **/
public class CodeSettingsForm extends JDialog {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel contentPane;
    private JTextField userName;
    private JTextField packageName;
    private JTextField path;
    private JButton testButton;
    private JTextField serverCode;
    private JButton pathButton;
    private JPasswordField password;
    private JTextField address;
    private JTextField plugin;
    private JTextField hostIp;
    private JTextField command;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private SkynetConfigDto config;

    public void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        contentPane = new JPanel();
        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel();
        userName = new JTextField();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        packageName = new JTextField();
        path = new JTextField();
        testButton = new JButton();
        serverCode = new JTextField();
        JLabel label5 = new JLabel();
        pathButton = new JButton();
        JLabel label6 = new JLabel();
        password = new JPasswordField();
        address = new JTextField();
        plugin = new JTextField();
        JLabel label7 = new JLabel();
        JLabel label9 = new JLabel();
        hostIp = new JTextField();
        JLabel label4 = new JLabel();
        JLabel label8 = new JLabel();
        command = new JTextField();

        //======== contentPane ========
        {
            contentPane.setAlignmentX(0.5F);
            contentPane.setAlignmentY(0.5F);
            contentPane.setDoubleBuffered(false);
            contentPane.setInheritsPopupMenu(true);
            contentPane.setMaximumSize(new Dimension(345, 104));
            contentPane.setMinimumSize(new Dimension(345, 104));
            contentPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
            javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax
            . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
            .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,contentPane. getBorder( )) ); contentPane. addPropertyChangeListener (new java. beans.
            PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .
            equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            contentPane.setLayout(new GridLayoutManager(1, 3, new Insets(0, 5, 0, 0), 5, 5));

            //======== panel1 ========
            {
                panel1.setBorder(new TitledBorder("\u56fe\u8046Skynet"));
                panel1.setForeground(Color.darkGray);
                panel1.setLayout(new GridLayoutManager(10, 8, new Insets(0, 0, 0, 0), -1, -1));

                //---- label1 ----
                label1.setText("\u7248\u672c\u5305\u8def\u5f84");
                panel1.add(label1, new GridConstraints(5, 0, 1, 1,
                    GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- userName ----
                userName.setText("skynet");
                userName.setToolTipText("admin");
                panel1.add(userName, new GridConstraints(1, 1, 1, 3,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- label2 ----
                label2.setText("\u7528\u6237\u540d");
                panel1.add(label2, new GridConstraints(1, 0, 1, 1,
                    GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- label3 ----
                label3.setText("\u5305\u540d");
                panel1.add(label3, new GridConstraints(6, 0, 1, 1,
                    GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));
                panel1.add(packageName, new GridConstraints(6, 1, 1, 6,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));
                panel1.add(path, new GridConstraints(5, 1, 1, 6,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- testButton ----
                testButton.setText("\u6d4b\u8bd5\u8fde\u63a5");
                panel1.add(testButton, new GridConstraints(8, 6, 1, 1,
                    GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, new Dimension(101, 30), null));
                panel1.add(serverCode, new GridConstraints(4, 1, 1, 6,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- label5 ----
                label5.setText("\u5e94\u7528\u7cfb\u7edf\u7f16\u7801");
                panel1.add(label5, new GridConstraints(2, 0, 1, 1,
                    GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- pathButton ----
                pathButton.setText("...");
                panel1.add(pathButton, new GridConstraints(5, 7, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- label6 ----
                label6.setText("\u5bc6\u7801");
                panel1.add(label6, new GridConstraints(1, 4, 1, 1,
                    GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- password ----
                password.setText("");
                panel1.add(password, new GridConstraints(1, 5, 1, 2,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- address ----
                address.setText("http://");
                panel1.add(address, new GridConstraints(0, 1, 1, 6,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- plugin ----
                plugin.setText("beehive-paas");
                plugin.setToolTipText("\u670d\u52a1\u5b9a\u4e49-\u5e94\u7528\u5217\u8868");
                panel1.add(plugin, new GridConstraints(2, 1, 1, 6,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- label7 ----
                label7.setText("Skynet \u5730\u5740");
                panel1.add(label7, new GridConstraints(0, 0, 1, 1,
                    GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- label9 ----
                label9.setText("\u670d\u52a1\u5668\u5730\u5740");
                panel1.add(label9, new GridConstraints(3, 0, 1, 1,
                    GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));
                panel1.add(hostIp, new GridConstraints(3, 1, 1, 6,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- label4 ----
                label4.setText("\u670d\u52a1\u7f16\u7801");
                panel1.add(label4, new GridConstraints(4, 0, 1, 1,
                    GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- label8 ----
                label8.setText("\u6253\u5305\u547d\u4ee4");
                panel1.add(label8, new GridConstraints(7, 0, 1, 1,
                    GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- command ----
                command.setText("mvn clean package");
                panel1.add(command, new GridConstraints(7, 1, 1, 6,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));
            }
            contentPane.add(panel1, new GridConstraints(0, 0, 1, 2,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public void apply() {
        config.setAddress(this.address.getText());
        config.setPackageName(this.packageName.getText());
        config.setPassword(this.password.getText());
        config.setPath(this.path.getText());
        config.setPlugin(this.plugin.getText());
        config.setServerCode(this.serverCode.getText());
        config.setUserName(this.userName.getText());
        config.setHostIp(this.hostIp.getText());
        config.setCommand(this.command.getText());
    }

    /**
     * 页面的后置操作 主要用于组件事件绑定
     */
    public void postInitOperate(Project project){
        this.pathButton.addActionListener(e -> {
            FileChooseUi uiComponentFacade = FileChooseUi.getInstance(project);
            VirtualFile baseDir = project.getBaseDir().getParent();
            VirtualFile vf = uiComponentFacade.showSingleFolderSelectionDialog("选择项目路径", baseDir, baseDir);
            if (null != vf) {
                CodeSettingsForm.this.path.setText(vf.getPath());
                apply();
            }
        });

        this.testButton.addActionListener(e -> {
            apply();
            if (!ValidateUtil.validateHostConfig(project, config)) {
                return;
            }
            String userJson = "{\"username\":\""+config.getUserName()+"\",\"password\":\""+config.getPassword()+"\"}";
            String responeString = HttpClientUtil.sendPostByJson(config.getAddress()+"/skynet/auth/login", userJson ,0,null);
            if (StringUtils.isBlank(responeString)){
                Messages.showInfoMessage(project, "连接失败", "Fail");
            }else {
                Messages.showInfoMessage(project, "连接成功", "OK");
            }
        });

    }

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public SkynetConfigDto getConfig() {
        return config;
    }

    public void setConfig(SkynetConfigDto config) {
        this.config = config;
    }

    public void preInitOperate(Project project) {
        config = SkynetConfigDto.getInstance(project != null ? project : ProjectManager.getInstance().getDefaultProject());
        //填充数据
        this.address.setText(config.getAddress());
        this.packageName.setText(config.getPackageName());
        this.password.setText(config.getPassword());
        this.path.setText(config.getPath());
        this.plugin.setText(config.getPlugin());
        this.serverCode.setText(config.getServerCode());
        this.userName.setText(config.getUserName());
        this.hostIp.setText(config.getHostIp());
        this.command.setText(config.getCommand());
    }
}
