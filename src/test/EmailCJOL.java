package test;

import javax.mail.Message;
import javax.mail.Part;
import javax.mail.internet.MimeMessage;

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

		Message message[] = this.getMessages(PROTOCOL, host, PORT, FILE,
				username, password);
		Integer mesLength = message.length;
		ReciveOneMail pmm = null;
		
		Resume r = new Resume ();

		for (int i = 0; i < mesLength; i++) {
			pmm = new ReciveOneMail((MimeMessage) message[i]);
			try {
				pmm.getMailContent((Part) message[i]);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			Document doc = Jsoup.parse(pmm.getBodyText());
			Element body = doc.body();
			
			r.setUsername(body.select("body > table:eq(2) tr:eq(0) td:eq(0)")
					.text());
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
			new JDBCUtil().insert2(r);
			r = null;
//			System.out.println(doc.html());
//			System.out.println(username + "||" + r.getSex() + "||" + r.getAge() + "||"
//					+ r.getEducation() + "||" + r.getMajor() + "||" + r.getCollege());
//			System.out.println(r.getPhone() + "||" + r.getEmail() + "||" + r.getAddress());
//			System.out.println(r.getJobExperience());
//			System.out.println(r.getStuExperience());
//			System.out.println(r.getAssess());
//			break;
			
		}
	}
	
}
