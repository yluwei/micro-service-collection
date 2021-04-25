# oauth2 认证服务器

基于spring cloud starter oauth2实现，提供密码模式获取token

## 待完成
    1.由于目前使用redis存储token，重复登录得到的token是相同的，并且有效期不会更新。这也会引发几个问题：一点登出全部登出，无法刷新有效期。这种模式，适合单点登录，拒绝多点登录。
    2.要解决1中的问题，思路大概如下：
       * 多点登录，需要重写AuthenticationKeyGenerator，确保生成不同的redis的key；单点登录，挤掉之前登录的用户，那么不用重写，直接覆盖；
       * 重写createAccessToken，多点登录不用去查询已有的token。如果不允许重复登录，这里可以判断是否存在抛出异常；
       * 每次创建新的token就能拥有新的有效期。