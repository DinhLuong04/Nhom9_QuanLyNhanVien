package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.dangKiController;
import controller.dangNhapController;
import model.NhanVien;
import model.QLNVmodel;
import model.TaiKhoan;
import view.login;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Color;

public class DangKi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldTaiKhoan;
	private JTextField textFieldMatKhau;
	private JTextField textFieldNhapLaiMK;
	private JTable table;
	private QLNVmodel model;
	private List<TaiKhoan> DSTaiKhoan;
	private login lgin;
	private static DangKi instance;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangKi frame = new DangKi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 public static DangKi getInstance() {
	        if (instance == null) {
	            instance = new DangKi();
	        }
	        return instance;
	    }
	public DangKi() {

		this.model = new QLNVmodel();
		this.setTitle("Quản lý tài khoản");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 987, 479);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		ActionListener actionlistener = new dangKiController(this);
		MouseListener mouseactioner=new dangKiController(this);
		JLabel lblTaiKhoan = new JLabel("Tài Khoản");
		lblTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTaiKhoan.setBounds(60, 57, 107, 29);
		contentPane.add(lblTaiKhoan);

		JLabel lblMatKhau = new JLabel("Mật Khẩu");
		lblMatKhau.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMatKhau.setBounds(60, 96, 107, 29);
		contentPane.add(lblMatKhau);

		textFieldTaiKhoan = new JTextField();
		textFieldTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldTaiKhoan.setBounds(260, 57, 175, 28);
		contentPane.add(textFieldTaiKhoan);
		textFieldTaiKhoan.setColumns(10);

		textFieldMatKhau = new JTextField();
		textFieldMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldMatKhau.setBounds(260, 97, 175, 29);
		contentPane.add(textFieldMatKhau);
		textFieldMatKhau.setColumns(10);

		JLabel lblNhapLaiMatKhau = new JLabel("Nhập lại mật khẩu");
		lblNhapLaiMatKhau.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNhapLaiMatKhau.setBounds(60, 135, 190, 30);
		contentPane.add(lblNhapLaiMatKhau);

		textFieldNhapLaiMK = new JTextField();
		textFieldNhapLaiMK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldNhapLaiMK.setBounds(260, 136, 175, 29);
		contentPane.add(textFieldNhapLaiMK);
		textFieldNhapLaiMK.setColumns(10);

		JButton btnThemTaiKhoan = new JButton("Thêm");
		btnThemTaiKhoan.setBackground(new Color(0, 255, 255));
		btnThemTaiKhoan.setForeground(new Color(0, 0, 0));
		btnThemTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThemTaiKhoan.addActionListener(actionlistener);
		btnThemTaiKhoan.setBounds(561, 57, 147, 29);
		contentPane.add(btnThemTaiKhoan);
		ImageIcon originalIcon = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(DangKi.class.getResource("/Icon/add_TK.png")));
		Image originalImage = originalIcon.getImage();
		Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Set your desired width
																							// and height here
		ImageIcon resizedIcon = new ImageIcon(resizedImage);
		btnThemTaiKhoan.setIconTextGap(1);
		btnThemTaiKhoan.setIcon(resizedIcon);
		
		
		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBackground(new Color(255, 255, 0));
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCapNhat.addActionListener(actionlistener);
		btnCapNhat.setBounds(561, 96, 147, 29);
		contentPane.add(btnCapNhat);
		ImageIcon originalIcon1 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(DangKi.class.getResource("/Icon/Update_TK.png")));
		Image originalImage1 = originalIcon1.getImage();
		Image resizedImage1 = originalImage1.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Set your desired width
																							// and height here
		ImageIcon resizedIcon1 = new ImageIcon(resizedImage1);
		 btnCapNhat.setIconTextGap(1);
		 btnCapNhat.setIcon(resizedIcon1);
				
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBackground(new Color(128, 0, 255));
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXoa.addActionListener(actionlistener);
		btnXoa.setBounds(561, 135, 147, 30);
		contentPane.add(btnXoa);
		ImageIcon originalIcon2 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(DangKi.class.getResource("/Icon/delete_TK.png")));
		Image originalImage2 = originalIcon2.getImage();
		Image resizedImage2 = originalImage2.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Set your desired width
																							// and height here
		ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
		btnXoa.setIconTextGap(1);
		 btnXoa.setIcon(resizedIcon2);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setBackground(new Color(255, 128, 0));
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "T\u00E0i kho\u1EA3n", "M\u1EADt kh\u1EA9u" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(128);
		table.getColumnModel().getColumn(1).setPreferredWidth(127);
		table.setBounds(0, 0, 1, 1);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(120, 230, 524, 202);
		contentPane.add(scrollPane);
		table.addMouseListener(mouseactioner);
		JButton btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThoat.addActionListener(actionlistener);
		btnThoat.setBounds(786, 328, 107, 39);
		contentPane.add(btnThoat);
		ImageIcon originalIcon3 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(DangKi.class.getResource("/Icon/Back_TK.png")));
		Image originalImage3 = originalIcon3.getImage();
		Image resizedImage3 = originalImage3.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Set your desired width
																							// and height here
		ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);
		btnThoat.setIconTextGap(1);
		btnThoat.setIcon(resizedIcon3);
		

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBackground(new Color(255, 128, 128));
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRefresh.addActionListener(actionlistener);
		btnRefresh.setBounds(561, 179, 147, 29);
		contentPane.add(btnRefresh);
		ImageIcon originalIcon4 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(DangKi.class.getResource("/Icon/refresh_TK.png")));
		Image originalImage4 = originalIcon4.getImage();
		Image resizedImage4 = originalImage4.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Set your desired width
																							// and height here
		ImageIcon resizedIcon4 = new ImageIcon(resizedImage4);
		btnRefresh.setIconTextGap(1);
		btnRefresh.setIcon(resizedIcon4);
		
		JLabel lblAVTar = new JLabel("");
		lblAVTar.setBounds(732, 90, 202, 240);
		contentPane.add(lblAVTar);
		ImageIcon originalIcon5 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(DangKi.class.getResource("/Icon/Account.png")));
		Image originalImage5 = originalIcon5.getImage();
		Image resizedImage5 = originalImage5.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Set your desired width
																							// and height here
		ImageIcon resizedIcon5 = new ImageIcon(resizedImage5);
		
		lblAVTar.setIcon(resizedIcon5);

	}
	

	public JTextField getTextFieldTaiKhoan() {
		return textFieldTaiKhoan;
	}

	public void setTextFieldTaiKhoan(JTextField textFieldTaiKhoan) {
		this.textFieldTaiKhoan = textFieldTaiKhoan;
	}

	public JTextField getTextFieldMatKhau() {
		return textFieldMatKhau;
	}

	public void setTextFieldMatKhau(JTextField textFieldMatKhau) {
		this.textFieldMatKhau = textFieldMatKhau;
	}

	public JTextField getTextFieldNhapLaiMK() {
		return textFieldNhapLaiMK;
	}

	public void setTextFieldNhapLaiMK(JTextField textFieldNhapLaiMK) {
		this.textFieldNhapLaiMK = textFieldNhapLaiMK;
	}

	public QLNVmodel getModel() {
		return model;
	}

	public void setModel(QLNVmodel model) {
		this.model = model;
	}
	
	
	//controller
	
	
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public void themTaiKhoanVaoTable(TaiKhoan tk) {
	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
	    model_table.addRow(new Object[] { tk.getTaiKhoan(), tk.getMatKhau() });
	}

	//thêm tài khoản
	public void themTaiKhoan(TaiKhoan tk) {
	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();

	    if (!this.model.KiemTraTonTaiTaiKhoan(tk)) {
	        this.model.getInstance().insertTaiKhoan(tk);
	        this.themTaiKhoanVaoTable(tk);
	        QuanLyNhanVienView view = QuanLyNhanVienView.getInstanceView();
	        view.getComboBoxTaiKhoan().addItem(tk.getTaiKhoan());

	        // Logging để kiểm tra danh sách tài khoản
	        System.out.println("Danh sách tài khoản sau khi thêm: " + this.model.getInstance().getDSTaiKhoan());

	        JOptionPane.showMessageDialog(this, "Đăng kí thành công");
	    } else {
	        JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại");
	    }
	}
	//cập nhật
	public void capnhatTaiKhoan(TaiKhoan tk) {
	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();

	    if (this.model.KiemTraTonTaiTaiKhoan(tk)) {
	        this.model.updateTaiKhoan(tk);
	        int soLuongDong = model_table.getRowCount();
	        for (int i = 0; i < soLuongDong; i++) {
	            String id = model_table.getValueAt(i, 0).toString();
	            if (id.equals(String.valueOf(tk.getTaiKhoan()))) {
	                model_table.setValueAt(tk.getTaiKhoan(), i, 0);
	                model_table.setValueAt(tk.getMatKhau(), i, 1);
	                // Cập nhật mật khẩu mới trong mảng danh sách tài khoản
	                for (TaiKhoan taiKhoan : this.model.getDSTaiKhoan()) {
	                    if (taiKhoan.getTaiKhoan().equals(tk.getTaiKhoan())) {
	                        taiKhoan.setMatKhau(tk.getMatKhau());
	                        break;
	                    }
	                }
	            }
	        }
	        JOptionPane.showMessageDialog(this, "Cập nhật thành công");
	      
	    } else {
	        JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại");
	    }
	}



	public void ThucHienthemTaiKhoan() {
		try {
			String taiKhoan = this.getTextFieldTaiKhoan().getText();
			if (taiKhoan.isEmpty() || (  taiKhoan.matches("[0-9]+"))||taiKhoan.equals("admin")||taiKhoan.equals("ADMIN")) {
	            throw new IllegalArgumentException("Tài khoản không hợp lệ.");
	        }
			
			String matKhau = this.getTextFieldMatKhau().getText();
			if (matKhau.isEmpty()) {
	            throw new IllegalArgumentException("Mật khẩu không được bỏ trống.");
	        }

	        // Kiểm tra độ dài mật khẩu
	       if (matKhau.length() < 8) {
	            throw new IllegalArgumentException("Mật khẩu phải dài ít nhất 8 ký tự.");
	        }

	        // Kiểm tra ít nhất 1 ký tự in hoa
	        boolean hasUpperCase = false;
	        for (char c : matKhau.toCharArray()) {
	            if (Character.isUpperCase(c)) {
	                hasUpperCase = true;
	                break;
	            }
	        }

	        if (!hasUpperCase) {
	            throw new IllegalArgumentException("Mật khẩu phải có ít nhất 1 ký tự in hoa.");
	        }

	        // Kiểm tra ít nhất 1 ký tự đặc biệt
	        String specialChars = "!@#$%^&*()-+";
	        boolean hasSpecialChar = false;
	        for (char c : matKhau.toCharArray()) {
	            if (specialChars.contains(String.valueOf(c))) {
	                hasSpecialChar = true;
	                break;
	            }
	        }

	        if (!hasSpecialChar) {
	            throw new IllegalArgumentException("Mật khẩu phải có ít nhất 1 ký tự đặc biệt.");
	        }
			
			String NhaplaiMK = this.getTextFieldNhapLaiMK().getText();
			if (NhaplaiMK.isEmpty()) {
				throw new IllegalArgumentException("Yêu cầu nhập lại mật khẩu để xác thực");
			}
			else if(!NhaplaiMK.equalsIgnoreCase(matKhau))
			{
				throw new IllegalArgumentException("Xác thực mật khẩu không khớp");
			}
			TaiKhoan tk=new TaiKhoan(taiKhoan, matKhau);
			this.themTaiKhoan(tk);
			 this.lgin.getInstance().writeToFile();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
		}
	}
	
	public TaiKhoan getTKDangChon() {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		int i_row = table.getSelectedRow();
		if (i_row < 0) {
			return null; // Không có dòng nào được chọn
		}
		String tk = model_table.getValueAt(i_row, 0).toString();

		// Lấy và kiểm tra các giá trị khác
		String mk = model_table.getValueAt(i_row, 1).toString();
		return new TaiKhoan(tk,mk);
	}
	
	public void showTTtaikhoanDaChon() {
		TaiKhoan tk = getTKDangChon();
		if (tk == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài khoản để hiển thị thông tin!");
			return;
		}
		this.textFieldTaiKhoan.setText(tk.getTaiKhoan());
		this.textFieldMatKhau.setText(tk.getMatKhau());
		this.textFieldNhapLaiMK.setText("");
	}
	
	public void thucHienTaiLaiDuLieu() {
	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
	    model_table.setRowCount(0); // Xóa hết các dòng hiện tại trong bảng

	    boolean check = false;
	    for (TaiKhoan tk : this.model.getInstance().getDSTaiKhoan()) {
	        System.out.println("Adding to table: " + tk.getTaiKhoan() + ", " + tk.getMatKhau()); // In kiểm tra
	        this.themTaiKhoanVaoTable(tk);
	        check = true;
	    }

	    if (!check) {
	        JOptionPane.showMessageDialog(this, "Danh sách rỗng");
	    }
	}

	
	public void ThucHienUpdateTaiKhoan() {
		try {
			// Kiểm tra xem có nhân viên nào được chọn hay không
	        if (table.getSelectedRow() == -1) {
	            throw new IllegalArgumentException("Vui lòng chọn một tài khoản để cập nhật thông tin!");
	        }
			String taiKhoan = this.getTextFieldTaiKhoan().getText();
			if (taiKhoan.isEmpty() || (  taiKhoan.matches("[0-9]+"))||taiKhoan.equals("admin")||taiKhoan.equals("ADMIN")) {
	            throw new IllegalArgumentException("Tài khoản không hợp lệ.");
	        }
			
			String matKhau = this.getTextFieldMatKhau().getText();
			if (matKhau.isEmpty()) {
	            throw new IllegalArgumentException("Mật khẩu không được bỏ trống.");
	        }

	        // Kiểm tra độ dài mật khẩu
	       if (matKhau.length() < 8) {
	            throw new IllegalArgumentException("Mật khẩu phải dài ít nhất 8 ký tự.");
	        }

	        // Kiểm tra ít nhất 1 ký tự in hoa
	        boolean hasUpperCase = false;
	        for (char c : matKhau.toCharArray()) {
	            if (Character.isUpperCase(c)) {
	                hasUpperCase = true;
	                break;
	            }
	        }

	        if (!hasUpperCase) {
	            throw new IllegalArgumentException("Mật khẩu phải có ít nhất 1 ký tự in hoa.");
	        }

	        // Kiểm tra ít nhất 1 ký tự đặc biệt
	        String specialChars = "!@#$%^&*()-+";
	        boolean hasSpecialChar = false;
	        for (char c : matKhau.toCharArray()) {
	            if (specialChars.contains(String.valueOf(c))) {
	                hasSpecialChar = true;
	                break;
	            }
	        }

	        if (!hasSpecialChar) {
	            throw new IllegalArgumentException("Mật khẩu phải có ít nhất 1 ký tự đặc biệt.");
	        }
			
			String NhaplaiMK = this.getTextFieldNhapLaiMK().getText();
			if (NhaplaiMK.isEmpty()) {
				throw new IllegalArgumentException("Yêu cầu nhập lại mật khẩu để xác thực");
			}
			else if(!NhaplaiMK.equalsIgnoreCase(matKhau))
			{
				throw new IllegalArgumentException("Xác thực mật khẩu không khớp");
			}
			TaiKhoan tk=new TaiKhoan(taiKhoan, matKhau);
			this.capnhatTaiKhoan(tk);
			this.lgin.getInstance().writeToFile();
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
		}
	}
	

	public void thucHienXoa() {
	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
	    int i_row = table.getSelectedRow();

	    if (i_row >= 0) {
	        int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa tài khoản đã chọn?");
	        if (luaChon == JOptionPane.YES_OPTION) {
	            TaiKhoan tk = getTKDangChon();
	            if (tk != null) {
	                try {
	                    // Xóa tài khoản khỏi model
	                    this.model.getInstance().deleteTK(tk); 
	                    // Xóa tài khoản khỏi bảng
	                    model_table.removeRow(i_row);

	                    // Lấy view và cập nhật ComboBox
	                    QuanLyNhanVienView view = QuanLyNhanVienView.getInstanceView();
	                    view.getComboBoxTaiKhoan().removeItem(tk.getTaiKhoan());

	                    // Lấy danh sách tài khoản và xóa tài khoản trong danh sách
	                    ArrayList<TaiKhoan> dstk = this.model.getInstance().getDSTaiKhoan();
	                    dstk.remove(tk);

	                    // Kiểm tra và xóa thông tin nhân viên liên quan nếu có
	                    ArrayList<NhanVien> dsNhanVien = this.model.getInstance().getDSNhanVien();
	                    boolean nvDaXoa = false;
	                    for (int j = 0; j < dsNhanVien.size(); j++) {
	                        NhanVien nv = dsNhanVien.get(j);
	                        if (nv.getTaiKhoan().equals(tk.getTaiKhoan())) {
	                            this.model.getInstance().delete(nv); // Xóa nhân viên khỏi model
	                            DefaultTableModel model_table_NV = (DefaultTableModel) QuanLyNhanVienView.getInstanceView().getTable().getModel();
	                            model_table_NV.removeRow(j); // Xóa nhân viên khỏi bảng
	                            dsNhanVien.remove(nv); // Xóa nhân viên khỏi danh sách
	                            nvDaXoa = true;
	                            j--; // Giảm chỉ số do đã xóa một phần tử
	                        }
	                    }

	                    if (nvDaXoa) {
	                        // Tải lại dữ liệu trên view nếu đã xóa nhân viên
	                        QuanLyNhanVienView.getInstanceView().thucHienTaiLaiDuLieu();
	                    }

	                    // In ra danh sách tài khoản sau khi xóa
	                    System.out.println("Danh sách tài khoản sau khi xóa: " + this.model.getInstance().getDSTaiKhoan());
	                    // In ra danh sách nhân viên sau khi xóa
	                    System.out.println("Danh sách nhân viên sau khi xóa tài khoản liên quan: " + this.model.getInstance().getDSNhanVien());

	                    JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công!");
	                    this.lgin.getInstance().writeToFile();
	                } catch (Exception e) {
	                    JOptionPane.showMessageDialog(this, "Xóa tài khoản thất bại!");
	                }
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản để xóa!");
	    }
	}


	
	public void refresh() {
		textFieldTaiKhoan.setText("");
		textFieldMatKhau.setText("");
		textFieldNhapLaiMK.setText("");
	}
	
	public void thoatKhoiChuongTrinh() {
		int luaChon = JOptionPane.showConfirmDialog(this, "Trở về giao diện quản lý nhân viên", "Exit",
				JOptionPane.YES_NO_OPTION);
		if (luaChon == JOptionPane.YES_OPTION) {
			this.dispose();
			this.setVisible(false);
	        QuanLyNhanVienView view= QuanLyNhanVienView.getInstanceView();
	        view.setVisible(true);
		}
	}
	
	public void dangkibenNgoai(String tk,String mk)
	{
		try {
			String taiKhoan = tk;
			 if (taiKhoan.isEmpty() || (  taiKhoan.matches("[0-9]+"))||tk.equals("admin")||tk.equals("ADMIN")) {
		            throw new IllegalArgumentException("Tài khoản không hợp lệ.");
		        }
			 String matKhau = mk;
		        if (matKhau.isEmpty()) {
		            throw new IllegalArgumentException("Mật khẩu không được bỏ trống.");
		        }

		        // Kiểm tra độ dài mật khẩu
		       if (matKhau.length() < 8) {
		            throw new IllegalArgumentException("Mật khẩu phải dài ít nhất 8 ký tự.");
		        }

		        // Kiểm tra ít nhất 1 ký tự in hoa
		        boolean hasUpperCase = false;
		        for (char c : matKhau.toCharArray()) {
		            if (Character.isUpperCase(c)) {
		                hasUpperCase = true;
		                break;
		            }
		        }

		        if (!hasUpperCase) {
		            throw new IllegalArgumentException("Mật khẩu phải có ít nhất 1 ký tự in hoa.");
		        }

		        // Kiểm tra ít nhất 1 ký tự đặc biệt
		        String specialChars = "!@#$%^&*()-+";
		        boolean hasSpecialChar = false;
		        for (char c : matKhau.toCharArray()) {
		            if (specialChars.contains(String.valueOf(c))) {
		                hasSpecialChar = true;
		                break;
		            }
		        }

		        if (!hasSpecialChar) {
		            throw new IllegalArgumentException("Mật khẩu phải có ít nhất 1 ký tự đặc biệt.");
		        }
		    
		    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
			TaiKhoan TK=new TaiKhoan(taiKhoan, matKhau);
			
			if (!this.model.KiemTraTonTaiTaiKhoan(TK)) {
		       
		        this.themTaiKhoanVaoTable(TK);
		        this.setVisible(false);
		        QuanLyNhanVienView view = QuanLyNhanVienView.getInstanceView();
		        view.setVisible(false);
		        view.getComboBoxTaiKhoan().addItem(TK.getTaiKhoan());
		        this.model.getInstance().insertTaiKhoan(TK);
		        JOptionPane.showMessageDialog(this, "Đăng kí thành công");
		    }
			else {
				JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại: ");
			}
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
		}
	}
	public void updatePassword(String username, String newPassword) {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    int rowCount = model.getRowCount();

	    for (int i = 0; i < rowCount; i++) {
	        if (model.getValueAt(i, 0).equals(username)) {
	            model.setValueAt(newPassword, i, 1);
	            break;
	        }
	    }

	    for (TaiKhoan taiKhoan : this.model.getInstance().getDSTaiKhoan()) {
	        if (taiKhoan.getTaiKhoan().equals(username)) {
	            taiKhoan.setMatKhau(newPassword);
	            break;
	        }
	    }
	}
}
