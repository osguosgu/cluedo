
package com.example.cluedo;

import java.util.ArrayList;

import android.widget.ArrayAdapter;

public class GameLogic {

	
	ArrayList<String> names;
	boolean[] active;
	int playerid, card_amount;
	ArrayAdapter<String> logAdapter;
	ArrayList<Integer> cards;
	
	ArrayList<LogItem> log;
	
	GridStatus[][] grid;
	
	public GameLogic(ArrayList<String> names, boolean[] active, ArrayList<Integer> cards, int playerid, int card_amount){
		System.out.println(names);
		System.out.println(active);
		System.out.println(cards);
		System.out.println(playerid);
		this.names = names;
		this.active = active;
		this.cards = cards;
		this.playerid = playerid;
		this.card_amount = card_amount;
		log = new ArrayList<LogItem>();
		this.updateSheetData();
	}
	
	public ArrayList<String> getNamesArrayList(){
		return new ArrayList<String>(this.names);
	}
	
	public ArrayList<String> getLogArrayList() {
		ArrayList<String> r = new ArrayList<String>();
		for (int i = 0; i < this.log.size(); i++ ) {
			r.add(this.log.get(i).getString());
		}
		return r;
	}

	public void removeFromLog(int index){
		this.log.remove(index);
	}
	
	public void addInput(int asker, int answerer, int room_card, int weapon_card, int character_card) {
		if (answerer >= this.names.size())
			return;
		this.log.add(new LogItem(asker, answerer, room_card, weapon_card, character_card));
	}
	
	public void addKnownCard(int id) {
		this.log.add(new LogItem(id));
	}
	
	public void updateSheetData() {
		// This needs to be called so that getDataAt returns things that are up to date
		grid = new GridStatus[this.card_amount][this.names.size()];
		for (int i = 0; i < this.card_amount; i++){
			for (int j = 0; j < this.names.size(); j++) {
				grid[i][j] = new GridStatus();
			}
		}
		int guess_id = 0;
		for (LogItem i : log) {
			if (i.type == 0) {
				if (i.answerer == this.playerid){
					grid[i.known_card][i.asker].is_known = true;  
				}
			}
			if (i.type == 1) {
				for (int j = 0; j < this.names.size(); j ++) {
					grid[i.known_card][j].is_known = true;
				}
			}
		}
	}
	
	public int getDataAt(int card_id, int player_id) {
		System.out.println(this.grid);
		if (this.grid[card_id][player_id].is_known)
			return 1;
		return 0;
	}
	
	
	public class LogItem {
		int type; // What type this log is, 0 for normal thing and 1 for just known card added
		// Things for type 0
		int asker, answerer;
		int room_card, weapon_card, character_card;
		// Things for type 1
		int known_card;
		public LogItem(int card) {
			type = 1;
			this.known_card = card;
		}
		public LogItem(int asker, int answerer, int room_card, int weapon_card, int character_card) {
			this.asker = asker;
			this.answerer = answerer;
			this.room_card = room_card;
			this.character_card = character_card;
			this.weapon_card = weapon_card;
		}
		public String getString() {
			if (type == 0) {
				return "Asked: " + this.room_card + this.weapon_card + this.character_card;
			}
			if (type == 1) {
				return "Known card id was: " + this.known_card;
			}
			return "Error";
		}
	}
	public class GridStatus {
		Boolean is_known;
		Boolean can_have;
		public GridStatus() {
			is_known = false;
			can_have = true;
		}
	}
}

