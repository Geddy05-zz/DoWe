package com.cal.evento.evento;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cal.evento.evento.EventFragment.OnListFragmentInteractionListener;
import com.cal.evento.evento.Models.FacebookEvents;
import com.cal.evento.evento.Models.Location;
import com.cal.evento.evento.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * specified {@link EventFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FacebookEventsAdapter extends RecyclerView.Adapter<FacebookEventsAdapter.ViewHolder> {

    private final List<FacebookEvents> mValues;
    private final EventFragment.OnListFragmentInteractionListener mListener;

    public FacebookEventsAdapter(List<FacebookEvents> items, EventFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
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
        public FacebookEvents mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mCity = (TextView) view.findViewById(R.id.where);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
