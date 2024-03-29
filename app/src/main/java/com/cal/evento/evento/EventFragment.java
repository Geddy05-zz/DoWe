package com.cal.evento.evento;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cal.evento.evento.Models.FacebookEvents;
import com.cal.evento.evento.dummy.DummyContent;
import com.cal.evento.evento.dummy.DummyContent.DummyItem;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphRequestBatch;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;
import java.util.Objects;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class EventFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    public List<FacebookEvents> facebookEvents;
    private RecyclerView recyclerView;
    private Context mContext;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EventFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static EventFragment newInstance(int columnCount) {
        EventFragment fragment = new EventFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mContext = this.getContext();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        Bundle parameters = new Bundle();
//        MyApplication appState = ((MyApplication)getActivity().getApplicationContext());
//        Long tsLong = System.currentTimeMillis()/1000;
//        String ts = tsLong.toString();
        Date cDate = new Date();
        String ts = new SimpleDateFormat("yyyy-MM-dd").format(cDate);
        parameters.putString("q","Rotterdam");
        parameters.putString("type", "event");
//        parameters.putString("center","4.481776,51.924216");
//        parameters.putString("distance","10");
//        parameters.putString("start_time", ts);
//        search?q=London&type=event
        GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/search",
                parameters,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try {
                            JSONArray events = response.getJSONObject().getJSONArray("data");
//                            facebookEvents = new FacebookEvents[events.length()];
                            facebookEvents = new ArrayList();
                            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                            for (int i = 0; i < events.length() - 1; i++) {
                                JSONObject event = events.getJSONObject(i);
                                String name = event.getString("name");
                                String description = event.getString("description");
//                                String start_timeString =  event.getString("start_time");
//                                Date start_time = format.parse(start_timeString);

                                String id = event.getString("id");
                                FacebookEvents newEvent = new FacebookEvents(id);
                                newEvent.setName(name);
                                newEvent.setDescription(description);
                                JSONObject loc = event.getJSONObject("place");
                                newEvent.setLocation(loc);
//                                newEvent.setStart_time(start_time);
                                facebookEvents.add(newEvent);
                            }
                        } catch (JSONException e) {
//                            e.printStackTrace();
                        }
            /* handle the result */
//                        int a = facebookEvents.length;
                        recyclerView.setAdapter(new FacebookEventsAdapter(mContext,facebookEvents,mListener));
                        getExtraEventInfo();
                    }
                }
        ).executeAsync();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyEventRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void getExtraEventInfo(){
//        GraphRequest[] req = new GraphRequest[facebookEvents.size()];
        GraphRequestBatch batch = new GraphRequestBatch();
        Bundle parameters = new Bundle();
//        MyApplication appState = ((MyApplication)getActivity().getApplicationContext());
//        Long tsLong = System.currentTimeMillis()/1000;
//        String ts = tsLong.toString();
//        Date cDate = new Date();
//        String ts = new SimpleDateFormat("yyyy-MM-dd").format(cDate);
        parameters.putString("fields","cover");

        for (int i = 0; i < facebookEvents.size() - 1; i++) {
            final FacebookEvents event = facebookEvents.get(i);
            final int index = i;

            GraphRequest req = new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/"+ event.getiD()+"",
                    parameters,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
                            /* handle the result */
                            JSONObject events = response.getJSONObject();
                            try {
                                JSONObject json = events.getJSONObject("cover");
                                String pic = json.getString("source");
                                event.setPhoto(pic);
                                facebookEvents.set(index,event);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
            );
            batch.add(req);
        }

//        GraphRequestBatch batch = new GraphRequestBatch();
        batch.addCallback(new GraphRequestBatch.Callback() {
            @Override
            public void onBatchCompleted(GraphRequestBatch graphRequests) {
                // Application code for when the batch finishes
                int a = 1;
                recyclerView.setAdapter(new FacebookEventsAdapter(mContext,facebookEvents,mListener));

            }
        });
        batch.executeAsync();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=11111
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
