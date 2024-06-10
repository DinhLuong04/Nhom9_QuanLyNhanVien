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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
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
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;



import model.NhanVien;
import model.PhongBan;
import model.QLNVmodel;
import model.QueQuan;
import model.TaiKhoan;


import javax.swing.JRadioButton;
import java.awt.Color;

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

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1234, 1054);
		this.setSize(1650, 1080);
		this.setTitle("Phần mềm quản lý nhân viên");

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 128, 0));
		setJMenuBar(menuBar);

		JMenu menuFile = new JMenu("File");
		menuFile.setBackground(new Color(255, 128, 64));
		menuFile.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(menuFile);

		JMenuItem menuOpen = new JMenuItem("Open");

		menuOpen.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuFile.add(menuOpen);

		ImageIcon originalIcon1 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/folder-icon.png")));
		Image originalImage1 = originalIcon1.getImage();
		Image resizedImage1 = originalImage1.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage1);
		menuOpen.setIcon(resizedIcon);

		JMenuItem menuSave = new JMenuItem("Save");

		menuSave.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuFile.add(menuSave);
		ImageIcon originalIcon2 = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyNhanVienView.class.getResource("/Icon/storage-icon.png")));
		Image originalImage2 = originalIcon2.getImage();
		Image resizedImage2 = originalImage2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
		menuSave.setIcon(resizedIcon2);

		JSeparator separator = new JSeparator();
		menuFile.add(separator);

		JMenuItem menuExit = new JMenuItem("Exit");

		menuExit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuFile.add(menuExit);
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



	

}
