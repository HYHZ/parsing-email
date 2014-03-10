package mailResolved;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

import mailResolved.pojo.Email51Job;
import mailResolved.pojo.EmailCJOL;
import mailResolved.pojo.EmailZL;

/**
 * 解析Email类,提供程序运行入口Main函数
 * @author sylvan__
 *
 */
public class ParseEmail {

	/**
	 * 解析Email执行方法
	 */
	public static void main(String args[]) throws Exception {
		
		//pe.parseingZL();
		new EmailZL().parseingZL();
		new EmailCJOL().parseingZol();
		new Email51Job().parseing51Job();
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
	
	
	
	
//	public String[] getLinks2() {
//
//		String protocol = "pop3";
//		String host = "pop3.163.com";
//		Integer port = 110;
//		String file = null;
//		String username = "lepintongzhaopin@163.com";
//		String password = "lepintong201401";
//
//		Message message[] = this.getMessages(protocol, host, port, file,
//				username, password);
//		Integer mesLength = message.length;
//		String[] allLinks = new String[mesLength];
//		ReciveOneMail pmm = null;
//
//		for (int i = 0; i < mesLength; i++) {
//			pmm = new ReciveOneMail((MimeMessage) message[i]);
//			try {
//				pmm.getMailContent((Part) message[i]);
//				pmm.setAttachPath("c:\\");
//				pmm.saveAttachMent((Part) message[i]);
//				System.out.println(pmm.getBodyText());
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			Document doc = Jsoup.parse(pmm.getBodyText());
//			Element body = doc.body();
//			Elements links = body.getElementsByTag("a");
//
//			for (int k = 0; k < links.size(); k++) {
//				allLinks[k] = links.get(k).attr("href");
//				System.out.println(allLinks[k]);
//				break;
//			}
//			break;
//		}
//		return allLinks;
//	}

	
	
	

//	public void testa(Part part) throws Exception {
//		String fileName = "";
//		if (part.isMimeType("multipart/*")) {
//			Multipart mp = (Multipart) part.getContent();
//			for (int i = 0; i < mp.getCount(); i++) {
//				BodyPart mpart = mp.getBodyPart(i);
//				String disposition = mpart.getDisposition();
//				if ((disposition != null)
//						&& ((disposition.equals(Part.ATTACHMENT)) || (disposition
//								.equals(Part.INLINE)))) {
//					fileName = mpart.getFileName();
//					if (fileName.toLowerCase().indexOf("gb2312") != -1) {
//						fileName = MimeUtility.decodeText(fileName);
//					}
//					// mpart.getContent();
//					// File input = new File(mpart.getInputStream());
//					// mpart.get
//					// Jsoup.parse(in, charsetName, baseUri);
//					// saveFile(fileName, mpart.getInputStream());
//				} else if (mpart.isMimeType("multipart/*")) {
//					testa(mpart);
//				} else {
//					fileName = mpart.getFileName();
//					if ((fileName != null)
//							&& (fileName.toLowerCase().indexOf("GB2312") != -1)) {
//						fileName = MimeUtility.decodeText(fileName);
//						// saveFile(fileName, mpart.getInputStream());
//					}
//				}
//			}
//		} else if (part.isMimeType("message/rfc822")) {
//			testa((Part) part.getContent());
//		}
//	}

	

	
	
}
