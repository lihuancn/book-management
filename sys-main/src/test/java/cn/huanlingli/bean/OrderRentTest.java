package cn.huanlingli.bean;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class OrderRentTest {

    @Test
    public void Test(){
        OrderRent orderRent = new OrderRent(1,2,new Date(120,02,02),new Date(120,03,02),new Date(120,03,02),1,20013213);
        String s = orderRent.GetValues();
        System.out.println(s);
    }

}