package com.custom.sean.common.domain.dto;

/**
 * @author seang
 */
public class ProjectBrandDTO {
    private String projectName,projectCode,brandName,brandCode;

    @java.beans.ConstructorProperties({"projectName", "projectCode", "brandName", "brandCode"})
    public ProjectBrandDTO(String projectName, String projectCode, String brandName, String brandCode) {
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.brandName = brandName;
        this.brandCode = brandCode;
    }

    public ProjectBrandDTO() {
    }

    public String getProjectName() {
        return this.projectName;
    }

    public String getProjectCode() {
        return this.projectCode;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public String getBrandCode() {
        return this.brandCode;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }
}
