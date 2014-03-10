package mailResolved.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Message;
import javax.mail.Part;
import javax.mail.internet.MimeMessage;

import mailResolved.entity.Resume;
import mailResolved.utils.javamail.ReciveOneMail;
import mailResolved.utils.jdbc.JDBCUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class EmailCJOL extends Email {

	String host = "pop3.163.com";
	String username = "lepintongcjol@163.com";
	String password = "lepintong201401";



	public static void main(String[] args){
		new EmailCJOL().parseingZol();
	}

	public void parseingZol() {
		
		Date date = new Date();
		date.getTime();
		ReciveOneMail pmm = null;
		Integer successCount = 0; // 成功个数

		System.out.println("中国人才在线解析开始，，解析邮箱为:"+username);

		// 取得所有信息
		Message message[] = this.getMessages(PROTOCOL, host, PORT, FILE,
				username, password);
		Integer mesLength = message.length;
		System.out.println("共有邮件" + mesLength+"封");

		List<Resume> resumes = new ArrayList<Resume>();
		Resume r = null;

		for (int i = 0; i < mesLength; i++) {
			pmm = new ReciveOneMail((MimeMessage) message[i]);
			try {
				System.out.print("正在解析第 " + (i + 1) + "封邮件 : "
						+ pmm.getSubject() + "......"); // 打印邮件主题
				pmm.getMailContent((Part) message[i]);
				Document doc = Jsoup.parse(pmm.getBodyText());
				Element body = doc.body();

				r = new Resume ();
				r.setUsername(body.select("body > table:eq(2) tr:eq(0) td:eq(0)").text());
				r.setSex("男".equals(selfSelectorText1(body,"性别："))?1:0);
				r.setAge(Integer.parseInt(selfSelectorText1(body,"年龄：").split("岁")[0]));
				r.setEducation(selfSelectorText1(body,"学历："));
				r.setMajor(selfSelectorText1(body,"专业："));
				r.setCollege(selfSelectorText1(body,"毕业院校："));
				r.setPhone(selfSelectorText1(body,"手机号码："));
				r.setEmail(selfSelectorText1(body,"Email："));
				r.setAddress(selfSelectorText1(body,"通信地址："));
				r.setJobExperience(selfSelectorText2(body,"工作经历"));
				r.setStuExperience(selfSelectorText2(body,"教育背景"));
				r.setAssess(selfSelectorText2(body,"工作经历详细介绍"));
				r.setDataSource("中国人才在线");
				resumes.add(r);
				System.out.print("解析完成\n");
				successCount++;
			} catch (Exception e1) {
				System.out.print("解析失败,跳过!\n");
				e1.printStackTrace();
			}
			

			// 是否为调试状态
			if(isDebug){
				//System.out.println(doc.html());
				System.out.println(username + "||" + r.getSex() + "||" + r.getAge() + "||"
						+ r.getEducation() + "||" + r.getMajor() + "||" + r.getCollege());
				System.out.println(r.getPhone() + "||" + r.getEmail() + "||" + r.getAddress());
				System.out.println(r.getJobExperience());
				System.out.println(r.getStuExperience());
				System.out.println(r.getAssess());
				break;
			}
			//break;
		}
		new JDBCUtil().insert2(resumes);
		System.out
		.println("邮件解析完成,共" + successCount + "/" + mesLength
				+ "个邮件被解析,共用时"
				+ (new Date().getTime() - date.getTime()) + "ms");
	}

}
