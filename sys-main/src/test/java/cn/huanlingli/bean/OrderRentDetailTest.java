package cn.huanlingli.bean;

import org.junit.Test;

import static org.junit.Assert.*;

public class OrderRentDetailTest {
    @Test
    public void Test(){
        OrderRentDetail orderRentDetail = new OrderRentDetail(1,2,3);
        System.out.println(orderRentDetail.GetValues());
    }

}