package br.com.ifsc.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.Nonnull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FrutaAdapter extends ArrayAdapter<Fruta> {
    Context mContext;
    int mLayout;
    Fruta mFrutas[];
    public FrutaAdapter(@Nonnull Context context, int resource, @Nonnull Fruta[] objects) {
        super(context, resource, objects);
        mContext=context;
        mLayout=resource;
        mFrutas=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent){
        if(view==null){
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view=layoutInflater.inflate(mLayout, parent, false);
        }
        return view;
    }
}
