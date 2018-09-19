package com.battaglino.santiago.sweatworks.user.mvvm.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.battaglino.santiago.sweatworks.R;
import com.battaglino.santiago.sweatworks.base.mvvm.view.BaseView;
import com.battaglino.santiago.sweatworks.db.entities.User;
import com.battaglino.santiago.sweatworks.user.activity.UserDetailActivity;
import com.battaglino.santiago.sweatworks.user.mvvm.viewmodel.UserGridViewModel;
import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Santiago Battaglino.
 */
public class UserDetailView extends BaseView<UserDetailActivity, UserGridViewModel> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

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

    private User user;
    private List<User> mUsersFavorites = new ArrayList<>();

    @SuppressLint("CheckResult")
    @Inject
    public UserDetailView(UserDetailActivity activity, UserGridViewModel viewModel) {
        super(activity, viewModel);
        user = baseActivity.get().getUser();

        baseActivity.get().setTitle(user.getFullName());

        ButterKnife.bind(this, activity);
        setUpNavigation(toolbar);
        fab.setOnClickListener(view -> addContactIntent());

        Picasso.get().load(user.picture.large).into(image);

        fullName.setText(user.getFullName());
        phone.setText(user.phone);
        email.setText(user.email);

        RxView.clicks(addFavorites)
                .subscribe(click -> addFavorite());
    }

    private void addFavorite() {
        user.isFavorite = !user.isFavorite;
        baseViewModel.addFavorite(user);
        baseActivity.get().finish();
    }

    @Override
    protected void subscribeUiToLiveData() {

    }

    @Override
    protected void showDataInUi() {

    }

    private void setUpNavigation(Toolbar toolbar) {
        baseActivity.get().setSupportActionBar(toolbar);
        baseActivity.get().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addContactIntent() {
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.PHONETIC_NAME, user.getFullName());
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, user.email);
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, user.phone);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
        baseActivity.get().startActivity(intent);
    }
}
