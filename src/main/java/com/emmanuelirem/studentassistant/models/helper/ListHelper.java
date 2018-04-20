package com.emmanuelirem.studentassistant.models.helper;


import java.util.ArrayList;
import java.util.List;

public class ListHelper<T> {

    private List<T> item = new ArrayList<>();


    public ListHelper() {}

    public List<T> getItem() {
        return item;
    }
    public List<Long> getLongValue(){
        List<Long> longVar = new ArrayList<>();

        this.getItem().forEach(item -> {
            try{
                longVar.add((long) Integer.parseInt((String) item));
            } catch (Exception e){
                System.out.println(e);
            }
        });

        return longVar;
    }

    public void setItem(List<T> list) {
        list.forEach(listItem -> {
            item.add(listItem);
        });
    }

}
