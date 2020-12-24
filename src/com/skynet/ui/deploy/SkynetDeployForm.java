/*
 * Copyright (c) 2018. CK. All rights reserved.
 */

package com.skynet.ui.deploy;

import java.awt.*;
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
import org.apache.http.client.HttpClient;

/**
 * @ClassName: CodeSettingsForm
 * @Author: jianhu5
 * @Date: 2019-12-05 15:26
 * @Description: 表单
 * @Version: 1.0
 **/
public class SkynetDeployForm extends JDialog {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel contentPane;
    private JTextField startContent;
    private JTextField stopContent;
    private JTextField upload;
    private JScrollPane scrollPane1;
    private JTextArea buildLog;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        contentPane = new JPanel();
        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel();
        JLabel label3 = new JLabel();
        startContent = new JTextField();
        stopContent = new JTextField();
        JLabel label4 = new JLabel();
        upload = new JTextField();
        JLabel label7 = new JLabel();
        scrollPane1 = new JScrollPane();
        buildLog = new JTextArea();

        //======== contentPane ========
        {
            contentPane.setAlignmentX(0.5F);
            contentPane.setAlignmentY(0.5F);
            contentPane.setDoubleBuffered(false);
            contentPane.setInheritsPopupMenu(true);
            contentPane.setMaximumSize(new Dimension(345, 104));
            contentPane.setMinimumSize(new Dimension(945, 704));
            contentPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
            . EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax
            . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,
            12 ), java. awt. Color. red) ,contentPane. getBorder( )) ); contentPane. addPropertyChangeListener (new java. beans
            . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .
            getPropertyName () )) throw new RuntimeException( ); }} );
            contentPane.setLayout(new GridLayoutManager(3, 3, new Insets(0, 5, 0, 0), 5, 5));

            //======== panel1 ========
            {
                panel1.setBorder(new TitledBorder("\u56fe\u8046Skynet"));
                panel1.setForeground(Color.darkGray);
                panel1.setLayout(new GridLayoutManager(7, 8, new Insets(0, 0, 0, 0), -1, -1));

                //---- label1 ----
                label1.setText("\u505c\u6b62\u670d\u52a1");
                panel1.add(label1, new GridConstraints(4, 0, 1, 1,
                    GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- label3 ----
                label3.setText("\u542f\u52a8\u670d\u52a1");
                panel1.add(label3, new GridConstraints(5, 0, 1, 1,
                    GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));
                panel1.add(startContent, new GridConstraints(5, 1, 1, 6,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));
                panel1.add(stopContent, new GridConstraints(4, 1, 1, 6,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- label4 ----
                label4.setText("\u4e0a\u4f20\u7248\u672c\u5305");
                panel1.add(label4, new GridConstraints(3, 0, 1, 1,
                    GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));
                panel1.add(upload, new GridConstraints(3, 1, 1, 6,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //---- label7 ----
                label7.setText("\u9879\u76ee\u6784\u5efa");
                panel1.add(label7, new GridConstraints(0, 0, 1, 1,
                    GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null));

                //======== scrollPane1 ========
                {

                    //---- buildLog ----
                    buildLog.setTabSize(10);
                    scrollPane1.setViewportView(buildLog);
                }
                panel1.add(scrollPane1, new GridConstraints(0, 1, 3, 7,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    new Dimension(700, 500), null, null));
            }
            contentPane.add(panel1, new GridConstraints(0, 0, 1, 3,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }




    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public void setContentPane(JPanel contentPane) {
        this.contentPane = contentPane;
    }

    public JTextField getStartContent() {
        return startContent;
    }

    public void setStartContent(JTextField startContent) {
        this.startContent = startContent;
    }

    public JTextField getStopContent() {
        return stopContent;
    }

    public void setStopContent(JTextField stopContent) {
        this.stopContent = stopContent;
    }

    public JTextField getUpload() {
        return upload;
    }

    public void setUpload(JTextField upload) {
        this.upload = upload;
    }



    public void appendBuildLog(String buildLog) {
        this.buildLog.append(buildLog+"\n");
    }

}
