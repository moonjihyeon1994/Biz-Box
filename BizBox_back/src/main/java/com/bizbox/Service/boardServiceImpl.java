package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.model.DAO.boardDAO;
import com.ssafy.model.dto.Board;

@Service
public class boardServiceImpl implements boardService{

	@Autowired
	boardDAO dao;
	
	@Override
	public List<Board> findAllNotice() {
		return dao.findAllNotice();
	}

	@Override
	@Transactional
	public boolean updateBoard(Board board) {
		return dao.updateBoard(board);
	}

	@Override
	@Transactional
	public boolean addBoard(Board board) {
		return dao.addBoard(board);
	}

	@Override
	@Transactional
	public boolean deleteboard(int bidx) {
		return dao.deleteboard(bidx);
	}

	@Override
	public Board detailNotice(int bidx) {
		return dao.detailNotice(bidx);
	}

	@Override
	@Transactional
	public Board detailincrease(int bidx) {
		Board board = detailNotice(bidx);
		int x = board.getBwatch();
		board.setBwatch(x+1);
		updateBoard(board);
		return board;
	}

}
