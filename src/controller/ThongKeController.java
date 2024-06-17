package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.QuanLyNhanVienView;
import view.ThongKe;
import view.ThongKeChiTiet;

public class ThongKeController implements ActionListener {
private ThongKe thongke;
private QuanLyNhanVienView view;
public ThongKeController() {
	
}

public ThongKeController(ThongKe thongke) {
    this.thongke = thongke;
    
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cm = e.getActionCommand();
        try {
            switch (cm) {
                case "Thống kê chi tiết": {
                    // Gọi phương thức để xử lý sắp xếp
                	
                	this.thongke.getInstance().thongkechitiet();
                    break;
                }
                case "Thoát": {
                    // Gọi phương thức để xử lý liệt kê lương
                	this.thongke.getInstance().thoat();
                	
                    break;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + e2.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
	}

}
