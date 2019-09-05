### 工程目录
``` lua
mybatis 逻辑删除  0正常 1删除
pigx
├── pigx-auth -- 授权服务提供[3000]
├── pigx-common -- 系统公共模块 
├    ├── pigx-common-bom -- 公共依赖版本
├    ├── pigx-common-core -- 公共工具类核心包
├    ├── pigx-common-data -- 数据相关
├    ├── pigx-common-job -- 定时任务
├    ├── pigx-common-log -- 日志服务
├    ├── pigx-common-security -- 安全工具类
├    └── pigx-common-transaction -- 分布式事务工具包
├── pigx-config -- 配置中心[8888]
├── pigx-eureka -- 服务注册与发现[8761]
├── pigx-gateway -- Spring Cloud Gateway网关[9999]
├── pigx-upms -- 通用用户权限管理模块
├    ├── pigx-upms-api -- 通用用户权限管理系统公共api模块
├    └── pigx-upms-biz -- 通用用户权限管理系统业务处理模块[4000]
└── pigx-user  -- App用户管理用模块  
├    ├── pigx-user-api -- App用户管理系统公共api模块
├    └── pigx-user-biz -- App用户管理系统业务处理模块
	 
```

### 部署说明

> 修改host文件

```

127.0.0.1   farm-mysql       
127.0.0.1  farm-eureka 
127.0.0.1  farm-config
127.0.0.1  farm-gateway 

```

> 项目启动顺序

```
1. farm-eureka
2. farm-config
3. farm-gateway
4. farm-auth
5. farm-upms
6. ...

```

> 访问接口

```
127.0.0.1:9999 // farm-gateway 服务对应地址
```