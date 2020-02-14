package com.bizbox.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {
	private String email;
	private String store_name;
	private String category_large;
	private String category_middle;
	private String category_small;
	private String lat; //짧은거
	private String lot;
	private String flag;
	private String open_date;
	private String close_date;
}
