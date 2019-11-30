package me.Straiker123;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NumbersAPI {
	String fromString;
	public NumbersAPI(String string) {
		if(string!=null)
		fromString = string;
	}
	
	public double calculate() {
		ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
			return getDouble(engine.eval(fromString).toString());
		} catch (ScriptException e) {
		}
		return 0;
	}
	public double getDouble() {
		String a=fromString.replaceAll("[a-zA-Z]+", "").replace(",", ".");
		if (isDouble(a)) {
		return Double.parseDouble(a);
		}else {
		return 0.0;
	}}
	
	
	private double getDouble(String s) {
		 String aa =s.replaceAll("[a-zA-Z]+", "").replace(",", ".");
		if (isDouble(aa)) {
			return Double.parseDouble(aa);
			}
			else {
			return 0.0;
		}
	}
	private boolean isDouble(String a) {
		 String aa =a.replaceAll("[a-zA-Z]+", "").replace(",", ".");
		try {
			Double.parseDouble(aa);
		} catch (NumberFormatException e) {
		return false;
		}
		return true;
	}
	
	public boolean isDouble() {
		String a =fromString.replaceAll("[a-zA-Z]+", "").replace(",", ".");
		try {
			Double.parseDouble(a);
		} catch (NumberFormatException e) {
		return false;
		}
		return true;
	}
	public long getLong() {
		String a=fromString.replaceAll("[a-zA-Z]+", "");
		if (isLong()) {
		return Long.parseLong(a);
		}
		else {
		return 0;
	}}
	public boolean isLong() {
		String a =fromString.replaceAll("[a-zA-Z]+", "");
		try {
		Long.parseLong(a);
		} catch (NumberFormatException e) {
		return false;
		}
		return true;
	}
	public int getInt() {
		String a=fromString.replaceAll("[a-zA-Z]+", "");
		if (isInt()) {
		return Integer.parseInt(a);
		}
		else {
		return 0;
	}}
	public boolean isInt() {
		String a =fromString.replaceAll("[a-zA-Z]+", "");
		try {
		Integer.parseInt(a);
		} catch (NumberFormatException e) {
		return false;
		}
		return true;
	}
}
