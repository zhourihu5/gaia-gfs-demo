-- 这里需要注意 h2 的一些局限性, 大部分的 mysql 增删改查都没问题, 不过不支持 timestampe 的 ON UPDATE CURRENT TIMESTAMP
CREATE TABLE IF NOT EXISTS test_user (
  user_id varchar(64),
  username varchar(64)
);
