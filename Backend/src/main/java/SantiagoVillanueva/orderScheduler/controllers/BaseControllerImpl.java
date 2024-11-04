package SantiagoVillanueva.orderScheduler.controllers;

import SantiagoVillanueva.orderScheduler.models.BaseModel;
import SantiagoVillanueva.orderScheduler.services.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseControllerImpl<E extends BaseModel, S extends BaseServiceImpl<E, Long>> implements BaseController<E, Long> {

    @Autowired
    protected S service;
}
