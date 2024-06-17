package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.QuanLyNhanVienController;
import controller.dangNhapController;
import model.NhanVien;
import model.QLNVmodel;
import model.TaiKhoan;
import view.DangKi;
import view.QuanLyNhanVienView;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Panel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField matKhauField;
	private JLabel lbTaiKhoan;
	private JLabel lbMatKhau;
	private JFormattedTextField TaiKhoanTextField;
	private QLNVmodel dangki;
	private String PWadmin="Nhom9@123";
	private static login instance;
	private QuanLyNhanVienView qlnv;
	private DangKi dk;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();

					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 public static login getInstance() {
	        if (instance == null) {
	            instance = new login();
	        }
	        return instance;
	    }
	 
	 
	
	public login() {
		this.dangki=new QLNVmodel();
		this.setTitle("Phần mềm quản lý nhân viên");
		ActionListener actionlistener = new dangNhapController(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 323);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbQuanLyNhanVien = new JLabel("Quản lý nhân viên");
		lbQuanLyNhanVien.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbQuanLyNhanVien.setBounds(280, 10, 250, 69);
		contentPane.add(lbQuanLyNhanVien);

		TaiKhoanTextField = new JFormattedTextField();
		TaiKhoanTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		TaiKhoanTextField.setBounds(360, 89, 170, 31);
		contentPane.add(TaiKhoanTextField);

		lbTaiKhoan = new JLabel("Tài khoản:");
		lbTaiKhoan.setBackground(new Color(255, 0, 0));
		lbTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTaiKhoan.setBounds(225, 78, 130, 45);
		contentPane.add(lbTaiKhoan);
		ImageIcon originalIcon1 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(login.class.getResource("/Icon/user.png")));
		Image originalImage1 = originalIcon1.getImage();
		Image resizedImage1 = originalImage1.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Set your desired width
																							// and height here
		ImageIcon resizedIcon1 = new ImageIcon(resizedImage1);

		lbTaiKhoan.setIcon(resizedIcon1);

		lbMatKhau = new JLabel("Mật khẩu:");
		lbMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbMatKhau.setBounds(225, 133, 130, 43);
		contentPane.add(lbMatKhau);
		ImageIcon originalIcon2 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(login.class.getResource("/Icon/password.png")));
		Image originalImage2 = originalIcon2.getImage();
		Image resizedImage2 = originalImage2.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Set your desired width
																							// and height here
		ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
		lbMatKhau.setIcon(resizedIcon2);

		matKhauField = new JPasswordField();
		matKhauField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		matKhauField.setBounds(360, 141, 170, 35);
		contentPane.add(matKhauField);

		JLabel lbimg = new JLabel("");
		lbimg.setBackground(new Color(0, 128, 64));
		lbimg.setBounds(10, 10, 180, 206);
		contentPane.add(lbimg);

		ImageIcon originalIcon = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(login.class.getResource("/Icon/NhanVien.png")));
		Image originalImage = originalIcon.getImage();
		Image resizedImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Set your desired width
																							// and height here
		ImageIcon resizedIcon = new ImageIcon(resizedImage);

		lbimg.setIcon(resizedIcon);

		JButton btnlogin = new JButton("Đăng nhập");
		btnlogin.addActionListener(actionlistener);
		btnlogin.setBackground(new Color(128, 0, 255));
		btnlogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnlogin.setBounds(280, 197, 139, 30);
		contentPane.add(btnlogin);
		ImageIcon originalIcon3 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(login.class.getResource("/Icon/login-icon.png")));
		Image originalImage3 = originalIcon3.getImage();
		Image resizedImage3 = originalImage3.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Set your desired width
																							// and height here
		ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);
		btnlogin.setIconTextGap(1);
		btnlogin.setIcon(resizedIcon3);

		JButton btnThoat = new JButton("Thoát");
		btnThoat.setBackground(new Color(0, 255, 255));
		btnThoat.addActionListener(actionlistener);
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThoat.setBounds(596, 246, 100, 30);
		contentPane.add(btnThoat);
		ImageIcon originalIcon4 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(login.class.getResource("/Icon/exit-con.png")));
		Image originalImage4 = originalIcon4.getImage();
		Image resizedImage4 = originalImage4.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Set your desired width
																							// and height here
		ImageIcon resizedIcon4 = new ImageIcon(resizedImage4);
		btnThoat.setIconTextGap(1);
		btnThoat.setIcon(resizedIcon4);
		
		JButton btnDangKi = new JButton("Đăng kí");
		btnDangKi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDangKi.setBackground(new Color(255, 255, 0));
		btnDangKi.addActionListener(actionlistener);
		btnDangKi.setBounds(429, 197, 130, 30);
		contentPane.add(btnDangKi);
		ImageIcon originalIcon5 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(login.class.getResource("/Icon/add_TK.png")));
		Image originalImage5 = originalIcon5.getImage();
		Image resizedImage5 = originalImage5.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Set your desired width
																							// and height here
		ImageIcon resizedIcon5 = new ImageIcon(resizedImage5);
		btnDangKi.setIconTextGap(1);
		btnDangKi.setIcon(resizedIcon5);

	}

	public JPasswordField getMatKhauField() {
		return matKhauField;
	}

	public void setMatKhauField(JPasswordField matKhauField) {
		this.matKhauField = matKhauField;
	}

	public JFormattedTextField getTaiKhoanTextField() {
		return TaiKhoanTextField;
	}

	public void setTaiKhoanTextField(JFormattedTextField taiKhoanTextField) {
		TaiKhoanTextField = taiKhoanTextField;
	}
	
	public String getPWadmin() {
		return PWadmin;
	}

	public void setPWadmin(String pWadmin) {
		PWadmin = pWadmin;
	}
	
	public void Loginview() {
	    readFromFile();

	    String username = this.TaiKhoanTextField.getText();
	    boolean check = false;

	    for (TaiKhoan tk : dangki.getInstance().getDSTaiKhoan()) {
	        if (this.TaiKhoanTextField.getText().equals(tk.getTaiKhoan()) &&
	            new String(this.matKhauField.getPassword()).equals(tk.getMatKhau())) {
	            check = true;
	            break;
	        }
	    }

	    if (check || isAdminAccount()) {
	        this.setVisible(false);
	        QuanLyNhanVienView view = QuanLyNhanVienView.getInstanceView();
	        view.thucHienTaiLaiDuLieu();
	        this.dk.getInstance().thucHienTaiLaiDuLieu();
	        view.setVisible(true);
	     // Làm mới comboBoxTaiKhoan
            qlnv.getInstanceView().getComboBoxTaiKhoan().removeAllItems();
            qlnv.getInstanceView().getComboBoxTaiKhoan().addItem("");
            for (TaiKhoan tk : dangki.getInstance().getDSTaiKhoan()) {
                if (!isTaiKhoanInComboBox(tk.getTaiKhoan())) {
                    qlnv.getInstanceView().getComboBoxTaiKhoan().addItem(tk.getTaiKhoan());
                }
            }
	        view.setTextFieldTaikhoan(username);
	    } else if (this.TaiKhoanTextField.getText().isEmpty() || 
	               new String(this.matKhauField.getPassword()).isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu chưa nhập");
	    } else {
	        JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu sai");
	    }
	}

	private boolean isAdminAccount() {
	    String adminUsername = "admin";
	    String adminPassword = PWadmin; // replace with your actual admin password
	    return this.TaiKhoanTextField.getText().equals(adminUsername) &&
	           new String(this.matKhauField.getPassword()).equals(adminPassword);
	}

	public void dangKiTaiKhoanBenNgoai() {
	    readFromFile();

	    JTextField usernameField = new JTextField();
	    JPasswordField passwordField = new JPasswordField();
	    JPasswordField confirmPasswordField = new JPasswordField();
	    Object[] message = {
	        "Nhập tài khoản:", usernameField,
	        "Nhập mật khẩu:", passwordField,
	        "Nhập lại mật khẩu:", confirmPasswordField
	    };
	    int option = JOptionPane.showConfirmDialog(this, message, "Đăng kí tài khoản", JOptionPane.OK_CANCEL_OPTION);

	    if (option == JOptionPane.OK_OPTION) {
	        String username = usernameField.getText();
	        String password = new String(passwordField.getPassword());
	        String confirmPassword = new String(confirmPasswordField.getPassword());

	        if (!password.isEmpty() && password.equals(confirmPassword)) {
	            DangKi dkFrame = DangKi.getInstance();
	            dkFrame.dangkibenNgoai(username, password);
	            writeToFile();
	        } else {
	            JOptionPane.showMessageDialog(this, "Mật khẩu không khớp hoặc trống.");
	        }
	    }
	}

	public void readFromFile() {
	    try {
	        File file = new File("QuanLyNhanVien.dat");
	        if (file.exists()) {
	            FileInputStream fis = new FileInputStream(file);
	            ObjectInputStream ois = new ObjectInputStream(fis);

	            // Đọc danh sách nhân viên
	            ArrayList<NhanVien> dsNhanVien = (ArrayList<NhanVien>) ois.readObject();
	            System.out.println("===================================");
	            dangki.getInstance().setDSNhanVien(dsNhanVien);
	            System.out.println("Đã tải DSNhanVien: ");
	            int soLuongNV=dsNhanVien.size();
	            System.out.println("Số lượng nhân viên: "+ soLuongNV);
	            int i=0;
	            for(NhanVien nv : dsNhanVien)
	            {
	            	i++;
	            	System.out.println((i)+" :"+ nv );
	            }
	            //System.out.println("Đã tải DSNhanVien: " + dsNhanVien);
	            
	            // Đọc danh sách tài khoản
	            ArrayList<TaiKhoan> dsTaiKhoan = (ArrayList<TaiKhoan>) ois.readObject();
	            dangki.getInstance().setDSTaiKhoan(dsTaiKhoan);
	            //System.out.println("Đã tải DSTaiKhoan: " + dsTaiKhoan);
	            int soLuongTK=dsTaiKhoan.size();
	            System.out.println("Đã tải dsTaiKhoan: ");
	            System.out.println("Số lượng tài khoản: "+ soLuongTK);
	            i=0;
	            for(TaiKhoan tk : dsTaiKhoan)
	            {
	            	i++;
	            	System.out.println((i)+" :"+ tk );
	            }
	            

	            

	            ois.close();
	        }
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	}

	public void writeToFile() {
	    try {
	        FileOutputStream fos = new FileOutputStream("QuanLyNhanVien.dat");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);

	        // Lưu danh sách nhân viên
	        oos.writeObject(dangki.getInstance().getDSNhanVien());
	        
	        // Lưu danh sách tài khoản
	        oos.writeObject(dangki.getInstance().getDSTaiKhoan());
	        int soLuongNV=dangki.getInstance().getDSNhanVien().size();
	        System.out.println("===================================");
	        System.out.println("DS nhân viên sau khi ghi file :");
            System.out.println("Số lượng nhân viên: "+ soLuongNV);
	        //System.out.println("DS nhân viên sau khi ghi file : +" +dangki.getInstance().getDSNhanVien());
            int i=0;
            for(NhanVien nv :dangki.getInstance().getDSNhanVien() )
            {
            	i++;
            	System.out.println((i)+" :"+ nv );
            }
	        int soLuongTK=dangki.getInstance().getDSTaiKhoan().size();
	        System.out.println("DS tài khoản sau khi ghi file :");
	        System.out.println("Số lượng tài khoản: "+ soLuongTK);
             i=0;
            for(TaiKhoan tk :dangki.getInstance().getDSTaiKhoan() )
            {
            	i++;
            	System.out.println((i)+" :"+ tk );
            	
            }
	        //System.out.println("DS tài khoản sau khi ghi file : +" +dangki.getInstance().getDSTaiKhoan());
	        oos.close();
	        System.out.println("Đã lưu dữ liệu vào file");
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	}

	private boolean isTaiKhoanInComboBox(String taiKhoan) {
	    for (int i = 0; i < qlnv.getInstanceView().getComboBoxTaiKhoan().getItemCount(); i++) {
	        if (qlnv.getInstanceView().getComboBoxTaiKhoan().getItemAt(i).equals(taiKhoan)) {
	            return true;
	        }
	    }
	    return false;
	}


	
	public void thoat() {
		int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoải khỏi chương trình?", "Exit",
				JOptionPane.YES_NO_OPTION);
		if (luaChon == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}
