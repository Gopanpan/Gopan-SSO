package com.horse.sso.domain.entity;

import java.time.LocalDateTime;

public class SsoRole {
    private Long id;

    private String name;

    private String code;

    private Long systemId;

    private String systemName;

    private String detailExplain;

    private Boolean available;

    private Long createUser;

    private LocalDateTime createTime;

    private Long updateUser;

    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName == null ? null : systemName.trim();
    }

    public String getDetailExplain() {
        return detailExplain;
    }

    public void setDetailExplain(String detailExplain) {
        this.detailExplain = detailExplain == null ? null : detailExplain.trim();
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}