package com.job.showcase.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.job.showcase.R;
import com.job.showcase.SendActivity;
import com.job.showcase.model.Users;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Job on Friday : 3/23/2018.
 */

public class UserRecylerViewAdapter extends RecyclerView.Adapter<UserRecylerViewAdapter.UserViewHolder> {

    private List<Users> usersList;
    private Context context;

    public UserRecylerViewAdapter(List<Users> usersList, Context context) {
        this.usersList = usersList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_list_view,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        holder.userListProfileName.setText(usersList.get(position).getName());
        CircleImageView circleImageView = holder.userListProfileImage;

        Glide.with(context)
                .setDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.profile_placeholder))
                .load(usersList.get(position).getImageurl())
                .into(holder.userListProfileImage);

        final String user_id = usersList.get(position).userId;
        final String user_name = usersList.get(position).getName();

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, SendActivity.class);
                intent.putExtra("user_id",user_id);
                intent.putExtra("user_name",user_name);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        public View mView;
        @BindView(R.id.user_list_profile_name)
        TextView userListProfileName;
        @BindView(R.id.user_list_notificationtxt)
        TextView userListNotificationTxt;
        @BindView(R.id.user_list_profileimage)
        CircleImageView userListProfileImage;


        public UserViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, mView);
        }
    }
}
