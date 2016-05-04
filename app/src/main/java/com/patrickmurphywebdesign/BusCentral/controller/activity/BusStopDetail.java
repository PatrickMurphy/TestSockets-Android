package com.patrickmurphywebdesign.BusCentral.controller.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.patrickmurphywebdesign.BusCentral.controller.Console;
import com.patrickmurphywebdesign.BusCentral.model.BusStop;
import com.patrickmurphywebdesign.BusCentral.model.BusStopSchedule;
import com.patrickmurphywebdesign.BusCentral.R;
import com.patrickmurphywebdesign.BusCentral.model.StopTime;

public class BusStopDetail extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private BusStop Stop;
    private int lastSection;
    private View RootView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_stop_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        }catch (Exception e) {
            System.out.println("MyActivity::MyMethod" + e.getMessage());
        }
    }

    public void setStop(BusStop stop, int section, View rootView){
        Stop = stop;
        this.lastSection = section;
        this.RootView = rootView;
        BusStopSchedule stopSchedule = new BusStopSchedule(stop);
        getSupportActionBar().setTitle(Stop.getName());
        try {
            switch (section) {
                case 1:
                    //return "Schedule";

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    LinearLayout.LayoutParams layoutParams_left_10 = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    LinearLayout.LayoutParams layoutParams_left_25 = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams_left_10.setMargins(10,0,0,0);
                    layoutParams_left_25.setMargins(5,0,0,0);
                    layoutParams.setMargins(0,6,0,6);
                    LinearLayout scheduleLinearLayout = (LinearLayout) rootView.findViewById(R.id.scheduleContainer);
                    int stopCount = 0;

                    scheduleLinearLayout.removeAllViews();

                    for(StopTime st : stopSchedule.getStops()){
                        LinearLayout StopRowLinearLayout = new LinearLayout(rootView.getContext());
                        StopRowLinearLayout.setPadding(6, 6, 6, 6);

                        if((stopCount++)%2 == 1){
                            StopRowLinearLayout.setBackgroundColor(Color.rgb(245, 245, 245));
                        }
                        StopRowLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

                        // image for cycle
                        ImageView routeDirectionImageDisplay = new ImageView(rootView.getContext());
                        if(st.isCC()) {
                            routeDirectionImageDisplay.setImageResource(R.drawable.cc);
                        }else{
                            routeDirectionImageDisplay.setImageResource(R.drawable.cw);
                        }
                        routeDirectionImageDisplay.setMaxWidth(32);
                        routeDirectionImageDisplay.setMaxHeight(32);

                        StopRowLinearLayout.addView(routeDirectionImageDisplay);

                        LinearLayout timeDirectionStackLinearLayout = new LinearLayout(rootView.getContext());
                        timeDirectionStackLinearLayout.setOrientation(LinearLayout.VERTICAL);

                        // text for exact time of stop
                        TextView stopTimeFormattedText = new TextView(rootView.getContext());
                        stopTimeFormattedText.setText(st.getFormattedTime());
                        stopTimeFormattedText.setTextColor(Color.DKGRAY);
                        stopTimeFormattedText.setTextSize(15);

                        // text for clock or counter clock
                        TextView routeDirectionTextDisplay = new TextView(rootView.getContext());
                        routeDirectionTextDisplay.setText(st.getDirection());
                        routeDirectionTextDisplay.setTextColor(Color.LTGRAY);

                        timeDirectionStackLinearLayout.addView(stopTimeFormattedText);
                        timeDirectionStackLinearLayout.addView(routeDirectionTextDisplay);

                        timeDirectionStackLinearLayout.setPadding(20,0,0,0);
                        StopRowLinearLayout.addView(timeDirectionStackLinearLayout);

                        // relative tiem
                        TextView relativeTimeToStopText = new TextView(rootView.getContext());
                        relativeTimeToStopText.setText(st.getRelativeTime());
                        relativeTimeToStopText.setTextColor(Color.rgb(196, 97, 0));
                        relativeTimeToStopText.setTextSize(21);
                        relativeTimeToStopText.setGravity(Gravity.RIGHT);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
                        params.weight = 1.0f;
                        params.gravity = Gravity.RIGHT;

                        relativeTimeToStopText.setLayoutParams(params);


                        StopRowLinearLayout.addView(relativeTimeToStopText);

                        scheduleLinearLayout.addView(StopRowLinearLayout, layoutParams);
                    }
                    break;
                case 2:
                    //return "Details";
                    TextView typeText = (TextView) rootView.findViewById(R.id.stop_type);

                    TextView addressText = (TextView) rootView.findViewById(R.id.stop_address);
                    addressText.setText("SampleAddress");
                    TextView descText = (TextView) rootView.findViewById(R.id.stop_description);
                    descText.setText("SampleDesc");
                    TextView timerText = (TextView) rootView.findViewById(R.id.stop_nextTimer);

                    if (Stop.getIsTimed()) {
                        typeText.setText("Stop Type: Timed Stop");
                    } else {
                        typeText.setText("Stop Type: On-Demand Stop");
                        addressText.setText("");
                        descText.setText("");
                    }


                    StopTime nextStopTime = stopSchedule.getNextTime();
                    timerText.setText(nextStopTime.getHour() + " : " + nextStopTime.getMinute());


                    break;
                //case 3:
                //return "Nearby";

                //break;
            }
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
        setStop(this.Stop, this.lastSection, this.RootView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bus_stop_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
