/*
 * Copyright (c) 2018. CK. All rights reserved.
 */

package com.skynet.until;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.skynet.dto.SkynetConfigDto;
import org.apache.commons.lang.StringUtils;

public class ValidateUtil {

    public static boolean validateHostConfig(Project project, SkynetConfigDto config) {
        if (StringUtils.isBlank(config.getAddress())) {
            Messages.showErrorDialog(project, "skynet地址不能为空.", "Error");
            return false;
        }
        if (StringUtils.isBlank(config.getUserName())) {
            Messages.showErrorDialog(project, "skynet用户名不能为空.", "Error");
            return false;
        }
        if (StringUtils.isBlank(config.getPassword())) {
            Messages.showErrorDialog(project, "密码不能为空", "Error");
            return false;
        }
        return true;
    }
    public static boolean validateConfig(Project project, SkynetConfigDto config) {
            if(!validateHostConfig(project, config)){
                return false;
            }
            if (StringUtils.isBlank(config.getPath())) {
                Messages.showErrorDialog(project, "项目路径为空.", "Error");
                return false;
            }
            if (StringUtils.isBlank(config.getPackageName())) {
                Messages.showErrorDialog(project, "包名为空.", "Error");
                return false;
            }
            if (StringUtils.isBlank(config.getPlugin())) {
                Messages.showErrorDialog(project, "应用系统编码为空.", "Error");
                return false;
            }
            if (StringUtils.isBlank(config.getServerCode())) {
                Messages.showErrorDialog(project, "服务编码为空.", "Error");
                return false;
            }
            return true;
    }
}
