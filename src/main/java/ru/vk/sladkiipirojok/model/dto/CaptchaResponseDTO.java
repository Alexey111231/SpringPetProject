package ru.vk.sladkiipirojok.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptchaResponseDTO {
    private boolean success;
    @JsonAlias("error-codes")
    private Set<String> errorCode;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Set<String> getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Set<String> errorCode) {
        this.errorCode = errorCode;
    }
}
