# 项目介绍


# 项目文件架构
```text
book-management
├── LICENSE                                         许可证
└── sys-main                                        
    ├── database                                    数据库相关文件
    ├── pom.xml                                     Maven项目Project Object Model
    ├── src                                         存储源代码
    │   ├── main
    │   │   ├── java
    │   │   │   └── cn
    │   │   │       └── huanlingli
    │   │   │           ├── bean                    存储实体类
    │   │   │           │   └── intf                实体类需要实现的接口
    │   │   │           ├── config                  枚举类
    │   │   │           ├── controllers             Servlet
    │   │   │           ├── dao                     数据库连接
    │   │   │           ├── excepts                 自定义异常
    │   │   │           ├── filter                  Web过滤器
    │   │   │           └── services                Web服务接口
    │   │   │               └── impl                Web服务实现        
    │   │   └── resources                           项目配置文件
    │   └── test                                    测试类
    ├── sys-main.iml                                IDEA自动生成的文件
    ├── target                                      IDEA编译的文件
    └── web                                         Web文件夹
        ├── WEB-INF
        │   └── web.xml                             web项目配置文件
        ├── index.jsp
        └── templates
```