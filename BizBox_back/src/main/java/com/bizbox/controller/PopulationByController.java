package com.bizbox.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizbox.Service.PopulationByService;
import com.bizbox.apis.JusoApi;
import com.bizbox.vo.PopulationByDong;
import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.PopulationByTime;
import com.bizbox.vo.PopulationBytimeByDongCode;



@CrossOrigin({ "*" })
@RestController
@RequestMapping("/population")
public class PopulationByController {
	private final int max=11715962;
	private final int avgmax=5073881;
	private final int avg=1568200;
	private final int avgmin=783098;
	private final int min=2004;
	
	@Autowired
	PopulationByService service;
	
	@GetMapping("/getPopulationByTime/{address}")
	public ResponseEntity<Object> getPopulationByTime(@PathVariable String address){
		PopulationByTime pbt;
		int Point=0;
		try {
			JSONObject jsonObject = new JSONObject();
			pbt = service.populationByTime(address);
			if(pbt==null) {
				JusoApi api = new JusoApi();
				String predoroname=api.getAddressByName(address).split(",")[11];
				String[] string=predoroname.split(" ");
				String doroname = string[2];
				System.out.println(doroname);
				int size=doroname.length();
				for(int i=0; i<size-1; i++) {
				pbt = service.populationByTime(doroname);
				if(pbt!=null)break;
				doroname=doroname.substring(0, size-1-i);
				}
				if(pbt==null) {//끝까지 정보 없으면 address테이블에서 동코드 가져옴
					System.out.println("hihihi");
					
					List<PopulationBytimeByDongCode> list= new LinkedList<PopulationBytimeByDongCode>();
					predoroname=api.getAddressByName(address).split(",")[5];
					String realname=predoroname;
					System.out.println(predoroname);
					String dongcode = service.getDongCodeList(predoroname);
					while(dongcode==null && predoroname.length()>0 ) {
						predoroname=predoroname.substring(0, predoroname.length()-2);
						dongcode = service.getDongCodeList(predoroname);
					}
					dongcode=dongcode.substring(0, dongcode.length()-2);
					System.out.println(dongcode);
					list=service.getTimeByDongCode(dongcode);
					double j = 0,k= 0,l= 0,m= 0,n= 0,o= 0;
					String J = null,K= null,L= null,M= null,N= null,O= null,P= null;
					for(int i=1;  i<7;  i++) {J=(j+Double.parseDouble(list.get(i).getD()))+"";}
					for(int i=7;  i<12; i++) {K=(k+Double.parseDouble(list.get(i).getD()))+"";}
					for(int i=12; i<15; i++) {L=(l+Double.parseDouble(list.get(i).getD()))+"";}
					for(int i=15; i<18; i++) {M=(m+Double.parseDouble(list.get(i).getD()))+"";}
					for(int i=18; i<22; i++) {N=(n+Double.parseDouble(list.get(i).getD()))+"";}
					for(int i=22; i<24; i++) {O=(o+Double.parseDouble(list.get(i).getD()))+"";}
					pbt = new PopulationByTime("", "", "", "", "",realname, "", "", "", J, K, L, M, N, O, "", "", "", "", "","", "");
					System.out.println(list.get(0).getA());
					jsonObject.put("pbt", pbt);
					jsonObject.put("point", Point);
					return new ResponseEntity<Object>(jsonObject,HttpStatus.OK);
				}
			}
			jsonObject.put("pbt", pbt);
			jsonObject.put("point", Point);
			if(pbt==null) {return new ResponseEntity<Object>("해당주소의 데이터가 존재하지 않습니다.",HttpStatus.NOT_FOUND);}
			return new ResponseEntity<Object>(jsonObject,HttpStatus.OK);
		} catch (Exception e) {
			
			e.printStackTrace();
			return new ResponseEntity<Object>("해당주소가 존재하지않습니다.", HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/getPopulationByLocation/{address}")
	public ResponseEntity<Object> getPopulationByLocation(@PathVariable String address){
		PopulationByLocation pbl;
		int Point=0;
		try {
			JSONObject jsonObject = new JSONObject();
			pbl = service.populationByLocation(address);
			if(pbl==null) {
				JusoApi api = new JusoApi();
				String[] sp= api.getAddressByName(address).split(",");
				String predoroname=sp[sp.length-1];
				System.out.println(predoroname);
				String[] string=predoroname.split(" ");
				String doroname = string[2];
				System.out.println(doroname);
				int size=doroname.length();
				for(int i=0; i<size-1; i++) {//처음 도로명 데이터 없으면 가장 가까운 상권 정보 알려주고
					pbl = service.populationByLocation(doroname);
				if(pbl!=null)break;
				doroname=doroname.substring(0, size-1-i);
				}
				if(pbl==null) {//끝까지 정보 없으면 address테이블에서 동코드 가져옴
					System.out.println("hihihi");
					predoroname=api.getAddressByName(address).split(",")[5];	
					String realname=predoroname;
					System.out.println(predoroname);
					String dongcode = service.getDongCodeList(predoroname);
					
					while(dongcode==null && predoroname.length()>0 ) {
						predoroname=predoroname.substring(0, predoroname.length()-2);
						dongcode = service.getDongCodeList(predoroname);
					}
					dongcode=dongcode.substring(0, dongcode.length()-2);
					System.out.println(dongcode);
					pbl=service.getByDongCode(dongcode);
					pbl.setF(realname);
					System.out.println(pbl.getA());
					jsonObject.put("pbl", pbl);
					jsonObject.put("point", Point);
					return new ResponseEntity<Object>(jsonObject,HttpStatus.OK);
				}
			}
			jsonObject.put("pbl", pbl);
			jsonObject.put("point", Point);
			if(pbl==null) {return new ResponseEntity<Object>("해당주소의 데이터가 존재하지 않습니다.",HttpStatus.NOT_FOUND);}
			return new ResponseEntity<Object>(jsonObject,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/getPopulationByDong/{address}")
	public ResponseEntity<Object> getPopulationByDong(@PathVariable String address){
		List<PopulationByDong> pbd;
		int Point=0;
		try {
			JSONObject jsonObject = new JSONObject();
			pbd = service.populationByDong(address);
			System.out.println(pbd);
			jsonObject.put("pbd", pbd);
			jsonObject.put("point", Point);
			return new ResponseEntity<Object>(jsonObject,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
}
