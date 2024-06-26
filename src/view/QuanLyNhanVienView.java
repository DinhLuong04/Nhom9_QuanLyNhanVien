package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controller.QuanLyNhanVienController;

import model.NhanVien;
import model.PhongBan;
import model.QLNVmodel;
import model.QueQuan;
import model.TaiKhoan;
import view.login;

import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPopupMenu;

public class QuanLyNhanVienView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTextField textFieldMaNV_Find;
	private JTable table;
	private JTextField textFieldID;
	private JTextField textFieldname;
	private JTextField textFieldDoB;
	private JTextField textFieldPhone;
	private JTextField textFieldChucVu;
	private JTextField textFieldTrinhDo;
	private JLabel lbLuong;
	private JTextField textFieldluong;
	private JComboBox DSquequan_Find;// tìm kiếm theo quê
	private JComboBox DSphongbanFind;// tìm kiếm theo phòng ban
	private JComboBox DStinh_find;// combobox tỉnh
	private JComboBox<String> DS_DT_FIND;// combobox dân tộc
	private JComboBox LuaChonPban;// combobox phòng ban
	private JRadioButton rdbtn_nam;
	private JRadioButton rdbtn_nu;
	private ButtonGroup btgr;// giới tính
	public QLNVmodel model;
	private JTextField textFieldTaikhoan;
	private JComboBox<String> comboBoxTaiKhoan;
	private static QuanLyNhanVienView instanceView;
	private DangKi dk;
	private login lgin;
	private double luongCaoNhat;
	private double luongThapNhat;
	private ThongKe tke;
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
//				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				QuanLyNhanVienView frame = new QuanLyNhanVienView();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public static QuanLyNhanVienView getInstanceView() {
		if (instanceView == null) {
			instanceView = new QuanLyNhanVienView();
		}
		return instanceView;
	}

	public QuanLyNhanVienView() {
		this.model = new QLNVmodel();
		ActionListener actionlistener = new QuanLyNhanVienController(this);
		MouseListener mouseactioner = new QuanLyNhanVienController(this);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1234, 1054);
		this.setSize(1650, 1080);
