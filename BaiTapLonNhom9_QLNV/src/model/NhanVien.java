package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

public class NhanVien implements Serializable {
	private int MaNV;
	private String HoTen;
	private boolean GioiTinh;
	private String NgaySinh;
	private String DanToc;
	private QueQuan Tinh;
	private String SoDT;
	private PhongBan phongban;
	private String chucvu;
	private String TDHV;
	private double luong;
	private String taiKhoan;
	private static QLNVmodel model;
	public NhanVien() {

	}

	public NhanVien(int maNV, String hoTen, boolean gioiTinh, String ngaySinh, String danToc, QueQuan tinh, String soDT,
			PhongBan phongban, String chucvu, String tDHV, double luong,String taikhoan) {

		MaNV = maNV;
		HoTen = hoTen;
		GioiTinh = gioiTinh;
		NgaySinh = ngaySinh;
		DanToc = danToc;
		Tinh = tinh;
		SoDT = soDT;
		this.phongban = phongban;
		this.chucvu = chucvu;
		TDHV = tDHV;
		this.luong = luong;
		this.taiKhoan=taikhoan;
		
	}

	
	public int getMaNV() {
		return MaNV;
	}

	public void setMaNV(int maNV) {
		MaNV = maNV;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	public boolean isGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public String getDanToc() {
		return DanToc;
	}

	public void setDanToc(String danToc) {
		DanToc = danToc;
	}

	public QueQuan getTinh() {
		return Tinh;
	}

	public void setTinh(QueQuan tinh) {
		Tinh = tinh;
	}

	public String getSoDT() {
		return SoDT;
	}

	public void setSoDT(String soDT) {
		SoDT = soDT;
	}

	public PhongBan getPhongban() {
		return phongban;
	}

	public void setPhongban(PhongBan phongban) {
		this.phongban = phongban;
	}

	public String getChucvu() {
		return chucvu;
	}

	public void setChucvu(String chucvu) {
		this.chucvu = chucvu;
	}

	public String getTDHV() {
		return TDHV;
	}

	public void setTDHV(String tDHV) {
		TDHV = tDHV;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}
	
	
	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	

	@Override
	public String toString() {
		return "NhanVien [MaNV=" + MaNV + ", HoTen=" + HoTen + ", GioiTinh=" + GioiTinh + ", NgaySinh=" + NgaySinh
				+ ", DanToc=" + DanToc + ", Tinh=" + Tinh + ", SoDT=" + SoDT + ", phongban=" + phongban + ", chucvu="
				+ chucvu + ", TDHV=" + TDHV + ", luong=" + luong + ", taiKhoan=" + taiKhoan + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(DanToc, GioiTinh, HoTen, MaNV, NgaySinh, SoDT, TDHV, Tinh, chucvu, luong, phongban);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(DanToc, other.DanToc) && GioiTinh == other.GioiTinh && Objects.equals(HoTen, other.HoTen)
				&& MaNV == other.MaNV && Objects.equals(NgaySinh, other.NgaySinh) && SoDT == other.SoDT
				&& Objects.equals(TDHV, other.TDHV) && Objects.equals(Tinh, other.Tinh)
				&& Objects.equals(chucvu, other.chucvu) && Objects.equals(luong, other.luong)
				&& Objects.equals(phongban, other.phongban);
	}

	public static ArrayList<String> DS_Dantoc() {
		String[] listDT = { "Kinh", "Tày", "Thái", "Hoa", "Khơ-me", "Mường", "Nùng", "HMông", "Dao", "Gia-rai", "Ngái",
				"Ê-đê", "Ba na", "Xơ-Đăng", "Sán Chay", "Cơ-ho", "Chăm", "Sán Dìu", "Hrê", "Mnông", "Ra-glai", "Xtiêng",
				"Bru-Vân Kiều", "Thổ", "Giáy", "Cơ-tu", "Gié Triêng", "Mạ", "Khơ-mú", "Co", "Tà-ôi", "Chơ-ro", "Kháng",
				"Xinh-mun", "Hà Nhì", "Chu ru", "Lào", "La Chí", "La Ha", "Phù Lá", "La Hủ", "Lự", "Lô Lô", "Chứt",
				"Mảng", "Pà Thẻn", "Co Lao", "Cống", "Bố Y", "Si La", "Pu Péo", "Brâu", "Ơ Đu", "Rơ măm", };
		ArrayList<String> list_DT=new ArrayList<String>();
		for(String tenDT : listDT)
		{
			list_DT.add(tenDT);
		}
		return list_DT;

	}
	
	public static ArrayList<String> list_tk()
	{
		return model.getInstance().layDSTaiKhoan();
	}
	
	
	

}
