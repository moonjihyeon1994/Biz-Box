package com.bizbox.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopulationByBusiness {
	private String stdr_yr_cd;
	private String stdr_qu_cd;
	private String trdar_cd;
	private String trdar_cd_nm;
	private String total_mv_co;
	private String total_bz_co; 
	private String total_co;
}
