package com.abdalazeez.firstspringproject.implementation.repositories;

import com.abdalazeez.firstspringproject.model.Item;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends ListCrudRepository<Item, Integer> {


}
