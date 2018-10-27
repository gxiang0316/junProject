package com.gordon.springboot.demo1;

import lombok.Data;

@Data
public class SubUser extends User {





    public SubUser(String name, String address, Integer age) {
        super(name, address, age);
    }




}
