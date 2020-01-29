package com.bizbox.utils;

public class AddressUtil {

	
	public String RemoveNumber(String address) {
		
		String removedAddress =address.replaceAll("[0-9]", "");
		System.out.println(removedAddress.substring(removedAddress.length()-1,removedAddress.length()));
		if(removedAddress.substring(removedAddress.length()-1,removedAddress.length()).contentEquals("길")) {
			removedAddress=removedAddress.substring(0, removedAddress.length()-1);
		}
		System.out.println(address+" "+ "==>" +" "+removedAddress);
		
		return removedAddress;
	}
	
}
