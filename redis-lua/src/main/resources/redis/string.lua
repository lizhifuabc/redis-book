local value = redis.call('get',KEYS[1])
-- 打印日志到reids
redis.log(redis.LOG_NOTICE,value)
return value