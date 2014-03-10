package mailResolved.templates;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 智联邮件解析模板类
 * 
 * @author Sylvan__
 * 
 */
public class ParsingTemplZL {

	private Document doc;
	private Element resumeDiv;

	public ParsingTemplZL() {
	}

	public ParsingTemplZL(Document doc) {
		this.doc = doc;
		this.resumeDiv = doc.select("#resumeDetail").first();
	}

	public String getUsername() {
		String username = getResumeDiv().select(".main-title-fl").text();
		return username;
	}

	public Integer getSex() {
		String blue = getResumeDiv().select(".summary-top span").first().html();
		String sexString = blue.split("&nbsp;&nbsp;&nbsp;&nbsp;")[0];
		return "男".equals(sexString.trim()) ? 1 : 0;
	}

	public Integer getAge() {
		String blue = getResumeDiv().select(".summary-top span").first().html();
		String sexString = blue.split("&nbsp;&nbsp;&nbsp;&nbsp;")[1].split("岁")[0];
		return Integer.valueOf(sexString);
	}

	public String getEducation() {
		String education = "";
		Elements Lables = getDoc().select(":containsOwn(教育经历)");
		if (Lables.size() > 0) {
			Element label = Lables.first();
			String stuExperience = label.nextElementSibling().html();
			education = stuExperience.split("&nbsp;&nbsp;")[3].replaceAll(
					"<br />", "").trim();
		}
		return education;
	}

	public String getMajor() {
		String education = "";
		Elements Lables = getDoc().select(":containsOwn(教育经历)");
		if (Lables.size() > 0) {
			Element label = Lables.first();
			String stuExperience = label.nextElementSibling().html();
			education = stuExperience.split("&nbsp;&nbsp;")[2];
		}
		return education;
	}

	public String getCollege() {
		String education = "";
		Elements Lables = getDoc().select(":containsOwn(教育经历)");
		if (Lables.size() > 0) {
			Element label = Lables.first();
			String stuExperience = label.nextElementSibling().html();
			education = stuExperience.split("&nbsp;&nbsp;")[1];
		}
		return education;
	}

	public String getAddress() {
		String address = null;
		String blue = getResumeDiv().select(".summary-top").first().text();
		address = blue.split("现居住地：")[1].split(" \\| ")[0];
		return address;
	}

	public String getPhone() {
		String phone = null;
		String blue = getResumeDiv().select(".summary-bottom").first().html();
		phone = blue.split("手机：")[1].split("<br \\/>")[0].trim();
		return phone;
	}

	public String getEmail() {
		String email = getResumeDiv().select(".summary-bottom a").first()
				.text();
		return email.trim();
	}

	public String getStuExperience() {
		String stuExperience = null;
		Elements stuExperienceLabels = getDoc().select(
				".fc6699cc:containsOwn(教育经历)");
		if (stuExperienceLabels.size() > 0) {
			Element stuExperienceLabel = stuExperienceLabels.first();
			stuExperience = stuExperienceLabel.siblingElements().text();
		}
		return stuExperience;
	}

	public String getJobExperience() {
		String jobExperience = null;
		Elements lables = getDoc().select(":containsOwn(工作经历)");
		if (lables.size() > 0) {
			Element lable = lables.first();
			jobExperience = lable.siblingElements().text();
		}
		return jobExperience;
	}

	public String getAssess() {
		String assess = null;
		Elements lables = getDoc().select(":containsOwn(自我评价)");
		if (lables.size() > 0) {
			Element lable = lables.first();
			assess = lable.siblingElements().text();
		}
		return assess;
	}

	public Element getResumeDiv() {
		return resumeDiv;
	}

	public void setResumeDiv(Element resumeDiv) {
		this.resumeDiv = resumeDiv;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

}
