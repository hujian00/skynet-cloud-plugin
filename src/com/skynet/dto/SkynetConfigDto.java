package com.skynet.dto;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @Author: jianhu5
 * @Date: 2020/11/19 16:51
 */
@State(name = "SkynetConfigDto", storages = { @Storage("skynetConfigDto.xml")})
public class SkynetConfigDto implements PersistentStateComponent<SkynetConfigDto> {
    //用户名
    private String userName;
    //包名字
    private String packageName;
    //路径
    private String path;
    //服务编码
    private String serverCode;
    //密码
    private String password;
    //skynet 地址
    private String address;
    //应用编码（组件编码）
    private String plugin;

    //服务器IP地址
    private String hostIp;
    //服务器打包命令
    private String command;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServerCode() {
        return serverCode;
    }

    public void setServerCode(String serverCode) {
        this.serverCode = serverCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    @Nullable
    @Override
    public SkynetConfigDto getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull SkynetConfigDto config) {
        XmlSerializerUtil.copyBean(config, this);
    }

    @Nullable
    public static SkynetConfigDto getInstance(Project project) {
        SkynetConfigDto config =  ServiceManager.getService(project, SkynetConfigDto.class);
        if (config == null) {
            config = new SkynetConfigDto();
        }
        return config;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
