package com.gordon.springboot;

import com.gordon.springboot.utils.PropertiesUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.UUID;

public class GwTest {

    public static void main(String[] args) {
//        String str = "AAAABBBB";
//        byte[] keys ;
//        try {
//            keys = str.getBytes("utf-8");
//            System.out.println(Base64Utils.encodeToString(Arrays.copyOf(keys,16)));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

//        c20726e7c1874ed1928ee7b51cd1a679
//        1000001859610415
//        System.out.println(UUID.randomUUID().toString().replace("-",""));
//        System.out.println(getOrderIdByUUId());
//        System.out.println(new SecureRandomNumberGenerator().nextBytes().toHex());

//        int anInt = PropertiesUtils.getInt("test.int");
//        System.out.println(" main test result : " + anInt);


        String str = "sysParam.properties";
        String[] split = str.split("\\.");
        for(int g = 0 ; g < split.length ;g++){
            System.out.println(" ==== "+split[g]);
        }

    }

    public static String getOrderIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        System.out.println(" hashCode : " + hashCodeV);
        return machineId + String.format("%032d", hashCodeV);
    }

}
