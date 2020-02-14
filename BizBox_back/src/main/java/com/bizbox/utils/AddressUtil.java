package com.bizbox.utils;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
@Service
public class AddressUtil {

	public String RemoveNumber(String address) {
		if(address.split("")[address.length()-1].equals("동")) {
			address=address.substring(0, address.length()-1);
		}
		int index = 0;
		Pattern p = Pattern.compile("[0-9]");

		String[] sp = address.split("");
		for (int i = 0; i < sp.length; i++) {
			if (sp[i].matches("[0-9]")) {
				index = i;
				break;
			}
		}
		if (index != 0) {
			String removedAddress = address.substring(0, index);
			return removedAddress;
		}

		return address;
	}
	
	public String RemoveDong(String address) {
		address=address.substring(0, address.length()-1);
		int index = 0;
		Pattern p = Pattern.compile("[0-9]");
		String[] sp = address.split("");
		for (int i = 0; i < sp.length; i++) {
			if (sp[i].matches("[0-9]")) {
				index = i;
				break;
			}
		}
		if (index != 0) {
			String removedAddress = address.substring(0, index);
			return removedAddress;
		}
		

		return address;
	}

}
