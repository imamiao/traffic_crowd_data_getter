从高德地图接口获取从2019-10-08到现在，各城市交通拥堵数据
- /resources/city_crowd_data.sql 是一个建库文件
- 数据库建好后，配置好`GetData`类中的数据库用户名，密码字段，直接执行即可

注意：执行完成之后有一定几率会卡在
```
10:21:45.771 [HikariPool-1 housekeeper] DEBUG com.zaxxer.hikari.pool.HikariPool - HikariPool-1 - Pool stats (total=10, active=0, idle=10, waiting=0)
10:22:15.773 [HikariPool-1 housekeeper] DEBUG com.zaxxer.hikari.pool.HikariPool - HikariPool-1 - Pool stats (total=10, active=0, idle=10, waiting=0)
10:22:45.773 [HikariPool-1 housekeeper] DEBUG com.zaxxer.hikari.pool.HikariPool - HikariPool-1 - Pool stats (total=10, active=0, idle=10, waiting=0)
10:23:15.774 [HikariPool-1 housekeeper] DEBUG com.zaxxer.hikari.pool.HikariPool - HikariPool-1 - Pool stats (total=10, active=0, idle=10, waiting=0)
```
直接结束即可，数据已经下载完成了。