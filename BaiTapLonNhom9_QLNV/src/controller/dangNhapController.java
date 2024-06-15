package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.login;

public class dangNhapController implements ActionListener {
	private login dangnhap;
	
	public dangNhapController(login dangnhap) {
		
		this.dangnhap =  dangnhap;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cm = e.getActionCommand();
		try {
			switch(cm) {
			case "Đăng nhập":{
				this.dangnhap.Loginview();
				break;
			}
			case "Đăng kí":{
				this.dangnhap.dangKiTaiKhoanBenNgoai();
				break;
			}
			case "Thoát":{
				this.dangnhap.thoat();
				break;
			}
			}
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}

}
