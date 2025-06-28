package junesoft.appl.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CodeDTO {
    private Long codeSeq;
    private String codeType;
    private String code;
    private String codeName;
    private String codeDesc;
    private Integer sortOrder;
    private String useFlag;
    private Date writeTime;
    private Date modifyTime;
    private String delFlag;
}