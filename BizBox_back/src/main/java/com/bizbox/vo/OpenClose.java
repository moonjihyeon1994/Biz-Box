package com.bizbox.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenClose {
	// 상권 이름
	private String location;
	// 중분류 이름
	private String middleCategory;
	// 폐업 가게수
	private int closeCnt;
	// 개업 가게수
	private int openCnt;
	// 전체 가게수
	private int totalStart;
	// 전체 가게수
	private int totalEnd;
}
