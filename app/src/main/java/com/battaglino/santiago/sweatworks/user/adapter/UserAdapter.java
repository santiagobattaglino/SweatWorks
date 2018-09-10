package com.battaglino.santiago.sweatworks.user.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.battaglino.santiago.sweatworks.R;
import com.battaglino.santiago.sweatworks.db.entities.User;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Santiago Battaglino.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private UserAdapter.OnViewHolderClick clickListener;

    private List<User> mUsers;

    public UserAdapter(Context context, UserAdapter.OnViewHolderClick clickListener, List<User> mUsers) {
        this.context = context;
        this.clickListener = clickListener;
        this.mUsers = mUsers;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        bindView(getItem(position), holder);
    }

    private void bindView(User user, UserViewHolder viewHolder) {
        if (user != null) {
            setUpUser(user, viewHolder);
        }
    }

    private void setUpUser(User user, UserViewHolder viewHolder) {
        /*viewHolder.observacion.setText(String.format(Locale.getDefault(), "uid: %d cell: %s email: %s gender: %s nat: %s",
                user.uid,
                user.cell,
                user.email,
                user.gender,
                user.nat));*/

        Picasso.get().load(user.picture.thumbnail).into(viewHolder.thumbnail);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(createView(context, parent, viewType), clickListener);
    }

    private View createView(Context context, ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.item_list_content, viewGroup, false);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void addAll(List<User> list) {
        mUsers.addAll(list);
        notifyDataSetChanged();
    }

    public void reset() {
        mUsers.clear();
        notifyDataSetChanged();
    }

    private User getItem(int index) {
        return ((mUsers != null && index < mUsers.size()) ? mUsers.get(index) : null);
    }

    public List<User> getList() {
        return mUsers;
    }

    public interface OnViewHolderClick {
        void onClick(View view, int position, User item);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.thumbnail)
        ImageView thumbnail;

        //@BindView(R.id.observacion)
        //TextView observacion;

        UserViewHolder(View view, UserAdapter.OnViewHolderClick listener) {
            super(view);
            ButterKnife.bind(this, view);

            if (listener != null)
                view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, getAdapterPosition(), getItem(getAdapterPosition()));
        }
    }
}