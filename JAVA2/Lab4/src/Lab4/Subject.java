package Lab4;


public class Subject implements Comparable<Subject>{

	private String title;
	private int mark;
	private String type;
	private int maxMark;
	
	private final String M = "Mandatory", EI = "ElectiveI", EII = "ElectiveII";
	
	public Subject() {};
	
	public Subject(String title , int mark , String type , int maxMark) {
		this.title=title;
		this.mark=mark;
		this.type=type;
		this.maxMark=maxMark;
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

	
	public int getMaxMark() {
		return maxMark;
	}

	public void setMaxMark(int maxMark) {
		this.maxMark = maxMark;
	}

	@Override
	public String toString() {
		return "Subject [title=" + title + ", mark=" + mark + ", type=" + type + "]";
	}

	@Override
	public int compareTo(Subject o) {
		if (this.title.equals(o.title)) {
			if(this.mark - o.mark > 0)
				return 1;
			else if (equals(o))
				return 0;
			return -1;
		}
		return -1;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Subject) 
			return (this.title.equals(((Subject)obj).title) && this.mark == 
			((Subject)obj).mark);
		return false;
	}
	
}