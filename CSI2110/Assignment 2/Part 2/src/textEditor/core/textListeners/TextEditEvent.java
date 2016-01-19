package textEditor.core.textListeners;

/**
 * Class defining a TextEditEvent. Objects of this class would be pushed into a stack
 * and popped whenever an undo operation is called
 */
public class TextEditEvent {
	public static final int INSERT_EVENT=0;
	public static final int REMOVE_EVENT=1;
	
	private int eventType;
	private String editStr;
	private int editOffset;
	private int editLength;

	public TextEditEvent(int eventType, String editStr, int editOffset,
			int editLength) {
		this.eventType = eventType;
		this.editStr = editStr;
		this.editOffset = editOffset;
		this.editLength = editLength;
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public String getEditStr() {
		return editStr;
	}

	public void setEditStr(String editStr) {
		this.editStr = editStr;
	}

	public int getEditOffset() {
		return editOffset;
	}

	public void setEditOffset(int editOffset) {
		this.editOffset = editOffset;
	}

	public int getEditLength() {
		return editLength;
	}

	public void setEditLength(int editLength) {
		this.editLength = editLength;
	}
}
