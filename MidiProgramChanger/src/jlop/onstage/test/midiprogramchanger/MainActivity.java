package jlop.onstage.test.midiprogramchanger;

import java.util.Set;

import jlop.onstage.test.midiprogrammchanger.R;
import jp.kshoji.driver.midi.activity.AbstractSingleMidiActivity;
import jp.kshoji.driver.midi.device.MidiInputDevice;
import jp.kshoji.driver.midi.device.MidiOutputDevice;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AbstractSingleMidiActivity {

	/*
	 * (non-Javadoc)
	 * setup screen
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * setup midi events
	 * @see jp.kshoji.driver.midi.listener.OnMidiDeviceDetachedListener#onDeviceDetached(android.hardware.usb.UsbDevice)
	 */
	public void onSendProgramChangeClick(View view) {
		EditText editText = (EditText) findViewById(R.id.editMidiProgram);
		if (editText != null && editText.getText() != null) {
			try {
				int program = Integer.parseInt(editText.getText().toString());
				if (program < 0 || program > 127) throw new NumberFormatException();
				sendMidiProgramChange(program);
			}
			catch(NumberFormatException nfe) {
				Toast.makeText(this, "Provide number between 0 and 127.", Toast.LENGTH_LONG).show();
			}
		}
	}
	
	private boolean sendMidiProgramChange(int program) {
		return sendMidiProgramChange(getMidiOutputDevice(), 0, 0, program);
	}
	
	private boolean sendMidiProgramChange(MidiOutputDevice receiver, int cable, int channel, int program) {
		if (receiver != null) {
			receiver.sendMidiProgramChange(cable, channel, program);
			Toast.makeText(this, "Program " + program + " sent to " + receiver.getUsbDevice().getDeviceName() + ".", Toast.LENGTH_LONG).show();
			return true;
		} else {
			Toast.makeText(this, "Program " + program + " not sent. No MIDI device connected.", Toast.LENGTH_LONG).show();
			return false;
		}
	}
	
	@Override
	public void onDeviceDetached(UsbDevice usbDevice) {
		Toast.makeText(this, "USB MIDI Device " + usbDevice.getDeviceName() + " has been detached.", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDeviceAttached(UsbDevice usbDevice) {
		Toast.makeText(this, "USB MIDI Device " + usbDevice.getDeviceName() + " has been attached.", Toast.LENGTH_LONG).show();	
	}

	@Override
	public void onMidiCableEvents(MidiInputDevice arg0, int arg1, int arg2,
			int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiChannelAftertouch(MidiInputDevice arg0, int arg1,
			int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiControlChange(MidiInputDevice arg0, int arg1, int arg2,
			int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiMiscellaneousFunctionCodes(MidiInputDevice arg0,
			int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiNoteOff(MidiInputDevice arg0, int arg1, int arg2,
			int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiNoteOn(MidiInputDevice arg0, int arg1, int arg2,
			int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiPitchWheel(MidiInputDevice arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiPolyphonicAftertouch(MidiInputDevice arg0, int arg1,
			int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiProgramChange(MidiInputDevice sender, int cable, int channel, int program) {
		Toast.makeText(this, "USB MIDI Device " + sender.getUsbDevice().getDeviceName() + 
				" - ProgramChange cable: " + cable + ", channel: " + channel + ", program: " + program, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onMidiSingleByte(MidiInputDevice arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiSystemCommonMessage(MidiInputDevice arg0, int arg1,
			byte[] arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiSystemExclusive(MidiInputDevice arg0, int arg1,
			byte[] arg2) {
		// TODO Auto-generated method stub
		
	}
	
}
