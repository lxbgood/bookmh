package com.book.dfapp.util;

import org.json.JSONObject;

public class GameMhMode {

		private String id;
		private String author;
		private String jianjie;
		private String name;
		private String filedir;
		private int endnum;
		private String filename;
		private String coverpic;
		public GameMhMode(JSONObject obj) {
			if (null == obj)
				return;
			try {
			 setId(obj.optString("id"));
			 setAuthor(obj.optString("author"));
			 setJianjie(obj.optString("jianjie"));
			 setCoverpic(obj.optString("coverpic"));
			 setName(obj.optString("name"));
			 setFiledir(obj.optString("filedir"));
			 setEndnum(obj.optInt("endnum"));
			 setFilename(obj.optString("filename"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public String getJianjie() {
			return jianjie;
		}
		public void setJianjie(String jianjie) {
			this.jianjie = jianjie;
		}
		public String getCoverpic() {
			return coverpic;
		}
		public void setCoverpic(String coverpic) {
			this.coverpic = coverpic;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getFiledir() {
			return filedir;
		}
		public void setFiledir(String filedir) {
			this.filedir = filedir;
		}
		 
		public int getEndnum() {
			return endnum;
		}
		public void setEndnum(int endnum) {
			this.endnum = endnum;
		}
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}
		
 
}
