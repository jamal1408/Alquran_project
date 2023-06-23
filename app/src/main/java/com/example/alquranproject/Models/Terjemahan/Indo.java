package com.example.alquranproject.Models.Terjemahan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Indo{

	@SerializedName("translations")
	private List<TranslationsItem> translations;

	@SerializedName("meta")
	private Meta meta;

	public Indo() {
	}

	public List<TranslationsItem> getTranslations(){
		return translations;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"Indo{" + 
			"translations = '" + translations + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}