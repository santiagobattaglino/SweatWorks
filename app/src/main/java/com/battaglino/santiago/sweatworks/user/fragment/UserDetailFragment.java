package com.battaglino.santiago.sweatworks.user.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.battaglino.santiago.sweatworks.R;
import com.battaglino.santiago.sweatworks.db.entities.User;
import com.battaglino.santiago.sweatworks.global.Constants;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailFragment extends Fragment {

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.fullName)
    TextView fullName;

    @BindView(R.id.phone)
    TextView phone;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.add_favorites)
    Button addFavorites;

    private User mUser;

    public UserDetailFragment() {

    }

    public static UserDetailFragment newInstance(User user) {
        UserDetailFragment fragment = new UserDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.ARG_USER, Parcels.wrap(user));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_detail, container, false);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            mUser = Parcels.unwrap(getArguments().getParcelable(Constants.ARG_USER));
        }
        setUpView();
        return view;
    }

    private void setUpView() {
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(mUser.getFullName());
        }

        Picasso.get().load(mUser.picture.large).into(image);

        fullName.setText(mUser.getFullName());
        phone.setText(mUser.phone);
        email.setText(mUser.email);
    }
}
