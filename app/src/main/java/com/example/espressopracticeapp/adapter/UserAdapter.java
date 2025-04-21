package com.example.espressopracticeapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.espressopracticeapp.DetailActivity;
import com.example.espressopracticeapp.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<String> data;
    private Context context;

    public UserAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textUser;

        public ViewHolder(View itemView) {
            super(itemView);
            textUser = itemView.findViewById(R.id.text_user);
        }

        public void bind(final String name, final Context context) {
            textUser.setText(name);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("username", name);
                context.startActivity(intent);
            });
        }
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position), context);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
