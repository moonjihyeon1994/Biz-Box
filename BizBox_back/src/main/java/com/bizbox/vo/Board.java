package com.bizbox.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private int bidx;
	private String btitle;
	private String bauthor;
	private String bdate;
	private String bboard;
	private int bwatch;
}
