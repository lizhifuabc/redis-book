# Redis 使用LUA脚本

## 基础

LUA基础：

- `tonumber`把字符串转换为数字

- `tostring`则把数字转换为字符串

- 把数字和空字符串连接起来，也可以达到`tostring`的效果：`10 .. ""`

- ```lua
  a = 5               -- 全局变量
  local b = 5         -- 局部变量
  ```

如何打印日志：

```lua
redis.log(redis.LOG_NOTICE,value)
```

Redis数据类型：

```
string（字符串），hash（哈希），list（列表），set（集合）及zset(sorted set：有序集合)。
```

## 返回数据类型

### 返回String

代码：StringScriptTest

```java
@SpringBootTest
public class StringScriptTest {
    @Resource
    private DefaultRedisScript<String> stringScript;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void test(){
        String key = "com.redis.lua";
        //设置一个数值
        stringRedisTemplate.opsForValue().set(key,"com.redis.lua");
        //通过lua获取该值
        List<String> keys = Arrays.asList(key);
        String result = stringRedisTemplate.execute(stringScript, keys);
        System.out.println("通过lua获取该值:"+result);
    }
}
```

###  返回Integer

代码：`IntegerScriptTest`

- 存入redis数据时采用的是`RedisTemplate<String,Integer>`，可以直接获取

  ```java
  @SpringBootTest
  public class IntegerScriptTest {
      @Resource
      private DefaultRedisScript<Integer> integerScript;
      @Resource
      private DefaultRedisScript<Long> integerScript2;
      @Resource
      private RedisTemplate<String,Integer> redisTemplate;
      @Resource
      private StringRedisTemplate stringRedisTemplate;
      @Test
      public void test(){
          String key = "com.redis.lua";
          //设置一个数值
          redisTemplate.opsForValue().set(key,1);
          //通过lua获取该值
          List<String> keys = Arrays.asList(key);
          Integer result = redisTemplate.execute(integerScript, keys);
          System.out.println("通过lua获取该值:"+result);
      }
  }
  ```

- 存入redis数据时采用的是`StringRedisTemplate`，LUA表达是内要使用`tonumber`

  ```java
      /**
       * Redis中的integer对应Java中的Long
       * @return
       */
      @Bean
      public DefaultRedisScript<Long> integerScript2() {
          DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
          redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/integer.lua")));
          redisScript.setResultType(Long.class);
          return redisScript;
      }
  ```

  ```lua
  local value = redis.call('get',KEYS[1])
  -- 打印日志到reids
  redis.log(redis.LOG_NOTICE,value)
  return tonumber(value)
  ```

### 返回List

代码：`ListScriptTest`

