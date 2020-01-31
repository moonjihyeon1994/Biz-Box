package com.bizbox.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessLank implements Comparable<BusinessLank> {
	String name;
	int value;
	int salespderstore;
	int salestotal;
	int businesschane;
	int businesstiming;
	int Maincustomer;
	
	@Override
	public int compareTo(BusinessLank o) {
		if(this.salespderstore==o.salespderstore) {
			return Integer.compare(o.value, this.value);
		}
		return Integer.compare(o.salespderstore, this.salespderstore);
	}
	
}
