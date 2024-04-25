package com.crm.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderDTO {

    private Long id;

    private String order_name;

    private Long priority;

    private String statusTitle;

    private String description;

    private List<String> ITServicesTitles;

    private List<String> ProgramsTitles;

    private String comments;

    private String clientTitle;

    private String userTitle;

}
