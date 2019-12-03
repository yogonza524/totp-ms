package com.vault.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vault.dto.validators.TOTP;
import java.io.Serializable;
import java.util.Objects;

@TOTP
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TOTPRequestPayload implements Serializable {
    private static final long serialVersionUID = -5346045406633425331L;

    @JsonProperty("generate")
    private boolean generate;

    @JsonProperty("exported")
    private boolean exported;

    @JsonProperty("key_size")
    private Integer keySize;

    @JsonProperty("url")
    private String url;

    @JsonProperty("key")
    private String key;

    @JsonProperty("issuer")
    private String issuer;

    @JsonProperty("account_name")
    private String accountName;

    @JsonProperty("period")
    private Integer period;

    @JsonProperty("algorithm")
    private String algorithm;

    @JsonProperty(value = "digits")
    private Integer digits;

    @JsonProperty("skew")
    private Integer skew;

    @JsonProperty("qr_size")
    private Integer qrSize;

    public TOTPRequestPayload() { }

    public boolean isGenerate() {
        return generate;
    }

    public void setGenerate(boolean generate) {
        this.generate = generate;
    }

    public boolean isExported() {
        return exported;
    }

    public void setExported(boolean exported) {
        this.exported = exported;
    }

    public Integer getKeySize() {
        return keySize;
    }

    public void setKeySize(Integer keySize) {
        this.keySize = keySize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public Integer getDigits() {
        return digits;
    }

    public void setDigits(Integer digits) {
        this.digits = digits;
    }

    public Integer getSkew() {
        return skew;
    }

    public void setSkew(Integer skew) {
        this.skew = skew;
    }

    public Integer getQrSize() {
        return qrSize;
    }

    public void setQrSize(Integer qrSize) {
        this.qrSize = qrSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TOTPRequestPayload that = (TOTPRequestPayload) o;
        return generate == that.generate &&
                exported == that.exported &&
                keySize == that.keySize &&
                period == that.period &&
                digits == that.digits &&
                skew == that.skew &&
                qrSize == that.qrSize &&
                Objects.equals(url, that.url) &&
                Objects.equals(key, that.key) &&
                Objects.equals(issuer, that.issuer) &&
                Objects.equals(accountName, that.accountName) &&
                Objects.equals(algorithm, that.algorithm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(generate, exported, keySize, url, key, issuer, accountName, period, algorithm, digits, skew, qrSize);
    }

    @Override
    public String toString() {
        return "TOTPRequestPayload{" +
                "generate=" + generate +
                ", exported=" + exported +
                ", keySize=" + keySize +
                ", url='" + url + '\'' +
                ", key='" + key + '\'' +
                ", issuer='" + issuer + '\'' +
                ", accountName='" + accountName + '\'' +
                ", period=" + period +
                ", algorithm='" + algorithm + '\'' +
                ", digits=" + digits +
                ", skew=" + skew +
                ", qrSize=" + qrSize +
                '}';
    }
}
