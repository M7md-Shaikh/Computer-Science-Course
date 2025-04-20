package one;

public class Subject {

	private String title;
	private int mark;
	private String type;
	
	public Subject(String title , int mark , String type) {
		this.title=title;
		this.mark=mark;
		this.type=type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title != null)
			this.title = title;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		if (mark >= 0 || mark <= 200)
			this.mark = mark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (type.equals(M) || type.equals(EI) || type.equals(EII))
			this.type = type;
	}

	@Override
	public String toString() {
		return "Subject [title=" + title + ", mark=" + mark + ", type=" + type + "]";
	}
	
}
