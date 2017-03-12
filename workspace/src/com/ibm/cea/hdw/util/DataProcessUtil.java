package com.ibm.cea.hdw.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataProcessUtil {

	// 把wmstat按照键值对存放到map中
	public static Map<String, String> genVmstatMap(String key, String value) {
		Map<String, String> vmstatMap = new HashMap<String, String>();
		key = key.trim();
		value = value.trim();
		String[] keyArray = key.split("\\s+"); // 正则表达式\s表示匹配任何空白字符，+表示匹配一次或多次。
		String[] valueArray = value.split("\\s+");
		if (keyArray.length == valueArray.length) {
			for (int i = 0; i < keyArray.length; i++) {
				vmstatMap.put(keyArray[i], valueArray[i]);
				System.out.println("Key:" + keyArray[i] + " value:"
						+ vmstatMap.get(keyArray[i]));
			}
		}
		return vmstatMap;
	}

	// 把memory按照键值对存放到map中
	public static Map<String, String> genMemMap(String key, String value) {
		Map<String, String> memMap = new HashMap<String, String>();
		key = key.trim();
		value = value.trim();
		String[] keyArray = key.split("\\s+");
		String[] valueArray = value.split("\\s+");
		if (keyArray.length + 1 == valueArray.length) {
			for (int i = 0; i < keyArray.length; i++) {
				memMap.put(keyArray[i], valueArray[i + 1]);
				System.out.println("Key:" + keyArray[i] + " value:"
						+ memMap.get(keyArray[i]));
			}
		}
		return memMap;
	}
	
	// 把disk space information按照键值对存放到map中
	public static Map<String, String> genDiskInfoMap(String key, String value) {
		Map<String, String> diskInfoMap = new HashMap<String, String>();
		key = key.trim();
		value = value.trim();
		String[] keyArray = key.split("\\s+");
		String[] valueArray = value.split("\\s+");
		if (keyArray.length - 1 == valueArray.length) {
			for (int i = 0; i < valueArray.length; i++) {
				diskInfoMap.put(keyArray[i], valueArray[i]);
				System.out.println("Key:" + keyArray[i] + " value:"
						+ diskInfoMap.get(keyArray[i]));
			}
		}
		return diskInfoMap;
	}
	
	public static String getProcessStatus(String[] data){
		String status = null;
		int columnNum = 2;//S
		for(String dataElement:data){
			dataElement = dataElement.trim();
			System.out.println("dataElement:"+dataElement);			
			String[] singleProgressArray = dataElement.split("\\s+");
			status = (singleProgressArray[columnNum]);
			break;
		}
		return status;
		
	}
	
	//get total memery of the selected progress
	public static int calculateTatolMemory(String[] data){
		int columnNum = 4;//rsz
		int sum = 0;
		for(String dataElement:data){
			dataElement = dataElement.trim();
			System.out.println("dataElement:"+dataElement);
			String[] singleProgressArray = dataElement.split("\\s+");
			int memery = normalizeStringToInt(singleProgressArray[columnNum]);
			sum = sum + memery;
		}
		return sum;
		
	}

	public static Long normalizeStringToLong(String input) {
		Pattern pattern = Pattern.compile("[^0-9]");
		Matcher matcher = pattern.matcher(input);
		String output = matcher.replaceAll("");
		return Long.parseLong(output);
	}

	public static int normalizeStringToInt(String input) {
		Pattern pattern = Pattern.compile("[^0-9]");
		Matcher matcher = pattern.matcher(input);
		String output = matcher.replaceAll("");
		return Integer.parseInt(output);
	}

	public static String normalizeUrl(String input) {
		String output = input.replace("\n", "");
		return output;
	}

	public static String[] splitString(String inputString){
	    String[] stringArray = inputString.split("\n");
        return stringArray;	    
	}

}
