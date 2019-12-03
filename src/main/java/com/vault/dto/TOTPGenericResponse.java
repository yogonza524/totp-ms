package com.vault.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class TOTPGenericResponse implements Serializable {
    private static final long serialVersionUID = 8620871756993111330L;

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("lease_id")
    private String leaseId;

    @JsonProperty("renewable")
    private boolean renewable;

    @JsonProperty("lease_duration")
    private int leaseDuration;

    @JsonProperty("data")
    private Map<String, Object> data;

    @JsonProperty("wrap_info")
    private String wrapInfo;

    @JsonProperty("warnings")
    private String warnings;

    @JsonProperty("auth")
    private String auth;

    public TOTPGenericResponse() {
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(String leaseId) {
        this.leaseId = leaseId;
    }

    public boolean isRenewable() {
        return renewable;
    }

    public void setRenewable(boolean renewable) {
        this.renewable = renewable;
    }

    public int getLeaseDuration() {
        return leaseDuration;
    }

    public void setLeaseDuration(int leaseDuration) {
        this.leaseDuration = leaseDuration;
    }

    public String getWrapInfo() {
        return wrapInfo;
    }

    public void setWrapInfo(String wrapInfo) {
        this.wrapInfo = wrapInfo;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TOTPGenericResponse that = (TOTPGenericResponse) o;
        return renewable == that.renewable &&
                leaseDuration == that.leaseDuration &&
                Objects.equals(requestId, that.requestId) &&
                Objects.equals(leaseId, that.leaseId) &&
                Objects.equals(data, that.data) &&
                Objects.equals(wrapInfo, that.wrapInfo) &&
                Objects.equals(warnings, that.warnings) &&
                Objects.equals(auth, that.auth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, leaseId, renewable, leaseDuration, data, wrapInfo, warnings, auth);
    }

    @Override
    public String toString() {
        return "TOTPGenericResponse{" +
                "requestId='" + requestId + '\'' +
                ", leaseId='" + leaseId + '\'' +
                ", renewable=" + renewable +
                ", leaseDuration=" + leaseDuration +
                ", data=" + data +
                ", wrapInfo='" + wrapInfo + '\'' +
                ", warnings='" + warnings + '\'' +
                ", auth='" + auth + '\'' +
                '}';
    }
}
