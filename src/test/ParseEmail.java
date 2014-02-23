package test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseEmail {

	/**
	 * PraseMimeMessage类测试
	 */
	public static void main(String args[]) throws Exception {
		//new ParseEmail().getLinks2();
		//new ParseEmail().parseing51();
		//new ParseEmail().parseingZL();
		new ParseEmail().parseingZol();
		
	}

	public Message[] getMessages(String protocol, String host, Integer port,
			String file, String username, String password) {

		Message[] messages = null;
		Properties props = System.getProperties();
		Session session = Session.getDefaultInstance(props, null);
		URLName urln = new URLName(protocol, host, port, file, username,
				password);
		Store store;
		try {
			store = session.getStore(urln);
			store.connect();
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			messages = folder.getMessages();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return messages;
	}
	
	
	
	public void parseingZol(){
		String protocol = "pop3";
		String host = "pop3.163.com";
		Integer port = 110;
		String file = null;
		String username = "lepintongcjol@163.com";
		String password = "lepintong201401";

		Message message[] = this.getMessages(protocol, host, port, file,
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
			username = body.select("body > table:eq(2) tr:eq(0) td:eq(0)").text();
			String sex = body.select(":containsOwn(性别：)").first().nextElementSibling().text();
			String age = body.select(":containsOwn(年龄：)").first().nextElementSibling().text();
			String education = body.select(":containsOwn(学历：)").first().nextElementSibling().text();
			String major = body.select(":containsOwn(专业：)").first().nextElementSibling().text();
			String college = body.select(":containsOwn(毕业院校：)").first().nextElementSibling().text();
			String phone = body.select(":containsOwn(手机号码：)").first().nextElementSibling().text();
			String email = body.select(":containsOwn(Email：)").first().nextElementSibling().text();
			String address = body.select(":containsOwn(通信地址：)").first().nextElementSibling().text();
			
			String jobExperience = body.select(":containsOwn(工作经历)").first().parent().nextElementSibling().text();
			String stuExperience = body.select(":containsOwn(教育背景)").first().parent().nextElementSibling().text();
			String assess = body.select(":containsOwn(工作经历详细介绍)").first().parent().nextElementSibling().text();
			System.out.println(username+"||"+sex+"||"+age+"||"+education+"||"+major+"||"+college);
			System.out.println(phone+"||"+email+"||"+address);
			System.out.println(jobExperience);
			System.out.println(stuExperience);
			System.out.println(assess);
			//System.out.println(doc.html());
			break;
		}
	}

	public void parseing51() {

		String[] allLinks = this.getLinks();
		for (int i = 0; i < allLinks.length; i++) {
			try {
				Document doc2 = Jsoup.connect(allLinks[i]).get();
				new ParseEmail().save(doc2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void parseingZL(){
		String protocol = "pop3";
		String host = "pop3.163.com";
		Integer port = 110;
		String file = null;
		String username = "lepintongzhaopin@163.com";
		String password = "lepintong201401";

		Message message[] = this.getMessages(protocol, host, port, file,
				username, password);
		Integer mesLength = message.length;
		ReciveOneMail pmm = null;

		for (int i = 0; i < mesLength; i++) {
			pmm = new ReciveOneMail((MimeMessage) message[i]);
			try {
				System.out.println("Message " + i + " subject: " + pmm.getSubject());
				pmm.getMailContent((Part) message[i]);
				String html  = new ParseEmail().parseingHtmlAttchement((Part) message[i]);
				System.out.println("=============================================================");
				System.out.println(html);
				Document doc = Jsoup.parse(html);
				this.saveZL(doc);
				//Element body = doc.body();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		//	break;
		}
	}
	
	public String[] getLinks() {

		String protocol = "pop3";
		String host = "pop3.163.com";
		Integer port = 110;
		String file = null;
		String username = "lepintong@163.com";
		String password = "lepintong201401";

		Message message[] = this.getMessages(protocol, host, port, file,
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
			//	break;
			}
		//	break;
		}
		return allLinks;
	}

	public String[] getLinks2() {

		String protocol = "pop3";
		String host = "pop3.163.com";
		Integer port = 110;
		String file = null;
		String username = "lepintongzhaopin@163.com";
		String password = "lepintong201401";

		Message message[] = this.getMessages(protocol, host, port, file,
				username, password);
		Integer mesLength = message.length;
		String[] allLinks = new String[mesLength];
		ReciveOneMail pmm = null;

		for (int i = 0; i < mesLength; i++) {
			pmm = new ReciveOneMail((MimeMessage) message[i]);
			try {
				pmm.getMailContent((Part) message[i]);
				pmm.setAttachPath("c:\\");
				pmm.saveAttachMent((Part) message[i]);
				System.out.println(pmm.getBodyText());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			Document doc = Jsoup.parse(pmm.getBodyText());
			Element body = doc.body();
			Elements links = body.getElementsByTag("a");

			for (int k = 0; k < links.size(); k++) {
				allLinks[k] = links.get(k).attr("href");
				System.out.println(allLinks[k]);
				break;
			}
			break;
		}
		return allLinks;
	}

	public void save(Document resumeHtml) {

		ParsingTempl51 pt = new ParsingTempl51(resumeHtml);

		Elements divResume = resumeHtml.select("#divResume");

		Elements sexAndAge = divResume.first().select(
				"table table:eq(1) table tr:eq(0) span b");

		Elements education = divResume.first().select("table table:eq(2)")
				.first().select("td:eq(2) table tr:eq(1) td:eq(1)");
		Elements major = divResume.first().select("table table:eq(2)").first()
				.select("td:eq(2) table tr:eq(2) td:eq(1)");
		Elements college = divResume.first().select("table table:eq(2)")
				.first().select("td:eq(2) table tr:eq(3) td:eq(1)");

		String ss = sexAndAge.text().split("\\|")[2].trim().split("岁")[0]
				.replaceAll(" ", "");
		String ss2 = sexAndAge.text().split("\\|")[1].replaceAll(" ", "");

		Resume r = new Resume();
		// r.setId(122);
		r.setName("zzzzzzzzz");
		r.setUsername(pt.getUsername());
		// r.setSex("男".equals(ss2) ? 1 : 0);
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
		new JDBCUtil().insert2(r);
	}
	
	public void saveZL(Document resumeHtml) {

		ParsingTemplZL pt = new ParsingTemplZL(resumeHtml);


		Resume r = new Resume();
		// r.setId(122);
		r.setName("zzzzzzzzz");
		r.setUsername(pt.getUsername());
		// r.setSex("男".equals(ss2) ? 1 : 0);
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
		new JDBCUtil().insert2(r);
	}

	public void oldSelecter() {

		// Element stuExperienceLabel =
		// resumeHtml.select(":containsOwn(教育经历)").first().parent();
		// Element jobExperience =
		// resumeHtml.select(":containsOwn(教育经历)").first().parent();
		// Element assess =
		// resumeHtml.select(":containsOwn(教育经历)").first().parent();
		// System.out.println(stuExperienceLabel.elementSiblingIndex());
		// System.out.println(stuExperienceLabel.parent().child(13).text());
		// Elements username = new
		// ParsingTempl51(resumeHtml).getResumeDiv().select("table table:eq(1) tr:eq(0) span:eq(1) b");
		// Elements address =
		// divResume.first().select("table table:eq(1) table tr:eq(2) td:eq(1)");
		// Elements phone =
		// divResume.first().select("table table:eq(1) table tr:eq(3) td:eq(1)");
		// Elements username = new
		// ParsingTempl51(resumeHtml).getResumeDiv().select("table table:eq(1) tr:eq(0) span:eq(1) b");
		// Elements email = divResume.first().select(
		// "table table:eq(1) table tr:eq(4) td:eq(1)");
	}

	public void testa(Part part) throws Exception {
		String fileName = "";
		if (part.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) part.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				BodyPart mpart = mp.getBodyPart(i);
				String disposition = mpart.getDisposition();
				if ((disposition != null)
						&& ((disposition.equals(Part.ATTACHMENT)) || (disposition
								.equals(Part.INLINE)))) {
					fileName = mpart.getFileName();
					if (fileName.toLowerCase().indexOf("gb2312") != -1) {
						fileName = MimeUtility.decodeText(fileName);
					}
					// mpart.getContent();
					// File input = new File(mpart.getInputStream());
					// mpart.get
					// Jsoup.parse(in, charsetName, baseUri);
					// saveFile(fileName, mpart.getInputStream());
				} else if (mpart.isMimeType("multipart/*")) {
					testa(mpart);
				} else {
					fileName = mpart.getFileName();
					if ((fileName != null)
							&& (fileName.toLowerCase().indexOf("GB2312") != -1)) {
						fileName = MimeUtility.decodeText(fileName);
						// saveFile(fileName, mpart.getInputStream());
					}
				}
			}
		} else if (part.isMimeType("message/rfc822")) {
			testa((Part) part.getContent());
		}
	}

	public String parseingHtmlAttchement(Part part) throws MessagingException,
			IOException {
		String fileName="";
		String html = "";
		if (part.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) part.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				BodyPart mpart = mp.getBodyPart(i);
				String disposition = mpart.getDisposition();
				if ((disposition != null)
						&& ((disposition.equals(Part.ATTACHMENT)) || (disposition
								.equals(Part.INLINE)))) {
					fileName = mpart.getFileName();
//					if (fileName.toLowerCase().indexOf("gb2312") != -1) {
//						fileName = MimeUtility.decodeText(fileName);
//					}
					byte[] getData =ParseEmail.readInputStream(mpart.getInputStream());     //获得网站的二进制数据  
					html = new String(getData, "utf-8");  
		            System.out.println("File name is :"+new String(fileName.getBytes("gb2312"),"utf-8"));  
				} else if (mpart.isMimeType("multipart/*")) {
					parseingHtmlAttchement(mpart);
				} else {
				}
			}
		} else if (part.isMimeType("message/rfc822")) {
			parseingHtmlAttchement((Part) part.getContent());
		}

		return html;
	}

	public static byte[] readInputStream(InputStream inputStream)
			throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}

		bos.close();
		return bos.toByteArray();
	}
	
}
