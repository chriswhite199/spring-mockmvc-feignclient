package com.cswhite.feignmockmvctest;

import com.cswhite.feignmockmvctest.controller.TestController;
import com.cswhite.feignmockmvctest.model.Store;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BaseTest {
    Store store1 = new Store("store1", "555-1234");

    @Autowired
    TestController testController;

    @Test
    public void feignClientWorks() {
        List<Store> stores = testController.getStores();
        assertThat(stores).isEmpty();

        testController.addStore(store1);
        stores = testController.getStores();
        assertThat(stores).containsExactly(store1);

        testController.renameStore("store1", "store 1");

        stores = testController.getStores();
        assertThat(stores).hasOnlyOneElementSatisfying(store -> assertThat(store.getName()).isEqualTo("store 1"));
    }
}
