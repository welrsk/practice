package edu.swu.shoujibao;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

public class shanghu_login extends Activity implements OnClickListener {

	private Button bt1;
	private CheckBox ck1;
	// 声明一个SharedPreferences对象和一个Editor对象
	private SharedPreferences preferences1;
	private SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shanghu_login);
		Button bt1 = (Button) findViewById(R.id.button1);
		bt1.setOnClickListener(this);
		TextView tv1 = (TextView) findViewById(R.id.textView1);
		tv1.setOnClickListener(this);

		ck1 = (CheckBox) findViewById(R.id.checkBox1);
		EditText et1 = (EditText) findViewById(R.id.editText1);
		EditText et2 = (EditText) findViewById(R.id.editText2);

		preferences1 = getSharedPreferences("UserInfo", MODE_PRIVATE);
		String name = preferences1.getString("userName", "");
		et1.setText(name);

		String pwd = preferences1.getString("userPassword", "");
		et2.setText(pwd);

		boolean isRemmber = preferences1.getBoolean("isRemmber", false);
		ck1.setChecked(isRemmber);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0.getId() == R.id.button1) {
			login();
		} else {
			startActivity(new Intent(this, RegisterActivity.class));
		}
	}

	private void login() {
		// TODO Auto-generated method stub
		EditText et1 = (EditText) findViewById(R.id.editText1);
		EditText et2 = (EditText) findViewById(R.id.editText2);

		final String name = et1.getText().toString().trim();
		final String pwd = et2.getText().toString().trim();
		String admin = "admin";
		String geren = "1235";
		String xx = "xxx";
		String xxpwd = "1221";

		if (pwd.length() == 0 && name.length() == 0) {
			Toast.makeText(getApplicationContext(), "密码或用户名不能为空", 1).show();

		} else {

			if (name.equals(xx) && pwd.equals(xxpwd)) {

				Toast.makeText(getApplicationContext(), "商户用户", 1).show();

				AVUser.logInInBackground(name, pwd,
						new LogInCallback<AVUser>() {

							@Override
							public void done(AVUser arg0, AVException arg1) {

								// Toast.makeText(shanghu_login.this,
								// "done success", 1).show();
								// TODO Auto-generated method stub
								if (arg1 == null) {
									Toast.makeText(shanghu_login.this,
											"login success", 1).show();

									// 如果用户选择了记住用户名
									// 将用户输入的用户名存入储存中，键为userName
									// 如果用户选择了记住密码
									// 将用户输入的密码存入储存中，键为userName
									SharedPreferences.Editor editor;
									preferences1 = getSharedPreferences(
											"UserInfo", MODE_PRIVATE);
									editor = preferences1.edit();
									editor.putString("userName", name);
									editor.putString("userPassword", pwd);
									editor.putBoolean("isRemmber",
											ck1.isChecked());
									editor.commit();

									startActivity(new Intent(
											shanghu_login.this,
											MainActivity.class));

								} else {
									Toast.makeText(getApplicationContext(),
											arg1.getMessage(), 1).show();
								}
							}
						});

			}

			else {

				Toast.makeText(getApplicationContext(), "此账号不是商户用户", 1).show();

			}

		}
	}
}
