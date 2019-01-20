package com.battaglino.santiago.sweatworks.liga.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.battaglino.santiago.sweatworks.R;
import com.battaglino.santiago.sweatworks.db.entities.Liga;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Santiago Battaglino.
 */
public class LigaAdapter extends RecyclerView.Adapter<LigaAdapter.LigaViewHolder> {

    private Context context;
    private LigaAdapter.OnViewHolderClick clickListener;

    private List<Liga> mLiga;

    public LigaAdapter(Context context, LigaAdapter.OnViewHolderClick clickListener, List<Liga> mLiga) {
        this.context = context;
        this.clickListener = clickListener;
        this.mLiga = mLiga;
    }

    @Override
    public void onBindViewHolder(@NonNull LigaAdapter.LigaViewHolder holder, int position) {
        bindView(getItem(position), holder);
    }

    private void bindView(Liga liga, LigaAdapter.LigaViewHolder viewHolder) {
        if (liga != null) {
            setUpLiga(liga, viewHolder);
        }
    }

    private void setUpLiga(Liga liga, LigaAdapter.LigaViewHolder viewHolder) {
        // para setear varios valores con String.format
        /*viewHolder.observacion.setText(String.format(Locale.getDefault(), "uid: %d cell: %s email: %s gender: %s nat: %s",
                liga.uid,
                liga.cell,
                liga.email,
                liga.gender,
                liga.nat));*/

        // para mostrar imagenes con Picasso
        //Picasso.get().load(liga.picture.thumbnail).into(viewHolder.thumbnail);

        // seteando strings
        viewHolder.leagueId.setText(liga.leagueId);
        viewHolder.leageName.setText(liga.leagueName);
    }

    @NonNull
    @Override
    public LigaAdapter.LigaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LigaAdapter.LigaViewHolder(createView(context, parent, viewType), clickListener);
    }

    private View createView(Context context, ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.liga_list, viewGroup, false);
    }

    @Override
    public int getItemCount() {
        return mLiga == null ? 0 : mLiga.size();
    }

    public void addAll(List<Liga> list) {
        mLiga.addAll(list);
        notifyDataSetChanged();
    }

    public void reset() {
        mLiga.clear();
        notifyDataSetChanged();
    }

    private Liga getItem(int index) {
        return ((mLiga != null && index < mLiga.size()) ? mLiga.get(index) : null);
    }

    public List<Liga> getList() {
        return mLiga;
    }

    public interface OnViewHolderClick {
        void onClick(View view, int position, Liga item);
    }

    public class LigaViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.leagueId)
        TextView leagueId;

        @BindView(R.id.leagueName)
        TextView leageName;

        LigaViewHolder(View view, LigaAdapter.OnViewHolderClick listener) {
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
