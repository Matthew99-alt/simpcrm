package com.crm.reposotiry;

import com.crm.entity.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmenitiesRepository extends JpaRepository<Amenities, Long> {

    List<Amenities> findAll();

}
