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
//		discovers.add(new Question("������ѧ�Ѳ��ѣ�", "����һ ��������Ⱥ��� ���޶� ƽ�漸����Բ�ķ��� ������ ͳ�Ƹ������㷨 ������ ���Ǻ�����ƽ������ ������ �����벻��ʽ", answers));
//		discovers.add(new Question("��������ѧ�����Գ�����������Щ������ô�죿", "����ѧ�ģ��Ҹо���ʱ�������⡣����ʱ����һֱ��Ϊ�Լ�����ƺܺá��ɼ�Ҳ������Ȼ���㾺�������ǵ��˸��ио������ˣ�������ǰһ���������ࡣ��������Ϊ���˻��ǱȽϳ���ģ������ϡ�����������ô�������֪����ʲôʲô�������ư����ݽ��ھ�֮��ģ�˵����ô��רҵ֪ʶ���о����̫���ˡ�����ѧ�Ķ���̫�ã������о���ѧ���ϲ���ҵ������Ҫȥ�����������ˣ�����ѹ���ô���Եľ�����ô�󣬸���ô�죿�һ��г�Ϣ�𣿲����������ʧ���� ���޸�ֻΪ�����׼������Ϣ��Ҳ����ĳЩ�����ߵ���⣬лл��⡣�ʳ�������⡣����Ϊ�о���������̫����", answers));
//		discovers.add(new Question("�����������Ķ����������", "�����Ǹ߿�ǰ���һ������ˣ�������������ȥ���Լ����������һ�£������Ҵ�С�����û��ô���ӹ����ģ�����ͻȻ˵��ú�ѧ���ģ��涼��֪����ôѧ��һֱ����ˢ���ĵ��Ķ���ʵ�ڷ�ʱ̫����������ʱ����������Щ�Ķ���⣬�������Ķ��������Ч���������Ч��Ҫ��ô���أ�", answers));
		out.write(GsonUtil.toJson(issues));
		out.flush();
		out.close();
	}

}
