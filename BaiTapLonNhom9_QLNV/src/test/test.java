package test;

import javax.swing.UIManager;

import view.QuanLyNhanVienView;
import view.login;

public class test {
	public static void main(String[] args) {
		try {
			
			login lg= login.getInstance();
			lg.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}