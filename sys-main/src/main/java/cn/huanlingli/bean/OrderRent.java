package cn.huanlingli.bean;

import cn.huanlingli.bean.intf.Insertable;
import cn.vorbote.commons.DateUtil;
import lombok.*;

import java.util.Date;
/**
 * OrderRent实体类
 *
 * @author 易小欢 lihuan@huanlingli.cn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderRent implements Insertable {
    private int id;                     //借阅记录Id
    private int userId;                 //用户Id
    private Date rentDate;              //租借日期
    private Date plannedReturnDate;     //应当归还时间
    private Date returnDate;            //实际归还时间
    private int isReturned;             //判断是否归还：1为已经归还，0为没有归还
    private int bookCount;              //书本的数量


    @Override
    public String GetValues() {
        String  strRentDate = DateUtil.DateToString(rentDate,"");
        String  strPlannedReturnDate = DateUtil.DateToString(plannedReturnDate,"");
        String  strReturnDate = DateUtil.DateToString(returnDate,"");
        return String.format("(%d, '%s', '%s', '%s', %d, %d)",userId,strRentDate,strPlannedReturnDate,strReturnDate,isReturned,bookCount);
    }
}
