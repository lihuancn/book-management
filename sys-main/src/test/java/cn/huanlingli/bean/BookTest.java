package cn.huanlingli.bean;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    @Test
    public void Test(){

        Book book = new Book(1,"西游记","吴辰",9.99,100);
        System.out.println(book.GetValues());
    }

}