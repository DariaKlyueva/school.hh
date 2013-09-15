package task.cv;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * 
 * @author Dasha Klyueva
 * 
 */
public class Activity1 extends Activity implements OnClickListener {

	/**
	 * Данные для выпадающего списка
	 */
	private String[] data = { "Мужской", "Женский" };

	/**
	 * Ответ на резюме
	 */
	public static String answer;

	/**
	 * Интент для вызова второго activity
	 */
	private Intent intent;

	/**
	 * Выпадающий список
	 */
	private Spinner spinner;

	/**
	 * Флаг для отображеня даилога с ответом
	 */
	public static boolean flag = false;

	/**
	 * Данные пользователя
	 */
	public static String first, last, middle, date, gender, position, salary,
			phone, email;

	/**
	 * Дата 
	 */
	private int year, month, day;
	
	/**
	 * Поле ввода даты рождения
	 */
	private TextView dateOfBirth;
	
	/**
	 * Диальговое окно
	 */
	private AlertDialog.Builder alert;

	@Override
	protected void onResume() {
		super.onResume();

		if (flag) {
			dialog();
		}
		flag = false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity1);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		Button send = (Button) findViewById(R.id.send);
		send.setOnClickListener(this);

		dateOfBirth = (TextView) findViewById(R.id.date);
		dateOfBirth.setOnClickListener(this);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, data);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner = (Spinner) findViewById(R.id.gender);
		spinner.setAdapter(adapter);
		spinner.setPrompt("Выберете пол");
		
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.date:
			showDialog(1);
			break;
		case R.id.send:
			String s;

			EditText buf = (EditText) findViewById(R.id.last);
			s = buf.getText().toString();
			if (s.length() == 0) {
				show("Фамилия");
				buf.requestFocus();
				return;
			}
			last = s;

			buf = (EditText) findViewById(R.id.first);
			s = buf.getText().toString();
			if (s.length() == 0) {
				show("Имя");
				buf.requestFocus();
				return;
			}
			first = s;

			buf = (EditText) findViewById(R.id.middle);
			s = buf.getText().toString();
			if (s.length() == 0) {
				show("Отчество");
				buf.requestFocus();
				return;
			}
			middle = s;

			dateOfBirth = (TextView) findViewById(R.id.date);
			s = dateOfBirth.getText().toString();
			if (s.length() == 0 ) {
				show("Дата рождения");
				dateOfBirth.requestFocus();
				return;
			}
			date = s;

			s = spinner.getSelectedItem().toString();
			if (s.length() == 0) {
				show("Пол");
				return;
			}
			gender = s;
			
			buf = (EditText) findViewById(R.id.position);
			s = buf.getText().toString();
			if (s.length() == 0) {
				show("Должность");
				buf.requestFocus();
				return;
			}
			position = s;

			buf = (EditText) findViewById(R.id.salary);
			s = buf.getText().toString();
			if (s.length() == 0) {
				show("Зарплата");
				buf.requestFocus();
				return;
			}
			salary = s;

			buf = (EditText) findViewById(R.id.phone);
			s = buf.getText().toString();
			if (s.length() == 0) {
				show("Контактный телефон");
				buf.requestFocus();
				return;
			}
			phone = s;

			buf = (EditText) findViewById(R.id.mail);
			s = buf.getText().toString();
			if (s.length() == 0) {
				show("Адрес элетронной почты");
				buf.requestFocus();
				return;
			}
			email = s;

			intent = new Intent(this, Activity2.class);
			startActivity(intent);
			break;
		}

	}
	
    OnDateSetListener myCallBack = new OnDateSetListener() { 
	@Override
	public void onDateSet(DatePicker datepicker, int year, int month, int day) {
		Activity1.this.year = year;
		Activity1.this.month = month;
		Activity1.this.day = day;
		dateOfBirth.setText(day+"."+(month+1)+"."+year);
	}
    };
	
	/**
	 * Диалог для выбора даты
	 */

    
    protected Dialog onCreateDialog(int id) {
        if (id == 1) {
          DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, year, month, day);
          return tpd;
        }
        return super.onCreateDialog(id);
      }

	/**
	 * Диалог с ответом
	 */
	private void dialog() {
		alert = new AlertDialog.Builder(this);
		alert.setTitle("Ответ");
		alert.setMessage(answer);
		alert.setNeutralButton("ОК", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});
		alert.show();
	}

	/**
	 * Диалог, всплывающий при неаполненой форме
	 * 
	 * @param s
	 *            Имя незаполненного поля
	 */
	private void show(String s) {
		alert = new AlertDialog.Builder(this);
		alert.setTitle("Резюме не заполнено!");
		alert.setMessage("Поле \"" + s + "\" пустое. Заполните его.");
		alert.setNeutralButton("ОК", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});
		alert.show();
	}

}
