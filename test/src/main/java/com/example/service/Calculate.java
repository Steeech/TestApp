package com.example.service;

import java.util.Collections;
import java.util.LinkedList;

import com.example.model.OperationModel;
import org.springframework.stereotype.Service;

@Service
public class Calculate {

    public  String task1(OperationModel model){
        LinkedList<String> a1 = model.getA1();
        LinkedList<String> a2= model.getA2();
        LinkedList<String> res = new LinkedList<>();
        for(String wrd:a1){
            if (isContain(a2, wrd)){
                res.add(wrd);
            }
        }
        Collections.sort(res);
        return res.toString();
    }
    private static boolean isContain(LinkedList<String> arr, String wrd){
        for (String wrd1: arr){
            if(wrd1.contains(wrd)) {
                return true;
            }
        }
        return false;
    }

    public  String task2(OperationModel model){
        int b = model.getB();
        String res = b%10+"";
        b = b/10;
        int digit = 10;
        while (b>0){
            res = (b % 10)*digit + "+" + res;
            digit = digit*10;
            b = b/10;
        }
        return res;
    }
}