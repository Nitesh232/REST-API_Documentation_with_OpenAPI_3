package com.nitesh.springboot.constants;

public class ConstantVariables {
	
	private static final char[] charArray = new char[] {'a', 'c', 'e', 'g', 'i', 'k', 'm', 'o', 'q', 's', 'u', 'w', 'y'};
	
	public boolean isInitialPresent(char initialNameChar) {
		
		for (char x : charArray) {
	        if (x == initialNameChar) {
	            return true;
	        }
	    }
	    return false;
	}

}
