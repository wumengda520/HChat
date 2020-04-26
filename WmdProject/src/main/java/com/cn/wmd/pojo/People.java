package com.cn.wmd.pojo;

import java.util.Observable;
import java.util.Observer;

/**
 * @Descripation:
 * @Author: wmd
 * @Date: 2020-04-21 17:56
 * @since 1.0
 */
public class People  implements Observer {// 观察者 人
    public void update(Observable o, Object arg) {
        System.out.println("人看到猫动了");
    }
}