package kr.or.ddit.board.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

public class BoardController {
	
	private Scanner scan = new Scanner(System.in);
	private int boardNo = 0;
	
	private IBoardService service;
	
	public BoardController() {
		service = BoardServiceImpl.getInstance();
	}
	
	public static void main(String[] args) {
		new BoardController().start();
	}
	
	private void start() {
		while(true) {
			displayAll();
			int input = displayMenu();
			
			switch(input) {
			case 1:
				insert();
				break;
				
			case 2:
				displayAll();
				input = select();
				while(true) {
					switch(input) {
					case 1:
						update();
						break;
						
					case 2:
						delete();
						break;
						
					case 3:
						start();
						break;
					default:
						System.out.println("번호를 잘못입력하셨습니다.");
						System.out.println("다시 입력해주세요.");
					}
					break;
				}
				
			case 3:
				search();
				break;
				
			case 0:
				System.out.println("작업이 종료됩니다.");
				System.exit(0);
				
			default:
				System.out.println("번호를 잘못입력하셨습니다.");
				System.out.println("다시 입력해주세요.");
			}
			
			
		}
		
	}
	
	private void delete() {
		int cnt = service.deleteBoard(boardNo);
		
		if(cnt>0) {
			System.out.println("삭제 성공");
		}else {
			System.out.println("삭제 실패");
		}
		
	}

	private void update() {
		System.out.println();
		System.out.println("수정 작업하기");
		System.out.println("-----------------------------------");
		System.out.print("- 제  목 : ");
		String newTitle = scan.nextLine();
		
		System.out.print("- 내  용 : ");
		String newContent = scan.nextLine();
		
		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_no(boardNo);
		boardVo.setBoard_title(newTitle);
		boardVo.setBoard_content(newContent);
		
		int cnt = service.updateBoard(boardVo);
		
		if(cnt>0) {
			System.out.println(boardNo + "번 글이 수정되었습니다.");
		}else {
			System.out.println("수정 실패");
		}
	}

	private int select() {
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		boardNo =  Integer.parseInt(scan.nextLine());
		
		Map<Integer, BoardVO> boardMap = service.selectBoard(boardNo);
		
		Set<Integer> keyset = boardMap.keySet();
		
		for (Integer key : keyset) {
			System.out.println("-----------------------------------------------------------");
			System.out.println(" 제   목 : " + boardMap.get(key).getBoard_title());
			System.out.println(" 작성자 : " + boardMap.get(key).getBoard_writer());
			System.out.println(" 내   용 : " + boardMap.get(key).getBoard_content());
			System.out.println(" 작성일 : " + boardMap.get(key).getBoard_date());
			System.out.println(" 조회수 : " + boardMap.get(key).getBoard_cnt());
			System.out.println("-----------------------------------------------------------");
		}
		
		service.updateBoardCnt(boardNo);
		
		System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
		System.out.print("작업선택 >> ");
		return Integer.parseInt(scan.nextLine());
	}

	private void search() {
		System.out.println();
		System.out.println("검색 작업");
		System.out.println("--------------------------------------------");
		System.out.print("- 검색할 제목 입력 : ");
		String title = "%" + scan.nextLine() + "%";
		
		System.out.println();
		System.out.println("------------------------------------------------------------");
		System.out.println("No \t\t 제목 \t\t 작성자 \t\t 조회수");
		System.out.println("------------------------------------------------------------");
		List<BoardVO> boardList = service.searchBoard(title);
		if(boardList==null || boardList.size()==0) {
			displayAll();
		}else {
			Collections.sort(boardList);
			for (BoardVO boardVo : boardList) {
				System.out.println(boardVo.getBoard_no() + "\t\t" + boardVo.getBoard_title() + "\t\t" + boardVo.getBoard_writer() + "\t\t" + boardVo.getBoard_cnt());
			}
		}
		
		System.out.println("------------------------------------------------------------");
		
	}

	private void insert() {
		System.out.println();
		System.out.println("새글 작성하기");
		System.out.println("-----------------------------------");
		System.out.print("- 제   목 : ");
		String title = scan.nextLine();
		
		System.out.print("- 작성자 : ");
		String writer = scan.nextLine();
		
		System.out.print("- 내   용 : ");
		String content = scan.nextLine();
		
		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);
		
		int cnt = service.insertBoard(boardVo);
		
		if(cnt>0) {
			System.out.println("게시글 작성 완료!");
		}else {
			System.out.println("게시글 작성 실패");
		}
		
	}

	private int displayMenu() {
		System.out.println();
		System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
		System.out.print("작업선택 >> ");
		
		return Integer.parseInt(scan.nextLine());
	}

	private void displayAll() {
		System.out.println();
		System.out.println("------------------------------------------------------------");
		System.out.println("No \t\t 제목 \t\t 작성자 \t\t 조회수");
		System.out.println("------------------------------------------------------------");
		
		List<BoardVO> boardList = service.getAllBoard();
		
		if(boardList==null || boardList.size()==0) {
			System.out.println("\t\t게시물이 존재하지 않습니다.");
		}else {
			Collections.sort(boardList);
			for (BoardVO boardVo : boardList) {
				System.out.println(boardVo.getBoard_no() + "\t\t" + boardVo.getBoard_title() + "\t\t" + boardVo.getBoard_writer() + "\t\t" + boardVo.getBoard_cnt());
			}
		}
		
		System.out.println("------------------------------------------------------------");
	}

}
