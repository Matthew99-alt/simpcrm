package com.crm.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderProgramDTO {
    private Long IdProgram;

    private Long IdOrder;
}
