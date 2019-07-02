# Pasta do Projeto de Cassandra e Java

/usr/java/jdk-9/bin/java -javaagent:/opt/idea-IC-182.4129.33/lib/idea_rt.jar=35579:/opt/idea-IC-182.4129.33/bin -Dfile.encoding=UTF-8 -classpath /home/treinamento/src/inf1802/hello-twitter/out/production/hello-twitter:/home/treinamento/.m2/repository/com/datastax/cassandra/cassandra-driver-core/3.7.1/cassandra-driver-core-3.7.1.jar:/home/treinamento/.m2/repository/io/netty/netty-handler/4.0.56.Final/netty-handler-4.0.56.Final.jar:/home/treinamento/.m2/repository/io/netty/netty-buffer/4.0.56.Final/netty-buffer-4.0.56.Final.jar:/home/treinamento/.m2/repository/io/netty/netty-common/4.0.56.Final/netty-common-4.0.56.Final.jar:/home/treinamento/.m2/repository/io/netty/netty-transport/4.0.56.Final/netty-transport-4.0.56.Final.jar:/home/treinamento/.m2/repository/io/netty/netty-codec/4.0.56.Final/netty-codec-4.0.56.Final.jar:/home/treinamento/.m2/repository/com/google/guava/guava/19.0/guava-19.0.jar:/home/treinamento/.m2/repository/io/dropwizard/metrics/metrics-core/3.2.2/metrics-core-3.2.2.jar:/home/treinamento/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar:/home/treinamento/.m2/repository/com/github/jnr/jnr-ffi/2.1.7/jnr-ffi-2.1.7.jar:/home/treinamento/.m2/repository/com/github/jnr/jffi/1.2.16/jffi-1.2.16.jar:/home/treinamento/.m2/repository/org/ow2/asm/asm/5.0.3/asm-5.0.3.jar:/home/treinamento/.m2/repository/org/ow2/asm/asm-commons/5.0.3/asm-commons-5.0.3.jar:/home/treinamento/.m2/repository/org/ow2/asm/asm-analysis/5.0.3/asm-analysis-5.0.3.jar:/home/treinamento/.m2/repository/org/ow2/asm/asm-tree/5.0.3/asm-tree-5.0.3.jar:/home/treinamento/.m2/repository/org/ow2/asm/asm-util/5.0.3/asm-util-5.0.3.jar:/home/treinamento/.m2/repository/com/github/jnr/jnr-x86asm/1.0.2/jnr-x86asm-1.0.2.jar:/home/treinamento/.m2/repository/com/github/jnr/jnr-posix/3.0.44/jnr-posix-3.0.44.jar:/home/treinamento/.m2/repository/com/github/jnr/jnr-constants/0.9.9/jnr-constants-0.9.9.jar hellotwitter.HelloTweet
Hello, Tweet!
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
3.11.4
Create repository tweets
Use repository tweets
Cleaning tables if some mess happened...
deleteTable ---- init
DROP TABLE IF EXISTS tweets
deleteTable ---- end
deleteTable ---- init
DROP TABLE IF EXISTS tweetsByUser
deleteTable ---- end
Create table tweets
createTable ---- init
CREATE TABLE IF NOT EXISTS tweets(id uuid PRIMARY KEY,user text,message text,date date,source text,truncated Boolean,latitude double,longitude double,favorited Boolean,contributors list<bigint>);
createTable ---- end
Create table tweetsByUser
createTableTweetsByUser ---- init
CREATE TABLE IF NOT EXISTS tweetsByUser(id uuid,user text,message text,date date,source text,truncated Boolean,latitude double,longitude double,favorited Boolean,contributors list<bigint>,PRIMARY KEY (user, id));
createTableTweetsByUser ---- end
Insert tweets
insertTweet ---- init
INSERT INTO tweets(id, user, message, date, source, truncated, latitude, longitude, favorited, contributors) VALUES (d72c6ad0-9cca-11e9-98d2-d588985441bf, 'User 1', 'Wow, Cassandra is amazing!', '2019-06-25', 'Some source', false, -33.0, -21.673356, true, [1, 33474]);
insertTweet ---- end
insertTweet ---- init
INSERT INTO tweets(id, user, message, date, source, truncated, latitude, longitude, favorited) VALUES (d72c91e0-9cca-11e9-98d2-d588985441bf, 'User 1', 'Look at what I can do with Cassandra and Java!', '2019-06-24', 'A crazy source', false, -3.235, 15.0, true);
insertTweet ---- end
insertTweet ---- init
INSERT INTO tweets(id, user, message, date, source, truncated, latitude, longitude, favorited) VALUES (d72c91e1-9cca-11e9-98d2-d588985441bf, 'User 3', 'This is really impressive.', '2019-05-25', 'This source is amazing', false, -33.21256, -26.34356, false);
insertTweet ---- end
insertTweet ---- init
INSERT INTO tweets(id, user, message, date, source, truncated, latitude, longitude, favorited) VALUES (d72c91e2-9cca-11e9-98d2-d588985441bf, 'User 4', 'I cant wait to learn more! I am so exc', '2019-08-25', 'Sent from Android', true, -20.233467, -12.378337, false);
insertTweet ---- end
insertTweet ---- init
INSERT INTO tweets(id, user, message, date, source, truncated, latitude, longitude, favorited) VALUES (d72c91e3-9cca-11e9-98d2-d588985441bf, 'User 5', 'See you next time.', '2020-06-25', 'Somewhere over the rainbow', false, 0.0, 0.0, true);
insertTweet ---- end
Insert tweets by user
insertTweetByUser ---- init
INSERT INTO tweetsByUser(id, user, message, date, source, truncated, latitude, longitude, favorited, contributors) VALUES (d72c6ad0-9cca-11e9-98d2-d588985441bf, 'User 1', 'Wow, Cassandra is amazing!', '2019-06-25', 'Some source', false, -33.0, -21.673356, true, [1, 33474]);
insertTweetByUser ---- end
insertTweetByUser ---- init
INSERT INTO tweetsByUser(id, user, message, date, source, truncated, latitude, longitude, favorited) VALUES (d72c91e0-9cca-11e9-98d2-d588985441bf, 'User 1', 'Look at what I can do with Cassandra and Java!', '2019-06-24', 'A crazy source', false, -3.235, 15.0, true);
insertTweetByUser ---- end
insertTweetByUser ---- init
INSERT INTO tweetsByUser(id, user, message, date, source, truncated, latitude, longitude, favorited) VALUES (d72c91e1-9cca-11e9-98d2-d588985441bf, 'User 3', 'This is really impressive.', '2019-05-25', 'This source is amazing', false, -33.21256, -26.34356, false);
insertTweetByUser ---- end
insertTweetByUser ---- init
INSERT INTO tweetsByUser(id, user, message, date, source, truncated, latitude, longitude, favorited) VALUES (d72c91e2-9cca-11e9-98d2-d588985441bf, 'User 4', 'I cant wait to learn more! I am so exc', '2019-08-25', 'Sent from Android', true, -20.233467, -12.378337, false);
insertTweetByUser ---- end
insertTweetByUser ---- init
INSERT INTO tweetsByUser(id, user, message, date, source, truncated, latitude, longitude, favorited) VALUES (d72c91e3-9cca-11e9-98d2-d588985441bf, 'User 5', 'See you next time.', '2020-06-25', 'Somewhere over the rainbow', false, 0.0, 0.0, true);
insertTweetByUser ---- end
Select all tweets
selectAll ---- init
SELECT * FROM tweets
Tweet = d72c6ad0-9cca-11e9-98d2-d588985441bf, User 1, Wow, Cassandra is amazing!, 2019-06-25, Some source, false, -33.0, -21.673356, true, [1, 33474]
Tweet = d72c91e1-9cca-11e9-98d2-d588985441bf, User 3, This is really impressive., 2019-05-25, This source is amazing, false, -33.21256, -26.34356, false, []
Tweet = d72c91e3-9cca-11e9-98d2-d588985441bf, User 5, See you next time., 2020-06-25, Somewhere over the rainbow, false, 0.0, 0.0, true, []
Tweet = d72c91e0-9cca-11e9-98d2-d588985441bf, User 1, Look at what I can do with Cassandra and Java!, 2019-06-24, A crazy source, false, -3.235, 15.0, true, []
Tweet = d72c91e2-9cca-11e9-98d2-d588985441bf, User 4, I cant wait to learn more! I am so exc, 2019-08-25, Sent from Android, true, -20.233467, -12.378337, false, []
selectAll ---- end
Select all tweets by user
selectAll ---- init
SELECT * FROM tweetsByUser
Tweet = d72c91e3-9cca-11e9-98d2-d588985441bf, User 5, See you next time., 2020-06-25, Somewhere over the rainbow, false, 0.0, 0.0, true, []
Tweet = d72c91e2-9cca-11e9-98d2-d588985441bf, User 4, I cant wait to learn more! I am so exc, 2019-08-25, Sent from Android, true, -20.233467, -12.378337, false, []
Tweet = d72c91e1-9cca-11e9-98d2-d588985441bf, User 3, This is really impressive., 2019-05-25, This source is amazing, false, -33.21256, -26.34356, false, []
Tweet = d72c6ad0-9cca-11e9-98d2-d588985441bf, User 1, Wow, Cassandra is amazing!, 2019-06-25, Some source, false, -33.0, -21.673356, true, [1, 33474]
Tweet = d72c91e0-9cca-11e9-98d2-d588985441bf, User 1, Look at what I can do with Cassandra and Java!, 2019-06-24, A crazy source, false, -3.235, 15.0, true, []
selectAll ---- end
Select a tweet by User 1
selectByUser ---- init
SELECT * FROM tweetsByUser WHERE user = 'User 1';
Tweet = d72c6ad0-9cca-11e9-98d2-d588985441bf, User 1, Wow, Cassandra is amazing!, 2019-06-25, Some source, false, -33.0, -21.673356, true, [1, 33474]
selectByUser ---- end
Delete tweet
deleteTweet ---- init
DELETE FROM tweets WHERE id = d72c6ad0-9cca-11e9-98d2-d588985441bf;
deleteTweet ---- end
Delete tweet by user
deleteTweetByUser ---- init
DELETE FROM tweetsByUser WHERE user = 'User 3';
deleteTweetByUser ---- end
Select all tweets
selectAll ---- init
SELECT * FROM tweets
Tweet = d72c91e1-9cca-11e9-98d2-d588985441bf, User 3, This is really impressive., 2019-05-25, This source is amazing, false, -33.21256, -26.34356, false, []
Tweet = d72c91e3-9cca-11e9-98d2-d588985441bf, User 5, See you next time., 2020-06-25, Somewhere over the rainbow, false, 0.0, 0.0, true, []
Tweet = d72c91e0-9cca-11e9-98d2-d588985441bf, User 1, Look at what I can do with Cassandra and Java!, 2019-06-24, A crazy source, false, -3.235, 15.0, true, []
Tweet = d72c91e2-9cca-11e9-98d2-d588985441bf, User 4, I cant wait to learn more! I am so exc, 2019-08-25, Sent from Android, true, -20.233467, -12.378337, false, []
selectAll ---- end
Select all tweets by user
selectAll ---- init
SELECT * FROM tweetsByUser
Tweet = d72c91e3-9cca-11e9-98d2-d588985441bf, User 5, See you next time., 2020-06-25, Somewhere over the rainbow, false, 0.0, 0.0, true, []
Tweet = d72c91e2-9cca-11e9-98d2-d588985441bf, User 4, I cant wait to learn more! I am so exc, 2019-08-25, Sent from Android, true, -20.233467, -12.378337, false, []
Tweet = d72c6ad0-9cca-11e9-98d2-d588985441bf, User 1, Wow, Cassandra is amazing!, 2019-06-25, Some source, false, -33.0, -21.673356, true, [1, 33474]
Tweet = d72c91e0-9cca-11e9-98d2-d588985441bf, User 1, Look at what I can do with Cassandra and Java!, 2019-06-24, A crazy source, false, -3.235, 15.0, true, []
selectAll ---- end
Delete table tweets
deleteTable ---- init
DROP TABLE IF EXISTS tweets
deleteTable ---- end
Delete table tweets by user
deleteTable ---- init
DROP TABLE IF EXISTS tweetsByUser
deleteTable ---- end
Delete keyspace tweets

Process finished with exit code 0
