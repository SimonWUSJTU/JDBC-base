# JDBC-base
Java DataBase Connectivity
## JDBC定义概念

JAVA链接数据库的基础语言

## 使用的第一步，导入jar包，导入驱动jar包 mysql-connector-java-5.1.37-bin.jar

			1.复制mysql-connector-java-5.1.37-bin.jar到项目的libs目录下
			2.右键-->Add As Library

## 基本的使用步骤

注册驱动

获取数据库连接对象

定义SQL

获取SQL执行对象

对结果进行处理

**注意其中用try/catch处理异常，开启的资源（结果集合，数据库链接对象，SQL执行的对象）要及时的关闭**

## 包内文件说明

1，mysql-connector-java-5.1.37-bin.jar文件是导入的jar包，作为基础的支撑库

2，jdbc.properties，在抽取工具类的时候（简化重复的代码，抽取公共部分作为类）使用的配置文件，讲明链接的数据库的基本信息和驱动器的位置。

3，JDBCUtil.java，抽取的公共部分做的公共类，这个类用静止代码块static注册驱动，用配置文件加载的形式获得驱动的位置和获得数据库链接对象；同时定义了两个重载方法资源的开启和关闭。

4，Emp.java，一个实体的类，记录员工的信息

5，其中Demo演示了最基本的JDBC语言的使用和操作，包括DDL,DML几种类型。

6，其中Demo02记录了用JDBC的DQL查询得到的结果，然后封装成Emp类，并且作为展示。

7，其中Demo03是在Demo02的基础上，把重复的部分，比如注册驱动，获取数据库链接对象和获取SQL执行对象的部分，封装成一个工具类，并用配置文件加载的形式实现的。然后对其优化。

8，其中Demo04是一个登录系统，从键盘读取用户名和密码，然后和数据库中存储的数据进行匹配看看是否能成功，这里引入了预编译的SQL执行对象，防止因为拼接导致的欺骗。

# 文件被放置在D:\代码库\JAVA中，由于上传不了就不上传了，烦啦


# 数据库池

## 概念和优化思想

JBDC注册、获取链接对象和执行的语句比较重复，使用抽出的工具类后虽然有所改善，但仍然十分复杂；并且开一个关上一个十分不方便，因此一次性开好放在数据库池里面，用一个取一个还一个，方便使用。

## CP30

1,使用前需要导入jai包，此处的jar包有c3p0-0.9.5.2.jar，mchange-commons-java-0.2.12.jar，这两个，同时这个工具是基于JDBC的，所以要加上JDBC的基础的包mysql-connector-java-5.1.37-bin.jar。  导入后加入library

2，配置文件的配置，里面有数据库的基本信息，和开启的数据库池的大小以及响应的时间，这个是名称： c3p0.properties 或者 c3p0-config.xml。

3，前述步骤相当于搞了个工具类，在使用时，获取数据库池对象，获取数据库链接对象即可。

## Druid:由阿里巴巴提供的

1，导包，druid-1.0.9.jar和所以要加上JDBC的基础的包mysql-connector-java-5.1.37-bin.jar。

2，配置文件druid.properties，加载配置文件

3，前述步骤相当于搞了个工具类，在使用时，获取数据库池对象，获取数据库链接对象即可。

4，优化步骤，抽取一个工具类实现前面的步骤， JDBCUtils。

## spring JDBC

一种开发框架提供的，前述的数据库池的方法还是很麻烦，特别是在DQL查询后的结果处理方面，而spring JDBC就提供了简化的方法。他是建立在上述的Druid之上的，因此在配置的时候，需要依赖前面的Druid的包和抽象出来的工具类 JDBCUtils。

1，导包，spring-beans-5.0.0.RELEASE.jar，spring-core-5.0.0.RELEASE.jar，spring-jdbc-5.0.0.RELEASE.jar，spring-tx-5.0.0.RELEASE.jar

2，JdbcTemplate template = new JdbcTemplate(ds);

	3. 调用JdbcTemplate的方法来完成CRUD的操作
		* update():执行DML语句。增、删、改语句
		* queryForMap():查询结果将结果集封装为map集合，将列名作为key，将值作为value 将这条记录封装为一个map集合
			* 注意：这个方法查询的结果集长度只能是1
		* queryForList():查询结果将结果集封装为list集合
			* 注意：将每一条记录封装为一个Map集合，再将Map集合装载到List集合中
		* query():查询结果，将结果封装为JavaBean对象
			* query的参数：RowMapper
				* 一般我们使用BeanPropertyRowMapper实现类。可以完成数据到JavaBean的自动封装
				* new BeanPropertyRowMapper<类型>(类型.class)
		* queryForObject：查询结果，将结果封装为对象
			* 一般用于聚合函数的查询

## 文件说明

1,libs中是jar包

2，.xml是CP30的配置文件，druid.properties是druid的配置文件

3，Emp是个对象的实体类

4，C3p0Demo01.java是演示CP30链接的文件的

5，druidDemo01.java是演示druid链接的文件的

6，druidDemo01.java是演示druid  DML的文件的

7，**最重要的文件** 演示spring JDBC对数据库操作的，其中用到了Juitl的测试方法，也就是加上一个@Test的注解，使得没有主函数，每个单独的函数本身也能够自主操作。templateDemo02.java









