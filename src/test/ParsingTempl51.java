package test;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParsingTempl51 {

	private Document doc;
	private Element resumeDiv;

	public ParsingTempl51() {
	}

	public ParsingTempl51(Document doc) {
		this.doc = doc;
		this.resumeDiv = doc.select("#divResume").first();
	}

	public String getUsername() {
		return getResumeDiv().select("table table:eq(1) tr:eq(0) span:eq(1) b")
				.text();
	}

	public Integer getSex() {
		String blue = getResumeDiv().select(".blue").first().text();
		String sexString = blue.split("\\|")[1].replaceAll(" ", "").replaceAll(" ", "");
		return "男".equals(sexString.trim())?1:0;
	}
	
	public String getAddress(){
		String address = null;
		Elements labels = getDoc().select(":containsOwn(地　址)");
		if (labels.size() > 0) {
			Element label = labels.first();
			address = label.nextElementSibling().text();
		}else{
			Elements labels2 = getDoc().select(":containsOwn(居住地)");
			if (labels2.size() > 0) {
				Element label = labels2.first();
				address = label.nextElementSibling().text();
			}
		}
		return address;
	}

	public String getPhone(){
		String phone = null;
		Elements labels = getDoc().select(":containsOwn(电　话)");
		if (labels.size() > 0) {
			Element label = labels.first();
			phone = label.nextElementSibling().text().split("（")[0];
		}
		return phone;
	}
	
	public String getEmail(){
		String email = null;
		Elements labels = getDoc().select(":containsOwn(E-mail：)");
		if (labels.size() > 0) {
			Element label = labels.first();
			email = label.nextElementSibling().text();
		}
		return email;
	}
	
	public String getStuExperience() {
		String stuExperience = null;
		Elements stuExperienceLabels = getDoc().select("td.cvtitle:containsOwn(教育经历)");
		if (stuExperienceLabels.size() > 0) {
			Element stuExperienceLabel = stuExperienceLabels.first().parent();
			Integer index = stuExperienceLabel.elementSiblingIndex();
			stuExperience = stuExperienceLabel.parent().child(index + 3).text();
		}
		return stuExperience;
	}

	public String getJobExperience(){
		String jobExperience = null;
		try {
			Elements lables = getDoc().select("td.cvtitle:containsOwn(工作经验)");
			if (lables.size() > 0) {
				Element label = lables.first().parent();
				Integer index = label.elementSiblingIndex();
				jobExperience = label.parent().child(index + 3).text();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobExperience;
	}
	
	public String getAssess(){
		String assess = null;
		Elements lables = getDoc().select("td.cvtitle:containsOwn(自我评价)");
		if (lables.size() > 0) {
			Element label = lables.first().parent();
			Integer index = label.elementSiblingIndex();
			assess = label.parent().child(index + 3).text();
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
