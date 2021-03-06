package mailResolved.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Message;
import javax.mail.Part;
import javax.mail.internet.MimeMessage;

import mailResolved.entity.Resume;
import mailResolved.templates.ParsingTemplZL;
import mailResolved.utils.javamail.ReciveOneMail;
import mailResolved.utils.jdbc.JDBCUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 解析智联的操作类
 * 
 * @author Sylvan__
 * 
 */
public class EmailZL extends Email {

	String host = "pop3.163.com";
	String username = "lepintongzhaopin@163.com";
	String password = "lepintong201401";


	public static void main(String[] args) {

		for(int i=0;i<100;i++){
			new EmailZL().parseingZL();
		}
	}

	public void parseingZL() {

		Date date = new Date();
		date.getTime();
		ReciveOneMail pmm = null;
		String html = "";
		Integer successCount = 0; // 成功个数

		System.out.println("智联解析开始，，解析邮箱为:"+username);

		// 取得所有信息
		Message message[] = this.getMessages(PROTOCOL, host, PORT, FILE,
				username, password);

		// 得到邮件的总条数
		Integer mesLength = message.length;
		System.out.println("共有邮件" + mesLength+"封");

		List<Resume> resumes = new ArrayList<Resume>();

		// 开始轮询解析
		for (int i = 0; i < mesLength; i++) {
			pmm = new ReciveOneMail((MimeMessage) message[i]);
			try {
				System.out.print("正在解析第 " + (i + 1) + "封邮件 : "
						+ pmm.getSubject() + "......"); // 打印邮件主题
				pmm.getMailContent((Part) message[i]);
				html = super.parseingHtmlAttchement((Part) message[i]);
				Document doc = Jsoup.parse(html);
				resumes.add(this.getResumeByDoc(doc));
				System.out.print("解析完成\n");
				successCount++;
			} catch (Exception e1) {
				System.out.print("解析失败,跳过!\n");
				e1.printStackTrace();
			}
			break;
		}
		new JDBCUtil().insert2(resumes);
		System.out
		.println("邮件解析完成,共" + successCount + "/" + mesLength
				+ "个邮件被解析,共用时"
				+ (new Date().getTime() - date.getTime()) + "ms");
	}

	public Resume getResumeByDoc(Document resumeHtml) {

		ParsingTemplZL pt = new ParsingTemplZL(resumeHtml);

		Resume r = new Resume();
		r.setUsername(pt.getUsername());
		r.setSex(pt.getSex());
		r.setAge(pt.getAge());
		r.setNation(null);
		r.setEducation(pt.getEducation());
		r.setMajor(pt.getMajor());
		r.setCollege(pt.getCollege());
		r.setAddress(pt.getAddress());
		r.setPhone(pt.getPhone());
		r.setEmail(pt.getEmail());
		r.setStuExperience(pt.getStuExperience());
		r.setJobExperience(pt.getJobExperience());
		r.setAssess(pt.getAssess());
		r.setDataSource("智联");
		return r;
	}

}
