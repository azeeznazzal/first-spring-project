package com.abdalazeez.firstspringproject.implementation.services;

import com.abdalazeez.firstspringproject.implementation.repositories.ItemRepository;
import com.abdalazeez.firstspringproject.model.Item;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {


    ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostConstruct
    private void init(){
        itemRepository.deleteAll();

        itemRepository.save(Item.builder().id(1).name("bottle").price(0.99).build());
        itemRepository.save(Item.builder().id(2).name("toy").price(5).build());
        itemRepository.save(Item.builder().id(3).name("glass").price(0.5).build());
        itemRepository.save(Item.builder().id(4).name("carpet").price(20.75).build());
        itemRepository.save(Item.builder().id(5).name("basket").price(4.99).build());

        //itemRepository.save(Item.builder().build());

        //itemRepository.save(new Item(1,"bottle",0.99));

    }


    public Optional<List<Item>> getAll() {
        return Optional.of(itemRepository.findAll());
    }


    public Optional<Item> getItem(int id){
        return itemRepository.findById(id);
    }


    public void addItem(Item item) {
        itemRepository.save(item);
    }



    public Item updateItem(int id, Item item) {
        Optional<Item> existingItemOpt = itemRepository.findById(id);
        if(existingItemOpt.isPresent()){
//            itemRepository.deleteById(id);
//            return itemRepository.save(Item.builder().id(id).name(item.getName()).price(item.getPrice()).build());

            Item existingItem = existingItemOpt.get();
            existingItem.setName(item.getName());
            existingItem.setPrice(item.getPrice());
            return itemRepository.save(existingItem);

        }
        else {
            throw new RuntimeException("the Item with ID '"+id+"' doesn't exist!");
        }
    }


    public void deleteItem(int id) {
        Optional<Item> existingItemOpt = itemRepository.findById(id);
        if(existingItemOpt.isPresent()){
            itemRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("the Item with ID '"+id+"' doesn't exist!");
        }
    }
}
