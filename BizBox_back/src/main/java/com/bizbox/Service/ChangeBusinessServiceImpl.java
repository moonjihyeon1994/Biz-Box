package com.bizbox.Service;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbox.apis.JusoApi;
import com.bizbox.dao.ChangeBusinessDAO;
import com.bizbox.utils.AddressUtil;
import com.bizbox.vo.Changebusiness;
import com.bizbox.vo.color;



@Service
public class ChangeBusinessServiceImpl implements ChangeBusinessService {

	@Autowired
	ChangeBusinessDAO dao;
	
	@Autowired
	AddressUtil util;
	
	@Autowired
	JusoService jusoService;
	
	@Override
	public List<Changebusiness> getChangeHistory(String dongcode) {
		
		return dao.getChangeHistory(dongcode);
	}

	@Override
	public JSONObject getChangeHistorySub(String dong) throws Exception {
		String predongcode = "";
		int Point = 0;
		List<Changebusiness> precblist = new LinkedList<Changebusiness>();
		List<Changebusiness> cblist = new LinkedList();
		int live[] = new int[6];
		int die[] = new int[6];
		int averL[] = new int[6];
		int averD[] = new int[6];
		int what[][] = new int[6][4];

		for (int i = 0; i < 6; i++) {
			cblist.add(new Changebusiness((2014 + i) + ""));
		}
		List<String> donglist = new LinkedList<String>();
		donglist = jusoService.getDongSetByName(dong);

		for (String string : donglist) {
			precblist = getChangeHistory(string);
			predongcode = string;
			
			if (!precblist.isEmpty())
				break;
		}
		if (precblist.isEmpty()) {
			predongcode = "죄송합니다." + " \" " + predongcode + " \" " + "에 대한 데이터가 존재 하지않습니다.";
		}
		for (Changebusiness changebusiness : precblist) {
			int index = 0;
			live[Integer.parseInt(changebusiness.getA()) - 2014] += Integer.parseInt(changebusiness.getG());

			die[Integer.parseInt(changebusiness.getA()) - 2014] += Integer.parseInt(changebusiness.getH());

			averL[Integer.parseInt(changebusiness.getA()) - 2014] += Integer.parseInt(changebusiness.getI());

			averD[Integer.parseInt(changebusiness.getA()) - 2014] += Integer.parseInt(changebusiness.getJ());
			if (changebusiness.getF().equals("정체")) {
				index = 1;
			}
			if (changebusiness.getF().equals("다이나믹")) {
				index = 2;
			}
			if (changebusiness.getF().equals("상권확장")) {
				index = 3;
			}
			if (changebusiness.getF().equals("상권축소")) {
				index = 0;
			}
			what[Integer.parseInt(changebusiness.getA()) - 2014][index]++;

		}
		for (int i = 0; i < 6; i++) {
			Changebusiness changebusiness = cblist.get(i);
			changebusiness.setA((2014 + i) + "");
			changebusiness.setG(live[i] / 8 + "");
			changebusiness.setH(die[i] / 8 + "");
			changebusiness.setI(averL[i] / 8 + "");
			changebusiness.setJ(averD[i] / 8 + "");
			changebusiness.setD(predongcode);
			int max = 0;
			int maxindex = 0;
			for (int j = 0; j < 4; j++) {
				if (max < what[i][j]) {
					maxindex = j;
					max = what[i][j];
				}
			}

			if (maxindex == 0) {
				changebusiness.setF("상권축소");
			} else if (maxindex == 1) {
				changebusiness.setF("정체");
			} else if (maxindex == 2) {
				changebusiness.setF("다이나믹");
			} else if (maxindex == 3) {
				changebusiness.setF("상권확장");
			}
		}
		JSONObject jsonObject = new JSONObject();
		Changebusiness recChange = cblist.get(5);
		if (recChange.getF().equals("상권축소")) {
			Point = 25;
		} else if (recChange.getF().equals("정체")) {
			Point = 37;
		} else if (recChange.getF().equals("다이나믹")) {
			Point = 60;
		} else if (recChange.getF().equals("상권확장")) {
			Point = 85;
		}
		jsonObject.put("cblist", cblist);
		jsonObject.put("point", Point);
		return jsonObject;
	}
	
	public List<Changebusiness> getChangeHistorySubtolist(String dong) throws Exception {
		String predongcode = "";
		int Point = 0;
		List<Changebusiness> precblist = new LinkedList<Changebusiness>();
		List<Changebusiness> cblist = new LinkedList();
		int live[] = new int[6];
		int die[] = new int[6];
		int averL[] = new int[6];
		int averD[] = new int[6];
		int what[][] = new int[6][4];

		for (int i = 0; i < 6; i++) {
			cblist.add(new Changebusiness((2014 + i) + ""));
		}
		JusoApi api = new JusoApi();
		AddressUtil util = new AddressUtil();
		List<String> donglist = new LinkedList<String>();
		donglist = jusoService.getDongSetByName(dong);

		for (String string : donglist) {
			precblist = getChangeHistory(string);
			predongcode = string;
			
			if (!precblist.isEmpty())
				break;
		}
		if (precblist.isEmpty()) {
			predongcode = "죄송합니다." + " \" " + predongcode + " \" " + "에 대한 데이터가 존재 하지않습니다.";
		}
		for (Changebusiness changebusiness : precblist) {
			int index = 0;
			live[Integer.parseInt(changebusiness.getA()) - 2014] += Integer.parseInt(changebusiness.getG());

			die[Integer.parseInt(changebusiness.getA()) - 2014] += Integer.parseInt(changebusiness.getH());

			averL[Integer.parseInt(changebusiness.getA()) - 2014] += Integer.parseInt(changebusiness.getI());

			averD[Integer.parseInt(changebusiness.getA()) - 2014] += Integer.parseInt(changebusiness.getJ());
			if (changebusiness.getF().equals("정체")) {
				index = 1;
			}
			if (changebusiness.getF().equals("다이나믹")) {
				index = 2;
			}
			if (changebusiness.getF().equals("상권확장")) {
				index = 3;
			}
			if (changebusiness.getF().equals("상권축소")) {
				index = 0;
			}
			what[Integer.parseInt(changebusiness.getA()) - 2014][index]++;

		}
		return cblist;
	}

	@Override
	public JSONObject getColorlist() {
		JSONObject jsonObject = new JSONObject();
		List<color> colorlist = new LinkedList<color>();
		colorlist=dao.getColor();
		jsonObject.put("colorlist", colorlist);
		return jsonObject;
	}

	@Override
	public JSONObject getChangeHistorySubtoAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
