package org.example.service;

import org.example.data.DataBase;

public class Service {

    protected final DataBase dataBase;

    public Service(DataBase dataBase) {
        this.dataBase = dataBase;
    }
}
