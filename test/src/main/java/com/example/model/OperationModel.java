package com.example.model;

import java.util.LinkedList;

public class OperationModel {

    private String category;
    private LinkedList<String> a1;
    private LinkedList<String> a2;
    private int b;

    public OperationModel(){
    }

    public OperationModel(String category){
        this.category=category;
    }
    public OperationModel(LinkedList<String> a1, LinkedList<String> a2){
        this.a1 = a1;
        this.a2 = a2;
    }
    public OperationModel(int b){
        this.b = b;
    }

    public LinkedList<String> getA1() {
        return a1;
    }

    public LinkedList<String> getA2() {
        return a2;
    }

    public int getB() {
        return b;
    }

    public String getCategory() {
        return category;
    }

    public void setA1(LinkedList<String> a1) {
        this.a1 = a1;
    }

    public void setA2(LinkedList<String> a2) {
        this.a2 = a2;
    }

    public void setB(String b) {
        this.b=(!b.isEmpty())?  Integer.parseInt(b):0;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String toString(LinkedList<String> arr){
        String res ="";
        for (String s:arr){

            res = (res.isEmpty())?s: res + ", " + s;
        }
        return res;
    }

}
