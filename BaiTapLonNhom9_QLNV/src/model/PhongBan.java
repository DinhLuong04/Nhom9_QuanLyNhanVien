package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class PhongBan implements Serializable {
    private int MaPB;
    private String TenPB;
    private String SoDT;

    public PhongBan(int maPB, String tenPB, String soDT) {
        MaPB = maPB;
        TenPB = tenPB;
        SoDT = soDT;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        PhongBan other = (PhongBan) obj;
        return MaPB == other.MaPB && Objects.equals(SoDT, other.SoDT) && Objects.equals(TenPB, other.TenPB);
    }

    public int getMaPB() {
		return MaPB;
	}

	public void setMaPB(int maPB) {
		MaPB = maPB;
	}

	public String getTenPB() {
		return TenPB;
	}

	public void setTenPB(String tenPB) {
		TenPB = tenPB;
	}

	public String getSoDT() {
		return SoDT;
	}

	public void setSoDT(String soDT) {
		SoDT = soDT;
	}

	@Override
    public int hashCode() {
        return Objects.hash(MaPB, TenPB, SoDT);
    }

    public static ArrayList<PhongBan> getDSPB() {
        String[] arr_PhongBan = {
            "Ban giám đốc", "Ban quản lý", "Ban sản xuất", "Phòng nhân sự",
            "Phòng tài chính–kế toán", "Phòng Marketing", "Phòng công nghệ thông tin",
            "Phòng kinh doanh", "Phòng chăm sóc khách hàng"
        };
        String[] arr_sdt_PB = {
            "0917957668", "01675786999", "0833966669", "0874657833",
            "0553567989", "0977555888", "01573899555", "0876599774", "0917567773"
        };
        ArrayList<PhongBan> DS_PB = new ArrayList<>();
        for (int i = 0; i < arr_PhongBan.length; i++) {
            PhongBan pb = new PhongBan(i, arr_PhongBan[i], arr_sdt_PB[i]);
            DS_PB.add(pb);
        }
        return DS_PB;
    }

    public static PhongBan getphongbanById(int pb) {
		return PhongBan.getDSPB().get(pb);
	}

    public static PhongBan getPhongBanbyTen(String str) {
       
        for (PhongBan pb : getDSPB()) {
           
            if (pb.getTenPB().equals(str)) {
                return pb;
            }
        }
        return null;
    }
}
