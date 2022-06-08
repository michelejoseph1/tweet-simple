package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {

    //pass in context and list of tweets
    Context context;
    List<Tweet> tweets;

    public TweetAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }


    //inflating layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
return new ViewHolder(view);
    }

    //binding values based on position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);

        }

    //total #
    @Override
    public int getItemCount() {
        return tweets.size();
    }

public class ViewHolder  extends RecyclerView.ViewHolder {

    ImageView ivProfileImage;
    TextView tvBody;
    TextView tvScreenName;
    ImageView contentImage;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
        tvBody = itemView.findViewById(R.id.tvBody);
        tvScreenName = itemView.findViewById(R.id.tvScreenName);
        contentImage = itemView.findViewById(R.id.contentImage);
    }

    public void bind(Tweet tweet) {
        tvBody.setText(tweet.body);
        tvScreenName.setText(tweet.user.screenName);
        Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
        Glide.with(context).load(tweet.tweet_URL).into(contentImage);

    }
}
}
