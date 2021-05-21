package cn.huanlingli.bean;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Admin implements Insertable{
    private int id;               //id
    private String username;      //用户名
    private String password;      //用户密码
    private String phone;         //电话号码
    private String email;         //电子邮件


    @Override
    public String ToSQL() {
        return null;
    }
}