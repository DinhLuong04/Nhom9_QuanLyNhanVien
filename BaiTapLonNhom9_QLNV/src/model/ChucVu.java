package model;

import java.io.Serializable;
import java.util.Objects;

public class ChucVu implements Serializable {
	private int MaCV;
	private String TenCV;

	public ChucVu() {

	}

	public ChucVu(int maCV, String tenCV) {

		MaCV = maCV;
		TenCV = tenCV;
	}

	public int getMaCV() {
		return MaCV;
	}

	public void setMaCV(int maCV) {
		MaCV = maCV;
	}

	public String getTenCV() {
		return TenCV;
	}

	public void setTenCV(String tenCV) {
		TenCV = tenCV;
	}

	@Override
	public String toString() {
		return "ChucVu [MaCV=" + MaCV + ", TenCV=" + TenCV + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(MaCV, TenCV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChucVu other = (ChucVu) obj;
		return MaCV == other.MaCV && Objects.equals(TenCV, other.TenCV);
	}

	

}
