package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import vo.ContentVO;
import vo.PagingVO;

public class BoardService {

	
	public BoardService() {
		
	}
	
	public List<ContentVO> getListWithPaging(PagingVO page, List<ContentVO> list){
		
		
		System.out.println(list.size());
		
		int start = (page.getCurrentPage()-1) * page.getDisplayRow();
		int end = page.getCurrentPage() * page.getDisplayRow();
		System.out.println(start + " : " + end );
		if(end > list.size()) end = list.size();
		List<ContentVO> pageList = list.subList(start, end + 1);

		
		System.out.println(pageList.get(0).toString());
		
		
        return pageList;
    }
	
	public PagingVO setPageInfo(int currentPage, int total) {
		
		PagingVO pagingVO = new PagingVO();
		
		pagingVO.setCurrentPage(currentPage);
		
		// 전체 페이지 설정 
		pagingVO.setTotalCount(total);

		// 페이지 묶음 ex) (begin)6 7 8 9 (end)10
		int endPage = ((int) Math.ceil(pagingVO.getCurrentPage() / (double)pagingVO.getDisplayPage()));
		endPage	= endPage * pagingVO.getDisplayPage(); 
		pagingVO.setEndPage(endPage);	
		
		int beginPage = pagingVO.getEndPage() - (pagingVO.getDisplayPage() - 1);
		pagingVO.setBeginPage(beginPage);
		
		int totalPage = (int) Math.ceil(pagingVO.getTotalCount() / (double)pagingVO.getDisplayRow());
		pagingVO.setTotalPage(totalPage);
		if (pagingVO.getEndPage() > pagingVO.getTotalPage()) {
			pagingVO.setEndPage(pagingVO.getTotalPage());
		}
		
		return pagingVO;
	}
	

}
