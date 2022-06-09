package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {

    public String body;
    public String createdAt;
    public User user;
    public String tweet_URL;
    public String util;

    public Tweet(){}

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));

        if(jsonObject.has("full_text"))
            tweet.body = jsonObject.getString("full_text");
        else
            tweet.body = jsonObject.getString("text");
//        tweet.retweeted = jsonObject.getBoolean("retweeted");
//        tweet.liked = jsonObject.getBoolean("favorited");
//        tweet.numLikes=jsonObject.getInt("favorite_count");

        User user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.user = user;
//        tweet.userId = user
        if(!jsonObject.getJSONObject("entities").has("media")) {
            tweet.tweet_URL = "none";
        }
        else {
            tweet.tweet_URL = jsonObject.getJSONObject("entities").getJSONArray("media").getJSONObject(0).getString("media_url");
        }


        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
//testing using a pull request*
    }
}
