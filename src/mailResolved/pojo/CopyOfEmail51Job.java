package mailResolved.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.Part;
import javax.mail.internet.MimeMessage;

import mailResolved.entity.Resume;
import mailResolved.templates.ParsingTempl51;
import mailResolved.utils.javamail.ReciveOneMail;
import mailResolved.utils.jdbc.JDBCUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 解析51Job的操作类
 * @author Sylvan__
 *
 */
public class CopyOfEmail51Job extends Email {
	
	

	String host = "pop3.163.com";
	String username = "lepintong@163.com";
	String password = "lepintong201401";

	public static void main(String[] args) {
		new CopyOfEmail51Job().parseing51Job();
	}

	public void parseing51Job() {
		
		String[] allLinks = this.getLinks();
		List<Resume> resumes = new ArrayList<Resume>();
		Resume r = null;
		
		for (int i = 0; i < allLinks.length; i++) {
			try {
				r = new Resume();
				Document doc2 = Jsoup.connect(allLinks[i]).get();
				ParsingTempl51 pt = new ParsingTempl51(doc2);

				Elements divResume = doc2.select("#divResume");

				Elements sexAndAge = divResume.first().select(
						"table table:eq(1) table tr:eq(0) span b");

				Elements education = divResume.first()
						.select("table table:eq(2)").first()
						.select("td:eq(2) table tr:eq(1) td:eq(1)");
				Elements major = divResume.first().select("table table:eq(2)")
						.first().select("td:eq(2) table tr:eq(2) td:eq(1)");
				Elements college = divResume.first()
						.select("table table:eq(2)").first()
						.select("td:eq(2) table tr:eq(3) td:eq(1)");

				String ss = sexAndAge.text().split("\\|")[2].trim().split("岁")[0]
						.replaceAll(" ", "");

				r.setUsername(pt.getUsername());
				r.setSex(pt.getSex());
				r.setAge(Integer.valueOf(ss));
				r.setNation(null);
				r.setEducation(education.text());
				r.setMajor(major.text());
				r.setCollege(college.text());
				r.setAddress(pt.getAddress());
				r.setPhone(pt.getPhone());
				r.setEmail(pt.getEmail());
				r.setStuExperience(pt.getStuExperience());
				r.setJobExperience(pt.getJobExperience());
				r.setAssess(pt.getAssess());
				r.setDataSource("51Job");
				resumes.add(r);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		new JDBCUtil().insert2(resumes);
	}

	public String[] getLinks() {

		Message message[] = this.getMessages(PROTOCOL, host, PORT, FILE,
				username, password);
		Integer mesLength = message.length;
		String[] allLinks = new String[mesLength];
		ReciveOneMail pmm = null;

		for (int i = 0; i < mesLength; i++) {
			pmm = new ReciveOneMail((MimeMessage) message[i]);
			try {
				pmm.getMailContent((Part) message[i]);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			Document doc = Jsoup.parse(pmm.getBodyText());
			Element body = doc.body();
			Elements links = body.getElementsByTag("a");

			for (int k = 0; k < links.size(); k++) {
				allLinks[k] = links.get(k).attr("href");
				System.out.println(allLinks[k]);
				// break;
			}
			// break;
		}
		return allLinks;
	}

}
