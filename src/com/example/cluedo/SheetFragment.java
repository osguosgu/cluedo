package com.example.cluedo;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class SheetFragment extends Fragment{ /*
	GameLogic logic;
	String[] characters, weapons, rooms;
	View inputView;
	TableLayout thistable;
	Boolean hidden;*/
	View inputView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Kutsuttiin on create viewiä sheet fragmentista");
		
		inputView = inflater.inflate(R.layout.tab_1_layout, container, false);


		updateTable();
		
		return inputView;
	}

	@Override
	public void onAttach(Activity activity) {
		System.out.println("HeiheiheiJASDFFJDSDFJKLJLADFKSDJKLÖJKLÖSDJLÖSDJKLÖSDFJÖ");
		super.onAttach(activity);
	}
	
	public void updateTable() {
		// Lets update gamelogic
		GameLogic logic = ((GameActivity) getActivity()).getLogic();
		logic.updateSheetData();
		
		// Get and clear the table
		TableLayout table = (TableLayout) inputView.findViewById(R.id.tableLayout1);
		table.removeAllViews();
		
		ArrayList<String> players = ((GameActivity) getActivity()).getLogic().getNamesArrayList();
		TableRow row;
		row = new TableRow(inputView.getContext());
		TextView t = new TextView(inputView.getContext());
		row.addView(t);
		
		
		for (int i = 0; i < players.size(); i++) {
			t = new TextView(inputView.getContext());
			t.setPadding(8,8,8,8);
			String str = players.get(i);
			if (str.length() > 3)
				t.setText(str.substring(0, 3));
			else
				t.setText(str);
			row.addView(t);
		}
		table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			
		Resources res = getActivity().getResources();
		String[] string;
		string = res.getStringArray(R.array.character_array);

		int counter = 0;
		for (int i = 0; i < string.length; i++) {
			row = new TableRow(inputView.getContext());
			t = new TextView(inputView.getContext());
			t.setText(string[i]);
			t.setPadding(8,8,8,8);
			row.addView(t);
			for (int j = 0; j < players.size(); j++) {
				ImageView ai = new ImageView(inputView.getContext());
				switch (logic.getDataAt(counter, j)) {
				case 0:
					ai.setImageResource(R.drawable.ob);
					break;
				case 1:
					ai.setImageResource(R.drawable.nb);
					break;
				case 2:
					ai.setImageResource(R.drawable.qb);
					break;
				}
				
				//t = new TextView(inputView.getContext());
				//t.setText(logic.getDataAt(counter, j));
				//t.setPadding(8,8,8,8);
				//row.addView(t);
				row.addView(ai);
			}
			row.setId(counter);
			counter += 1;
			table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
		string = res.getStringArray(R.array.weapon_array);
		for (int i = 0; i < string.length; i++) {
			row = new TableRow(inputView.getContext());
			t = new TextView(inputView.getContext());
			t.setText(string[i]);
			t.setPadding(8,8,8,8);
			row.addView(t);
			for (int j = 0; j < players.size(); j++) {
				ImageView ai = new ImageView(inputView.getContext());
				switch (logic.getDataAt(counter, j)) {
				case 0:
					ai.setImageResource(R.drawable.ob);
					break;
				case 1:
					ai.setImageResource(R.drawable.nb);
					break;
				case 2:
					ai.setImageResource(R.drawable.qb);
					break;
				}
				
				//t = new TextView(inputView.getContext());
				//t.setText(logic.getDataAt(counter, j));
				//t.setPadding(8,8,8,8);
				//row.addView(t);
				row.addView(ai);
			}
			row.setId(counter);
			counter += 1;
			table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
		string = res.getStringArray(R.array.room_array);
		for (int i = 0; i < string.length; i++) {
			row = new TableRow(inputView.getContext());
			t = new TextView(inputView.getContext());
			t.setText(string[i]);
			t.setPadding(8,8,8,8);
			row.addView(t);
			for (int j = 0; j < players.size(); j++) {
				ImageView ai = new ImageView(inputView.getContext());
				switch (logic.getDataAt(counter, j)) {
				case 0:
					ai.setImageResource(R.drawable.ob);
					break;
				case 1:
					ai.setImageResource(R.drawable.nb);
					break;
				case 2:
					ai.setImageResource(R.drawable.qb);
					break;
				}
				
				//t = new TextView(inputView.getContext());
				//t.setText(logic.getDataAt(counter, j));
				//t.setPadding(8,8,8,8);
				//row.addView(t);
				row.addView(ai);
			}
			row.setId(counter);
			counter += 1;
			table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
	}

}
