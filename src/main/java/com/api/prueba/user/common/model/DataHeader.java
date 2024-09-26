package com.api.prueba.user.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DataHeader <br>
 *
 * @author DREAMCODE <br>
 *
 * @date 05/07/2022
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class DataHeader implements Serializable
{
    private Integer        code         = 200;
    private String         status       = "OK";
    private List<ErrorDTO> errorList    = new ArrayList<>();
    private boolean        hasErrors    = false;
    private int            currentPage  = 0;
    private int            totalPage    = 0;
    private long           totalRecords = 0;

    public DataHeader()
    {}

    public DataHeader(Integer code, String status, List<ErrorDTO> errorList)
    {
        this.code      = code;
        this.status    = status;
        addErrors(errorList);
    }

    public void addError(ErrorDTO errorDTO)
    {
        errorList.add(errorDTO);
        setHasErrors(true);
    }

    public void addErrors(List<ErrorDTO> errorsDTO)
    {
        if(errorsDTO != null && !(errorsDTO.isEmpty()))
        {
            errorList.addAll(errorsDTO);
            setHasErrors(true);
        }
    }

    /**
     * Establece la informaci\u00f3n de la paginaci\u00f3n del conjunto de registros actual.
     * @param page instancia Page
     */
    public void setCurrentPageInfo(Page page)
    {
        setTotalPage(page.getTotalPages());
        setCurrentPage(page.getNumber());
        setTotalRecords(page.getTotalElements());
    }
}
