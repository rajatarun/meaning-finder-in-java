package tarun.practice;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.modelmbean.XMLParseException;
import javax.xml.bind.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.test.JSONAssert;
import net.sf.json.util.JSONBuilder;

public class Practice1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String a = "D:\\java\\story1.txt";	
		File file = new File(a);	//creating a new File object file
		try {
			FileReader fis = new FileReader(file); //creating a new file reader object fis
			BufferedReader bis = new BufferedReader(fis); // creating a new budderedreader object in bis
			//DataInputStream dis = new DataInputStream(bis);
			
			ArrayList<String[]> novel = new ArrayList<String[]>(); // creating a arraylist novel to insert meanings
			
			String line = null;
			int index = 0;
			//looping over the lines in novel
			//splitting the line and storing the words into a string array b
			//looping over the words and adding them to novel arraylist
			while((line = bis.readLine()) != null){ // reading each line till end of the novel
				if(line.isEmpty() == false) { 
				String [] b = line.trim().toLowerCase().split(" ");
				for(int k = 0; k < b.length && b[k].matches("^[A-Za-z]*") ; k++){
				novel.add(index,b[k].trim().split(","));
				index++;
				}

				}
			}
			
			
			//Spliterator<String[]> novel1 = novel.spliterator();
			String novel1 = Arrays.deepToString(novel.toArray());
			String[] ab = novel1.split("[^a-z]");
			// passing string array of words and a search letter to getwords method
			getwords(ab,args[0]);
			//System.out.println(Arrays.deepToString(ab));
//			for (int i = 0; i < ab.length; i++) {
//				
//			
//			System.out.println(ab[i]);}
//			//System.out.println(novel;);
//			System.out.println(novel.size());
//			System.out.println(ab.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void getwords(String[] ab, String searchkey) {
		// TODO Auto-generated method stub
		ArrayList<String> finallist = new ArrayList<String>();
		int j = 0;
		int k = 0;
		//use this for loop to search for words starting with search key
		
//		for (int i = 0; i < ab.length; i++) {
//			//char letter = (char)(i+97);
//			
//			if(ab[i].startsWith(searchkey)){
//					
//					finallist.add(j,ab[i]);
//					j++;
//			}
//		}
		
		//comment this for loop if you want to use the above loop
		//looping through the words and searching for words starting with a-z in each loop
		
		for(k=97;k<122;k++){
		for (int i = 0; i < ab.length; i++) {
			//char letter = (char)(i+97);
			
			if(ab[i].startsWith(Character.toString((char)(k)))){
					//indexing the words and adding into array list
					finallist.add(j,ab[i]);
					j++;
			}
		}
		}
		//System.out.println(Arrays.deepToString(finallist.toArray()));
		//passing final list to get mean method to get meanings from dictionary API
		getmean(finallist);
	}
//getmean method access yandex dictionary API with  key and gets response in XML format
//XML is parsed using dbuilder 
//meanings are printed out 
// if the response has no meaning attribute it prints no meaning 
	private static void getmean(ArrayList<String> finallist) {
		// looping over all the words in the finallist arraylist
		
		for (int i = 0; i < finallist.size(); i++) {
		String s = "https://dictionary.yandex.net/api/v1/dicservice/lookup?key=dict.1.1.20150924T212235Z.54015ab19bfd428d.aa1301af93842a68b2879add6be5f2dffbf888b9&lang=en-ru&text="+finallist.get(i);
		try {
			URL url = new URL(s);
			Scanner scan = new Scanner(url.openStream());
		    String str = new String();
		   // FileWriter file = new FileWriter("D:\\input.xml");
		    while (scan.hasNext())
		        str += scan.nextLine();
		    scan.close();
		  
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(str.getBytes("utf-8"))));
			//System.out.println(str);
			System.out.println(finallist.get(i));
			try{
			System.out.println("meaning:"+doc.getElementsByTagName("mean").item(0).getTextContent());
			}catch(NullPointerException n){
				System.out.println("cant find meaning");
			}
			
		
		} catch (IOException | SAXException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}

	

}
