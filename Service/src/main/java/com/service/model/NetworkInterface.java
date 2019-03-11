package com.service.model;

public class NetworkInterface {

    private String name;
    private String hwAddress;
    private String[] inetAddress;
    private String MTU;

    public String getName() {
        return name;
    }

    public String getHwAddress() {
        return hwAddress;
    }

    public String[] getInetAddress() {
        return inetAddress;
    }

    public String getMTU() {
        return MTU;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHwAddress(String hwAddress) {
        this.hwAddress = hwAddress;
    }

    public void setInetAddress(String[] inetAddress) {
        this.inetAddress = inetAddress;
    }

    public void setMTU(String MTU) {
        this.MTU = MTU;
    }
}
