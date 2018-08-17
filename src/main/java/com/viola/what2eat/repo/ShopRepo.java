package com.viola.what2eat.repo;

import com.viola.what2eat.model.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepo extends CrudRepository<Shop, Long> {
    Optional<Shop> findByName(String name);
}
