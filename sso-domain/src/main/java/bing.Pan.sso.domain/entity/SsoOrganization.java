package bing.Pan.sso.domain.entity;

public class SsoOrganization {
    private Long id;

    private String name;

    private Long parentId;

    private String parentIds;

    private Long isSystem;

    private Boolean available;

    private String extends1;

    private String extends2;

    private String extends3;

    private String extends4;

    private String extends5;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    public Long getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Long isSystem) {
        this.isSystem = isSystem;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getExtends1() {
        return extends1;
    }

    public void setExtends1(String extends1) {
        this.extends1 = extends1 == null ? null : extends1.trim();
    }

    public String getExtends2() {
        return extends2;
    }

    public void setExtends2(String extends2) {
        this.extends2 = extends2 == null ? null : extends2.trim();
    }

    public String getExtends3() {
        return extends3;
    }

    public void setExtends3(String extends3) {
        this.extends3 = extends3 == null ? null : extends3.trim();
    }

    public String getExtends4() {
        return extends4;
    }

    public void setExtends4(String extends4) {
        this.extends4 = extends4 == null ? null : extends4.trim();
    }

    public String getExtends5() {
        return extends5;
    }

    public void setExtends5(String extends5) {
        this.extends5 = extends5 == null ? null : extends5.trim();
    }
}