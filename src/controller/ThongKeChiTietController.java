package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.NhanVien;
import view.QuanLyNhanVienView;
import view.ThongKeChiTiet;

public class ThongKeChiTietController implements ActionListener {
    private ThongKeChiTiet view;
    private QuanLyNhanVienView qlnvView;
    private String loaiThongKe;
    private static ThongKeChiTietController instance;

    public static ThongKeChiTietController getInstance() {
        if (instance == null) {
            instance = new ThongKeChiTietController();
        }
        return instance;
    }
    public ThongKeChiTietController() {
    	
    }

    public ThongKeChiTietController(ThongKeChiTiet view) {
        this.view = view;
        
    }

    public String getLoaiThongKe() {
        return loaiThongKe;
    }

    public void setLoaiThongKe(String loaiThongKe) {
        this.loaiThongKe = loaiThongKe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cm = e.getActionCommand();
        try {
            switch (cm) {
                case "Sắp Xếp": {
                    // Gọi phương thức để xử lý sắp xếp
                    xuLySapXep();
                    break;
                }
                case "Liệt kê Lương": {
                    // Gọi phương thức để xử lý liệt kê lương
                    xuLyLietKeLuong();
                    break;
                }
                case "Thoát":{
                	this.view.thoat();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + e2.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void xuLySapXep() {
        // Lấy bảng hiện tại từ ThongKeChiTiet
        JTable table = view.getTable();

        // Hiển thị dialog cho phép chọn tiêu chí sắp xếp
        String[] sapXepOptions = {"Mã NV", "Tên", "Lương"};
        String selectedOption = (String) JOptionPane.showInputDialog(null, "Chọn tiêu chí sắp xếp:",
                "Sắp xếp bảng", JOptionPane.PLAIN_MESSAGE, null, sapXepOptions, sapXepOptions[0]);

        if (selectedOption != null) {
            // Hiển thị dialog cho phép chọn hướng sắp xếp
            String[] sapXepDirection = {"Tăng dần", "Giảm dần"};
            String selectedDirection = (String) JOptionPane.showInputDialog(null, "Chọn hướng sắp xếp:",
                    "Hướng sắp xếp", JOptionPane.PLAIN_MESSAGE, null, sapXepDirection, sapXepDirection[0]);

            if (selectedDirection != null) {
                // Sắp xếp lại danh sách chi tiết theo tiêu chí selectedOption và hướng selectedDirection
                int sortOrder = selectedDirection.equals("Tăng dần") ? 1 : -1;
                ArrayList<NhanVien> danhSachNhanVien = view.getDanhNhanVienTrongBangHienTai();

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
                DefaultTableModel chiTietTableModel = (DefaultTableModel) table.getModel();
                chiTietTableModel.setRowCount(0);

                // Thêm lại dữ liệu đã sắp xếp vào chiTietTableModel theo loại thống kê
                for (NhanVien nv : danhSachNhanVien) {
                    chiTietTableModel.addRow(new Object[]{
                            nv.getMaNV(), nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(),
                            nv.getTDHV(), nv.getPhongban().getTenPB(), nv.getTaiKhoan()
                    });
                }
            }
        }
    }


    private void xuLyLietKeLuong() {
        double luongMax = Double.MIN_VALUE;
        double luongMin = Double.MAX_VALUE;
        JTable table = view.getTable();

        // Retrieve the current list of employees
        ArrayList<NhanVien> danhSachNhanVien = view.getDanhNhanVienTrongBangHienTai();

        // Find the maximum and minimum salaries
        for (NhanVien nv : danhSachNhanVien) {
            if (nv.getLuong() > luongMax) {
                luongMax = nv.getLuong();
            }
            if (nv.getLuong() < luongMin) {
                luongMin = nv.getLuong();
            }
        }

        // Display dialog to choose between highest or lowest salary
        String[] options = {"Lương cao nhất", "Lương thấp nhất"};
        String selectedOption = (String) JOptionPane.showInputDialog(null, "Chọn loại lương để hiển thị:",
                "Chọn lương", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (selectedOption != null) {
            // Clear current table data
            DefaultTableModel chiTietTableModel = (DefaultTableModel) table.getModel();
            chiTietTableModel.setRowCount(0);

            // Display employees based on selected option
            if (selectedOption.equals("Lương cao nhất")) {
                for (NhanVien nv : danhSachNhanVien) {
                    if (nv.getLuong() == luongMax) {
                        chiTietTableModel.addRow(new Object[]{
                                nv.getMaNV(), nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(),
                                nv.getTDHV(), nv.getPhongban().getTenPB(), nv.getTaiKhoan()
                        });
                    }
                }
            } else if (selectedOption.equals("Lương thấp nhất")) {
                for (NhanVien nv : danhSachNhanVien) {
                    if (nv.getLuong() == luongMin) {
                        chiTietTableModel.addRow(new Object[]{
                                nv.getMaNV(), nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(),
                                nv.getTDHV(), nv.getPhongban().getTenPB(), nv.getTaiKhoan()
                        });
                    }
                }
            }
        }
    }

}
