package com.ssafy.model.DAO;

import java.util.List;

import com.ssafy.model.dto.Board;

public interface boardDAO {
	List<Board> findAllNotice();
	boolean updateBoard(Board board);
	boolean addBoard(Board board);
	boolean deleteboard(int bidx);
	Board detailNotice(int bidx);
}
