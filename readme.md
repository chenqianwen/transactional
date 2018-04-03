client 配置最大2个数据库链接
在浏览器发2次请求时：http://localhost:9070/order/updateModel/1
在发请求时:http://localhost:9070/order/findById/2 链接池不够，就会报错！
原因:
orderServiceImpl中updateModel方法上的@Transactional
@Transactional声明式事务：
锁定整个方法内部。在远程响应之前，事务不会释放，数据库链接不会释放。

 