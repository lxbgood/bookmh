package com.book.dfapp.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class IndexMode { 
		private  int code;
		private ArrayList<BookMhMode> mhlist=new ArrayList<BookMhMode>();
		private ArrayList<BookMode> booklist=new ArrayList<BookMode>();
	private ArrayList<GameMhMode> gameMhModeArrayList=new ArrayList<GameMhMode>();
		public IndexMode(JSONObject obj) {
			if (null == obj)
				return;
			try {
				 
				code=obj.optInt("code");
			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONArray jsonArray=obj.optJSONArray("mhlist");
			if(jsonArray!=null)
			{
				for (int i = 0; i < jsonArray.length(); i++) {
					BookMhMode bookMhMode=new BookMhMode(jsonArray.optJSONObject(i));
					mhlist.add(bookMhMode);
				}
			}
			JSONArray newjsonArray=obj.optJSONArray("booklist");
			if(newjsonArray!=null)
			{
				for (int i = 0; i < newjsonArray.length(); i++) {
					BookMode bookMode=new BookMode(newjsonArray.optJSONObject(i));
					booklist.add(bookMode);
				}
			}
			JSONArray gamejsonArray=obj.optJSONArray("gamelist");
			if(gamejsonArray!=null)
			{
				for (int i = 0; i < gamejsonArray.length(); i++) {
					GameMhMode gameMhMode=new GameMhMode(gamejsonArray.optJSONObject(i));
					gameMhModeArrayList.add(gameMhMode);
				}
			}
		}
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public ArrayList<BookMhMode> getMhlist() {
			return mhlist;
		}
		public void setMhlist(ArrayList<BookMhMode> mhlist) {
			this.mhlist = mhlist;
		}
		public ArrayList<BookMode> getBooklist() {
			return booklist;
		}
		public void setBooklist(ArrayList<BookMode> booklist) {
			this.booklist = booklist;
		}

	public ArrayList<GameMhMode> getGameMhModeArrayList() {
		return gameMhModeArrayList;
	}

	public void setGameMhModeArrayList(ArrayList<GameMhMode> gameMhModeArrayList) {
		this.gameMhModeArrayList = gameMhModeArrayList;
	}
}
