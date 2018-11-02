package com.github.chriswhite199.feignmockmvctest.controller;

import com.github.chriswhite199.feignmockmvctest.model.Store;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stores")
public class TestControllerImpl implements TestController {
    List<Store> stores = new ArrayList<>();

    @Override
    public List<Store> getStores() {
        return stores;
    }

    @Override
    public void addStore(@RequestBody Store store) {
        this.stores.add(store);
    }

    @Override
    public void renameStore(@PathVariable("oldName") String oldName,
                            @RequestParam("newName") String newName) {
        stores.stream()
                .filter(store -> store.getName().equals(oldName))
                .forEach(store -> store.setName(newName));
    }
}
