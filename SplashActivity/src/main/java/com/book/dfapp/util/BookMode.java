package com.book.dfapp.util;

import org.json.JSONObject;

public class BookMode { 
		 
		private String id;
		private String author;
		private String jianjie;
		private String coverpic; 
		private String name; 
		public BookMode(JSONObject obj) {
			if (null == obj)
				return;
			try {
			 setId(obj.optString("id"));
			 setAuthor(obj.optString("author"));
			 setJianjie(obj.optString("jianjie"));
			 setCoverpic(obj.optString("coverpic"));
			 setName(obj.optString("name"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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
		
 
}
