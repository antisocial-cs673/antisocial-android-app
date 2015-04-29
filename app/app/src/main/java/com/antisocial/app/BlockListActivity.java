package com.antisocial.app;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.Button;

import com.antisocial.app.adapter.AppListAdapter;
import com.antisocial.app.util.BlockUtils;
import java.util.Calendar;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;


public class BlockListActivity extends ListActivity {

	private AppListAdapter mAdapter = null;
    static final int TIME_DIALOG_ID = 999;
    private TimePicker timePicker1;
    private Button btnChangeTime;

    public static int hour=0;
    public static int minute=0;
    public static int diff = 0;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_applist);
           addListenerOnButton();

        List<PackageInfo> appList = getPackageManager().getInstalledPackages(0);
		List<PackageInfo> installedList = new ArrayList<PackageInfo>();

		for (PackageInfo packageInfo : appList) {
			
			if ( !isSystemPackage(packageInfo) && !getApplicationInfo().packageName.equals(packageInfo.packageName)) {
				installedList.add(packageInfo);
			}
		}

		mAdapter = new AppListAdapter(this, installedList, BlockUtils.getBlockList(getApplicationContext()));
		setListAdapter(mAdapter);
	}

	private boolean isSystemPackage(PackageInfo packageInfo) {
		return ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true
				: false;
	}

    public void addListenerOnButton() {

       btnChangeTime = (Button) findViewById(R.id.btnChangeTime);

        btnChangeTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);

            }

        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                // set time picker as current time
                          return new TimePickerDialog(this,
                        timePickerListener, hour, minute,true);

        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;

                    // set current time into textview
                  //  tvDisplayTime.setText(new StringBuilder().append(pad(hour))
                    //        .append(":").append(pad(minute)));

                    // set current time into timepicker
                  //  timePicker1.setCurrentHour(hour);
                   // timePicker1.setCurrentMinute(minute);
                    TextView st = (TextView) findViewById(R.id.btnChangeTime);
                   if(minute<10) {
                       st.setTextSize(45);
                       st.setText(hour + ":" +"0"+ minute );
                   }else{
                       st.setTextSize(45);
                       st.setText(hour + ":" + minute );
                   }
                    int hour1 =hour * 60;
                    int minute1 = minute;
                    diff = (hour1 + minute1);
                }
            };



}