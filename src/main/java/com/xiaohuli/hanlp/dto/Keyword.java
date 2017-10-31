package com.xiaohuli.hanlp.dto;

import java.io.Serializable;

public class Keyword implements Serializable{

    private String name;

    private int frequency;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
