package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.Issue;
import edu.whu.iss.service.IssueService;
import edu.whu.iss.utils.GsonUtil;

public class LoadDiscoverServlet extends HttpServlet {
	private IssueService service = new IssueService();
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String start = request.getParameter("start");
		List<Issue> issues = service.getIssues(Integer.parseInt(start));
//		ArrayList<Question> discovers = new ArrayList<Question>();
//		ArrayList<Answer> answers = new ArrayList<Answer>();
//		
//		discovers.add(new Question("高中数学难不难？", "必修一 集合与初等函数 必修二 平面几何与圆的方程 必修三 统计概率与算法 必修四 三角函数与平面向量 必修五 数列与不等式", answers));
//		discovers.add(new Question("高中物理化学都略显吃力，心里有些慌乱怎么办？", "现在学的，我感觉有时候很难理解。初中时，我一直认为自己的理科很好。成绩也蛮好虽然不搞竞赛。但是到了高中感觉不行了，不像以前一样游刃有余。不过自认为本人还是比较成熟的，心智上。不过看了这么多大神在知乎，什么什么奥赛金牌啊，演讲冠军之类的，说的这么多专业知识。感觉差距太大了。现在学的都不太好，甚至感觉大学都毕不了业。今年要去美国读高中了，心理压力好大，面对的竞争那么大，该怎么办？我会有出息吗？不想让身边人失望。 （修改只为传达更准备的信息，也减少某些评论者的误解，谢谢理解。问出这个问题。是因为感觉我能力不太够。", answers));
//		discovers.add(new Question("高中语文练阅读理解有用吗？", "现在是高考前最后一个暑假了，总想拿这个暑假去把自己的语文提高一下，可是我从小到大就没怎么重视过语文，现在突然说想好好学语文，真都不知道怎么学，一直觉得刷语文的阅读题实在费时太长，甚至有时候在想做那些阅读理解，文言文阅读题真的有效果吗？如果有效果要怎么做呢？", answers));
		out.write(GsonUtil.toJson(issues));
		out.flush();
		out.close();
	}

}
