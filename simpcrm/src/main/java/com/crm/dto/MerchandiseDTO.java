package com.crm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchandiseDTO {

    private Long id;

    private String title;

    private String description;

    private Long price;

    private double ratio;

    private Long numberInWarehouse;
}
