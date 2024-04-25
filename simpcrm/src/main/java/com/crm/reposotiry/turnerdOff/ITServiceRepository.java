package com.crm.reposotiry.turnerdOff;

import com.crm.entity.ITService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface ITServiceRepository{ // extends CrudRepository<ITService, Long>{

//    @Override
    List<ITService> findAll();

    String findTitleById(Long id);

    List<ITService> findByPrice(Long price);

    List<ITService> findByTitle(String title);

//    @Query("select i from ITService i where i.price > :price")
    List<ITService> findByPriceGreaterThan(@Param("price") Long price);
}
