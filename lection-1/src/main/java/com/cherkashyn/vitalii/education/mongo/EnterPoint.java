package com.cherkashyn.vitalii.education.mongo;

import java.util.ArrayList;
import java.util.List;

public class EnterPoint {

	private final static List<String> NAMES=new ArrayList<String>(){
		{
			this.add("value");
			System.out.println(EnterPoint.NAMES);
		}
	};

	public static void main(String[] args){
		System.out.println("OK");
	}
}
