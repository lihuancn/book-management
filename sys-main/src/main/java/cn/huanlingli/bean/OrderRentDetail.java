package cn.huanlingli.bean;

import cn.huanlingli.bean.intf.Insertable;
import lombok.*;

/**
 * OrderRentDetail实体类
 *
 * @author 易小欢 lihuan@huanlingli.cn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderRentDetail implements Insertable {
    private int id;          //借阅记录详情Id
    private int orderId;     //借阅记录Id
    private int bookId;      //书本Id

    @Override
    public String GetValues() {
        return String.format("(%d, %d)", orderId, bookId);
    }
}
