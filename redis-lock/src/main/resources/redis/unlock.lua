local value = redis.call('get', KEYS[1])
local arg = ARGV[1]

if value ==  arg then
 	return redis.call('del', KEYS[1])
else 
	return 0
end