package com.bizbox.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point {
	private String lat;
	private String lot;
	private String Oplat;
	private String Oplot;
	private String distance;
	public Point(String lat, String lot, String distance) {
		this.lat = lat;
		this.lot = lot;
		this.distance = distance;
	}
}
