package presentation.servlet;
import java.io.IOException;

import business.service.BloodTypeFortuneService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * {@link SampleMemoServlet}
 */
@WebServlet("/bloodType") //←クライアントからどのリクエストがあった時に呼び出すServletかを記述する。
public class BloodTypeFortuneServlet extends HttpServlet {
	/*	****************************************
	 * 使用するServiceがある場合ここでインスタンスを生成する。	*
	 * ****************************************/
	 private BloodTypeFortuneService service = new BloodTypeFortuneService();
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*	*******************************
		 * ↓クライアントに返却するファイルの場所を指定	*
		 * *******************************/
		String view = "/WEB-INF/view/bloodTypeFortune.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String bloodType = request.getParameter("bloodType");

		String fortune = service.execute(bloodType);
		
		request.setAttribute("fortune",fortune);
		
		String view = "/WEB-INF/view/bloodTypeFortuneResult.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}














