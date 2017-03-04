package com;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		String data = "";
		int resultLength = 0;
		int order = 0;
		
		Scanner s = new Scanner(System.in);
		System.out.println("Input the training text file location:");
		//Get the parameters
		try {
			data = getFile(s.nextLine());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		
		System.out.println("Input the order:");
		order = s.nextInt();
		System.out.println("Input the number of orders:");
		resultLength = s.nextInt();
		s.close();
		
		//Find the mappings
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		for (int i = 0; i < data.length() - (order * 2); i += order) {
			String key = data.substring(i, i + order);
			String val = data.substring(i + order, i + (order * 2));
			List<String> vals = map.get(key);
			if (vals == null) {
				vals = new ArrayList<String>();
				vals.add(val);
				map.put(key, vals);
			} else {
				vals.add(val);
			}
		}
		
		Random r = new Random();
		List<String> startList = new ArrayList<String>(map.keySet());
		
		StringBuilder toPrint = new StringBuilder(startList.get(r.nextInt(startList.size())));
		String lastOrder = toPrint.toString();
		for (int i = 1; i < resultLength; i++) {
			List<String> vals = map.get(lastOrder);
			lastOrder = vals.get(r.nextInt(vals.size()));
			toPrint.append(lastOrder);
		}
		System.out.println(toPrint);
	}
	
	public static String getFile(String file) throws IOException {
		StringBuilder contentBuilder = new StringBuilder();
	    BufferedReader in = new BufferedReader(new FileReader(file));
	    String str;
	    while ((str = in.readLine()) != null) {
	        contentBuilder.append(str);
	    }
	    in.close();
		String content = contentBuilder.toString();
		return content;
	}
}