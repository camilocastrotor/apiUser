package com.api.prueba.user.common.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * ResponseDTO <br>
 *
 * @author DREAMCODE <br>
 *
 * @date 05/07/2022
 * @version 1.0
 */
@Getter
@Setter
public class ResponseDTO<T> implements Serializable
{
    private static final long serialVersionUID = 2076140798235745183L;
    @Schema(name = "body", description = "Object with the data service information")
    private T            body;
    @Schema(name = "dataHeader", description = "Object Data header that has the response of the service")
    private DataHeader dataHeader = new DataHeader();

    public ResponseDTO()
    {
        super();
    }

    public ResponseDTO(T body)
    {
        super();
        this.body = body;
    }

    public ResponseDTO(T body, DataHeader dataHeader)
    {
        super();
        this.body       = body;
        this.dataHeader = dataHeader;
    }

}
