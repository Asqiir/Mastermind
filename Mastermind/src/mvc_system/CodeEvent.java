package mvc_system;

import java.awt.event.ActionEvent;

public class CodeEvent extends ActionEvent {
	private static final long serialVersionUID = 6813849048361538204L;
	Code c;

	public CodeEvent(Object source, int id, String command, Code co) {
		super(source, id, command);
		c = co;
	}

	public Code getCode()
	{
		return c;
	}
}
