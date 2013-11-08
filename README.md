social-sdk
==========

social-sdk是一个集成[新浪微博开放平台][1]、[QQ互联][2]、[腾讯微博开发平台][3]、[微信公众平台][4]等社交平台的接口的Java库。



其实在开始这个项目之前，各个平台都已经提供相应的Java
SDK，有官方的、也有非官方的开源项目，如如新浪微博开放平台有[weibo4j][5]。我也一直在使用这些项目。但是在使用过程中越到的问题越来越多，越来越麻烦，如：

    -   这些SDK都是提供一个ZIP包，不适合Maven或Ivy管理的项目。

    -   各个SDK都引入了开源公共类库，确改了包名，造成类库过多、混乱。

    -   SDK更新超级慢，跟不上平台上接口的变更。

    -   没有交流环境，遇到BUG找不到资料，找不到沟通的地方，需要自己去琢磨源代码。

    -   ......

因此，自己开发一个，尝试尽可能多的集成社交平台。



**因为我并没有在项目中使用到那么多社交平台，所以有的社交平台没有可供测试的应用信息（通常叫做AppKey和AppSecret）。所以非常希望有资源的朋友共享测试帐号。**



已实现
---

-   新浪微博登录

-   QQ帐号登录

-   腾讯微博登录（未测试）

-   微信帐号登录（未测试）

-   获取新浪微博用户信息

-   获取QQ用户信息

-   获取腾讯微博用户信息（未测试）

-   获取微信用户信息（未测试）



计划开发
----

-   更多的接口

-   支持更多的平台



下载
--

推荐使用Maven下载。social-sdk已发布到Maven中央库。

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
<dependency>
    <groupId>com.belerweb</groupId>
    <artifactId>social-sdk</artifactId>
    <version>0.0.1</version>
</dependency>
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



参与
--

交流：GitHub上留言或加入QQ群（328171904）

共享代码：Fork项目并提交Pull Requests

提交BUG：直接在GitHub上提交

其他：欢迎任何形式的贡献，文档、经验、意见...



链接
--

[1]: <http://open.weibo.com/>
[2]: <http://connect.qq.com/>
[3]: <http://dev.t.qq.com/>
[4]: <http://mp.weixin.qq.com/wiki/index.php>
[5]: <http://code.google.com/p/weibo4j/>
[6]: <https://github.com/belerweb/weibo4j>
[7]: <https://github.com/belerweb/qq-connect>
[8]: <https://github.com/belerweb/weixin-mp-sdk>
