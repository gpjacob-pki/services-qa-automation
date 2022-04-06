package test.Smoke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import test.Utility.BaseClass;
import test.Utility.ExcelReader;

public class dummy  extends BaseClass{

	@Test
	public void dummy1() throws IOException, InvalidFormatException {
		
//		int rowNumber=3;
//		ExcelReader reader = new ExcelReader();
//		List<Map<String,String>> testData = 
//				reader.getData(testdatasheetpathxlsx, "Experiment");
//		
//		String user = testData.get(rowNumber).get("user");
//		String usertype = testData.get(rowNumber).get("usertype");
//		String Template = testData.get(rowNumber).get("Template");
//		String Internal = testData.get(rowNumber).get("Internal field");
//		
//		System.out.println("user is "+user);
//		System.out.println("usertype is "+usertype);
//		System.out.println("Template is "+Template);
//		System.out.println("Internal is "+Internal);
		
		
	
		     String val="sarath";
		     
		     char[] val2=val.toCharArray();
		    
		   
		    HashSet<Character> set = new HashSet<>();   
		
		for(int i=0;i<val2.length;i++){
		    set.add(val.charAt(i));
	
		
		}
		for(Character ch : set) {  
            System.out.print(ch);  
		}
		
		
		
		
		StringBuilder input1 = new StringBuilder();
		 
        // append a string into StringBuilder input1
        input1.append(val);
 
        // reverse StringBuilder input1
        input1.reverse();
        
 
        // print reversed String
        System.out.println("**"+input1);
        
        for(int i=0;i<val2.length;i++){
		    for(int j=1;j<val2.length;j++) {
		    	if((val2[i]==val2[j]) && (i==val2.length-1)) {
		    		input1.deleteCharAt(j);
		    	}
		    }
        	
    		
		   
		}
        System.out.println("Pakka code:"+input1);
		
		
	}
	
}






