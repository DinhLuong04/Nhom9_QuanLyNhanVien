package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class TaiKhoan implements Serializable {
private String TaiKhoan;
private String MatKhau;


public TaiKhoan() {
	
}


public TaiKhoan(String taiKhoan, String matKhau) {
	TaiKhoan = taiKhoan;
	MatKhau = matKhau;
	
}


public String getTaiKhoan() {
	return TaiKhoan;
}


public void setTaiKhoan(String taiKhoan) {
	TaiKhoan = taiKhoan;
}


public String getMatKhau() {
	return MatKhau;
}


public void setMatKhau(String matKhau) {
	MatKhau = matKhau;
}


@Override
public int hashCode() {
	return Objects.hash(MatKhau, TaiKhoan);
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	TaiKhoan other = (TaiKhoan) obj;
	return Objects.equals(MatKhau, other.MatKhau) && Objects.equals(TaiKhoan, other.TaiKhoan);
}


@Override
public String toString() {
	return "TaiKhoan [TaiKhoan=" + TaiKhoan + ", MatKhau=" + MatKhau + "]";
}





}
