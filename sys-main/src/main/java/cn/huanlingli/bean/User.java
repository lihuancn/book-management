package cn.huanlingli.bean;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    private int id;               //id
    private String username;      //用户名
    private String password;      //用户密码
    private String phone;         //电话号码
    private String email;         //电子邮件
    private int status;           //用户状态
    private int level;            //用户等级
    private int availableCount;   //可借阅次数

}
