package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import view.login;
import view.DangKi;

public class dangKiController implements ActionListener ,MouseListener{
	private DangKi dangkiTaiKhoan;
	private login dangnhap;
	private JPopupMenu popupMenu;
	public dangKiController(DangKi dk) {
		
		this.dangkiTaiKhoan =  dk;
		createPopupMenu();
	}
	 private void createPopupMenu() {
	        popupMenu = new JPopupMenu();
	        JMenuItem menuItemEdit = new JMenuItem("Chọn để sửa");
	        menuItemEdit.addActionListener(e ->dangkiTaiKhoan.showTTtaikhoanDaChon());
	        popupMenu.add(menuItemEdit);
	    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cm = e.getActionCommand();
		try {
			switch(cm) {
				case "Thêm" :{
					this.dangkiTaiKhoan.ThucHienthemTaiKhoan();
					break;
				}
				case"Cập nhật" :{
					this.dangkiTaiKhoan.ThucHienUpdateTaiKhoan();
					break;
				}
				case "Xóa":{
					this.dangkiTaiKhoan.thucHienXoa();
					break;
				}
				case "Refresh" :{
					this.dangkiTaiKhoan.refresh();
					break;
				}
				case "Thoát":{
					this.dangkiTaiKhoan.thoatKhoiChuongTrinh();
					break;
				}
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	
	  @Override
	    public void mouseClicked(MouseEvent e) {
	        // Not used
	    }

	    @Override
	    public void mousePressed(MouseEvent e) {
	        if (e.isPopupTrigger()) {
	            showPopup(e);
	            
	        }
	    }

	    @Override
	    public void mouseReleased(MouseEvent e) {
	        if (e.isPopupTrigger()) {
	            showPopup(e);
	        }
	    }

	    private void showPopup(MouseEvent e) {
	        popupMenu.show(e.getComponent(), e.getX(), e.getY());
	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {
	        // Not used
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
	        // Not used
	    }
}
