package com.github.adminfaces.starter.bean;

import com.github.adminfaces.persistence.service.CrudService;
import com.github.adminfaces.persistence.service.Service;
import com.github.adminfaces.starter.model.Car;
import com.github.adminfaces.template.config.AdminConfig;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.stream.IntStream;

@Singleton
@Startup
public class InitAppMB implements Serializable {

    @Inject
    @Service
    private CrudService<Car, Integer> crudService;

    @Inject
    AdminConfig adminConfig;

    @PostConstruct
    public void init() {
//        IntStream.rangeClosed(1, 50)
//                .forEach(i -> create(i));
        adminConfig.setDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    private void create(int i) {

        crudService.insert(new Car().model("model " + i).name("name" + i).price(Double.valueOf(i)));
    }

}
