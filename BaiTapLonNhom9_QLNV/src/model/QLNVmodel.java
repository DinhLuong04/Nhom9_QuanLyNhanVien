package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;



public class QLNVmodel {
	private ArrayList<NhanVien> DSNhanVien;
	private ArrayList<TaiKhoan> DSTaiKhoan;
	private String luachon;
	private String tenFile;
	private static QLNVmodel instance;
	
	 public static QLNVmodel getInstance() {
	        if (instance == null) {
	            instance = new QLNVmodel();
	        }
	        return instance;
	    }
	public QLNVmodel() {
		this.DSNhanVien = new ArrayList<NhanVien>();
		this.DSTaiKhoan=new ArrayList<TaiKhoan>();
		this.luachon="";
		this.tenFile="";
	}
	
	public ArrayList<TaiKhoan> getDSTaiKhoan() {
		return this.DSTaiKhoan;
	}
	 public ArrayList<String> layDSTaiKhoan() {
	        ArrayList<String> dsTaiKhoan = new ArrayList<>();
	        for (TaiKhoan tk : DSTaiKhoan) {
	            dsTaiKhoan.add(tk.getTaiKhoan());
	        }
	        return dsTaiKhoan;
	    }
	public void setDSTaiKhoan(ArrayList<TaiKhoan> dSTaiKhoan) {
		this.DSTaiKhoan = dSTaiKhoan;
	}

	public ArrayList<NhanVien> getDSNhanVien() {
		return this.DSNhanVien;
	}

	public void setDSNhanVien(ArrayList<NhanVien> dSNhanVien) {
		this.DSNhanVien = dSNhanVien;
	}
	
	public void insert(NhanVien nv) {
	    if (!KiemTraTonTai(nv)) {
	        this.DSNhanVien.add(nv);
	    }
	}

	public void delete(NhanVien nv) {
		this.DSNhanVien.remove(nv);
	}
	public void update(NhanVien nv) {
	    for (int i = 0; i < this.DSNhanVien.size(); i++) {
	        if (this.DSNhanVien.get(i).getMaNV() == nv.getMaNV()) {
	            this.DSNhanVien.set(i, nv);
	            break;
	        }
	    }
	}


	public String getLuachon() {
		return luachon;
	}

	public void setLuachon(String luachon) {
		this.luachon = luachon;
	}

	public boolean KiemTraTonTai(NhanVien nv) {
	    for (NhanVien nvTrongDS : this.DSNhanVien) {
	        if (nvTrongDS.getMaNV() == nv.getMaNV()) {
	            return true;
	        }
	    }
	    return false;
	}


	
	public String getTenFile() {
		return tenFile;
	}

	public void setTenFile(String tenFile) {
		this.tenFile = tenFile;
	}

	
	public boolean KiemTraTaiKhoanNhanVienTonTai(String taiKhoan) {
	    for (NhanVien nv : this.DSNhanVien) {
	        if (nv.getTaiKhoan().equals(taiKhoan)) {
	            return true;
	        }
	    }
	    return false;
	}


	public boolean KiemTraTonTaiTaiKhoan(TaiKhoan tk) {
	    System.out.println("Checking existence for account: " + tk.getTaiKhoan());
	    int count = 0; // Biến đếm số lượng tài khoản đã duyệt qua
	    for (TaiKhoan taiKhoan : QLNVmodel.getInstance().getDSTaiKhoan()) {
	        System.out.println("Existing account: " + taiKhoan.getTaiKhoan());
	        if (taiKhoan.getTaiKhoan().equals(tk.getTaiKhoan())) {
	            return true;
	        }
	        count++; // Tăng biến đếm sau mỗi lần duyệt
	    }
	    System.out.println("Total accounts checked: " + count); // In ra tổng số tài khoản đã duyệt qua
	    return false;
	}


	public void insertTaiKhoan(TaiKhoan tk) {
	    System.out.println("Adding new account: " + tk.getTaiKhoan()); // Logging
	    DSTaiKhoan.add(tk);
	    System.out.println("Current accounts: " + DSTaiKhoan); // Logging
	}

	public void updateTaiKhoan(TaiKhoan tk) {
	    for (TaiKhoan taiKhoan : QLNVmodel.getInstance().getDSTaiKhoan()) {
	        if (taiKhoan.getTaiKhoan().equals(tk.getTaiKhoan())) {
	            taiKhoan.setMatKhau(tk.getMatKhau());
	            System.out.println("Updated account: " + tk.getTaiKhoan()); // Logging
	            break;
	        }
	    }
	}

	public void deleteTK(TaiKhoan tk) {
	    DSTaiKhoan.remove(tk);
	    System.out.println("Deleted account: " + tk.getTaiKhoan()); // Logging
	}
	
	
}
