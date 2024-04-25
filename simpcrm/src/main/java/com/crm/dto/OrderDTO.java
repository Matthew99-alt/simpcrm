package com.crm.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDTO {

    private Long id;

    private String orderName;

    private Long priority;

    private String statusTitle;

    private String description;

    private List<String> itServicesTitles;

    private List<String> programsTitles;

    private String comments;

    private String clientTitle;

    private String userTitle;

}
