package com.cal.evento.evento;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.facebook.internal.Utility;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalendarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MaterialCalendarView calendarView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        cal();
//        createCal();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("DOWE", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
//            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
//            updateUI(false);
        }
    }

    public void cal(){
        calendarView = (MaterialCalendarView) getView().findViewById(R.id.calendarView);
        calendarView.setTopbarVisible(false);
        Date date = new Date();
        calendarView.setCurrentDate(date);
        calendarView.setSelectedDate(date);
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Januari");
            }
        });

    }



//    public void createCal(){
//
//        calendarView = (CalendarView) getView().findViewById(R.id.calendarView);
//
//        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
//        calendarView.setIsOverflowDateVisible(true);
//        calendarView.setCurrentDay(new Date(System.currentTimeMillis()));
//        calendarView.setBackButtonColor(R.color.white);
//        calendarView.setNextButtonColor(R.color.white);
//        calendarView.refreshCalendar(Calendar.getInstance(Locale.getDefault()));
//        calendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
//            @Override
//            public void onDateSelected(@NonNull Date selectedDate) {
////                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
////                textView.setText(df.format(selectedDate));
//            }
//        });
//
//        calendarView.setOnMonthChangedListener(new CalendarView.OnMonthChangedListener() {
//            @Override
//            public void onMonthChanged(@NonNull Date monthDate) {
//                removeDayView(monthDate);
//                Calendar cal = Calendar.getInstance(Locale.getDefault());
//                cal.set(monthDate.getYear(),monthDate.getMonth(),monthDate.getDay());
//                calendarView.refreshCalendar(cal);
//                drawDayView(monthDate);
//                SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
////                if (null != actionBar)
////                    actionBar.setTitle(df.format(monthDate));
//            }
//        });
//
//        DayView dayView = calendarView.findViewByDate(new Date(System.currentTimeMillis()));
//        Drawable circle = drawCircle(getContext(),12,12,R.color.colorAccentBlue);
//        Drawable myIcon = getResources().getDrawable( R.drawable.calendardot );
//        if(null != dayView)
//            dayView.setCompoundDrawablesWithIntrinsicBounds(null,null,null,circle);
////            Toast.makeText(this.getContext(), "Today is: " + dayView.getText().toString() + "/" + calendarView.getCurrentMonth() + "/" +  calendarView.getCurrentYear(), Toast.LENGTH_SHORT).show();
//    }
//
//    public void removeDayView(Date monthDate){
//        DayView dayView = calendarView.findViewByDate(new Date(System.currentTimeMillis()));
//        if(null != dayView)
//            dayView.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
//    }
//
//    public void drawDayView(Date monthDate){
//        DayView dayView = calendarView.findViewByDate(new Date(2016,02,12));
//        Drawable circle = drawCircle(getContext(),10,10,R.color.colorAccentBlue);
//        if(null != dayView) {
//            dayView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, circle);
//        }else{
//            dayView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
//        }
//    }

    public static ShapeDrawable drawCircle (Context context, int width, int height, int color) {

        //////Drawing oval & Circle programmatically /////////////

        ShapeDrawable oval = new ShapeDrawable(new OvalShape());
        oval.setIntrinsicHeight(height);
        oval.setIntrinsicWidth(width);
        oval.getPaint().setColor(context.getResources().getColor(color));
        return oval;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
