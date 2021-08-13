local value = redis.call('lrange',KEYS[1],0,-1)
-- 打印日志到reids
redis.log(redis.LOG_NOTICE,value)
return value