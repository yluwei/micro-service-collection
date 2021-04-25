# API网关

## 概述
基于spring cloud gateway搭建的api网关，使用eureka作为注册中心，通过配置自动发现路由。引入Spring Security Oauth2进行统一的认证、鉴权。

## 工作流程
* ServerSecurityContextRepository：自定义了实现类，可以根据自己的实现去获取Authentication对象，并传入过滤连；
* ReactiveAuthorizationManager：除了permitAll之外的URL全部会进入此实现类，主要的鉴权逻辑写在这里；
* ServerAccessDeniedHandler：自定义AccessDenied处理，Authentication为空，则不会进入。

## 心得
    1.ServerSecurityContextRepository很重要，则这里可以根据实际登录情况封装Authentication;
    2.一开始ReactiveAuthorizationManager返回false时，始终不进入自定义的AccessDenied处理器，最后发现是因为没有则这里可以根据实际登录情况封装Authentication对象，响应就会一直401；
    3.2中一直响应401需要在仔细看源码，找到设置401的地方；
    4.这里没有使用Oauth2资源服务器，直接用了RedisTokenStore来处理认证。
    5.通过RedisTokenStore的readAccessToken和storeAccessToken方法进行token的刷新，注意读取token应转换为DefaultOAuth2AccessToken，否则没有setExpiration方法。