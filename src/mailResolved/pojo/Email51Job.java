package mailResolved.pojo;

import java.util.ArrayList;
import java.util.Date;
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
 * 
 * @author Sylvan__
 * 
 */
public class Email51Job extends Email {

	String host = "pop3.163.com";
	String username = "lepintong@163.com";
	String password = "lepintong201401";

	public static void main(String[] args) {
		new Email51Job().parseing51Job();
	}

	public void parseing51Job() {

		Date date = new Date();
		date.getTime();
		ReciveOneMail pmm = null;
		Integer successCount = 0; // 成功个数

		System.out.println("中国人才在线解析开始，，解析邮箱为:" + username);

		// 取得所有信息
		Message message[] = this.getMessages(PROTOCOL, host, PORT, FILE,
				username, password);
		Integer mesLength = message.length;
		System.out.println("共有邮件" + mesLength + "封");

		List<Resume> resumes = new ArrayList<Resume>();
		Resume r = null;
		// String[] allLinks = new String[mesLength];

		for (int i = 0; i < mesLength; i++) {
			pmm = new ReciveOneMail((MimeMessage) message[i]);
			try {
				pmm.getMailContent((Part) message[i]);
				System.out.print("正在解析第 " + (i + 1) + "封邮件 : "
						+ pmm.getSubject() + "......"); // 打印邮件主题
				Document doc = Jsoup.parse(pmm.getBodyText());
				Element body = doc.body();
				Elements links = body.getElementsByTag("a");

				for (int k = 0; k < links.size(); k++) {
					String link = links.get(k).attr("href");
					r = new Resume();
					Document doc2 = Jsoup.connect(link).get();
					ParsingTempl51 pt = new ParsingTempl51(doc2);

					Elements divResume = doc2.select("#divResume");

					Elements sexAndAge = divResume.first().select(
							"table table:eq(1) table tr:eq(0) span b");

					Elements education = divResume.first()
							.select("table table:eq(2)").first()
							.select("td:eq(2) table tr:eq(1) td:eq(1)");
					Elements major = divResume.first()
							.select("table table:eq(2)").first()
							.select("td:eq(2) table tr:eq(2) td:eq(1)");
					Elements college = divResume.first()
							.select("table table:eq(2)").first()
							.select("td:eq(2) table tr:eq(3) td:eq(1)");

					String ss = sexAndAge.text().split("\\|")[2].trim().split(
							"岁")[0].replaceAll(" ", "");

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
				}
				System.out.print("解析完成\n");
				successCount++;
			} catch (Exception e1) {
				System.out.print("解析失败,跳过!\n");
				e1.printStackTrace();
			}

		}
		// break;
		new JDBCUtil().insert2(resumes);
		System.out
				.println("邮件解析完成,共" + successCount + "/" + mesLength
						+ "个邮件被解析,共用时"
						+ (new Date().getTime() - date.getTime()) + "ms");
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
