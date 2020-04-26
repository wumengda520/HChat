package com.cn.wmd.pojo;

import java.util.Observable;
import java.util.Observer;

/**
 * @Descripation:
 * @Author: wmd
 * @Date: 2020-04-21 17:30
 * @since 1.0
 */

public class Mouse { // 观察者-老鼠

    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}