package com.cn.wmd.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Observable;

/**
 * @Descripation:
 * @Author: wmd
 * @Date: 2020-04-21 17:21
 * @since 1.0
 */
public class Cat extends Observable { // 被观察者-猫
    private String cool; // 颜色

    public String getCool() {
        return cool;
    }

    public void setCool(String cool) {
        this.cool = cool;
    }

    public static void main(String[] args) {
        RandomAccessFile raf = null ;
        try {
            raf = new RandomAccessFile("d://新文本文档.txt", "rw");
            FileChannel channel = raf.getChannel();
            ByteBuffer buff = ByteBuffer.allocate(1024); // 分配空间
            int read = channel.read(buff); // 把channel里的数据写入到缓存区-buff
            System.out.println(read);
            while (read != -1) {
                buff.flip();
                while (buff.hasRemaining()) {
                    System.out.println((char) buff.get());
                }
                buff.compact();
                read = channel.read(buff);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}