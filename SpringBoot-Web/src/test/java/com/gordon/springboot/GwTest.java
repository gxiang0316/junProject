package com.gordon.springboot;

import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class GwTest {

    public static void main(String[] args) {
        String str = "AAAABBBB";
        byte[] keys ;
        try {
            keys = str.getBytes("utf-8");
            System.out.println(Base64Utils.encodeToString(Arrays.copyOf(keys,16)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



}
