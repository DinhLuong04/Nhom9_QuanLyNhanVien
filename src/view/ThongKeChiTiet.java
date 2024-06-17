package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import controller.ThongKeChiTietController;
import model.NhanVien;
import model.QLNVmodel;
import view.ThongKe;
import javax.swing.JButton;

public class ThongKeChiTiet extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JButton btnSapXep;
    private JButton btnLietkeLuong;
    private ThongKe tke;
    private static ThongKeChiTiet instance;
    private DefaultTableModel chiTietTableModel;
    private String phongBanChon;
    private String trinhDoChon;
    private String luongChon;
    private String loaiThongKe;
    private ThongKeChiTietController controller;
    private ArrayList<NhanVien> danhNhanVienTrongBangHienTai = new ArrayList<NhanVien>();
	private login lgin;
	private QLNVmodel model;
	private ArrayList<String> phongBanArray;
	private ArrayList<String> TrinhdoArray;
	private QuanLyNhanVienView view;
    public static ThongKeChiTiet getInstance() {
        if (instance == null) {
            instance = new ThongKeChiTiet();
        }
        return instance;
    }

    public ThongKe getTke() {
        return tke;
    }

    public void setTke(ThongKe tke) {
        this.tke = tke;
    }
   
    private ThongKeChiTiet() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 978, 407);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        table = new JTable();
        table.setBackground(new Color(255, 128, 0));
        table.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(28, 10, 881, 317);
        contentPane.add(scrollPane);

        btnSapXep = new JButton("Sắp Xếp");
        controller = new ThongKeChiTietController(this);
        btnSapXep.addActionListener(controller);
        btnSapXep.setBounds(238, 339, 118, 21);
        contentPane.add(btnSapXep);

        btnLietkeLuong = new JButton("Liệt kê Lương");
        btnLietkeLuong.addActionListener(controller);
        btnLietkeLuong.setBounds(507, 339, 127, 21);
        contentPane.add(btnLietkeLuong);
        
        JButton btnThoat = new JButton("Thoát");
        btnThoat.addActionListener(controller);
        btnThoat.setBounds(764, 339, 85, 21);
        contentPane.add(btnThoat);
        setTitle("Thống kê chi tiết nhân viên");
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public String getPhongBanChon() {
        return phongBanChon;
    }

    public void setPhongBanChon(String phongBanChon) {
        this.phongBanChon = phongBanChon;
    }

    public DefaultTableModel getChiTietTableModel() {
        return chiTietTableModel;
    }

    public void setChiTietTableModel(DefaultTableModel chiTietTableModel) {
        this.chiTietTableModel = chiTietTableModel;
    }

    public String getTrinhDoChon() {
        return trinhDoChon;
    }

    public void setTrinhDoChon(String trinhDoChon) {
        this.trinhDoChon = trinhDoChon;
    }

    public String getLuongChon() {
        return luongChon;
    }

    public void setLuongChon(String luongChon) {
        this.luongChon = luongChon;
    }

    public String getLoaiThongKe() {
        return loaiThongKe;
    }

    public void setLoaiThongKe(String loaiThongKe) {
        this.loaiThongKe = loaiThongKe;
    }

    public ArrayList<NhanVien> getDanhNhanVienTrongBangHienTai() {
        return danhNhanVienTrongBangHienTai;
    }

    public void setDanhNhanVienTrongBangHienTai(ArrayList<NhanVien> danhNhanVienTrongBangHienTai) {
        this.danhNhanVienTrongBangHienTai = danhNhanVienTrongBangHienTai;
    }
    
    public boolean luachonThongKe() {
        this.danhNhanVienTrongBangHienTai.clear();
        String[] options = { "Phòng ban", "Trình độ học vấn", "Lương" };
        this.loaiThongKe = (String) JOptionPane.showInputDialog(null, "Chọn loại thống kê chi tiết:",
                "Chi tiết thống kê nhân viên", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (this.loaiThongKe != null) {
            boolean result = false;
            if (this.loaiThongKe.equals("Phòng ban")) {
                result = thongKeTheoPhongBan();
            } else if (this.loaiThongKe.equals("Trình độ học vấn")) {
                result = thongKeTheoTrinhDohocVan();
            } else if (this.loaiThongKe.equals("Lương")) {
                result = thongKeTheoKhoangLuong();
            }
            System.out.println("Loại thống kê : " + this.loaiThongKe);
            return result;
        } else {
            System.out.println("Không chọn loại thống kê nào, thoát.");
            return false;
        }
    }



    public boolean thongKeTheoPhongBan() {
        laydulieu();
        ArrayList<String> phongBanArray = this.phongBanArray;
        phongBanChon = (String) JOptionPane.showInputDialog(null, "Chọn phòng ban để xem chi tiết:",
                "Xem chi tiết nhân viên", JOptionPane.QUESTION_MESSAGE, null, phongBanArray.toArray(), phongBanArray.get(0));
        if (phongBanChon == null) {
            System.out.println("Thống kê theo phòng ban bị hủy.");
            return false; // Exit if the user cancels
        }

        String[] chiTietColumnNames = { "Mã NV", "Tên", "Giới tính", "Lương","Trình độ", "Phòng ban", "Tài khoản" };
        chiTietTableModel = new DefaultTableModel(chiTietColumnNames, 0);
        table.setModel(chiTietTableModel);
        setTitle("Danh sách nhân viên trong phòng ban " + phongBanChon);

        danhNhanVienTrongBangHienTai.clear();
        for (NhanVien nv : this.model.getInstance().getDSNhanVien()) {
            if (nv.getPhongban().getTenPB().equals(phongBanChon)) {
                chiTietTableModel.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ",
                        nv.getLuong(), nv.getTDHV(),nv.getPhongban().getTenPB(), nv.getTaiKhoan() });
                danhNhanVienTrongBangHienTai.add(nv);
            }
        }

        System.out.println("Danh sách nhân viên trong phòng ban " + phongBanChon + ":");
        for (NhanVien nv : danhNhanVienTrongBangHienTai) {
            System.out.println(nv);
        }
        return true;
    }



    public boolean thongKeTheoTrinhDohocVan() {
        laydulieu();
        ArrayList<String> trinhDoArray = this.TrinhdoArray;
        trinhDoChon = (String) JOptionPane.showInputDialog(null, "Chọn trình độ học vấn để xem chi tiết:",
                "Xem chi tiết nhân viên", JOptionPane.QUESTION_MESSAGE, null, trinhDoArray.toArray(), trinhDoArray.get(0));
        if (trinhDoChon == null) {
            System.out.println("Thống kê theo trình độ học vấn bị hủy.");
            return false; // Exit if the user cancels
        }

        String[] chiTietColumnNames = { "Mã NV", "Tên", "Giới tính", "Lương","Trình độ", "Phòng ban", "Tài khoản" };
        chiTietTableModel = new DefaultTableModel(chiTietColumnNames, 0);
        table.setModel(chiTietTableModel);
        setTitle("Danh sách nhân viên có trình độ học vấn " + trinhDoChon);

        danhNhanVienTrongBangHienTai.clear();
        for (NhanVien nv : this.model.getInstance().getDSNhanVien()) {
            if (nv.getTDHV().equals(trinhDoChon)) {
                chiTietTableModel.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ",
                        nv.getLuong(),nv.getTDHV(), nv.getPhongban().getTenPB(), nv.getTaiKhoan() });
                danhNhanVienTrongBangHienTai.add(nv);
            }
        }

        System.out.println("Danh sách nhân viên có trình độ học vấn " + trinhDoChon + ":");
        for (NhanVien nv : danhNhanVienTrongBangHienTai) {
            System.out.println(nv);
        }
        return true;
    }


    
    public boolean thongKeTheoKhoangLuong() {
        String[] luongArray = { "Dưới 500", "Từ 500 đến 1000", "Trên 1000" };
        luongChon = (String) JOptionPane.showInputDialog(null, "Chọn khoảng lương để xem chi tiết:", "Xem chi tiết nhân viên",
                JOptionPane.QUESTION_MESSAGE, null, luongArray, luongArray[0]);
        if (luongChon == null) {
            System.out.println("Thống kê theo khoảng lương bị hủy.");
            return false; // Exit if the user cancels
        }

        String[] chiTietColumnNames = { "Mã NV", "Tên", "Giới tính", "Lương", "Trình độ", "Phòng ban", "Tài khoản" };
        chiTietTableModel = new DefaultTableModel(chiTietColumnNames, 0);
        table.setModel(chiTietTableModel);
        setTitle("Danh sách nhân viên với mức lương " + luongChon + ":");

        danhNhanVienTrongBangHienTai.clear();
        for (NhanVien nv : this.model.getInstance().getDSNhanVien()) {
            if (luongChon.equals("Dưới 500") && nv.getLuong() < 500) {
                chiTietTableModel.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ",
                        nv.getLuong(), nv.getTDHV(), nv.getPhongban().getTenPB(), nv.getTaiKhoan() });
                danhNhanVienTrongBangHienTai.add(nv);
            } else if (luongChon.equals("Từ 500 đến 1000") && nv.getLuong() >= 500 && nv.getLuong() <= 1000) {
                chiTietTableModel.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ",
                        nv.getLuong(), nv.getTDHV(), nv.getPhongban().getTenPB(), nv.getTaiKhoan() });
                danhNhanVienTrongBangHienTai.add(nv);
            } else if (luongChon.equals("Trên 1000") && nv.getLuong() > 1000) {
                chiTietTableModel.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ",
                        nv.getLuong(), nv.getTDHV(), nv.getPhongban().getTenPB(), nv.getTaiKhoan() });
                danhNhanVienTrongBangHienTai.add(nv);
            }
        }

        System.out.println("Danh sách nhân viên với mức lương " + luongChon + ":");
        for (NhanVien nv : danhNhanVienTrongBangHienTai) {
            System.out.println(nv);
        }
        return true;
    }


    public void laydulieu() {
        phongBanArray = new ArrayList<String>();
        TrinhdoArray = new ArrayList<String>();

        this.lgin.getInstance().readFromFile();
        Set<String> uniquePhongBan = new HashSet<>();
        Set<String> uniqueTrinhDo = new HashSet<>();

        for (NhanVien nv : this.model.getInstance().getDSNhanVien()) {
            uniquePhongBan.add(nv.getPhongban().getTenPB());
            uniqueTrinhDo.add(nv.getTDHV());
        }

        // Chuyển từ Set về ArrayList (nếu cần)
        phongBanArray.addAll(uniquePhongBan);
        TrinhdoArray.addAll(uniqueTrinhDo);
    }
    
    public void thoat() {
    	this.dispose();
		this.setVisible(false);
		this.view.getInstanceView().setVisible(true);
		this.view.getInstanceView().thucHienTaiLaiDuLieu();
		
    }
}
