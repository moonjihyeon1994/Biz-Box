package com.ssafy.model.contoroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.model.dto.Board;
import com.ssafy.model.dto.BoolResult;
import com.ssafy.model.dto.EatList;
import com.ssafy.model.service.boardService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@CrossOrigin({"*"})
@EnableSwagger2
@Slf4j
public class boardRestController {

	@Autowired
	boardService service;

	@GetMapping("/findAllNotice")
	@ApiOperation(value = "전체 공지사항을 반환한다.") // 리스트를 반환한다는 것까지 명시가능
	public Object findAllFoods() {
		log.trace("findAllNotice");
		try {
			List<Board> blist = service.findAllNotice();
			return new ResponseEntity<Object>(blist, HttpStatus.OK);
		} catch (RuntimeException e) {
			log.trace("findAllNotice", e);
			throw e; // spring에게 던짐 -> spring은 tomcat에게 던짐 -> tomcat은 사용자에게 던짐
		}
	}
	@GetMapping("/detailNotice/{bidx}")
	@ApiOperation(value = "공지사항 디테일을 반환한다.") // 리스트를 반환한다는 것까지 명시가능
	public Object detailNotice(@PathVariable int bidx) {
		log.trace("detailNotice");
		try {
			Board board = service.detailNotice(bidx);
			return new ResponseEntity<Object>(board, HttpStatus.OK);
		} catch (RuntimeException e) {
			log.trace("detailNotice", e);
			throw e; // spring에게 던짐 -> spring은 tomcat에게 던짐 -> tomcat은 사용자에게 던짐
		}
	}
	@GetMapping("/detailincrease/{bidx}")
	@ApiOperation(value = "공지사항 디테일을 조회수를 증가시켜 반환한다.") // 리스트를 반환한다는 것까지 명시가능
	public Object detailincrease(@PathVariable int bidx) {
		log.trace("detailincrease");
		try {
			Board board = service.detailincrease(bidx);
			return new ResponseEntity<Object>(board, HttpStatus.OK);
		} catch (RuntimeException e) {
			log.trace("detailincrease", e);
			throw e; // spring에게 던짐 -> spring은 tomcat에게 던짐 -> tomcat은 사용자에게 던짐
		}
	}
	@PostMapping("/addBoard")
	@ApiOperation(value="게시글을 등록한다.")
	public ResponseEntity<Object> addBoard(@RequestBody Board board){
		log.trace("addBoard");
		try {
			boolean result = service.addBoard(board);
			BoolResult br = null;
			if(result) {
				br = new BoolResult("addBoard", result, "SUCCESS");
			}else {
				br = new BoolResult("addBoard", result, "FAIL");
			}
			return new ResponseEntity<Object>(br, HttpStatus.OK);
		}catch(RuntimeException e) {
			log.error("addBoard", e);
			throw e;
		}
	}
	@PutMapping("/updateBoard")
	@ApiOperation(value="게시글 수정.")
								//json 형태로 받는다 ->  request body
	public ResponseEntity<Object> updateBoard(@RequestBody Board board){
		log.trace("updateBoard");
		try {
			boolean result = service.updateBoard(board);
			BoolResult br = null;
			if(result) {
				br = new BoolResult("updateBoard", result, "SUCCESS");
			}else {
				br = new BoolResult("updateBoard", result, "FAIL");
			}
			return new ResponseEntity<Object>(br, HttpStatus.OK);
		}catch(RuntimeException e) {
			log.error("updateBoard", e);
			throw e;
		}
	}
	
	@DeleteMapping("/deleteboard/{bidx}")
	@ApiOperation(value="게시글을 삭제합니다")
	public ResponseEntity<Object> deleteboard(@PathVariable int bidx){
		log.trace("deleteboard");
		try {
			boolean result = service.deleteboard(bidx);
			BoolResult br = null;
			if(result) {
				br = new BoolResult("deleteboard", result, "SUCCESS");
			}else {
				br = new BoolResult("deleteboard", result, "FAIL");
			}
			return new ResponseEntity<Object>(br, HttpStatus.OK);
		}catch(RuntimeException e) {
			log.error("deleteboard", e);
			throw e;
		}
	}
	
}
