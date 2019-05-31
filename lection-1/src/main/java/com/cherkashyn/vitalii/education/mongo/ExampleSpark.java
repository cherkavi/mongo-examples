package com.cherkashyn.vitalii.education.mongo;

import spark.Request;
import spark.Response;
import spark.Route;																																																																																					
import spark.Spark;


public class ExampleSpark {
	public static void main( String[] args )
	{

		Spark.get(new Route("/echo"){
			@Override
			public Object handle(Request request, Response response) {
				String message=request.queryParams("message");
				if(message==null){
					return "argument 'message' should be specified";
				}
				return message;
			}
		});

	}

}
