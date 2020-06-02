package tools;

/**
 * Class program timing 
 * @author Yilia Lui, Alex Marchant, Tom Pound
 * @version - 2.1
 * @date - 01/06/20
 *
 */

public class TimingObject {
	//global variables
	private int slideNumber;
	private int startTime;
	private int endTime;
	private boolean isDrawn;
	private String type;
	private int index;
	
	/**
	 * Constructor for the timing object
	 * @param sn - slide number
	 * @param st - start time
	 * @param et - end time
	 * @param id - is drawn?
	 * @param t - media type
	 * @param i - media type index
	 */
	public TimingObject(int sn, int st, int et, boolean id, String t, int i) {
		slideNumber = sn;
		startTime = st;
		endTime = et;
		isDrawn = id;
		type = t;
		index = i;
	}
	
	public TimingObject(int sn, int st, boolean id, String t, int i) {
		slideNumber = sn;
		startTime = st;
		isDrawn = id;
		type = t;
		index = i;
		endTime = 3600000;
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
