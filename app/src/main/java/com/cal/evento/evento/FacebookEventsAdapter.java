package com.cal.evento.evento;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cal.evento.evento.EventFragment.OnListFragmentInteractionListener;
import com.cal.evento.evento.Models.FacebookEvents;
import com.cal.evento.evento.Models.Location;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * specified {@link EventFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FacebookEventsAdapter extends RecyclerView.Adapter<FacebookEventsAdapter.ViewHolder> {

    private final List<FacebookEvents> mValues;
    private final EventFragment.OnListFragmentInteractionListener mListener;
//    private final  ImageLoader imageLoader;
    private final Context mContext;

    public FacebookEventsAdapter(Context context, List<FacebookEvents> items, EventFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getName());
        Location location = mValues.get(position).getLocation();
        holder.mContentView.setText(location.Name);
//        imageLoader.displayImage(imageUrls[position], holder.image, null);
        holder.mCity.setText(mValues.get(position).getDescription());
        String iD = mValues.get(position).getiD();
        if (mValues.get(position).getPhoto() != null) {
            Picasso.with(mContext).load(mValues.get(position).getPhoto()).into(holder.mPhoto);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mCity;
        public final ImageView mPhoto;
        public FacebookEvents mItem;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mCity = (TextView) view.findViewById(R.id.where);
            mPhoto = (ImageView) view.findViewById(R.id.eventPhoto);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
