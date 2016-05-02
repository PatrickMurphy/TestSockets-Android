package com.patrickmurphywebdesign.testsockets.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.patrickmurphywebdesign.testsockets.R;
import com.patrickmurphywebdesign.testsockets.controller.RouteProperties;

public class BusStopDetailFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public BusStopDetailFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static BusStopDetailFragment newInstance(int sectionNumber) {
        BusStopDetailFragment fragment = new BusStopDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;

        switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
            case 1:
                //return "Details";
                rootView = inflater.inflate(R.layout.fragment_bus_stop_detail_details, container, false);
                break;
            case 2:
                //return "Schedule";
                rootView = inflater.inflate(R.layout.fragment_bus_stop_detail_schedule, container, false);
                break;
            //case 3:
                //return "Nearby";
                //rootView = inflater.inflate(R.layout.fragment_bus_stop_detail_details, container, false);
                //break;
            default:
                rootView = inflater.inflate(R.layout.fragment_bus_stop_detail_details, container, false);
        }

        Intent intent = getActivity().getIntent();
        RouteProperties routeProperties = new RouteProperties();
        ((BusStopDetail) getActivity()).setStop(routeProperties.getStopsMap().get(intent.getStringExtra("name")),getArguments().getInt(ARG_SECTION_NUMBER),rootView);

        return rootView;
    }
}
