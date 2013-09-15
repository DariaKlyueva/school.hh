package task.cv;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
 * 
 * @author Dasha Klyueva
 *
 */
public class Activity2 extends Activity implements OnClickListener {
	/**
	 * Диалоговое окно
	 */
	private AlertDialog.Builder alert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		Button respondButton = (Button) findViewById(R.id.respond);
		respondButton.setOnClickListener(this);

		TextView buf = (TextView) findViewById(R.id.last);
		buf.setText(Activity1.last);

		buf = (TextView) findViewById(R.id.first);
		buf.setText(Activity1.first);

		buf = (TextView) findViewById(R.id.middle);
		buf.setText(Activity1.middle);

		buf = (TextView) findViewById(R.id.date);
		buf.setText(Activity1.date);

		buf = (TextView) findViewById(R.id.gender);
		buf.setText(Activity1.gender);

		buf = (TextView) findViewById(R.id.salary);
		buf.setText(Activity1.salary);

		buf = (TextView) findViewById(R.id.position);
		buf.setText(Activity1.position);

		buf = (TextView) findViewById(R.id.phone);
		buf.setText(Activity1.phone);

		buf = (TextView) findViewById(R.id.mail);
		buf.setText(Activity1.email);
	}

	@Override
	public void onClick(View v) {
		String s;
		EditText buf = (EditText) findViewById(R.id.respond_text);
		s = buf.getText().toString();
		if (s.length() == 0) {
			show();
			return;
		}
		Activity1.answer = s;
		Activity1.flag = true;
		finish();
	}

	/**
	 * Отображает диалоговое окно
	 */
	private void show() {
		alert = new AlertDialog.Builder(this);
		alert.setTitle("Ответ не написан!");
		alert.setMessage("Введите ваш ответ на резюме и отправте его.");
		alert.setNeutralButton("ОК", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});
		alert.show();
	}

}
