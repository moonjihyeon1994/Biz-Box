package com.bizbox.controller;

import java.io.FileReader;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bizbox.Service.OpenCloseService;
import com.bizbox.vo.AddressCategory;
import com.bizbox.vo.OpenClose;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin({ "*" })
@Slf4j
public class OpenCloseController {

	@Autowired
	OpenCloseService service;

	@GetMapping("/getOpenClose/{addr}/{middle}")
	public ResponseEntity<Object> getOpenClose(@PathVariable String addr, @PathVariable String middle) {
		try {
			AddressCategory ac = new AddressCategory(addr, middle);
			OpenClose result = service.getOpenClose(ac);
			return new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("해당 정보가 존재하지않습니다.", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/tempAdd")
	@ApiOperation(value="Json을 통해 개폐업률 정보를 받아옵니다.")
	public void tempAdd() {
		log.trace("tempAdd");
		try {
			CheckJSON cjson = new CheckJSON();
			JSONArray ja = cjson.check();
//	        cjson.check();
//	        System.out.println(cjson);
//			System.out.println(param.size());
			for (int i = 0; i < ja.size(); i++) {
				JSONObject jo = (JSONObject) ja.get(i);
				String Loc = (String) jo.get("address");
				String middleCategory = (String) jo.get("indu");
				List<Long> closecnt = (List<Long>) jo.get("closecnt");
				List<Long> opencnt = (List<Long>) jo.get("closecnt");
				List<Long> totalcnt = (List<Long>) jo.get("storecnt");
				long totalStart = totalcnt.get(0);
				long totalEnd = totalcnt.get(totalcnt.size()-1);
				int opensum = 0;
				int closesum = 0;
				for (int j = 0; j < closecnt.size(); j++) {
					closesum += closecnt.get(j);
				}
				for (int j = 0; j < opencnt.size(); j++) {
					opensum += opencnt.get(j);
				}
				OpenClose oc = new OpenClose(Loc, middleCategory, closesum, opensum, (int) totalStart, (int) totalEnd);
				service.insertOpenCloseData(oc);
			}
		}catch(Exception e) {
			log.error("tempAdd", e);
		}
	}

	class CheckJSON
	{
	    String driver = null;

	    public JSONArray check() throws Exception{
	        JSONParser parser = new JSONParser();
	        Object obj = parser.parse(new FileReader("C://version2.json"));
	        JSONArray ja = (JSONArray) obj;
	        return ja;
//	        System.out.println(ja);
//	        setDriver(("JDBCDriver"));
	    }

	    public void setDriver(String driver){
	        this.driver = driver;
	    }
	    public String getDriver(){
	        return driver;
	    }
	}
}
