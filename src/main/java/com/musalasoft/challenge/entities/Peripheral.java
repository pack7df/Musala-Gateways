package com.musalasoft.challenge.entities;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class Peripheral {
    @NotBlank
    private int uid;
    @NotBlank
    private String vendor;
    private Date created;
    @NotBlank
    private boolean status;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
