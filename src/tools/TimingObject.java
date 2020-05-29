package tools;

public class TimingObject {
	private int slideNumber;
	private int startTime;
	private int endTime;
	private boolean isDrawn;
	private String type;
	private int index;
	public TimingObject(int sn, int st, int et, boolean id, String t, int i) {
		slideNumber = sn;
		startTime = st;
		endTime = et;
		isDrawn = id;
		type = t;
		index = i;
		System.out.println(type+":"+slideNumber+":"+index+":"+startTime+":"+endTime+":"+isDrawn);
	}
	public TimingObject(int sn, int st, boolean id, String t, int i) {
		slideNumber = sn;
		startTime = st;
		isDrawn = id;
		type = t;
		index = i;
		endTime = 3600000;
		System.out.println(type+":"+slideNumber+":"+index+":"+startTime+":"+endTime+":"+isDrawn);
	}
	public int getSlideNumber() {
		return slideNumber;
	}
	public int getStartTime() {
		return startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public boolean isDrawn() {
		return isDrawn;
	}
	public void setDrawn(boolean drawn) {
		isDrawn = drawn;
	}
	public String getType() {
		return type;
	}
	public int getIndex() {
		return index;
	}
	
}
