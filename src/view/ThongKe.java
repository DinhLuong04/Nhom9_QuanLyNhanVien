package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ThongKeController;
import model.NhanVien;
import model.PhongBan;
import model.QLNVmodel;
import model.QueQuan;

import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class ThongKe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnThoat;
	private QuanLyNhanVienView view;
	private double luongCaoNhat;
	private double luongThapNhat;
	private login lgin;
	private HashMap<String, Integer> trinhDoCounts;
	private HashMap<String, Integer>  phongBanCounts;
	private ArrayList<NhanVien> danhSachNhanVien;
	private ThongKeChiTiet tkchitiet;
	private static ThongKe instance;
	private QLNVmodel model;
	
	 public static ThongKe getInstance() {
        if (instance == null) {
            instance = new ThongKe();
        }
        return instance;
    }
	 
	public void tatThongKe() {
		this.dispose();
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ThongKe frame = new ThongKe();
					frame.thongkedulieu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ThongKe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 976, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		ActionListener actionlistener= new ThongKeController(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBackground(new Color(255, 128, 0));
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Loại thống kê", "Giá trị" }));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 10, 881, 317);
		
		contentPane.add(scrollPane);
		
		JButton btnThongKeChiTiet =new JButton("Thống kê chi tiết");
		btnThongKeChiTiet.addActionListener(actionlistener);
		btnThongKeChiTiet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThongKeChiTiet.setBounds(127, 332, 178, 21);
		contentPane.add(btnThongKeChiTiet);
		
		btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(actionlistener);
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThoat.setBounds(388, 332, 106, 21);
		contentPane.add(btnThoat);
//		this.setVisible(true);
	}
	
	public JTable getTable() {
		return table;
	}


	public void setTable(JTable table) {
		this.table = table;
	}
	

	public double getLuongCaoNhat() {
		return luongCaoNhat;
	}
	public void setLuongCaoNhat(double luongCaoNhat) {
		this.luongCaoNhat = luongCaoNhat;
	}
	public double getLuongThapNhat() {
		return luongThapNhat;
	}
	public void setLuongThapNhat(double luongThapNhat) {
		this.luongThapNhat = luongThapNhat;
	}
	public HashMap<String, Integer> getTrinhDoCounts() {
		return trinhDoCounts;
	}
	public void setTrinhDoCounts(HashMap<String, Integer> trinhDoCounts) {
		this.trinhDoCounts = trinhDoCounts;
	}
	public HashMap<String, Integer> getPhongBanCounts() {
		return phongBanCounts;
	}
	public void setPhongBanCounts(HashMap<String, Integer> phongBanCounts) {
		this.phongBanCounts = phongBanCounts;
	}
	
	public ArrayList<NhanVien> getDanhSachNhanVien() {
		return danhSachNhanVien;
	}
	public void setDanhSachNhanVien(ArrayList<NhanVien> danhSachNhanVien) {
		this.danhSachNhanVien = danhSachNhanVien;
	}
	public void thongkedulieu() {
	    
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		updateTableWithStatistics(tableModel);

	}

	public void updateTableWithStatistics(DefaultTableModel tableModel) {
	    // Xóa toàn bộ dữ liệu trong bảng
	    tableModel.setRowCount(0);

	    // Thực hiện tính toán và thống kê
	    danhSachNhanVien = this.model.getInstance().getDSNhanVien();

	    // Khởi tạo các biến thống kê
	    phongBanCounts = new HashMap<>();
	    trinhDoCounts = new HashMap<>();
	    int soLuongNam = 0;
	    int soLuongNu = 0;
	    double tongLuong = 0;
	    luongCaoNhat = Double.MIN_VALUE;
	    luongThapNhat = Double.MAX_VALUE;
	    int duoi500 = 0;
	    int tu500Den1000 = 0;
	    int tren1000 = 0;

	    // Thực hiện tính toán
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
	            luongCaoNhat = nv.getLuong();
	        }
	        if (nv.getLuong() < luongThapNhat) {
	            luongThapNhat = nv.getLuong();
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

	    // Tính các chỉ số thống kê cuối cùng
	    double luongTrungBinh = tongLuong / danhSachNhanVien.size();
	    double tyLeNam = (soLuongNam / (double) danhSachNhanVien.size()) * 100;
	    double tyLeNu = (soLuongNu / (double) danhSachNhanVien.size()) * 100;

	    // Thêm dữ liệu thống kê mới vào bảng
	    tableModel.addRow(new Object[] { "Số lượng nhân viên", danhSachNhanVien.size() });
	    tableModel.addRow(new Object[] { "Số lượng nhân viên nam", String.valueOf(soLuongNam) + " (" + String.format("%.2f", tyLeNam) + "%)" });
	    tableModel.addRow(new Object[] { "Số lượng nhân viên nữ", String.valueOf(soLuongNu) + " (" + String.format("%.2f", tyLeNu) + "%)" });
	    tableModel.addRow(new Object[] { "Lương trung bình", String.format("%.2f", luongTrungBinh) });
	    tableModel.addRow(new Object[] { "Lương cao nhất", String.valueOf(luongCaoNhat) });
	    tableModel.addRow(new Object[] { "Lương thấp nhất", String.valueOf(luongThapNhat) });
	    tableModel.addRow(new Object[] { "Số lượng nhân viên có lương dưới 500", String.valueOf(duoi500) });
	    tableModel.addRow(new Object[] { "Số lượng nhân viên có lương từ 500 đến 1000", String.valueOf(tu500Den1000) });
	    tableModel.addRow(new Object[] { "Số lượng nhân viên có lương trên 1000", String.valueOf(tren1000) });

	    // Thêm thông tin thống kê theo phòng ban
	    for (Map.Entry<String, Integer> entry : phongBanCounts.entrySet()) {
	        tableModel.addRow(new Object[] { "Phòng ban " + entry.getKey(), String.valueOf(entry.getValue()) });
	    }

	    // Thêm thông tin thống kê theo trình độ học vấn
	    for (Map.Entry<String, Integer> entry : trinhDoCounts.entrySet()) {
	        double tyLePhanTram = (entry.getValue() / (double) danhSachNhanVien.size()) * 100;
	        tableModel.addRow(new Object[] { "Trình độ " + entry.getKey(), String.valueOf(entry.getValue()) + " (" + String.format("%.2f", tyLePhanTram) + "%)" });
	    }
	}




	
	public void thongkechitiet() {
	   
	    ThongKeChiTiet tk = tkchitiet.getInstance();
	    boolean selected = tk.luachonThongKe(); // Hiển thị cửa sổ chọn thống kê và điền dữ liệu
	    if (selected) {
	    	this.dispose();
	 	    this.setVisible(false);
	        tk.setVisible(true); // Hiển thị cửa sổ ThongKeChiTiet nếu đã chọn loại thống kê
	    } else {
	        System.out.println("Thống kê chi tiết bị hủy.");
	        this.dispose();
	 	    this.setVisible(false);
	        this.view.getInstanceView().setVisible(true);
	        this.view.getInstanceView().thucHienTaiLaiDuLieu();
	    }
	}

	
	public void thoat() {
		this.dispose();
		this.setVisible(false);
        this.view.getInstanceView().setVisible(true);
	}



}
