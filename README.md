# Redis使用场景

GitHub实时更新，Gitee会同步GitHub上面的内容。

GitHub地址：[lizhifuabc/redis-book: redis笔记 (github.com)](https://github.com/lizhifuabc/redis-book)

Gitee地址：[redis-book: redis笔记 (gitee.com)](https://gitee.com/lizhifu/redis-book)

 [LUA脚本](./redis-lua/README.md)

- [x] 返回String
- [x] 返回Long
- [x] 返回List

 [全局ID](./redis-id/README.md)

- [x] 简单版本数据库唯一ID：IdGeneratorService
- [x] 增加本地缓存版本：IdLocalGeneratorService

 [排行榜](./redis-rank/README.md)

- [x] 使用Redis 有序集合(sorted set)实现热榜：RankService

 [位图(bitmap)](./redis-bitmap/README.md)

- [x] setbit理解测试：TestOffset
- [x] 位图统计用户登录：UserLoginService

