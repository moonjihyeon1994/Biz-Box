//package com.ssafy.model.DAO;
//
//import java.util.List;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.ssafy.model.dto.Board;
//
//@Repository
//public class boardDAOImpl implements boardDAO {
//
//	private final String ns = "com.ssafy.model.dto.Mapper.";
//	
//	
//	@Autowired
//	SqlSession session;
//	
//	@Override
//	public List<Board> findAllNotice() {
//		return session.selectList(ns+"findAllNotice");
//	}
//
//	@Override
//	public boolean updateBoard(Board board) {
//		return session.update(ns+"updateBoard", board)>0;
//	}
//
//	@Override
//	public boolean addBoard(Board board) {
//		return session.insert(ns+"addBoard", board)>0;
//	}
//
//	@Override
//	public boolean deleteboard(int bidx) {
//		return session.delete(ns+"deleteboard", bidx)>0;
//	}
//
//	@Override
//	public Board detailNotice(int bidx) {
//		return session.selectOne(ns+"detailNotice", bidx);
//	}
//
//}
