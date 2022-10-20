package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import vo.ContentVO;
import vo.PagingVO;


@WebServlet(name="boardController", value="/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		
		HttpSession session = request.getSession();
		List<ContentVO> contentList = (List) session.getAttribute("contentList");
		
		// 현재 페이지 체크 및 설정
		// 첫 페이지면 1이 설정, 아니라면 해당 페이지 넘버가 설정됨
		int currentPage = 1;
		if (request.getParameter("page") != null) {
			System.out.println("BoardController페이지" + request.getParameter("page"));
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		BoardService boardService = new BoardService();
		
		int total = contentList.size();
		PagingVO pagingVO = boardService.setPageInfo(currentPage, total);
		List<ContentVO> pageList = boardService.getListWithPaging(pagingVO,contentList);
		
		
		try {
			request.setAttribute("pageInfo", pagingVO);
			request.setAttribute("pageList", pageList);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/view/list.jsp");
			dispatch.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request,response);
	}
	
	
	
	


}
