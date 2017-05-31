package com.example.demo.data.repository;

import com.example.demo.data.domain.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Sreenivasulu on 5/20/2017.
 */
public interface AddressRepository extends CrudRepository<Address, Long> {

    List<Address> findAll();
}
