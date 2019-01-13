package com.uwaterlooplus.uwaterlooplus.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uwaterlooplus.uwaterlooplus.LockedActivity;
import com.uwaterlooplus.uwaterlooplus.R;

import java.math.BigInteger;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewAdventureFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewAdventureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewAdventureFragment extends Fragment {

//    private TextView mTvTimerText;
//    private Button mBtnStart;
//    private CountDownTimer countDownTimer;
//    private long timeLeftinMilliconds = 600000; //10 mins
//    private long mEndTime = System.currentTimeMillis() + timeLeftinMilliconds;
//    private boolean timerRunning;

    private EditText mInputAmoutofTime;
    private Button mBtnStart;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NewAdventureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewAdventureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewAdventureFragment newInstance(String param1, String param2) {
        NewAdventureFragment fragment = new NewAdventureFragment();
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

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        if(savedInstanceState != null) {
//            timeLeftinMilliconds = savedInstanceState.getLong("millisLeft");
//            timerRunning = savedInstanceState.getBoolean("timerRunning");
//            updateTimer();
//
//            if (timerRunning) {
//                mEndTime = savedInstanceState.getLong("endTime");
//                timeLeftinMilliconds = mEndTime - System.currentTimeMillis();
//                startTimer();
//            }
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_new_adventure, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBtnStart = view.findViewById(R.id.button_start);
        mInputAmoutofTime = view.findViewById(R.id.et_amountOfTime);

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mInputAmoutofTime.getText().toString();
                if (input.length() == 0){
                    Toast.makeText(getActivity(),"Cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }

                long millisInputSeconds = Long.parseLong(input) * 60000;
                if (millisInputSeconds == 0){
                    Toast.makeText(getActivity(),"Need positive number please",Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getContext(),LockedActivity.class);
                LockedActivity.setTimeLeftMilliconds(millisInputSeconds);
                startActivity(intent);
            }
        });


//        mTvTimerText = view.findViewById(R.id.textView_countdown);

//
//        mBtnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startStop();
//            }
//        });
//
//        updateTimer();
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
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

//    public void startStop(){
//        if(timerRunning){
//            stopTimer();
//        }else {
//            startTimer();
//        }
//    }

//    public void startTimer(){
//        countDownTimer = new CountDownTimer(timeLeftinMilliconds,100) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                timeLeftinMilliconds = millisUntilFinished;
//                updateTimer();
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        }.start();
//        mBtnStart.setText("Pause");
//        timerRunning = true;
//    }

//    public void stopTimer(){
//        countDownTimer.cancel();
//
//        mBtnStart.setText("Start");
//
//        timerRunning = false;
//    }

//    public void updateTimer(){
//        int minutes = (int) timeLeftinMilliconds / 60000;
//        int seconds = (int) timeLeftinMilliconds % 60000 / 1000;
//
//        String timeLeftText;
//
//        timeLeftText = "" + minutes;
//        timeLeftText += ":";
//
//        if(seconds < 10){
//            timeLeftText += "0";
//        }
//
//        timeLeftText += seconds;
//
//        mTvTimerText.setText(timeLeftText);
//
//    }


    //To make sure every time is saved

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putLong("millisLeft",timeLeftinMilliconds);
//        outState.putBoolean("timerRunning", timerRunning);
//        outState.putLong("endTime",mEndTime);
//    }


//    @Override
//    public void onViewStateRestored(Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//
//        timeLeftinMilliconds = savedInstanceState.getLong("millisLeft");
//        timerRunning = savedInstanceState.getBoolean("timerRunning");
//        updateTimer();
//
//        if(timerRunning){
//            mEndTime = savedInstanceState.getLong("endTime");
//            timeLeftinMilliconds = mEndTime - System.currentTimeMillis();
//            startTimer();
//        }
//    }
}
