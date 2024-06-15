package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JMenuItem;
import view.QuanLyNhanVienView;

public class QuanLyNhanVienController implements ActionListener, MouseListener {
	private QuanLyNhanVienView view;
	private JPopupMenu popupMenu;

	public QuanLyNhanVienController(QuanLyNhanVienView view) {
		this.view = view;
		createPopupMenu();
	}

	private void createPopupMenu() {
		popupMenu = new JPopupMenu();
		JMenuItem menuItemEdit = new JMenuItem("Chọn để sửa");
		menuItemEdit.addActionListener(e -> view.hienThiThongTinNhanVienDaChon());
		popupMenu.add(menuItemEdit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		try {
			switch (cm) {
			case "Thêm":
				this.view.thucHienThemNhanVien();
				break;

			case "Cập nhật":

				this.view.thucHienUpdateNhanVien();
				break;

			case "Xóa":
				if (!isAdmin()) {
					showPermissionDeniedMessage();
					break;
				}
				this.view.thucHienXoa();
				break;
			case "Refresh":
				this.view.xoaForm();
				break;
			case "Tìm kiếm":
				this.view.thucHienTim();
				break;
			case "Hủy":
				this.view.thucHienTaiLaiDuLieu();
				break;
			case "About Me":
				this.view.hienThiAbout();
				break;
			case "Exit":
				this.view.thoatKhoiChuongTrinh();
				break;
			case "Tổng hợp báo cáo":
				if (!isAdmin()) {
					showPermissionDeniedMessage();
					break;
				}
				this.view.thucHienXuatBaoCao();
				break;
			case "Đăng xuất":
				this.view.dangxuat();
				break;
			case "Quản lý tài khoản":
				this.view.QuanLiTaiKhoan();
				break;
			case "Đổi mật khẩu" :
				this.view.DoiMatKhau();
				break;
			default:
				throw new UnsupportedOperationException("Unsupported action: " + cm);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean isAdmin() {
		return "admin".equalsIgnoreCase(view.getTextFieldTaikhoan().getText());
	}

	private void showPermissionDeniedMessage() {
		JOptionPane.showMessageDialog(view, "Bạn không được phép thực hiện hành động này.", "Permission Denied",
				JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Not used
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger()) {
			showPopup(e);
			this.view.hienThiThongTinNhanVienDaChon();
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
