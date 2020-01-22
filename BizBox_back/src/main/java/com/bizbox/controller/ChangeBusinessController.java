package com.bizbox.controller;

import java.util.LinkedList;
import java.util.List;

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
		List<Changebusiness> precblist;
		List<Changebusiness> cblist = new LinkedList();
		int live[] = new int[6];
		int die[] = new int[6];
		int averL[] = new int[6];
		int averD[] = new int[6];
		
		
		for(int i=0; i<6; i++) {
			cblist.add(new Changebusiness((2014+i)+""));
		}
		JusoApi api = new JusoApi();
		
		try {
			String predongcode=api.getAddressByName(dong).split(",")[5];//동 이름 가져옴
			String dongcode=predongcode.substring(0,predongcode.length()-1);
			System.out.println(dongcode);
			precblist = service.getChangeHistory(dongcode);
			for (Changebusiness changebusiness : precblist) {

			   live[Integer.parseInt(changebusiness.getA())-2014]+=
				    Integer.parseInt(changebusiness.getG());
				
				die[Integer.parseInt(changebusiness.getA())-2014]+=
					Integer.parseInt(changebusiness.getH());
				
			  averL[Integer.parseInt(changebusiness.getA())-2014]+=
					Integer.parseInt(changebusiness.getI());
				
			  averD[Integer.parseInt(changebusiness.getA())-2014]+=
					Integer.parseInt(changebusiness.getJ());

			}
			for(int i=0; i<6; i++) {
				Changebusiness changebusiness =cblist.get(i);
				changebusiness.setA((2014+i)+"");
				changebusiness.setG(live[i]/8+"");
				changebusiness.setH(die[i]/8+"");
				changebusiness.setI(averL[i]/8+"");
				changebusiness.setJ(averD[i]/8+"");
				changebusiness.setD(predongcode);
				int Live=Integer.parseInt(changebusiness.getG());
				int Die=Integer.parseInt(changebusiness.getH());
				int AL=Integer.parseInt(changebusiness.getI());
				int AD=Integer.parseInt(changebusiness.getJ());
				if(Live<=AL && Die <AD) {changebusiness.setF("다이나믹");}
				else if(Live<=AL && Die >AD) {changebusiness.setF("상권확장");}
				else if(Live>=AL && Die <AD) {changebusiness.setF("상권축소");}
				else if(Live>=AL && Die >AD) {changebusiness.setF("정체");}
			}
			
			return new ResponseEntity<Object>(cblist,HttpStatus.OK);
		} catch (Exception e) {
			
			e.printStackTrace();
			return new ResponseEntity<Object>("해당 주소가 존재하지않습니다.", HttpStatus.NOT_FOUND);
		}
	}

}
