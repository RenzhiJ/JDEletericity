/**
  * Copyright 2017 bejson.com 
  */
package com.example.bwie.mydemo.bean;
import java.util.List;


public class Tuijian {

    private List<DataList> list;
    private String name;
    public void setList(List<DataList> list) {
         this.list = list;
     }
     public List<DataList> getList() {
         return list;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

}