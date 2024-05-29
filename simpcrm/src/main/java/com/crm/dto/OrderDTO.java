package com.crm.dto;

import com.crm.entity.Status;
import com.crm.entity.User;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDTO {

    private Long id;

    private String orderName;

    private Long priority;

    private Status status;

    private String description;

    private List<AmenitiesDTO> amenities;

    private List<MerchandiseDTO> merchandises;

    private String comments;

    private User client;

    private User users;

    private int totalNumberOfMerchandise;

    private int totalNumberOfAmenities;

    private double totalCostOfOrder;
}
