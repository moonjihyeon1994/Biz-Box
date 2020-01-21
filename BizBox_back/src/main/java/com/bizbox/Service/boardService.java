package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.dto.Board;

public interface boardService {
	public List<Board> findAllNotice();
	public boolean updateBoard(Board board);
	public boolean addBoard(Board board);
	public boolean deleteboard(int bidx);
	public Board detailNotice(int bidx);
	public Board detailincrease(int bidx);
}
