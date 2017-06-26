package com.redis.chapter1;

import redis.clients.jedis.Jedis;

/**
 * Created by i325622 on 6/19/17.
 */
public class ArticleVoteService {

    private final int WEEK_IN_SECONDS = 7 * 86400;
    private final int VOTE_SCORE = 432;

    Jedis jedis = new Jedis("127.0.0.1", 6379);

    public void articleVote(User user, Article article) {
        Long cutOff = System.currentTimeMillis() / 1000 - WEEK_IN_SECONDS;
        if (jedis.zscore("time:", article.toString()) < cutOff) {
            return;
        }

        Long id = article.getId();
        if (jedis.sadd("voted:" + id, user.toString()) == 1) {
            jedis.zincrby("score:", VOTE_SCORE, article.toString());
//            jedis.hincrBy(article);
        }
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis() / 1000);
        Long cutOff = System.currentTimeMillis() / 1000 - 7 * 86400;

        System.out.println(cutOff);

        String t = "article: 123123";
        System.out.println(t.split(":")[1].trim());
    }
}
