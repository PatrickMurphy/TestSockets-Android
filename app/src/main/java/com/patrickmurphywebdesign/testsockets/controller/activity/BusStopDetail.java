package com.patrickmurphywebdesign.testsockets.controller.activity;

import android.graphics.Color;
import android.provider.CalendarContract;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.Space;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.patrickmurphywebdesign.testsockets.model.BusStop;
import com.patrickmurphywebdesign.testsockets.model.BusStopSchedule;
import com.patrickmurphywebdesign.testsockets.R;
import com.patrickmurphywebdesign.testsockets.model.StopTime;

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
        BusStopSchedule bss = new BusStopSchedule(stop);
        getSupportActionBar().setTitle(Stop.getName());
        try {
            switch (section) {
                case 1:
                    //return "Details";
                    TextView typeText = (TextView) rootView.findViewById(R.id.stop_type);

                    if (Stop.getIsTimed()) {
                        typeText.setText("Stop Type: Timed Stop");
                    } else {
                        typeText.setText("Stop Type: On-Demand Stop");
                    }
                    TextView addressText = (TextView) rootView.findViewById(R.id.stop_address);
                    addressText.setText("SampleAddress");
                    TextView descText = (TextView) rootView.findViewById(R.id.stop_description);
                    descText.setText("SampleDesc");
                    TextView timerText = (TextView) rootView.findViewById(R.id.stop_nextTimer);

                    StopTime nextStopTime = bss.getNextTime();
                    timerText.setText(nextStopTime.getHour() + " : " + nextStopTime.getMinute());


                    break;
                case 2:
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
                    LinearLayout ll = (LinearLayout) rootView.findViewById(R.id.scheduleContainer);
                    int stopCount = 0;

                    for(StopTime st : bss.getStops()){
                        LinearLayout TempLL = new LinearLayout(rootView.getContext());
                        TempLL.setPadding(6,6,6,6);
                        if((stopCount++)%2 == 1){
                            TempLL.setBackgroundColor(Color.rgb(245,245,245));
                        }
                        TempLL.setOrientation(LinearLayout.HORIZONTAL);

                        LinearLayout innerTempLL = new LinearLayout(rootView.getContext());
                        innerTempLL.setOrientation(LinearLayout.VERTICAL);

                        // text for exact time of stop
                        TextView tv2 = new TextView(rootView.getContext());
                        tv2.setText(st.getFormattedTime());
                        tv2.setTextColor(Color.DKGRAY);
                        tv2.setTextSize(14);

                        // text for clock or counter clock
                        TextView tv3 = new TextView(rootView.getContext());
                        tv3.setText(st.getDirection());
                        tv3.setTextColor(Color.LTGRAY);

                        innerTempLL.addView(tv2);
                        innerTempLL.addView(tv3);

                        // image for cylce
                        ImageView iv = new ImageView(rootView.getContext());
                        if(st.isCC()) {
                            iv.setImageResource(R.drawable.cc);
                        }else{
                            iv.setImageResource(R.drawable.cw);
                        }
                        TempLL.addView(iv);
                        TempLL.addView(innerTempLL);

                        // relative tiem
                        TextView tv4 = new TextView(rootView.getContext());
                        tv4.setText(st.getFormattedTime());
                        tv4.setTextColor(Color.rgb(196,97,0));
                        tv4.setTextSize(22);

                        TempLL.addView(tv4);

                        ll.addView(TempLL, layoutParams);
                    }
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
