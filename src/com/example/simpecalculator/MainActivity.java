package com.example.simpecalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	String PATTERN_EXPRESSION = "([0-9]+[.]{0,1}[0-9]+)([*\\/+-])([0-9]+[.]{0,1}[0-9]+)";
	
	TextView input;
	StringBuilder sb = new StringBuilder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		input = (TextView) findViewById(R.id.inputField);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.clear:
			input.setText("0");
			break;
		case R.id.equal:
			input.setText(calculate(input.getText().toString()));
			sb.setLength(0);
			break;
		default:
			TextView temp = (TextView) v;
			assignSymbol(temp.getText().toString());
			input.setText(sb.toString());
		}
	}
	
	public String calculate(String expression) {
		Pattern pattern = Pattern.compile(PATTERN_EXPRESSION);
		Matcher matcher = pattern.matcher(expression);
		if (matcher.matches()) {
			String temp = matcher.group(1);
			Double arg1 = Double.parseDouble(temp);
			String operation = matcher.group(2);
			temp = matcher.group(3);
			Double arg2 =Double.parseDouble(temp);
			return getResult(arg1, arg2, operation);
		} else {
			return "err";
		}
	}
	
	public void assignSymbol(String symbol) {
		sb.append(symbol);
	}
	
	public String getResult(Double arg1, Double arg2, String operation) {
		if (operation.equalsIgnoreCase("-")) {
			return String.valueOf(arg1-arg2);
		} else if (operation.equalsIgnoreCase("+")) {
			return String.valueOf(arg1+arg2);
		} else if (operation.equalsIgnoreCase("/")) {
			String result = "err";
			try {
				result = String.valueOf(arg1/arg2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		} else if (operation.equalsIgnoreCase("*")) {
			return String.valueOf(arg1*arg2);
		}
		return "err";
	}
}
