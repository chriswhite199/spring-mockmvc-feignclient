package com.github.chriswhite199.feignmockmvctest.controller;

import com.github.chriswhite199.feignmockmvctest.model.Store;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "test", path = "/stores")
public interface TestController {
    @GetMapping
    List<Store> getStores();

    @PutMapping
    void addStore(@RequestBody Store store);

    @PostMapping("/{oldName}")
    void renameStore(@PathVariable("oldName") String oldName, @RequestParam("newName") String newName);
}
