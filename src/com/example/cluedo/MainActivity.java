
package com.example.cluedo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class MainActivity extends Activity {
	public final static String EXTRA_NAMES_LIST = "com.example.cluedo.NAMES_LIST";
	public final static String EXTRA_ACTIVE_LIST = "com.example.cluedo.ACTIVE_LIST";
	public final static String EXTRA_PLAYER_ID = "com.example.cluedo.PLAYER_ID";
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*Spinner spinner = (Spinner) findViewById(R.id.playerSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.character_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		*/
		setContentView(R.layout.activity_main);
		
		//ArrayList<String> player_names = new ArrayList<String>();
		//ArrayList<Boolean> player_active = new ArrayList<Boolean>();
		//int my_number = 0;
		
		Spinner spinner = (Spinner) findViewById(R.id.playerSpinner);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout1);
		
		Resources res = getResources();
		String[] data = res.getStringArray(R.array.character_array);
		
		//int[] image_data = getResources().getIntArray(R.array.icon_array);
		TypedArray icons = res.obtainTypedArray(R.array.icon_array);
		
		
		View new_layout;
		
		for (int i = 0; i < data.length; i++) {
			LayoutInflater inflater =
				    (LayoutInflater)this.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
			new_layout = inflater.inflate( R.layout.add_player_list_object, null );
			new_layout.setId(i);
			
			EditText edit = (EditText) new_layout.findViewById(R.id.editTextP);
			edit.setHint(data[i]);
			//edit.setId(0);
			
			//CheckBox box = (CheckBox) new_layout.findViewById(R.id.checkBoxP);
			//box.setActivated(true);
			//box.setId(1);
			
			ImageView image = (ImageView) new_layout.findViewById(R.id.imageViewP);
			image.setImageDrawable(icons.getDrawable(i));
			
			layout.addView(new_layout);
		}
		icons.recycle();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void continueToGame(View view) {
		// Looppi jossa haetaan kaikki valuet mit� k�ytt�j� on antanut
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Boolean> active = new ArrayList<Boolean>();
		int playerid;
		Resources res = getResources();
		int sum = res.getStringArray(R.array.character_array).length;
		LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout1);
		for (int i = 0; i < sum; i ++) {
			LinearLayout vlayout = (LinearLayout) layout.findViewById(i);
			String jes = ((EditText) vlayout.findViewById(R.id.editTextP)).getText().toString();
			if (!jes.equals(""))
				names.add( jes );
			active.add( ((CheckBox) vlayout.findViewById(R.id.checkBoxP)).isChecked() );
			
		}
		// Haetaan viel� vetolaatikon data
		Spinner spinner = (Spinner) findViewById(R.id.playerSpinner);
		playerid = spinner.getSelectedItemPosition();
		Intent intent = new Intent(this, AddCardsActivity.class);
		
		intent.putExtra(EXTRA_NAMES_LIST, names);
		intent.putExtra(EXTRA_ACTIVE_LIST, active.toArray());
		intent.putExtra(EXTRA_PLAYER_ID, playerid);
		startActivity(intent);
	}
	
	
}
