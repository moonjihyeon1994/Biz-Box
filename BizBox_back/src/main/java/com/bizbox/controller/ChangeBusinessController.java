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

import com.bizbox.Service.ChangeBusinessService;
import com.bizbox.Service.PopulationByService;
import com.bizbox.vo.Changebusiness;
import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.PopulationByTime;
import com.bizbox.utils.*;
import net.bytebuddy.dynamic.scaffold.MethodGraph.Linked;

import com.bizbox.apis.*;

@CrossOrigin({ "*" })
@RestController
@RequestMapping("/change")
public class ChangeBusinessController {
	@Autowired
	ChangeBusinessService service;
	
	@GetMapping("/getHistory/{dong}")
	public ResponseEntity<Object> getHistory(@PathVariable String dong){
		String predongcode="";
		int Point=0;
		List<Changebusiness> precblist = new LinkedList<Changebusiness>();
		List<Changebusiness> cblist = new LinkedList();
		int live[] = new int[6];
		int die[] = new int[6];
		int averL[] = new int[6];
		int averD[] = new int[6];
		int what[][]=new int[6][4];
		
		for(int i=0; i<6; i++) {
			cblist.add(new Changebusiness((2014+i)+""));
		}
		JusoApi api = new JusoApi();
		AddressUtil util = new AddressUtil();
		List<String> donglist = new LinkedList<String>();
		try {
			donglist=api.getDongSetByName(dong);
			for (String string : donglist) {
				System.out.println(string);
			}

			for (String string : donglist) {
				System.out.println("동이름:"+string);
				precblist = service.getChangeHistory(string);
				predongcode=string;
				if(!precblist.isEmpty())break;
			}
			if(precblist.isEmpty()) {
				predongcode="죄송합니다."+" \" "+predongcode+" \" "+"에 대한 데이터가 존재 하지않습니다.";
			}
			for (Changebusiness changebusiness : precblist) {
				int index=0;
			   live[Integer.parseInt(changebusiness.getA())-2014]+=
				    Integer.parseInt(changebusiness.getG());
				
				die[Integer.parseInt(changebusiness.getA())-2014]+=
					Integer.parseInt(changebusiness.getH());
				
			  averL[Integer.parseInt(changebusiness.getA())-2014]+=
					Integer.parseInt(changebusiness.getI());
				
			  averD[Integer.parseInt(changebusiness.getA())-2014]+=
					Integer.parseInt(changebusiness.getJ());
			  if(changebusiness.getF().equals("정체")) {index=1;}
			  if(changebusiness.getF().equals("다이나믹")) {index=2;}
			  if(changebusiness.getF().equals("상권확장")) {index=3;}
			  if(changebusiness.getF().equals("상권축소")) {index=0;}
			  what[Integer.parseInt(changebusiness.getA())-2014][index]++;

			}
			for(int i=0; i<6; i++) {
				Changebusiness changebusiness =cblist.get(i);
				changebusiness.setA((2014+i)+"");
				changebusiness.setG(live[i]/8+"");
				changebusiness.setH(die[i]/8+"");
				changebusiness.setI(averL[i]/8+"");
				changebusiness.setJ(averD[i]/8+"");
				changebusiness.setD(predongcode);
				int max=0;
				int maxindex=0;
				for(int j=0;j<4;j++) {
					
					if(max<what[i][j]){
						maxindex=j;
						max=what[i][j];
					}
	
				}

				if(maxindex==0) {changebusiness.setF("상권축소");}
				else if(maxindex==1) {changebusiness.setF("정체");}
				else if(maxindex==2) {changebusiness.setF("다이나믹");}
				else if(maxindex==3) {changebusiness.setF("상권확장");}
			}
			JSONObject jsonObject = new JSONObject();
			Changebusiness recChange = cblist.get(5);
			if(recChange.getF().equals("상권축소")) {Point = 25;}
			else if(recChange.getF().equals("정체")) {Point = 37;}
			else if(recChange.getF().equals("다이나믹")){Point = 60;	}
			else if(recChange.getF().equals("상권확장")) {Point = 85;	}
			jsonObject.put("cblist", cblist);
			jsonObject.put("point", Point);
			
			
			return new ResponseEntity<Object>(jsonObject,HttpStatus.OK);
		} catch (Exception e) {
			
			e.printStackTrace();
			return new ResponseEntity<Object>("해당 주소가 존재하지않습니다.", HttpStatus.NOT_FOUND);
		}
	}

}