//		Cấu hình JFrame để mở rộng ra toàn bộ màn hình
//      this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setTitle("Phần mềm quản lý nhân viên");

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 128, 0));
		setJMenuBar(menuBar);
		
		JMenu menuOption = new JMenu("Option");
		menuOption.setBackground(new Color(255, 128, 64));
		menuOption.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(menuOption);
		
		JMenuItem MenuThongKe = new JMenuItem("Thống kê");
		MenuThongKe.addActionListener(actionlistener);
		MenuThongKe.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuOption.add(MenuThongKe);
		ImageIcon originalIconThongke = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/report-icon.png")));
		Image originalImageThongke = originalIconThongke.getImage();
		Image resizedImageThongke = originalImageThongke.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon resizedIconThongke = new ImageIcon(resizedImageThongke);
		MenuThongKe.setIcon(resizedIconThongke);
		
		
		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.addActionListener(actionlistener);
		menuExit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuOption.add(menuExit);
		ImageIcon originalIcon3 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/exit-con.png")));
		Image originalImage3 = originalIcon3.getImage();
		Image resizedImage3 = originalImage3.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);
		menuExit.setIcon(resizedIcon3);

		JMenu menuAbout = new JMenu("About");

		menuAbout.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(menuAbout);

		JMenuItem menuAboutMe = new JMenuItem("About Me");
		menuAboutMe.addActionListener(actionlistener);
		menuAboutMe.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuAbout.add(menuAboutMe);
		ImageIcon originalIcon4 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/about.png")));
		Image originalImage4 = originalIcon4.getImage();
		Image resizedImage4 = originalImage4.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon4 = new ImageIcon(resizedImage4);
		menuAboutMe.setIcon(resizedIcon4);

		JMenu menuADMIN = new JMenu("ADMIN");
		menuADMIN.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(menuADMIN);

		JMenuItem MenuItemQLTK = new JMenuItem("Quản lý tài khoản");
		MenuItemQLTK.addActionListener(actionlistener);
		MenuItemQLTK.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuADMIN.add(MenuItemQLTK);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 0));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbPhongBanFind = new JLabel("Phòng Ban");
		lbPhongBanFind.setForeground(new Color(0, 0, 0));
		lbPhongBanFind.setBackground(new Color(240, 240, 240));
		lbPhongBanFind.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbPhongBanFind.setBounds(10, 33, 154, 34);
		contentPane.add(lbPhongBanFind);
		ImageIcon originalIcon23 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/phongBan-icon.png")));
		Image originalImage23 = originalIcon23.getImage();
		Image resizedImage23 = originalImage23.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon23 = new ImageIcon(resizedImage23);

		lbPhongBanFind.setHorizontalTextPosition(SwingConstants.LEFT);

		lbPhongBanFind.setIconTextGap(20);
		lbPhongBanFind.setIcon(resizedIcon23);

		JLabel lbMaNVFind = new JLabel("Mã nhân viên");
		lbMaNVFind.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbMaNVFind.setBounds(326, 33, 160, 34);
		contentPane.add(lbMaNVFind);
		ImageIcon originalIcon24 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/id-card.png")));
		Image originalImage24 = originalIcon24.getImage();
		Image resizedImage24 = originalImage24.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon24 = new ImageIcon(resizedImage24);

		lbMaNVFind.setHorizontalTextPosition(SwingConstants.LEFT);
		lbMaNVFind.setIconTextGap(12);
		lbMaNVFind.setIcon(resizedIcon24);

		JLabel lbQueQuanFind = new JLabel("Quê Quán");
		lbQueQuanFind.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbQueQuanFind.setBounds(647, 33, 132, 34);
		contentPane.add(lbQueQuanFind);
		ImageIcon originalIcon25 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/Vietnam_map.png")));
		Image originalImage25 = originalIcon25.getImage();
		Image resizedImage25 = originalImage25.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon25 = new ImageIcon(resizedImage25);

		lbQueQuanFind.setHorizontalTextPosition(SwingConstants.LEFT);

		lbQueQuanFind.setIconTextGap(5);
		lbQueQuanFind.setIcon(resizedIcon25);

		textFieldMaNV_Find = new JTextField();
		textFieldMaNV_Find.setBounds(505, 41, 132, 27);
		contentPane.add(textFieldMaNV_Find);
		textFieldMaNV_Find.setColumns(10);

		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBackground(new Color(255, 255, 0));
		btnTimKiem.addActionListener(actionlistener);
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTimKiem.setBounds(967, 42, 160, 27);
		contentPane.add(btnTimKiem);
		ImageIcon originalIcon26 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/findEmployee-icon.png")));
		Image originalImage26 = originalIcon26.getImage();
		Image resizedImage26 = originalImage26.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon26 = new ImageIcon(resizedImage26);

		btnTimKiem.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnTimKiem.setIconTextGap(10);
		btnTimKiem.setIcon(resizedIcon26);

		DSphongbanFind = new JComboBox();
		ArrayList<PhongBan> listPB = PhongBan.getDSPB();
		DSphongbanFind.addItem("");
		for (PhongBan pb : listPB) {
			DSphongbanFind.addItem(pb.getTenPB());
		}
		DSphongbanFind.setBounds(174, 42, 132, 27);
		contentPane.add(DSphongbanFind);

		DSquequan_Find = new JComboBox();
		ArrayList<QueQuan> listTinh = QueQuan.getDSTinh();
		DSquequan_Find.addItem("");
		for (QueQuan tinh : listTinh) {
			DSquequan_Find.addItem(tinh.getTenTinh());
		}

		DSquequan_Find.setBounds(791, 41, 132, 27);
		contentPane.add(DSquequan_Find);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 86, 1530, 2);
		contentPane.add(separator_1);

		JLabel lbDanhSachNV = new JLabel("Danh Sách Nhân viên");
		lbDanhSachNV.setForeground(new Color(0, 0, 0));
		lbDanhSachNV.setOpaque(true);
		lbDanhSachNV.setBackground(Color.YELLOW);
		lbDanhSachNV.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbDanhSachNV.setBounds(30, 96, 288, 34);
		contentPane.add(lbDanhSachNV);
		ImageIcon originalIcon5 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/checklist-icon.png")));
		Image originalImage5 = originalIcon5.getImage();
		Image resizedImage5 = originalImage5.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon5 = new ImageIcon(resizedImage5);

		lbDanhSachNV.setHorizontalTextPosition(SwingConstants.LEFT);

		lbDanhSachNV.setIconTextGap(10);
		lbDanhSachNV.setIcon(resizedIcon5);

		table = new JTable();
		table.setBackground(new Color(255, 128, 0));
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Dân tộc", "Tỉnh",
						"Số điện thoại", "Phòng ban", "Chức vụ", "Trình độ", "Tiền Lương", "Tài khoản" }));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 137, 1487, 253);
		contentPane.add(scrollPane);
		// Tạo JPopupMenu
		table.addMouseListener(mouseactioner);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 413, 1555, 2);
		contentPane.add(separator_1_1);

		JLabel lbThongTinNhanVien = new JLabel("Thông tin nhân viên");
		lbThongTinNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbThongTinNhanVien.setBounds(25, 425, 253, 34);
		contentPane.add(lbThongTinNhanVien);

		textFieldID = new JTextField();
		textFieldID.setColumns(10);
		textFieldID.setBounds(226, 469, 118, 27);
		contentPane.add(textFieldID);

		JLabel lbID = new JLabel("Mã nhân viên");
		lbID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbID.setBounds(30, 459, 187, 34);
		contentPane.add(lbID);
		ImageIcon originalIcon6 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/id-card.png")));
		Image originalImage6 = originalIcon6.getImage();
		Image resizedImage6 = originalImage6.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon6 = new ImageIcon(resizedImage6);

		lbID.setHorizontalTextPosition(SwingConstants.LEFT);

		lbID.setIconTextGap(10);
		lbID.setIcon(resizedIcon6);

		textFieldname = new JTextField();
		textFieldname.setColumns(10);
		textFieldname.setBounds(226, 505, 118, 27);
		contentPane.add(textFieldname);

		JLabel lbname = new JLabel("Họ và tên");
		lbname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbname.setBounds(30, 497, 187, 34);
		contentPane.add(lbname);
		ImageIcon originalIcon7 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/name-card.png")));
		Image originalImage7 = originalIcon7.getImage();
		Image resizedImage7 = originalImage7.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon7 = new ImageIcon(resizedImage7);

		lbname.setHorizontalTextPosition(SwingConstants.LEFT);

		lbname.setIconTextGap(41);
		lbname.setIcon(resizedIcon7);

		JLabel lbPBan = new JLabel("Phòng Ban");
		lbPBan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbPBan.setBounds(374, 497, 171, 34);
		contentPane.add(lbPBan);
		ImageIcon originalIcon12 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/phongBan-icon.png")));
		Image originalImage12 = originalIcon12.getImage();
		Image resizedImage12 = originalImage12.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon12 = new ImageIcon(resizedImage12);

		lbPBan.setHorizontalTextPosition(SwingConstants.LEFT);

		lbPBan.setIconTextGap(30);
		lbPBan.setIcon(resizedIcon12);

		LuaChonPban = new JComboBox();
		ArrayList<PhongBan> listPBLC = PhongBan.getDSPB();
		LuaChonPban.addItem("");
		for (PhongBan pb : listPBLC) {
			LuaChonPban.addItem(pb.getTenPB());
		}
		LuaChonPban.setBounds(553, 505, 117, 27);
		contentPane.add(LuaChonPban);

		JLabel lblDob = new JLabel("Ngày Sinh");
		lblDob.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDob.setBounds(30, 586, 187, 40);
		contentPane.add(lblDob);
		ImageIcon originalIcon8 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/dob-icon.png")));
		Image originalImage8 = originalIcon8.getImage();
		Image resizedImage8 = originalImage8.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Set your desired width
																							// and height here
		ImageIcon resizedIcon8 = new ImageIcon(resizedImage8);
		// Đặt vị trí ngang của chữ so với icon
		lblDob.setHorizontalTextPosition(SwingConstants.LEFT);
		// Tùy chọn, đặt khoảng cách giữa chữ và icon
		lblDob.setIconTextGap(32); // Đặt khoảng cách mong muốn giữa chữ và icon
		lblDob.setIcon(resizedIcon8);

		textFieldDoB = new JTextField();
		textFieldDoB.setColumns(10);
		textFieldDoB.setBounds(226, 599, 118, 27);
		contentPane.add(textFieldDoB);

		JLabel lblDanToc = new JLabel("Dân tộc");
		lblDanToc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDanToc.setBounds(30, 635, 186, 52);
		contentPane.add(lblDanToc);
		ImageIcon originalIcon10 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/nation-icon.png")));
		Image originalImage10 = originalIcon10.getImage();
		Image resizedImage10 = originalImage10.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon10 = new ImageIcon(resizedImage10);

		lblDanToc.setHorizontalTextPosition(SwingConstants.LEFT);

		lblDanToc.setIconTextGap(45);
		lblDanToc.setIcon(resizedIcon10);

		JLabel lblTinh = new JLabel("Tỉnh");
		lblTinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTinh.setBounds(30, 697, 169, 34);
		contentPane.add(lblTinh);
		ImageIcon originalIcon13 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/Vietnam_map.png")));
		Image originalImage13 = originalIcon13.getImage();
		Image resizedImage13 = originalImage13.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon13 = new ImageIcon(resizedImage13);

		lblTinh.setHorizontalTextPosition(SwingConstants.LEFT);

		lblTinh.setIconTextGap(85);
		lblTinh.setIcon(resizedIcon13);

		JLabel lbPhone = new JLabel("Số điện thoại");
		lbPhone.setBackground(new Color(128, 128, 255));
		lbPhone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbPhone.setBounds(374, 462, 175, 34);
		contentPane.add(lbPhone);
		ImageIcon originalIcon11 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/telephone.png")));
		Image originalImage11 = originalIcon11.getImage();
		Image resizedImage11 = originalImage11.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon11 = new ImageIcon(resizedImage11);

		lbPhone.setHorizontalTextPosition(SwingConstants.LEFT);

		lbPhone.setIconTextGap(10);
		lbPhone.setIcon(resizedIcon11);

		textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(553, 469, 118, 27);
		contentPane.add(textFieldPhone);

		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGiiTnh.setBounds(30, 542, 187, 40);
		contentPane.add(lblGiiTnh);
		ImageIcon originalIcon9 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/gender-icon.png")));
		Image originalImage9 = originalIcon9.getImage();
		Image resizedImage9 = originalImage9.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon9 = new ImageIcon(resizedImage9);

		lblGiiTnh.setHorizontalTextPosition(SwingConstants.LEFT);

		lblGiiTnh.setIconTextGap(45);
		lblGiiTnh.setIcon(resizedIcon9);

		JLabel lblChucVu = new JLabel("Chức vụ");
		lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChucVu.setBounds(372, 549, 171, 34);
		contentPane.add(lblChucVu);
		ImageIcon originalIcon14 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/chucvuicon.png")));
		Image originalImage14 = originalIcon14.getImage();
		Image resizedImage14 = originalImage14.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon14 = new ImageIcon(resizedImage14);

		lblChucVu.setHorizontalTextPosition(SwingConstants.LEFT);

		lblChucVu.setIconTextGap(50);
		lblChucVu.setIcon(resizedIcon14);

		textFieldChucVu = new JTextField();
		textFieldChucVu.setColumns(10);
		textFieldChucVu.setBounds(551, 553, 118, 27);
		contentPane.add(textFieldChucVu);

		JLabel lbTrinhDo = new JLabel("Trình độ");
		lbTrinhDo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTrinhDo.setBounds(372, 589, 169, 34);
		contentPane.add(lbTrinhDo);
		ImageIcon originalIcon15 = new ImageIcon(
		Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/education-icon.png")));
		Image originalImage15 = originalIcon15.getImage();
		Image resizedImage15 = originalImage15.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon15 = new ImageIcon(resizedImage15);

		lbTrinhDo.setHorizontalTextPosition(SwingConstants.LEFT);

		lbTrinhDo.setIconTextGap(50);
		lbTrinhDo.setIcon(resizedIcon15);

		textFieldTrinhDo = new JTextField();
		textFieldTrinhDo.setColumns(10);
		textFieldTrinhDo.setBounds(551, 597, 118, 27);
		contentPane.add(textFieldTrinhDo);

		lbLuong = new JLabel("Lương");
		lbLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbLuong.setBounds(372, 635, 171, 34);
		contentPane.add(lbLuong);
		ImageIcon originalIcon16 = new ImageIcon(
		Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/salary-icon.png")));
		Image originalImage16 = originalIcon16.getImage();
		Image resizedImage16 = originalImage16.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon16 = new ImageIcon(resizedImage16);

		lbLuong.setHorizontalTextPosition(SwingConstants.LEFT);

		lbLuong.setIconTextGap(70);
		lbLuong.setIcon(resizedIcon16);

		rdbtn_nam = new JRadioButton("Nam");
		rdbtn_nam.setBounds(226, 553, 52, 21);
		rdbtn_nam.setActionCommand("Nam");

		rdbtn_nu = new JRadioButton("Nữ");
		rdbtn_nu.setBounds(293, 553, 52, 21);
		rdbtn_nu.setActionCommand("Nữ");

		btgr = new ButtonGroup();
		btgr.add(rdbtn_nam);
		btgr.add(rdbtn_nu);

		contentPane.add(rdbtn_nam);
		contentPane.add(rdbtn_nu);

		JButton btnADD = new JButton("Thêm");
		btnADD.setBackground(new Color(255, 128, 0));
		btnADD.addActionListener(actionlistener);
		btnADD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnADD.setBounds(698, 467, 160, 34);
		contentPane.add(btnADD);
		ImageIcon originalIcon17 = new ImageIcon(
		Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/add-icon.png")));
		Image originalImage17 = originalIcon17.getImage();
		Image resizedImage17 = originalImage17.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon17 = new ImageIcon(resizedImage17);
		btnADD.setIcon(resizedIcon17);

		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.setBackground(new Color(128, 255, 255));
		btnUpdate.addActionListener(actionlistener);
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUpdate.setBounds(698, 527, 160, 34);
		contentPane.add(btnUpdate);
		ImageIcon originalIcon19 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/update-icon.png")));
		Image originalImage19 = originalIcon19.getImage();
		Image resizedImage19 = originalImage19.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon19 = new ImageIcon(resizedImage19);
		btnUpdate.setIcon(resizedIcon19);

		JButton btnRemove = new JButton("Xóa");
		btnRemove.setBackground(new Color(255, 128, 64));
		btnRemove.addActionListener(actionlistener);
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRemove.setBounds(698, 589, 160, 34);
		contentPane.add(btnRemove);
		ImageIcon originalIcon20 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/remove-icon.png")));
		Image originalImage20 = originalIcon20.getImage();
		Image resizedImage20 = originalImage20.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon20 = new ImageIcon(resizedImage20);
		btnRemove.setIcon(resizedIcon20);

		JButton btnTongHopBaoCao = new JButton("Tổng hợp báo cáo");
		btnTongHopBaoCao.setBackground(new Color(147, 112, 219));
		btnTongHopBaoCao.addActionListener(actionlistener);
		btnTongHopBaoCao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTongHopBaoCao.setBounds(1298, 24, 232, 52);
		contentPane.add(btnTongHopBaoCao);
		ImageIcon originalIcon28 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/report-icon.png")));
		Image originalImage28 = originalIcon28.getImage();
		Image resizedImage28 = originalImage28.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon28 = new ImageIcon(resizedImage28);

		btnTongHopBaoCao.setHorizontalTextPosition(SwingConstants.RIGHT);

		btnTongHopBaoCao.setIconTextGap(5);
		btnTongHopBaoCao.setIcon(resizedIcon28);

		JButton btnCancel = new JButton("Refresh");
		
		btnCancel.setBackground(new Color(128, 128, 255));
		btnCancel.addActionListener(actionlistener);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancel.setBounds(698, 644, 160, 34);
		contentPane.add(btnCancel);
		ImageIcon originalIcon21 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/refresh_TK.png")));
		Image originalImage21 = originalIcon21.getImage();
		Image resizedImage21 = originalImage21.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon21 = new ImageIcon(resizedImage21);
		btnCancel.setIcon(resizedIcon21);

		DStinh_find = new JComboBox();
		ArrayList<QueQuan> listTinhtt = QueQuan.getDSTinh();
		DStinh_find.addItem("");
		for (QueQuan tinh : listTinhtt) {
			DStinh_find.addItem(tinh.getTenTinh());
		}

		DStinh_find.setBounds(226, 697, 118, 27);
		contentPane.add(DStinh_find);

		DS_DT_FIND = new JComboBox();
		ArrayList<String> listDTtt = NhanVien.DS_Dantoc();
		DS_DT_FIND.addItem("");
		for (String tenDT : listDTtt) {
			DS_DT_FIND.addItem(tenDT);
		}
		DS_DT_FIND.setBounds(226, 643, 118, 27);
		contentPane.add(DS_DT_FIND);

		textFieldluong = new JTextField();
		textFieldluong.setColumns(10);
		textFieldluong.setBounds(551, 635, 118, 27);
		contentPane.add(textFieldluong);

		JButton btnHuyTimkiem = new JButton("Hủy");
		btnHuyTimkiem.setBackground(new Color(240, 128, 128));
		btnHuyTimkiem.addActionListener(actionlistener);
		btnHuyTimkiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHuyTimkiem.setBounds(1158, 41, 107, 27);
		contentPane.add(btnHuyTimkiem);
		ImageIcon originalIcon27 = new ImageIcon(
		Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/cancelled.png")));
		Image originalImage27 = originalIcon27.getImage();
		Image resizedImage27 = originalImage27.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon27 = new ImageIcon(resizedImage27);
		btnHuyTimkiem.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnHuyTimkiem.setIconTextGap(5);
		btnHuyTimkiem.setIcon(resizedIcon27);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(900, 425, 243, 260);
		contentPane.add(lblNewLabel);
		ImageIcon originalIcon22 = new ImageIcon(
		Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/NhanVien.png")));
		Image originalImage22 = originalIcon22.getImage();
		Image resizedImage22 = originalImage22.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon22 = new ImageIcon(resizedImage22);
		lblNewLabel.setIcon(resizedIcon22);

		textFieldTaikhoan = new JTextField();
		textFieldTaikhoan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldTaikhoan.setBounds(926, 664, 187, 45);
		contentPane.add(textFieldTaikhoan);
		textFieldTaikhoan.setColumns(10);

		JButton btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.setBackground(new Color(128, 0, 255));
		btnDangXuat.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDangXuat.addActionListener(actionlistener);
		btnDangXuat.setBounds(926, 715, 187, 40);
		contentPane.add(btnDangXuat);
		ImageIcon originalIcon31 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/LogOut_TK.png")));
		Image originalImage31 = originalIcon31.getImage();
		Image resizedImage31 = originalImage31.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon31 = new ImageIcon(resizedImage31);

		

		btnDangXuat.setIconTextGap(5);
		btnDangXuat.setIcon(resizedIcon31);
		

		JLabel lblTaiKhoan = new JLabel("Tài khoản");
		lblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTaiKhoan.setBounds(375, 690, 166, 34);
		contentPane.add(lblTaiKhoan);
		ImageIcon originalIcon29 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/user.png")));
		Image originalImage29 = originalIcon29.getImage();
		Image resizedImage29 = originalImage29.getScaledInstance(28, 28, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon29 = new ImageIcon(resizedImage29);

		lblTaiKhoan.setHorizontalTextPosition(SwingConstants.LEFT);

		lblTaiKhoan.setIconTextGap(33);
		lblTaiKhoan.setIcon(resizedIcon29);
		
		
		comboBoxTaiKhoan = new JComboBox();
		comboBoxTaiKhoan.setBounds(553, 697, 117, 27);
		comboBoxTaiKhoan.addItem("");
		contentPane.add(comboBoxTaiKhoan);
		
		JButton btnDoiMK = new JButton("Đổi mật khẩu");
		btnDoiMK.setBackground(new Color(255, 255, 0));
		btnDoiMK.addActionListener(actionlistener);
		btnDoiMK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDoiMK.setBounds(1158, 669, 187, 38);
		contentPane.add(btnDoiMK);
		ImageIcon originalIcon30 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/DoiMK_TK.png")));
		Image originalImage30 = originalIcon30.getImage();
		Image resizedImage30 = originalImage30.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon30 = new ImageIcon(resizedImage30);
		btnDoiMK.setIconTextGap(20);
		btnDoiMK.setIcon(resizedIcon30);
		
		this.setVisible(true);
	}

	public JTextField getTextFieldMaNV_Find() {
		return textFieldMaNV_Find;
	}

	public void setTextFieldMaNV_Find(JTextField textFieldMaNV_Find) {
		this.textFieldMaNV_Find = textFieldMaNV_Find;
	}

	public JTextField getTextFieldID() {
		return textFieldID;
	}

	public void setTextFieldID(JTextField textFieldID) {
		this.textFieldID = textFieldID;
	}

	public JTextField getTextFieldname() {
		return textFieldname;
	}

	public void setTextFieldname(JTextField textFieldname) {
		this.textFieldname = textFieldname;
	}

	public JTextField getTextFieldDoB() {
		return textFieldDoB;
	}

	public void setTextFieldDoB(JTextField textFieldDoB) {
		this.textFieldDoB = textFieldDoB;
	}

	public JTextField getTextFieldPhone() {
		return textFieldPhone;
	}

	public void setTextFieldPhone(JTextField textFieldPhone) {
		this.textFieldPhone = textFieldPhone;
	}

	public JTextField getTextFieldChucVu() {
		return textFieldChucVu;
	}

	public void setTextFieldChucVu(JTextField textFieldChucVu) {
		this.textFieldChucVu = textFieldChucVu;
	}

	public JTextField getTextFieldTrinhDo() {
		return textFieldTrinhDo;
	}

	public void setTextFieldTrinhDo(JTextField textFieldTrinhDo) {
		this.textFieldTrinhDo = textFieldTrinhDo;
	}

	public JTextField getTextFieldluong() {
		return textFieldluong;
	}

	public ButtonGroup getBtgr() {
		return btgr;
	}

	public void setBtgr(ButtonGroup btgr) {
		this.btgr = btgr;
	}

	public JComboBox getDS_DT_FIND() {
		return DS_DT_FIND;
	}

	public void setDS_DT_FIND(JComboBox dS_DT_FIND) {
		DS_DT_FIND = dS_DT_FIND;
	}

	public JComboBox getDStinh_find() {
		return DStinh_find;
	}

	public void setDStinh_find(JComboBox dStinh_find) {
		DStinh_find = dStinh_find;
	}

	public JComboBox getDSquequan_Find() {
		return DSquequan_Find;
	}

	public void setDSquequan_Find(JComboBox dSquequan_Find) {
		DSquequan_Find = dSquequan_Find;
	}

	public JComboBox getDSphongbanFind() {
		return DSphongbanFind;
	}

	public void setDSphongbanFind(JComboBox dSphongbanFind) {
		DSphongbanFind = dSphongbanFind;
	}

	public JComboBox getLuaChonPban() {
		return LuaChonPban;
	}

	public void setLuaChonPban(JComboBox luaChonPban) {
		LuaChonPban = luaChonPban;
	}

	public JTextField getTextFieldTaikhoan() {
		return textFieldTaikhoan;
	}

	public void setTextFieldTaikhoan(String user) {
		this.textFieldTaikhoan.setText(user);
	}

	public JComboBox getComboBoxTaiKhoan() {
		return comboBoxTaiKhoan;
	}

	public void setComboBoxTaiKhoan(JComboBox comboBoxTaiKhoan) {
		this.comboBoxTaiKhoan = comboBoxTaiKhoan;
	}
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	// conTroller

	public void xoaForm() {
		textFieldID.setText("");
		textFieldname.setText("");
		btgr.clearSelection();
		textFieldDoB.setText("");
		DS_DT_FIND.setSelectedIndex(-1);
		DStinh_find.setSelectedIndex(-1);
		LuaChonPban.setSelectedIndex(-1);
		textFieldPhone.setText("");
		textFieldChucVu.setText("");
		textFieldluong.setText("");
		textFieldTrinhDo.setText("");
		comboBoxTaiKhoan.setSelectedItem("");
	}
	
	public void themNhanVienVaoTable(NhanVien NV) {
		 DecimalFormat df = new DecimalFormat("#.##");
	     String luongFormatted = df.format(NV.getLuong());
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		model_table.addRow(new Object[] { NV.getMaNV(), NV.getHoTen(), NV.isGioiTinh() ? "Nam" : "Nữ", NV.getNgaySinh(),
				NV.getDanToc(), NV.getTinh().getTenTinh(), NV.getSoDT(), NV.getPhongban().getTenPB(), NV.getChucvu(),
				NV.getTDHV(), NV.getLuong(), NV.getTaiKhoan() });
	}

	public void themNhanVien(NhanVien NV) {
	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
	    String currentUser = this.textFieldTaikhoan.getText();
	    boolean isAdmin = currentUser.equals("admin");
	    String selectedTaiKhoan = this.comboBoxTaiKhoan.getSelectedItem().toString();

	    // Kiểm tra quyền của người dùng trước
	    if (!isAdmin && !currentUser.equals(selectedTaiKhoan)) {
	        JOptionPane.showMessageDialog(null, "Bạn chỉ có thể thêm thông tin cho chính mình!");
	        return;
	    }

	    // Kiểm tra nếu tài khoản nhân viên đã tồn tại
	    if (this.model.getInstance().KiemTraTaiKhoanNhanVienTonTai(NV.getTaiKhoan())) {
	        JOptionPane.showMessageDialog(null, "Tài khoản đã tạo với thông tin của nhân viên khác");
	        return;
	    }

	    // Kiểm tra nếu nhân viên đã tồn tại
	    if (this.model.getInstance().KiemTraTonTai(NV)) {
	        JOptionPane.showMessageDialog(null, "Nhân viên đã tồn tại!");
	        return;
	    }

	    // Thêm nhân viên vào danh sách
	    this.model.getInstance().insert(NV);
	    themNhanVienVaoTable(NV);
	    JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
	}




		//cap nhat
	public void capnhatNhanVien(NhanVien NV) {
	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
	    String currentUser = this.textFieldTaikhoan.getText();
	    boolean isAdmin = currentUser.equals("admin");

	    // Tìm nhân viên hiện tại trong danh sách
	    boolean nvTonTai = false;
	    for (NhanVien nvTrongDS : this.model.getInstance().getDSNhanVien()) {
	        if (nvTrongDS.getMaNV() == NV.getMaNV()) {
	            nvTonTai = true;
	            
	            // Kiểm tra nếu tài khoản đăng nhập hiện tại hoặc là admin
	            if (isAdmin || nvTrongDS.getTaiKhoan().equals(currentUser)) {
	                // Kiểm tra nếu tài khoản bị thay đổi
	                if (!nvTrongDS.getTaiKhoan().equals(NV.getTaiKhoan())) {
	                    JOptionPane.showMessageDialog(null, "Không thể cập nhật! Không được thay đổi tài khoản hoặc mã nhân viên.");
	                    return;
	                }

	                // Cập nhật thông tin nhân viên
	                this.model.getInstance().update(NV);
	                int soLuongDong = model_table.getRowCount();
	                for (int i = 0; i < soLuongDong; i++) {
	                    String id = model_table.getValueAt(i, 0).toString();
	                    if (id.equals(String.valueOf(NV.getMaNV()))) {
	                        // Không cho phép cập nhật mã nhân viên và tài khoản
	                        // Chỉ cập nhật các thông tin khác
	                        DecimalFormat df = new DecimalFormat("#.##");
	                        String luongFormatted = df.format(NV.getLuong());
	                        model_table.setValueAt(NV.getHoTen(), i, 1);
	                        model_table.setValueAt(NV.isGioiTinh() ? "Nam" : "Nữ", i, 2);
	                        model_table.setValueAt(NV.getNgaySinh(), i, 3);
	                        model_table.setValueAt(NV.getDanToc(), i, 4);
	                        model_table.setValueAt(NV.getTinh().getTenTinh(), i, 5);
	                        model_table.setValueAt(NV.getSoDT(), i, 6);
	                        model_table.setValueAt(NV.getPhongban().getTenPB(), i, 7);
	                        model_table.setValueAt(NV.getChucvu(), i, 8);
	                        model_table.setValueAt(NV.getTDHV(), i, 9);
	                        model_table.setValueAt(luongFormatted, i, 10);
	                        // Không cập nhật cột mã nhân viên và tài khoản
	                    }
	                }
	                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
	                return;
	            } else {
	                JOptionPane.showMessageDialog(null, "Không thể cập nhật! Bạn không có quyền cập nhật thông tin này.");
	                return;
	            }
	        }
	    }

	    if (!nvTonTai) {
	        JOptionPane.showMessageDialog(null, "Không thể cập nhật! Nhân viên không tồn tại.");
	    }
	}


	// thực hiện thêm
	public void thucHienThemNhanVien() {
		try {
			// Lấy và kiểm tra mã nhân viên
			String maNVText = this.getTextFieldID().getText();
			if (maNVText.isEmpty()) {
				throw new IllegalArgumentException("Mã nhân viên không được bỏ trống.");
			}
			int MaNV = Integer.parseInt(maNVText);

			// Lấy và kiểm tra các giá trị khác
			String HoTen = this.getTextFieldname().getText();
			if (HoTen.isEmpty()) {
				throw new IllegalArgumentException("Họ tên không được bỏ trống.");
			}

			// Kiểm tra radio button giới tính
			if (this.getBtgr().getSelection() == null) {
				throw new IllegalArgumentException("Vui lòng chọn giới tính.");
			}
			boolean gioiTinh = this.getBtgr().getSelection().getActionCommand().equals("Nam");

			String ngaysinhText = this.getTextFieldDoB().getText();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false); // Bắt lỗi ngày tháng không hợp lệ
			Date ngaysinh;
			try {
				ngaysinh = dateFormat.parse(ngaysinhText);
			} catch (ParseException e) {
				throw new IllegalArgumentException("Ngày sinh không hợp lệ. Định dạng đúng là dd/MM/yyyy.");
			}

			// Kiểm tra Dân Tộc
			if (this.getDS_DT_FIND().getSelectedItem() == null) {
				throw new IllegalArgumentException("Vui lòng chọn Dân tộc.");
			}
			String DanToc = this.getDS_DT_FIND().getSelectedItem().toString();
			if (DanToc.isEmpty()) {
				throw new IllegalArgumentException("Dân tộc không được bỏ trống.");
			}

			int tenTinh = getDStinh_find().getSelectedIndex() - 1;
			if (tenTinh < 0) {
				throw new IllegalArgumentException("Vui lòng chọn Tỉnh.");
			}
			QueQuan Tinh = QueQuan.getTinhById(tenTinh);
			if (Tinh == null) {
				throw new IllegalArgumentException("Tỉnh không hợp lệ.");
			}

			String soDT = this.getTextFieldPhone().getText();
			if (soDT.isEmpty()) {
				throw new IllegalArgumentException("Số điện thoại không được bỏ trống.");
			}
			if (!soDT.matches("\\d{10}")) {
				throw new IllegalArgumentException(
						"Số điện thoại không hợp lệ. Chỉ chứa các chữ số và có độ dài 10 ký tự.");
			}

			int tenPB = getLuaChonPban().getSelectedIndex() - 1;
			if (tenPB < 0) {
				throw new IllegalArgumentException("Vui lòng chọn Phòng ban.");
			}
			PhongBan pb = PhongBan.getphongbanById(tenPB);
			if (pb == null) {
				throw new IllegalArgumentException("Phòng ban không hợp lệ.");
			}

			String chucvu = this.getTextFieldChucVu().getText();
			if (chucvu.isEmpty()) {
				throw new IllegalArgumentException("Chức vụ không được bỏ trống.");
			}

			String TDHV = this.getTextFieldTrinhDo().getText();
			if (TDHV.isEmpty()) {
				throw new IllegalArgumentException("Trình độ học vấn không được bỏ trống.");
			}

			String luongText = this.getTextFieldluong().getText();
			if (!luongText.matches("\\d+(\\.\\d+)?")) {
				throw new IllegalArgumentException(
						"Lương không hợp lệ. Chỉ chứa các chữ số và có thể có dấu chấm thập phân.");
			}
			double luong = Double.parseDouble(luongText);
			

			String taikhoan = this.comboBoxTaiKhoan.getSelectedItem().toString();
			if (taikhoan.isEmpty()) {
				throw new IllegalArgumentException("Tài khoản không được bỏ trống.");
			}

			NhanVien nv = new NhanVien(MaNV, HoTen, gioiTinh, dateFormat.format(ngaysinh), DanToc, Tinh, soDT, pb,
					chucvu, TDHV, luong, taikhoan);
			this.themNhanVien(nv);
			this.lgin.getInstance().writeToFile();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
		}
	}

	// thực hiện cập nhật
	public void thucHienUpdateNhanVien() {
		try {
			// Kiểm tra xem có nhân viên nào được chọn hay không
	        if (table.getSelectedRow() == -1) {
	            throw new IllegalArgumentException("Vui lòng chọn một nhân viên để cập nhật thông tin!");
	        }
			// Lấy và kiểm tra mã nhân viên
			
			String maNVText = this.getTextFieldID().getText();
			if (maNVText.isEmpty()) {
				throw new IllegalArgumentException("Mã nhân viên không được bỏ trống.");
			}
			int MaNV = Integer.parseInt(maNVText);

			// Lấy và kiểm tra các giá trị khác
			String HoTen = this.getTextFieldname().getText();
			if (HoTen.isEmpty()) {
				throw new IllegalArgumentException("Họ tên không được bỏ trống.");
			}

			// Kiểm tra radio button giới tính
			if (this.getBtgr().getSelection() == null) {
				throw new IllegalArgumentException("Vui lòng chọn giới tính.");
			}
			boolean gioiTinh = this.getBtgr().getSelection().getActionCommand().equals("Nam");

			String ngaysinhText = this.getTextFieldDoB().getText();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false); // Bắt lỗi ngày tháng không hợp lệ
			Date ngaysinh;
			try {
				ngaysinh = dateFormat.parse(ngaysinhText);
			} catch (ParseException e) {
				throw new IllegalArgumentException("Ngày sinh không hợp lệ. Định dạng đúng là dd/MM/yyyy.");
			}

			// Kiểm tra Dân Tộc
			if (this.getDS_DT_FIND().getSelectedItem() == null) {
				throw new IllegalArgumentException("Vui lòng chọn Dân tộc.");
			}
			String DanToc = this.getDS_DT_FIND().getSelectedItem().toString();
			if (DanToc.isEmpty()) {
				throw new IllegalArgumentException("Dân tộc không được bỏ trống.");
			}

			int tenTinh = getDStinh_find().getSelectedIndex() - 1;
			if (tenTinh < 0) {
				throw new IllegalArgumentException("Vui lòng chọn Tỉnh.");
			}
			QueQuan Tinh = QueQuan.getTinhById(tenTinh);
			if (Tinh == null) {
				throw new IllegalArgumentException("Tỉnh không hợp lệ.");
			}

			String soDT = this.getTextFieldPhone().getText();
			if (soDT.isEmpty()) {
				throw new IllegalArgumentException("Số điện thoại không được bỏ trống.");
			}
			if (!soDT.matches("\\d{10}")) {
				throw new IllegalArgumentException(
						"Số điện thoại không hợp lệ. Chỉ chứa các chữ số và có độ dài 10 ký tự.");
			}

			int tenPB = getLuaChonPban().getSelectedIndex() - 1;
			if (tenPB < 0) {
				throw new IllegalArgumentException("Vui lòng chọn Phòng ban.");
			}
			PhongBan pb = PhongBan.getphongbanById(tenPB);
			if (pb == null) {
				throw new IllegalArgumentException("Phòng ban không hợp lệ.");
			}

			String chucvu = this.getTextFieldChucVu().getText();
			if (chucvu.isEmpty()) {
				throw new IllegalArgumentException("Chức vụ không được bỏ trống.");
			}

			String TDHV = this.getTextFieldTrinhDo().getText();
			if (TDHV.isEmpty()) {
				throw new IllegalArgumentException("Trình độ học vấn không được bỏ trống.");
			}

			String luongText = this.getTextFieldluong().getText();
			if (!luongText.matches("\\d+(\\.\\d+)?")) {
				throw new IllegalArgumentException(
						"Lương không hợp lệ. Chỉ chứa các chữ số và có thể có dấu chấm thập phân.");
			}
			double luong = Double.parseDouble(luongText);
			

			String taikhoan = this.comboBoxTaiKhoan.getSelectedItem().toString();
			if (taikhoan.isEmpty()) {
				throw new IllegalArgumentException("Tài khoản không được bỏ trống.");
			}

			NhanVien nv = new NhanVien(MaNV, HoTen, gioiTinh, dateFormat.format(ngaysinh), DanToc, Tinh, soDT, pb,
					chucvu, TDHV, luong, taikhoan);
			this.capnhatNhanVien(nv);
			this.lgin.getInstance().writeToFile();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
		}
	}

	public NhanVien getNhanVienDangChon() {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		int i_row = table.getSelectedRow();
		if (i_row < 0) {
			return null; // Không có dòng nào được chọn
		}

		// Lấy và kiểm tra mã nhân viên
		int MaNV = Integer.valueOf(model_table.getValueAt(i_row, 0).toString());

		// Lấy và kiểm tra các giá trị khác
		String HoTen = model_table.getValueAt(i_row, 1).toString();

		boolean gioiTinh = model_table.getValueAt(i_row, 2).toString().equals("Nam");

		String ngaysinh = model_table.getValueAt(i_row, 3).toString();
		String DanToc = model_table.getValueAt(i_row, 4).toString();
		QueQuan Tinh = QueQuan.getTinhbyTen(model_table.getValueAt(i_row, 5).toString());
		String soDT = model_table.getValueAt(i_row, 6).toString();
		PhongBan pb = PhongBan.getPhongBanbyTen(model_table.getValueAt(i_row, 7).toString());
		String chucvu = model_table.getValueAt(i_row, 8).toString();
		String TDHV = model_table.getValueAt(i_row, 9).toString();
		Double luong = Double.valueOf(model_table.getValueAt(i_row, 10).toString());
		String taiKhoan = model_table.getValueAt(i_row, 11).toString();
		return new NhanVien(MaNV, HoTen, gioiTinh, ngaysinh, DanToc, Tinh, soDT, pb, chucvu, TDHV, luong, taiKhoan);
	}

	public void hienThiThongTinNhanVienDaChon() {
		NhanVien nv = getNhanVienDangChon();
		if (nv == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để hiển thị thông tin!");
			return;
		}

		this.textFieldID.setText(String.valueOf(nv.getMaNV()));
		this.textFieldname.setText(nv.getHoTen());

		if (nv.isGioiTinh()) {
			this.rdbtn_nam.setSelected(true);
		} else {
			this.rdbtn_nu.setSelected(true);
		}
		this.textFieldDoB.setText(nv.getNgaySinh());
		this.DS_DT_FIND.setSelectedItem(nv.getDanToc());
		if (nv.getDanToc() != null) {
			DS_DT_FIND.setSelectedItem(nv.getDanToc());
		} else {
			DS_DT_FIND.setSelectedItem("");
		}
		this.DStinh_find.setSelectedItem(nv.getTinh());
		if (nv.getTinh() != null) {
			DStinh_find.setSelectedItem(nv.getTinh().getTenTinh());
		} else {
			DStinh_find.setSelectedItem("");
		}
		this.textFieldPhone.setText(nv.getSoDT());
		if (nv.getPhongban() != null) {
			LuaChonPban.setSelectedItem(nv.getPhongban().getTenPB());
		} else {
			LuaChonPban.setSelectedItem("");
		}
		this.textFieldChucVu.setText(nv.getChucvu());
		this.textFieldTrinhDo.setText(nv.getTDHV());
		this.textFieldluong.setText(String.valueOf(nv.getLuong()));

		this.comboBoxTaiKhoan.setSelectedItem(nv.getTaiKhoan());
		if (nv.getTaiKhoan() != null) {
			comboBoxTaiKhoan.setSelectedItem(nv.getTaiKhoan());
		} else {
			comboBoxTaiKhoan.setSelectedItem("");
		}
	}
	
	public void thucHienXoa() {
	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
	    int i_row = table.getSelectedRow();
	    if (i_row >= 0) {
	        int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa nhân viên đã chọn?");
	        if (luaChon == JOptionPane.YES_OPTION) {
	            NhanVien nv = getNhanVienDangChon();
	            if (nv != null) {
	                try {
	                    // Xóa nhân viên khỏi model
	                    this.model.getInstance().delete(nv); 
	                    // Xóa nhân viên khỏi bảng
	                    model_table.removeRow(i_row);
	                    // Tải lại dữ liệu
	                    thucHienTaiLaiDuLieu();

	                    // Tìm và xóa tài khoản liên quan
	                    String taiKhoanNV = nv.getTaiKhoan();
	                    ArrayList<TaiKhoan> dstk = this.model.getInstance().getDSTaiKhoan();
	                    for (int i = 0; i < dstk.size(); i++) {
	                        if (dstk.get(i).getTaiKhoan().equals(taiKhoanNV)) {
	                            this.model.getInstance().deleteTK(dstk.get(i));
	                            DefaultTableModel model_table_TK = (DefaultTableModel) this.dk.getInstance().getTable().getModel();
	                            model_table_TK.removeRow(i);
	                            this.comboBoxTaiKhoan.removeItem(taiKhoanNV);
	                            break;
	                        }
	                    }
	                    // In ra danh sách tài khoản sau khi xóa
	                    System.out.println("Danh sách tài khoản sau khi xóa: " + this.model.getInstance().getDSTaiKhoan());
	                    // In ra danh sách nhân viên sau khi xóa
	                    System.out.println("Danh sách nhân viên sau khi xóa tài khoản liên quan: " + this.model.getInstance().getDSNhanVien());
	                    JOptionPane.showMessageDialog(this, "Xóa nhân viên và tài khoản thành công!");
	                	this.lgin.getInstance().writeToFile();
	                } catch (Exception e) {
	                    JOptionPane.showMessageDialog(this, "Xóa nhân viên hoặc tài khoản thất bại!");
	                }
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để xóa!");
	    }
	}


	public void thucHienTim() {
	    // Lấy các giá trị tìm kiếm
	    int queQuan_find = this.getDSquequan_Find().getSelectedIndex() - 1;
	    int phongban_find = this.getDSphongbanFind().getSelectedIndex() - 1;
	    String maNV_find = this.getTextFieldMaNV_Find().getText();
	    
	    // Kiểm tra nếu các tiêu chí tìm kiếm đều trống
	    if (queQuan_find < 0 && phongban_find < 0 && maNV_find.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Bạn chưa nhập tiêu chí tìm kiếm.");
	        return;
	    }

	    // Tải lại dữ liệu ban đầu vào bảng
	    this.thucHienTaiLaiDuLieu();

	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
	    int soLuongDong = model_table.getRowCount();

	    Set<Integer> idCuaNVcanXoa = new TreeSet<>();

	    // Lọc theo quê quán
	    if (queQuan_find >= 0) {
	        QueQuan tinhDaChon = QueQuan.getTinhById(queQuan_find);
	        for (int i = 0; i < soLuongDong; i++) {
	            String tenTinh = model_table.getValueAt(i, 5).toString();
	            int id = Integer.valueOf(model_table.getValueAt(i, 0).toString());
	            if (!tenTinh.equals(tinhDaChon.getTenTinh())) {
	                idCuaNVcanXoa.add(id);
	            }
	        }
	    }

	    // Lọc theo phòng ban
	    if (phongban_find >= 0) {
	        PhongBan PBDaChon = PhongBan.getphongbanById(phongban_find);
	        for (int i = 0; i < soLuongDong; i++) {
	            String tenPB = model_table.getValueAt(i, 7).toString();
	            int id = Integer.valueOf(model_table.getValueAt(i, 0).toString());
	            if (!tenPB.equals(PBDaChon.getTenPB())) {
	                idCuaNVcanXoa.add(id);
	            }
	        }
	    }

	    // Lọc theo mã nhân viên
	    if (!maNV_find.isEmpty()) {
	        for (int i = 0; i < soLuongDong; i++) {
	            int id = Integer.valueOf(model_table.getValueAt(i, 0).toString());
	            if (id != Integer.valueOf(maNV_find)) {
	                idCuaNVcanXoa.add(id);
	            }
	        }
	    }

	    // Xóa các dòng không phù hợp
	    for (Integer idCanXoa : idCuaNVcanXoa) {
	        for (int i = 0; i < model_table.getRowCount(); i++) {
	            int idTrongTable = Integer.valueOf(model_table.getValueAt(i, 0).toString());
	            if (idTrongTable == idCanXoa) {
	                model_table.removeRow(i);
	                break;
	            }
	        }
	    }

	    // Kiểm tra nếu không còn dòng nào sau khi lọc
	    if (model_table.getRowCount() == 0) {
	        JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên nào khớp với tiêu chí tìm kiếm.");
	    }
	}


	public void thucHienTaiLaiDuLieu() {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();

		model_table.setRowCount(0);
		boolean check = false;

		for (NhanVien nv : this.model.getInstance().getDSNhanVien()) {
			this.themNhanVienVaoTable(nv);
			check = true;
		}
		if (!check) {
			JOptionPane.showMessageDialog(this, "Danh sách rỗng");
		}
	}

	public void hienThiAbout() {
		JOptionPane.showMessageDialog(this, "Phần mềm quản lý nhân viên 1.0!");
	}

	public void thoatKhoiChuongTrinh() {
		int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoải khỏi chương trình?", "Exit",
				JOptionPane.YES_NO_OPTION);
		if (luaChon == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	
	public void thucHienXuatBaoCao() {
        thucHienTaiLaiDuLieu();
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        int soLuongDong = model_table.getRowCount();
        ArrayList<NhanVien> danhSachNhanVien = new ArrayList<NhanVien>();

        // Lấy dữ liệu từ bảng hiện tại
        for (int i = 0; i < soLuongDong; i++) {
            int maNV = Integer.parseInt(model_table.getValueAt(i, 0).toString());
            String tenNV = model_table.getValueAt(i, 1).toString();
            boolean gioiTinh = model_table.getValueAt(i, 2).toString().equals("Nam");
            String ngaySinh = model_table.getValueAt(i, 3).toString();
            String danToc = model_table.getValueAt(i, 4).toString();
            QueQuan tinh = QueQuan.getTinhbyTen(model_table.getValueAt(i, 5).toString());
            String soDT = model_table.getValueAt(i, 6).toString();
            PhongBan phongBan = PhongBan.getPhongBanbyTen(model_table.getValueAt(i, 7).toString());
            String chucVu = model_table.getValueAt(i, 8).toString();
            String TDHV = model_table.getValueAt(i, 9).toString();
            double luong = Double.parseDouble(model_table.getValueAt(i, 10).toString());
            String tk = model_table.getValueAt(i, 11).toString();
            NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, danToc, tinh, soDT, phongBan, chucVu, TDHV,
                    luong, tk);
            danhSachNhanVien.add(nv);
        }

        // Hiển thị hộp thoại để chọn tiêu chí và thứ tự sắp xếp
        String[] criteriaOptions = { "Tên", "Mã nhân viên", "Lương" };
        String[] orderOptions = { "Tăng dần", "Giảm dần" };

        String criteria = (String) JOptionPane.showInputDialog(null, "Chọn tiêu chí sắp xếp:",
                "Tổng hợp báo cáo", JOptionPane.QUESTION_MESSAGE, null, criteriaOptions, criteriaOptions[0]);

        String order = (String) JOptionPane.showInputDialog(null, "Chọn thứ tự sắp xếp:",
                "Tổng hợp báo cáo", JOptionPane.QUESTION_MESSAGE, null, orderOptions, orderOptions[0]);

        if (criteria != null && order != null) {
            Comparator<NhanVien> comparator = null;

            // Xác định comparator dựa trên tiêu chí sắp xếp
            switch (criteria) {
                case "Tên":
                    comparator = Comparator.comparing(NhanVien::getHoTen, String.CASE_INSENSITIVE_ORDER);
                    break;
                case "Mã nhân viên":
                    comparator = Comparator.comparingInt(NhanVien::getMaNV);
                    break;
                case "Lương":
                    comparator = Comparator.comparingDouble(NhanVien::getLuong);
                    break;
            }

            // Nếu chọn Giảm dần, đảo ngược comparator
            if (order.equals("Giảm dần")) {
                comparator = comparator.reversed();
            }

            // Sắp xếp danh sách nhân viên theo tiêu chí và thứ tự đã chọn
            danhSachNhanVien.sort(comparator);
            this.model.getInstance().setDSNhanVien(danhSachNhanVien);
            // Xóa toàn bộ dữ liệu hiện tại trong bảng
            model_table.setRowCount(0);
            boolean check = false;
            // Cập nhật lại bảng với dữ liệu đã sắp xếp
            for (NhanVien nv : danhSachNhanVien) {
                Object[] rowData = new Object[] { nv.getMaNV(), nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ",
                        nv.getNgaySinh(), nv.getDanToc(), nv.getTinh().getTenTinh(), nv.getSoDT(),
                        nv.getPhongban().getTenPB(), nv.getChucvu(), nv.getTDHV(), nv.getLuong(), nv.getTaiKhoan() };
                model_table.addRow(rowData);
                check = true;
            }
            if (check) {
                JOptionPane.showMessageDialog(this, "Đã tổng hợp báo cáo sắp xếp theo thứ tự " + criteria.toLowerCase() + " " + order.toLowerCase());
                this.lgin.getInstance().writeToFile();
            }
        }
    }
	

	
	//cách 2
	public void hienThiThongKe() {
	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
	    int soLuongDong = model_table.getRowCount();
	    ArrayList<NhanVien> danhSachNhanVien = new ArrayList<NhanVien>();

	    // Lấy dữ liệu từ bảng hiện tại
	    for (int i = 0; i < soLuongDong; i++) {
	        int maNV = Integer.parseInt(model_table.getValueAt(i, 0).toString());
	        String tenNV = model_table.getValueAt(i, 1).toString();
	        boolean gioiTinh = model_table.getValueAt(i, 2).toString().equals("Nam");
	        String ngaySinh = model_table.getValueAt(i, 3).toString();
	        String danToc = model_table.getValueAt(i, 4).toString();
	        QueQuan tinh = QueQuan.getTinhbyTen(model_table.getValueAt(i, 5).toString());
	        String soDT = model_table.getValueAt(i, 6).toString();
	        PhongBan phongBan = PhongBan.getPhongBanbyTen(model_table.getValueAt(i, 7).toString());
	        String chucVu = model_table.getValueAt(i, 8).toString();
	        String TDHV = model_table.getValueAt(i, 9).toString();
	        double luong = Double.parseDouble(model_table.getValueAt(i, 10).toString());
	        String tk = model_table.getValueAt(i, 11).toString();
	        NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, danToc, tinh, soDT, phongBan, chucVu, TDHV, luong, tk);
	        danhSachNhanVien.add(nv);
	    }

	    // Tính số lượng nhân viên theo phòng ban, giới tính và lương
	    HashMap<String, Integer> phongBanCounts = new HashMap<>();
	    HashMap<String, Integer> trinhDoCounts = new HashMap<>();
	    int soLuongNam = 0;
	    int soLuongNu = 0;
	    double tongLuong = 0;
	    this.luongCaoNhat = Double.MIN_VALUE;
	    this.luongThapNhat = Double.MAX_VALUE;
	    int duoi500 = 0;
	    int tu500Den1000 = 0;
	    int tren1000 = 0;

	    for (NhanVien nv : danhSachNhanVien) {
	        String tenPB = nv.getPhongban().getTenPB();
	        phongBanCounts.put(tenPB, phongBanCounts.getOrDefault(tenPB, 0) + 1);

	        // Thống kê giới tính
	        if (nv.isGioiTinh()) {
	            soLuongNam++;
	        } else {
	            soLuongNu++;
	        }

	        // Thống kê lương
	        tongLuong += nv.getLuong();
	        if (nv.getLuong() > luongCaoNhat) {
	            this.luongCaoNhat = nv.getLuong();
	        }
	        if (nv.getLuong() < this.luongThapNhat) {
	            this.luongThapNhat = nv.getLuong();
	        }

	        // Phân loại nhân viên theo các khoảng lương
	        if (nv.getLuong() < 500) {
	            duoi500++;
	        } else if (nv.getLuong() <= 1000) {
	            tu500Den1000++;
	        } else {
	            tren1000++;
	        }

	        // Thống kê theo trình độ học vấn
	        String trinhDo = nv.getTDHV();
	        trinhDoCounts.put(trinhDo, trinhDoCounts.getOrDefault(trinhDo, 0) + 1);
	    }

	    double luongTrungBinh = tongLuong / danhSachNhanVien.size();
	    double tyLeNam = (soLuongNam / (double) danhSachNhanVien.size()) * 100;
	    double tyLeNu = (soLuongNu / (double) danhSachNhanVien.size()) * 100;

	    // Tạo bảng thống kê
	    String[] columnNames = { "Loại thống kê", "Giá trị" };
	    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
	    JTable table = new JTable(tableModel);

	    // Thêm dữ liệu vào bảng
	    tableModel.addRow(new Object[] { "Số lượng nhân viên", danhSachNhanVien.size() });
	    tableModel.addRow(new Object[] { "Số lượng nhân viên nam", soLuongNam + " (" + String.format("%.2f", tyLeNam) + "%)" });
	    tableModel.addRow(new Object[] { "Số lượng nhân viên nữ", soLuongNu + " (" + String.format("%.2f", tyLeNu) + "%)" });
	    tableModel.addRow(new Object[] { "Lương trung bình", luongTrungBinh });
	    tableModel.addRow(new Object[] { "Lương cao nhất", luongCaoNhat });
	    tableModel.addRow(new Object[] { "Lương thấp nhất", luongThapNhat });
	    tableModel.addRow(new Object[] { "Số lượng nhân viên có lương dưới 500", duoi500 });
	    tableModel.addRow(new Object[] { "Số lượng nhân viên có lương từ 500 đến 1000", tu500Den1000 });
	    tableModel.addRow(new Object[] { "Số lượng nhân viên có lương trên 1000", tren1000 });

	    for (Map.Entry<String, Integer> entry : phongBanCounts.entrySet()) {
	        tableModel.addRow(new Object[] { "Phòng ban " + entry.getKey(), entry.getValue() });
	    }

	    for (Map.Entry<String, Integer> entry : trinhDoCounts.entrySet()) {
	        double tyLePhanTram = (entry.getValue() / (double) danhSachNhanVien.size()) * 100;
	        tableModel.addRow(new Object[] { "Trình độ " + entry.getKey(), entry.getValue() + " (" + String.format("%.2f", tyLePhanTram) + "%)" });
	    }

	    // Tạo JPanel để chứa bảng và các nút
	    JPanel panel = new JPanel(new BorderLayout());
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setPreferredSize(new Dimension(700, 400)); 
	    panel.add(scrollPane, BorderLayout.CENTER);

	    // Hiển thị thống kê cơ bản với nút "Thống kê chi tiết"
	    int option = JOptionPane.showOptionDialog(this, panel, "Thống kê nhân viên",
	            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Thống kê chi tiết", "Đóng"}, "Đóng");

	    // Nếu người dùng chọn "Thống kê chi tiết"
	    if (option == 0) {
	        // Tạo danh sách các loại thống kê chi tiết để lựa chọn
	        String[] options = { "Phòng ban", "Trình độ học vấn", "Lương" };
	        String loaiThongKe = (String) JOptionPane.showInputDialog(null, "Chọn loại thống kê chi tiết:",
	                "Chi tiết thống kê nhân viên", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

	        // Hiển thị chi tiết nhân viên theo loại thống kê đã chọn
	        if (loaiThongKe != null) {
	        	if (loaiThongKe.equals("Phòng ban")) {
	        	    String[] phongBanArray = phongBanCounts.keySet().toArray(new String[0]);
	        	    String phongBanChon = (String) JOptionPane.showInputDialog(null, "Chọn phòng ban để xem chi tiết:",
	        	            "Xem chi tiết nhân viên", JOptionPane.QUESTION_MESSAGE, null, phongBanArray, phongBanArray[0]);

	        	    if (phongBanChon != null) {
	        	        String[] chiTietColumnNames = { "Mã NV", "Tên","Giới tính", "Lương", "Trình độ", "Tài khoản" };
	        	        DefaultTableModel chiTietTableModel = new DefaultTableModel(chiTietColumnNames, 0);
	        	        JTable chiTietTable = new JTable(chiTietTableModel);

	        	        // Khởi tạo lại lương cao nhất và thấp nhất cho phòng ban đã chọn
	        	        double luongCaoNhatPB = Double.MIN_VALUE;
	        	        double luongThapNhatPB = Double.MAX_VALUE;

	        	        for (NhanVien nv : danhSachNhanVien) {
	        	            if (nv.getPhongban().getTenPB().equals(phongBanChon)) {
	        	                chiTietTableModel.addRow(new Object[] {
	        	                        nv.getMaNV(), nv.getHoTen(),nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(),
	        	                        nv.getTDHV(), nv.getTaiKhoan()
	        	                });

	        	                // Tính lại lương cao nhất và thấp nhất trong phòng ban này
	        	                if (nv.getLuong() > luongCaoNhatPB) {
	        	                    luongCaoNhatPB = nv.getLuong();
	        	                }
	        	                if (nv.getLuong() < luongThapNhatPB) {
	        	                    luongThapNhatPB = nv.getLuong();
	        	                }
	        	            }
	        	        }

	        	      
	        	        luongCaoNhat = luongCaoNhatPB;
	        	        luongThapNhat = luongThapNhatPB;

	        	        // Tạo panel chứa bảng và các nút sắp xếp, liệt kê lương
						JPanel chiTietPanel = new JPanel(new BorderLayout());

	        	        // Thêm bảng chi tiết vào panel
	        	        JScrollPane chiTietScrollPane = new JScrollPane(chiTietTable);
	        	        chiTietScrollPane.setPreferredSize(new Dimension(700, 400)); 
	        	        chiTietPanel.add(chiTietScrollPane, BorderLayout.CENTER);

	        	        // Tạo nút sắp xếp và xử lý sự kiện
	        	        JButton sapXepButton = new JButton("Sắp xếp");
	        	        sapXepButton.addActionListener(e -> {
	        	            // Hiển thị dialog cho phép chọn tiêu chí sắp xếp
	        	            String[] sapXepOptions = { "Mã NV", "Tên", "Lương" };
	        	            String selectedOption = (String) JOptionPane.showInputDialog(null,
	        	                    "Chọn tiêu chí sắp xếp:", "Sắp xếp bảng", JOptionPane.PLAIN_MESSAGE,
	        	                    null, sapXepOptions, sapXepOptions[0]);

	        	            if (selectedOption != null) {
	        	                // Hiển thị dialog cho phép chọn hướng sắp xếp
	        	                String[] sapXepDirection = { "Tăng dần", "Giảm dần" };
	        	                String selectedDirection = (String) JOptionPane.showInputDialog(null,
	        	                        "Chọn hướng sắp xếp:", "Hướng sắp xếp", JOptionPane.PLAIN_MESSAGE,
	        	                        null, sapXepDirection, sapXepDirection[0]);

	        	                if (selectedDirection != null) {
	        	                    // Sắp xếp lại danh sách chi tiết theo tiêu chí selectedOption và hướng selectedDirection
	        	                    int sortOrder = selectedDirection.equals("Tăng dần") ? 1 : -1; 
	        	                    Collections.sort(danhSachNhanVien, (nv1, nv2) -> {
	        	                        switch (selectedOption) {
	        	                            case "Mã NV":
	        	                                return Integer.compare(nv1.getMaNV(), nv2.getMaNV()) * sortOrder;
	        	                            case "Tên":
	        	                                return nv1.getHoTen().compareTo(nv2.getHoTen()) * sortOrder;
	        	                            case "Lương":
	        	                                return Double.compare(nv1.getLuong(), nv2.getLuong()) * sortOrder;
	        	                            default:
	        	                                return 0;
	        	                        }
	        	                    });

	        	                    // Xóa hết dữ liệu hiện tại trong chiTietTableModel
	        	                    chiTietTableModel.setRowCount(0);

	        	                    // Thêm lại dữ liệu đã sắp xếp vào chiTietTableModel
	        	                    for (NhanVien nv : danhSachNhanVien) {
	        	                        if (nv.getPhongban().getTenPB().equals(phongBanChon)) {
	        	                            chiTietTableModel.addRow(new Object[] {
	        	                                    nv.getMaNV(), nv.getHoTen(),nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(),
	        	                                    nv.getTDHV(), nv.getTaiKhoan()
	        	                            });
	        	                        }
	        	                    }
	        	                }
	        	            }
	        	        });

	        	        // Thêm nút sắp xếp vào panel chi tiết
	        	        chiTietPanel.add(sapXepButton, BorderLayout.SOUTH);

	        	        // Tạo nút liệt kê lương và xử lý sự kiện
	        	        JButton lietKeLuongButton = new JButton("Liệt kê lương");
	        	        lietKeLuongButton.addActionListener(e -> {
	        	            // Hiển thị dialog cho phép chọn "Lương cao nhất" hoặc "Lương thấp nhất"
	        	            String[] luongOptions = { "Lương cao nhất", "Lương thấp nhất" };
	        	            String selectedOption = (String) JOptionPane.showInputDialog(null,
	        	                    "Chọn tiêu chí lương:", "Liệt kê lương", JOptionPane.PLAIN_MESSAGE,
	        	                    null, luongOptions, luongOptions[0]);
	        	            
	        	            if (selectedOption != null) {
	        	                // Xóa hết dữ liệu hiện tại trong chiTietTableModel
	        	                chiTietTableModel.setRowCount(0);

	        	                // Thêm vào chỉ những nhân viên thỏa mãn điều kiện lương cao nhất hoặc thấp nhất
	        	                if (selectedOption.equals("Lương cao nhất")) {
	        	                    for (NhanVien nv : danhSachNhanVien) {
	        	                        if (nv.getPhongban().getTenPB().equals(phongBanChon) && nv.getLuong() ==  luongCaoNhat) {
	        	                            chiTietTableModel.addRow(new Object[] {
	        	                                    nv.getMaNV(), nv.getHoTen(),nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(),
	        	                                    nv.getTDHV(), nv.getTaiKhoan()
	        	                            });
	        	                        }
	        	                    }
	        	                } else if (selectedOption.equals("Lương thấp nhất")) {
	        	                    for (NhanVien nv : danhSachNhanVien) {
	        	                        if (nv.getPhongban().getTenPB().equals(phongBanChon) && nv.getLuong() ==  luongThapNhat) {
	        	                            chiTietTableModel.addRow(new Object[] {
	        	                                    nv.getMaNV(), nv.getHoTen(),nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(),
	        	                                    nv.getTDHV(), nv.getTaiKhoan()
	        	                            });
	        	                        }
	        	                    }
	        	                }
	        	            }
	        	        });

	        	        // Thêm nút liệt kê lương vào panel chi tiết
	        	        chiTietPanel.add(lietKeLuongButton, BorderLayout.NORTH);

	        	        // Hiển thị dialog thông tin chi tiết
	        	        JOptionPane.showMessageDialog(this, chiTietPanel, "Chi tiết nhân viên phòng ban " + phongBanChon, JOptionPane.INFORMATION_MESSAGE);
	        	    }

	        	} else if (loaiThongKe.equals("Trình độ học vấn")) {
	                String[] trinhDoArray = trinhDoCounts.keySet().toArray(new String[0]);
	                String trinhDoChon = (String) JOptionPane.showInputDialog(null, "Chọn trình độ học vấn để xem chi tiết:",
	                        "Xem chi tiết nhân viên", JOptionPane.QUESTION_MESSAGE, null, trinhDoArray, trinhDoArray[0]);

	                if (trinhDoChon != null) {
	                    String[] chiTietColumnNames = { "Mã NV", "Tên","Giới tính", "Lương", "Phòng ban", "Tài khoản" };
	                    DefaultTableModel chiTietTableModel = new DefaultTableModel(chiTietColumnNames, 0);
	                    JTable chiTietTable = new JTable(chiTietTableModel);

	                    for (NhanVien nv : danhSachNhanVien) {
	                        if (nv.getTDHV().equals(trinhDoChon)) {
	                            chiTietTableModel.addRow(new Object[] {
	                                    nv.getMaNV(), nv.getHoTen(),nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(),
	                                    nv.getPhongban().getTenPB(), nv.getTaiKhoan()
	                            });
	                        }
	                    }

	                    JPanel chiTietPanel = new JPanel(new BorderLayout());
	                    JScrollPane chiTietScrollPane = new JScrollPane(chiTietTable);
	                    chiTietScrollPane.setPreferredSize(new Dimension(700, 400));
	                    chiTietPanel.add(chiTietScrollPane, BorderLayout.CENTER);
	                 // Thêm nút "Sắp xếp" cho option Trình độ học vấn và xử lý sự kiện
	                    JButton sapXepButton = new JButton("Sắp xếp");
	                    sapXepButton.addActionListener(e -> {
	                        // Hiển thị dialog cho phép chọn tiêu chí sắp xếp
	                        String[] sapXepOptions = { "Mã NV", "Tên", "Lương" };
	                        String selectedOption = (String) JOptionPane.showInputDialog(null,
	                                "Chọn tiêu chí sắp xếp:", "Sắp xếp bảng", JOptionPane.PLAIN_MESSAGE,
	                                null, sapXepOptions, sapXepOptions[0]);

	                        if (selectedOption != null) {
	                            // Hiển thị dialog cho phép chọn hướng sắp xếp
	                            String[] sapXepDirection = { "Tăng dần", "Giảm dần" };
	                            String selectedDirection = (String) JOptionPane.showInputDialog(null,
	                                    "Chọn hướng sắp xếp:", "Hướng sắp xếp", JOptionPane.PLAIN_MESSAGE,
	                                    null, sapXepDirection, sapXepDirection[0]);

	                            if (selectedDirection != null) {
	                                // Sắp xếp lại danh sách chi tiết theo tiêu chí selectedOption và hướng selectedDirection
	                                int sortOrder = selectedDirection.equals("Tăng dần") ? 1 : -1; // 1 for ascending, -1 for descending

	                                Collections.sort(danhSachNhanVien, (nv1, nv2) -> {
	                                    switch (selectedOption) {
	                                        case "Mã NV":
	                                            return Integer.compare(nv1.getMaNV(), nv2.getMaNV()) * sortOrder;
	                                        case "Tên":
	                                            return nv1.getHoTen().compareTo(nv2.getHoTen()) * sortOrder;
	                                        case "Lương":
	                                            return Double.compare(nv1.getLuong(), nv2.getLuong()) * sortOrder;
	                                        default:
	                                            return 0;
	                                    }
	                                });

	                                // Xóa hết dữ liệu hiện tại trong chiTietTableModel
	                                chiTietTableModel.setRowCount(0);

	                                // Thêm lại dữ liệu đã sắp xếp vào chiTietTableModel
	                                for (NhanVien nv : danhSachNhanVien) {
	                                    if (nv.getTDHV().equals(trinhDoChon)) {
	                                        chiTietTableModel.addRow(new Object[] {
	                                                nv.getMaNV(), nv.getHoTen(),nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(),
	                                                nv.getPhongban().getTenPB(), nv.getTaiKhoan()
	                                        });
	                                    }
	                                }

	                                // Cập nhật lại giá trị luongCaoNhat và luongThapNhat
	                                if (!danhSachNhanVien.isEmpty()) {
	                                    luongCaoNhat = danhSachNhanVien.get(0).getLuong();
	                                    luongThapNhat = danhSachNhanVien.get(0).getLuong();
	                                    for (NhanVien nv : danhSachNhanVien) {
	                                        if (nv.getLuong() > luongCaoNhat) {
	                                            luongCaoNhat = nv.getLuong();
	                                        }
	                                        if (nv.getLuong() < luongThapNhat) {
	                                            luongThapNhat = nv.getLuong();
	                                        }
	                                    }
	                                }
	                            }
	                        }
	                    });
	                    chiTietPanel.add(sapXepButton, BorderLayout.SOUTH);


	                    // Thêm nút "Liệt kê lương" cho option Trình độ học vấn và xử lý sự kiện
	                    JButton lietKeLuongButton = new JButton("Liệt kê lương");
	                    lietKeLuongButton.addActionListener(e -> {
	                        String[] luongOptions = { "Lương cao nhất", "Lương thấp nhất" };
	                        String selectedOption = (String) JOptionPane.showInputDialog(null,
	                                "Chọn tiêu chí lương:", "Liệt kê lương", JOptionPane.PLAIN_MESSAGE,
	                                null, luongOptions, luongOptions[0]);

	                        if (selectedOption != null) {
	                            chiTietTableModel.setRowCount(0);
	                            if (selectedOption.equals("Lương cao nhất")) {
	                                for (NhanVien nv : danhSachNhanVien) {
	                                    if (nv.getTDHV().equals(trinhDoChon) && nv.getLuong() == luongCaoNhat) {
	                                        chiTietTableModel.addRow(new Object[] {
	                                                nv.getMaNV(), nv.getHoTen(),nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(),
	                                                nv.getPhongban().getTenPB(), nv.getTaiKhoan()
	                                        });
	                                    }
	                                }
	                            } else if (selectedOption.equals("Lương thấp nhất")) {
	                                for (NhanVien nv : danhSachNhanVien) {
	                                    if (nv.getTDHV().equals(trinhDoChon) && nv.getLuong() == luongThapNhat) {
	                                        chiTietTableModel.addRow(new Object[] {
	                                                nv.getMaNV(), nv.getHoTen(),nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(),
	                                                nv.getPhongban().getTenPB(), nv.getTaiKhoan()
	                                        });
	                                    }
	                                }
	                            }
	                        }
	                    });
	                    chiTietPanel.add(lietKeLuongButton, BorderLayout.NORTH);
	                    JOptionPane.showMessageDialog(this, chiTietPanel, "Chi tiết nhân viên có trình độ " + trinhDoChon, JOptionPane.INFORMATION_MESSAGE);
	                }
	            } else if (loaiThongKe.equals("Lương")) {
	                String[] luongOptions = { "Dưới 500", "Từ 500 đến 1000", "Trên 1000" };
	                String luongChon = (String) JOptionPane.showInputDialog(null, "Chọn khoảng lương để xem chi tiết:",
	                        "Xem chi tiết nhân viên", JOptionPane.QUESTION_MESSAGE, null, luongOptions, luongOptions[0]);

	                if (luongChon != null) {
	                    String[] chiTietColumnNames = { "Mã NV", "Tên","Giới tính", "Lương", "Trình độ", "Phòng ban", "Tài khoản" };
	                    DefaultTableModel chiTietTableModel = new DefaultTableModel(chiTietColumnNames, 0);
	                    JTable chiTietTable = new JTable(chiTietTableModel);

	                    for (NhanVien nv : danhSachNhanVien) {
	                        if (luongChon.equals("Dưới 500") && nv.getLuong() < 500) {
	                            chiTietTableModel.addRow(new Object[] {
	                                    nv.getMaNV(), nv.getHoTen(),nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(),
	                                    nv.getTDHV(), nv.getPhongban().getTenPB(), nv.getTaiKhoan()
	                            });
	                        } else if (luongChon.equals("Từ 500 đến 1000") && nv.getLuong() >= 500 && nv.getLuong() <= 1000) {
	                            chiTietTableModel.addRow(new Object[] {
	                                    nv.getMaNV(), nv.getHoTen(),nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(),
	                                    nv.getTDHV(), nv.getPhongban().getTenPB(), nv.getTaiKhoan()
	                            });
	                        } else if (luongChon.equals("Trên 1000") && nv.getLuong() > 1000) {
	                            chiTietTableModel.addRow(new Object[] {
	                                    nv.getMaNV(), nv.getHoTen(),nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(),
	                                    nv.getTDHV(), nv.getPhongban().getTenPB(), nv.getTaiKhoan()
	                            });
	                        }
	                    }
	                    
	                    JPanel chiTietPanel = new JPanel(new BorderLayout());
	                    JScrollPane chiTietScrollPane = new JScrollPane(chiTietTable);
	                    chiTietScrollPane.setPreferredSize(new Dimension(700, 400));
	                    chiTietPanel.add(chiTietScrollPane, BorderLayout.CENTER);
	                    
	                    JButton sapXepButton = new JButton("Sắp xếp");
	                    sapXepButton.addActionListener(e -> {
	                        // Hiển thị dialog cho phép chọn tiêu chí sắp xếp
	                        String[] sapXepOptions = { "Mã NV", "Tên", "Lương" };
	                        String selectedOption = (String) JOptionPane.showInputDialog(null,
	                                "Chọn tiêu chí sắp xếp:", "Sắp xếp bảng", JOptionPane.PLAIN_MESSAGE,
	                                null, sapXepOptions, sapXepOptions[0]);

	                        if (selectedOption != null) {
	                            // Hiển thị dialog cho phép chọn hướng sắp xếp
	                            String[] sapXepDirection = { "Tăng dần", "Giảm dần" };
	                            String selectedDirection = (String) JOptionPane.showInputDialog(null,
	                                    "Chọn hướng sắp xếp:", "Hướng sắp xếp", JOptionPane.PLAIN_MESSAGE,
	                                    null, sapXepDirection, sapXepDirection[0]);

	                            if (selectedDirection != null) {
	                                // Sắp xếp lại danh sách nhân viên theo tiêu chí và hướng sắp xếp
	                                int sortOrder = selectedDirection.equals("Tăng dần") ? 1 : -1; // 1 for ascending, -1 for descending

	                                Collections.sort(danhSachNhanVien, (nv1, nv2) -> {
	                                    switch (selectedOption) {
	                                        case "Mã NV":
	                                            return Integer.compare(nv1.getMaNV(), nv2.getMaNV()) * sortOrder;
	                                        case "Tên":
	                                            return nv1.getHoTen().compareTo(nv2.getHoTen()) * sortOrder;
	                                        case "Lương":
	                                            return Double.compare(nv1.getLuong(), nv2.getLuong()) * sortOrder;
	                                        default:
	                                            return 0;
	                                    }
	                                });

	                                // Xóa hết dữ liệu hiện tại trong chiTietTableModel
	                                chiTietTableModel.setRowCount(0);

	                                // Thêm lại dữ liệu đã sắp xếp vào chiTietTableModel
	                                for (NhanVien nv : danhSachNhanVien) {
	                                    // Kiểm tra và thêm theo điều kiện lương được chọn
	                                    boolean luongConditionMet = false;
	                                    if (luongChon.equals("Dưới 500") && nv.getLuong() < 500) {
	                                        luongConditionMet = true;
	                                    } else if (luongChon.equals("Từ 500 đến 1000") && nv.getLuong() >= 500 && nv.getLuong() <= 1000) {
	                                        luongConditionMet = true;
	                                    } else if (luongChon.equals("Trên 1000") && nv.getLuong() > 1000) {
	                                        luongConditionMet = true;
	                                    }

	                                    if (luongConditionMet) {
	                                        chiTietTableModel.addRow(new Object[]{
	                                                nv.getMaNV(), nv.getHoTen(),nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(),
	                                                nv.getTDHV(), nv.getPhongban().getTenPB(), nv.getTaiKhoan()
	                                        });
	                                    }
	                                }
	                            }
	                        }
	                    });
	                    chiTietPanel.add(sapXepButton, BorderLayout.SOUTH);

	                    
	                    JButton lietKeLuongButton = new JButton("Liệt kê lương");
	                    lietKeLuongButton.addActionListener(e -> {
	                        String[] innerLuongOptions = { "Lương cao nhất", "Lương thấp nhất" };
	                        String selectedOption = (String) JOptionPane.showInputDialog(null,
	                                "Chọn tiêu chí lương:", "Liệt kê lương", JOptionPane.PLAIN_MESSAGE,
	                                null, innerLuongOptions, innerLuongOptions[0]);

	                        if (selectedOption != null) {
	                            // Xóa bảng và thêm các hàng dựa trên selectedOption
	                            chiTietTableModel.setRowCount(0);

	                            // Tính lại lương cao nhất và thấp nhất từ danh sách nhân viên hiển thị
	                            double maxLuong = Double.MIN_VALUE;
	                            double minLuong = Double.MAX_VALUE;

	                            for (NhanVien nv : danhSachNhanVien) {
	                                if (luongChon.equals("Dưới 500") && nv.getLuong() < 500) {
	                                    if (nv.getLuong() > maxLuong) {
	                                        maxLuong = nv.getLuong();
	                                    }
	                                    if (nv.getLuong() < minLuong) {
	                                        minLuong = nv.getLuong();
	                                    }
	                                } else if (luongChon.equals("Từ 500 đến 1000") && nv.getLuong() >= 500 && nv.getLuong() <= 1000) {
	                                    if (nv.getLuong() > maxLuong) {
	                                        maxLuong = nv.getLuong();
	                                    }
	                                    if (nv.getLuong() < minLuong) {
	                                        minLuong = nv.getLuong();
	                                    }
	                                } else if (luongChon.equals("Trên 1000") && nv.getLuong() > 1000) {
	                                    if (nv.getLuong() > maxLuong) {
	                                        maxLuong = nv.getLuong();
	                                    }
	                                    if (nv.getLuong() < minLuong) {
	                                        minLuong = nv.getLuong();
	                                    }
	                                }
	                            }

	                            luongCaoNhat = maxLuong;
	                            luongThapNhat = minLuong;

	                            // Thêm các nhân viên có lương bằng lương cao nhất hoặc thấp nhất vào bảng
	                            for (NhanVien nv : danhSachNhanVien) {
	                                if ((selectedOption.equals("Lương cao nhất") && nv.getLuong() == luongCaoNhat) ||
	                                    (selectedOption.equals("Lương thấp nhất") && nv.getLuong() == luongThapNhat)) {
	                                    chiTietTableModel.addRow(new Object[]{
	                                            nv.getMaNV(), nv.getHoTen(),nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(),
	                                            nv.getTDHV(), nv.getPhongban().getTenPB(), nv.getTaiKhoan()
	                                    });
	                                }
	                            }
	                        }
	                    });

	                    chiTietPanel.add(lietKeLuongButton, BorderLayout.NORTH);



	                    JOptionPane.showMessageDialog(this, chiTietPanel, "Chi tiết nhân viên có mức lương " + luongChon, JOptionPane.INFORMATION_MESSAGE);
	                }
	            }
	        }
	    }
	}

	



	public void dangxuat() {
		// TODO Auto-generated method stub
		this.dispose();
		this.setVisible(false);
		login.getInstance().setVisible(true);

	}

	public void QuanLiTaiKhoan() {
		// TODO Auto-generated method stub
		if (this.getTextFieldTaikhoan().getText().equals("admin")) {
			this.dispose();
			this.setVisible(false);
			DangKi.getInstance().setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "Bạn không phải ADMIN");
		}

	}

	public void DoiMatKhau() {
	    // Hiển thị hộp thoại để nhập mật khẩu mới và mật khẩu xác nhận
	    JPasswordField newPasswordField = new JPasswordField();
	    JPasswordField confirmPasswordField = new JPasswordField();
	    Object[] message = {
	        "Nhập mật khẩu mới:", newPasswordField,
	        "Nhập lại mật khẩu mới:", confirmPasswordField
	    };
	    int option = JOptionPane.showConfirmDialog(this, message, "Đổi mật khẩu", JOptionPane.OK_CANCEL_OPTION);

	    // Kiểm tra xem người dùng đã nhập mật khẩu mới hay chưa
	    if (option == JOptionPane.OK_OPTION) {
	        String newPassword = new String(newPasswordField.getPassword());
	        String confirmPassword = new String(confirmPasswordField.getPassword());

	        if (!newPassword.isEmpty() && newPassword.equals(confirmPassword)) {
	            try {
	                validatePassword(newPassword);
	                // Lấy tên tài khoản từ trường nhập
	                String username = this.textFieldTaikhoan.getText();
	                if (username.equals("admin")) {
	                    // Cập nhật mật khẩu mới cho admin
	                    login.getInstance().setPWadmin(newPassword);
	                    JOptionPane.showMessageDialog(this, "Mật khẩu admin đã được cập nhật thành công.");
	                } else {
	                    // Cập nhật mật khẩu mới vào danh sách tài khoản (nếu có)
	                    DangKi.getInstance().updatePassword(username, newPassword);
	                    JOptionPane.showMessageDialog(this, "Mật khẩu của " + username + " đã được cập nhật thành công.");
	                	this.lgin.getInstance().writeToFile();
	                }
	            } catch (IllegalArgumentException e) {
	                JOptionPane.showMessageDialog(this, e.getMessage());
	            }
	        } else {
	            // Hiển thị thông báo nếu mật khẩu mới không khớp hoặc trống
	            JOptionPane.showMessageDialog(this, "Mật khẩu mới không khớp hoặc trống.");
	        }
	    }
	}

	private void validatePassword(String matKhau) {
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
	}
	
	public void thongke() {
		this.dispose();
		this.setVisible(false);
		this.tke.getInstance().thongkedulieu();
		this.tke.getInstance().setVisible(true);
	}

}
