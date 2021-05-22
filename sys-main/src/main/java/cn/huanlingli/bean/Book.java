package cn.huanlingli.bean;

import cn.huanlingli.bean.intf.Insertable;
import lombok.*;

/**
 * Book实体类
 *
 * @author 易小欢 lihuan@huanlingli.cn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Book implements Insertable {
    private int id;               // 用户ID
    private String bookName;      // 书名
    private String author;        // 作者
    private double price;         // 价格
    private int stock;            // 库存数量

    @Override
    public String GetValues() {
       return String.format("('%s', '%s', %f, %d)",bookName,author,price,stock);
    }
}