package com.pierre.regex;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class HrefMatch {

	/**
	 * this program displays all URLs in a web page by matching a regular expression that describes the <a href=...> HTML tag. 
	 * start the program as <br>
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//get URL string from command line or use default
			String urlString;
			if (args.length > 0) {
				urlString = args[0];
			}else {
				urlString = "https://www.oracle.com/technetwork/java/index.html";
			}
			
			//open reader for URL
			InputStreamReader inputStreamReader = new InputStreamReader(new URL(urlString).openStream());
			
			//read contents into string builder
			StringBuilder stringBuilder = new StringBuilder();
			int ch;
			while ((ch = inputStreamReader.read()) != -1) {
				stringBuilder.append((char)ch);
			}
			
			//search for all occurrences of pattern
			String patternString = "<a\\s+href\\s*=\\s*(\"[^\"]*\"[^\\s>]*)\\s*>";
			Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(stringBuilder);
			
			while (matcher.find()) {
				int start = matcher.start();
				int end = matcher.end();
				String match = stringBuilder.substring(start, end);
				System.out.println(match);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PatternSyntaxException e) {
			e.printStackTrace();
		}
	}

}
