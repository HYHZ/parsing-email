package test;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

import org.jsoup.nodes.Element;

public class Email {
	
	public static String PROTOCOL = "pop3";
	public static Integer PORT = 110;
	public static String FILE = null;

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
