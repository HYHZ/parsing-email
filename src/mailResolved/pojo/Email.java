package mailResolved.pojo;

import java.io.ByteArrayOutputStream;
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

import org.jsoup.nodes.Element;

/**
 * Email基类,定义一些需要解析的邮件的公共配置和方法
 * @author Sylvan__
 *
 */
public class Email {
	
	public static String PROTOCOL = "pop3";
	public static Integer PORT = 110;
	public static String FILE = null;
	
	public Boolean isDebug = false;

	/**
	 * 返回邮件列表数据集
	 * @param protocol
	 * @param host
	 * @param port
	 * @param file
	 * @param username
	 * @param password
	 * @return
	 */
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
	
	/**
	 * 解析邮件附件中的HTML文件
	 * @param part
	 * @return 返回附件HTML源代码
	 * @throws MessagingException
	 * @throws IOException
	 */
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
					//			if (fileName.toLowerCase().indexOf("gb2312") != -1) {
					//				fileName = MimeUtility.decodeText(fileName);
					//			}
					byte[] getData =this.readInputStream(mpart.getInputStream());     //获得网站的二进制数据  
					html = new String(getData, "utf-8");  
				//	System.out.println("File name is :"+new String(fileName.getBytes("gb2312"),"utf-8"));  
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
	
	/**
	 * 自定义选择器1
	 * @param e
	 * @param containsOwn
	 * @return
	 */
	public String selfSelectorText1(Element e,String containsOwn){
		return e.select(":containsOwn("+containsOwn+")").first()
				.nextElementSibling().text();
	}
	
	/**
	 * 自定义选择器2
	 * @param e
	 * @param containsOwn
	 * @return
	 */
	public String selfSelectorText2(Element e,String containsOwn){
		return e.select(":containsOwn("+containsOwn+")").first().parent()
				.nextElementSibling().text();
	}

}
