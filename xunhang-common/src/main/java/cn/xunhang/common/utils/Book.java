package cn.xunhang.common.utils;

/*
 * <book id = "1">
		<name>Java程序教程</name>
		<author>Tony</author>
		<year>2018</year>
		<price>90</price>
	</book>
	<book id = "2">
		<name>安徒生童话故事</name>
		<year>2000</year>
		<language>English</language>
		<price>55</price>
		<version>2.0</version>
	</book>
 */
public class Book {
	private String id;
	private String name;
	private String anthor;
	private String year;
	private String price;
	private String language;
	private String version;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAnthor() {
		return anthor;
	}
	public void setAnthor(String anthor) {
		this.anthor = anthor;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", anthor=" + anthor + ", year=" + year + ", price=" + price
				+ ", language=" + language + ", version=" + version + "]";
	}
	
	
}
