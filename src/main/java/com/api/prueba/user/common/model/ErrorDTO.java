package com.api.prueba.user.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * ErrorDTO <br>
 *
 * @author DREAMCODE <br>
 *
 * @date 05/07/2022
 * @version 1.0
 */
@Data
public class ErrorDTO implements Serializable {
    private String errorCode;
    private String errorDescription;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorLevel;


    public ErrorDTO()
    {}

    /**
     * Constructor.
     * @param errorCode c\u00f3digo del error
     * @param errorDescription descripci\u00f3n del error.
     */
    public ErrorDTO(String errorCode, String errorDescription)
    {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;

    }


    /**
     * Constructor.
     * @param errorCode c\u00f3digo del error
     * @param errorDescription descripci\u00f3n del error.
     * @param errorLevel nivel del error.
     */
    public ErrorDTO(String errorCode, String errorDescription, String errorLevel)
    {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.errorLevel = errorLevel;
    }

    /**
     * Constructor.
     * @param errorDescription descripci\u00f3n del error.
     */
    public ErrorDTO(String errorDescription )
    {
        this.errorDescription = errorDescription;
    }
}
