一 服务搭建
(1) 创建数据库,建表语句在mybatise文件夹下.执行tables_mysql.sql和tables_db.sql.其中tables_mysql.sql为quartz-2.2.3的原始建表语句。
(2) 修改datasource.properties和quartz.properties两个地方的数据库连接
(3) 以上是所有修改点,然后就可以启动服务了.

本应用可以随着quartz的升级而升级,改动点非常少,只要调整POM.xml以及数据库和quartz相关的表结构即可.