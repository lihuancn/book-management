package cn.huanlingli.bean;

import cn.huanlingli.bean.intf.Insertable;
import lombok.*;

/**
 * Admin实体类
 *
 * @author 易小欢 lihuan@huanlingli.cn
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Admin implements Insertable {
    private int id;               // id
    private String username;      // 用户名
    private String password;      // 用户密码
    private String phone;         // 电话号码
    private String email;         // 电子邮件

    @Override
    public String GetValues() {
        return String.format("('%s', '%s', '%s', '%s')", username, password, phone, email);
    }
}