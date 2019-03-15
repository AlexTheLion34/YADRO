package com.service.model;

import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetworkInterface that = (NetworkInterface) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(hwAddress, that.hwAddress) &&
                Arrays.equals(inetAddress, that.inetAddress) &&
                Objects.equals(MTU, that.MTU);
    }
}
