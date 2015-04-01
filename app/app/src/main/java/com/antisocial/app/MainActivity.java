package com.antisocial.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.antisocial.app.service.CoreService;
import com.antisocial.app.util.BlockUtils;
import com.antisocial.app.util.Logger;
import com.antisocial.app.util.ProfileUtils;

import java.util.Date;

public class MainActivity extends Activity implements
		ActionBar.OnNavigationListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
    private final int MAIN_DISPLAY_TIME = 1000;
    private AlertDialog alert;
    private TextView statusTextView;
    private int currentSelectedItem = 0;
    private String[] profileList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

        // Set up the action bar to show a dropdown list.
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        profileList = ProfileUtils.getProfiles(this);

         // Set up the dropdown list navigation in the action bar.
          actionBar.setListNavigationCallbacks(
          // Specify a SpinnerAdapter to populate the dropdown list.
                                new ArrayAdapter<String>(actionBar.getThemedContext(),
                                        android.R.layout.simple_list_item_1,
                                        android.R.id.text1, profileList), this);

        statusTextView = (TextView)findViewById(R.id.statusTextView);


        if(BlockUtils.isBlockServiceRunning(this, CoreService.class))
        {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            currentSelectedItem = pref.getInt(STATE_SELECTED_NAVIGATION_ITEM, 0);
            actionBar.setSelectedNavigationItem(currentSelectedItem);
            statusTextView.setText(R.string.active);
            MenuItem item = (MenuItem)findViewById(R.id.block_item);
            if(item != null){
                item.setIcon(R.drawable.ic_unlock);
            }
        }
	}


	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    //ActionBar events
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
            if(BlockUtils.isBlockServiceRunning(this, CoreService.class)) {
                alert = new AlertDialog.Builder(this)
                        .setTitle("Alert")
                        .setMessage("Block is running")
                        .setPositiveButton("OK", null)
                        .show();
                return true;
            }

            Intent intent = new Intent(this, BlockListActivity.class);
            startActivity(intent);

			return true;
		}
        else if(id == R.id.block_item)
        {
            Date tmp = new Date();
            tmp.setTime(-5000);
            if(CoreService.getStartedAt().getTime() < tmp.getTime())
            {
                Toast.makeText(MainActivity.this, "HI", Toast.LENGTH_LONG).show();
                return true;
            }
            if (BlockUtils.isBlockServiceRunning(this, CoreService.class)) {
                Intent intent = new Intent(this, CoreService.class);
                stopService(intent);
                item.setIcon(R.drawable.ic_lock);
                statusTextView.setText(R.string.not_active);
            } else {
                Intent intent = new Intent(this, CoreService.class);
                startService(intent);
                item.setIcon(R.drawable.ic_unlock);

                statusTextView.setText(R.string.active);
            }
        }

		return super.onOptionsItemSelected(item);
	}

    //Profile events
	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		// When the given dropdown item is selected, show its contents in the
		// container view.

        if(BlockUtils.isBlockServiceRunning(this, CoreService.class))
        {
            alert = new AlertDialog.Builder(this)
                    .setTitle("Alert")
                    .setMessage("Block is running")
                    .setPositiveButton("OK", null)
                    .show();

            //reset to previous value
            getActionBar().setSelectedNavigationItem(currentSelectedItem);
        }
        else {
            BlockUtils.setCurrentMode(profileList[position]);
            currentSelectedItem = position;
        }


		/*getFragmentManager()
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();*/

		return true;
	}

    @Override
    public void onDestroy()
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar().getSelectedNavigationIndex());
        editor.commit();
        
        super.onDestroy();
    }
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		private Button startBlock, blockListBtn;
		
		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
			
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			TextView textView = (TextView) rootView
					.findViewById(R.id.section_label);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));

			startBlock = (Button) rootView.findViewById(R.id.start_block_btn);

			blockListBtn = (Button) rootView.findViewById(R.id.set_block_list);
				
			
			if(BlockUtils.isBlockServiceRunning(getActivity(), CoreService.class)){
				startBlock.setText(R.string.stop_block);
			}else{
				startBlock.setText(R.string.start_block);
			}
			
			startBlock.setOnClickListener(onListener);
			blockListBtn.setOnClickListener(onListener);

			return rootView;
		}

		private OnClickListener onListener = new OnClickListener() {

			@Override
			public void onClick(View view) {
				if (view == startBlock) {
					if (BlockUtils.isBlockServiceRunning(getActivity(),
							CoreService.class)) {
						Intent intent = new Intent();
						intent.setClass(getActivity(), CoreService.class);
						getActivity().stopService(intent);
						startBlock.setText(R.string.start_block);
					} else {
						Intent intent = new Intent();
						intent.setClass(getActivity(), CoreService.class);
						getActivity().startService(intent);
						startBlock.setText(R.string.stop_block);
					}
				} else if (view == blockListBtn) {
					Intent intent = new Intent();
					intent.setClass(getActivity(), BlockListActivity.class);
					getActivity().startActivity(intent);
				}
			}
		};
	}
}