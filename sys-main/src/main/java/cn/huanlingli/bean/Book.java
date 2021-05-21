package cn.huanlingli.bean;

import lombok.*;

/**
 * @author 易小欢 @huanlingli.cn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Book {
    private int id;               //用户ID
    private String bookName;      //书名
    private String author;        //作者
    private double price;         //价格
    private int stock;            //库存数量
}