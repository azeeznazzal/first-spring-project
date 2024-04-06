package com.abdalazeez.firstspringproject.controller;

import com.abdalazeez.firstspringproject.implementation.services.ItemService;
import com.abdalazeez.firstspringproject.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/items")
public class ItemController {

    ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("")
    public ResponseEntity<Optional<List<Item>>> getAll(){
        Optional<List<Item>> items = itemService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Item>> getItemById(@PathVariable int id){
        Optional<Item> item = itemService.getItem(id);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    @PostMapping("addItem")
    public ResponseEntity<Item> addItem(@RequestBody Item item){
        itemService.addItem(item);
        return ResponseEntity.status(HttpStatus.OK).body(item);

    }

    @PutMapping("updateItem")
    public ResponseEntity<Item> updateItem(@RequestParam int id, @RequestBody Item item){
        Item updatedItem = itemService.updateItem(id, item);
        return ResponseEntity.status(HttpStatus.OK).body(updatedItem);
    }

    @DeleteMapping("deleteItem/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable int id){
        itemService.deleteItem(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}

