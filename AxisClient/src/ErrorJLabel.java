import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;


public class ErrorJLabel extends JLabel{

	private static final long serialVersionUID = 1L;
	private long mSecondsStandard= 1500;

	public ErrorJLabel(String string) {
		super(string);
	}
	
	public void startMessage(String errMsg) {
		startMessage(errMsg, mSecondsStandard);
	}
	
	/**
	 *dd 
	 * @param errMsg
	 * @param secs in millisecs
	 */
	public void startMessage(String errMsg, long millisecs) {
		this.setText(errMsg);
		this.validate();
		Timer t = new Timer();
		t.schedule(new ClearMessageTimerTask(), millisecs);
	}
	
	class ClearMessageTimerTask extends TimerTask {
		@Override
		public void run() {
			ErrorJLabel.this.setText("");
			this.cancel();
		}
	}

}
