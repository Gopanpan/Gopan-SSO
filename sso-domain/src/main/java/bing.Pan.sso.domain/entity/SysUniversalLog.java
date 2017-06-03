package bing.Pan.sso.domain.entity;

import java.time.LocalDateTime;

public class SysUniversalLog {
    private Long id;

    private String moduleName;

    private String featureName;

    private String featurePath;

    private String requestResult;

    private String requestUser;

    private LocalDateTime requestTime;

    private String requestPcName;

    private String requestIp;

    private String extends1;

    private String extends2;

    private String extends3;

    private String extends4;

    private String extends5;

    private String requestParam;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName == null ? null : featureName.trim();
    }

    public String getFeaturePath() {
        return featurePath;
    }

    public void setFeaturePath(String featurePath) {
        this.featurePath = featurePath == null ? null : featurePath.trim();
    }

    public String getRequestResult() {
        return requestResult;
    }

    public void setRequestResult(String requestResult) {
        this.requestResult = requestResult == null ? null : requestResult.trim();
    }

    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser == null ? null : requestUser.trim();
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestPcName() {
        return requestPcName;
    }

    public void setRequestPcName(String requestPcName) {
        this.requestPcName = requestPcName == null ? null : requestPcName.trim();
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp == null ? null : requestIp.trim();
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

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam == null ? null : requestParam.trim();
    }
}