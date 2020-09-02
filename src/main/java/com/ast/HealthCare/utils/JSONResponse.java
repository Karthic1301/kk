package com.ast.HealthCare.utils;

/**
 *
 * @author Prabhu
 */
public class JSONResponse {

    private Boolean status;
    private StringBuilder errorMessage;
    private StringBuilder successMessage;
    private Object metaData;
    private Object data;
    private Integer count;

    public JSONResponse() {
        errorMessage = new StringBuilder();
        successMessage = new StringBuilder();
    }

    public Object getMetaData() {
        return metaData;
    }

    public void setMetaData(Object pMetaData) {
        this.metaData = pMetaData;
    }

    public Integer getCount() {
        return count;
    }

    public String getErrorMessage() {
        return errorMessage.toString();
    }

    public void setErrorMessage(String pErrorMessage) {
        this.errorMessage.append(pErrorMessage);
    }

    public boolean havingErrorMessage() {
        return errorMessage.length() > 0;
    }

    public void clearErrorMessage() {
        this.errorMessage.setLength(0);
    }

    public String getSuccessMessage() {
        return successMessage.toString();
    }

    public void setSuccessMessage(String pErrorMessage) {
        this.successMessage.append(pErrorMessage);
    }

    public boolean havingSuccessMessage() {
        return successMessage.length() > 0;
    }

    public void clearSuccessMessage() {
        this.successMessage.setLength(0);
    }

    public void setCount(Integer pCount) {
        this.count = pCount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
