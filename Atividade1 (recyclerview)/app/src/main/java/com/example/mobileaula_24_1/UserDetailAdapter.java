package com.example.mobileaula_24_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UserDetailAdapter extends RecyclerView.Adapter<UserDetailAdapter.UserDetailViewHolder> {

    private List<UserDetail> userDetailList;

    public UserDetailAdapter(List<UserDetail> userDetailList) {
        this.userDetailList = userDetailList;
    }

    @NonNull
    @Override
    public UserDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_detail, parent, false);
        return new UserDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDetailViewHolder holder, int position) {
        UserDetail userDetail = userDetailList.get(position);
        holder.textViewName.setText(userDetail.getName());
        holder.textViewPost.setText(userDetail.getPost());
        holder.textViewComment.setText(userDetail.getComment());
    }

    @Override
    public int getItemCount() {
        return userDetailList.size();
    }

    public static class UserDetailViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewPost, textViewComment;

        public UserDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPost = itemView.findViewById(R.id.textViewPost);
            textViewComment = itemView.findViewById(R.id.textViewComment);
        }
    }
}
