package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class QueQuan implements Serializable {
    private int MaTinh;
    private String TenTinh;

    public QueQuan(int maTinh, String tenTinh) {
        MaTinh = maTinh;
        TenTinh = tenTinh;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        QueQuan other = (QueQuan) obj;
        return MaTinh == other.MaTinh && Objects.equals(TenTinh, other.TenTinh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaTinh, TenTinh);
    }
    
    public int getMaTinh() {
		return MaTinh;
	}

	public void setMaTinh(int maTinh) {
		MaTinh = maTinh;
	}

	public String getTenTinh() {
		return TenTinh;
	}

	public void setTenTinh(String tenTinh) {
		TenTinh = tenTinh;
	}

	

	public static QueQuan getTinhById(int queQuan) {
		return QueQuan.getDSTinh().get(queQuan);
	}

    
    public static ArrayList<QueQuan> getDSTinh() {
    	 String[] arr_tinh = {
    	            "An Giang", "Bà Rịa – Vũng Tàu", "Bạc Liêu", "Bắc Giang",
    	            "Bắc Kạn", "Bắc Ninh", "Bến Tre", "Bình Dương", "Bình Định",
    	            "Bình Phước", "Bình Thuận", "Cà Mau", "Cao Bằng", "Cần Thơ",
    	            "Đà Nẵng", "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai",
    	            "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam", "Hà Nội",
    	            "Hà Tĩnh", "Hải Dương", "Hải Phòng", "Hậu Giang", "Hòa Bình",
    	            "Thành phố Hồ Chí Minh", "Hưng Yên", "Khánh Hòa", "Kiên Giang",
    	            "Kon Tum", "Lai Châu", "Lạng Sơn", "Lào Cai", "Lâm Đồng",
    	            "Long An", "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận",
    	            "Phú Thọ", "Phú Yên", "Quảng Bình", "Quảng Nam", "Quảng Ngãi",
    	            "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh",
    	            "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên Huế",
    	            "Tiền Giang", "Trà Vinh", "Tuyên Quang", "Vĩnh Long",
    	            "Vĩnh Phúc", "Yên Bái"
    	        };
        ArrayList<QueQuan> DS_Tinh = new ArrayList<>();
        int maTinh = 1; // Bắt đầu từ 1 hoặc một giá trị duy nhất khác
        for (String tenTinh : arr_tinh) {
            QueQuan t = new QueQuan(maTinh, tenTinh);
            DS_Tinh.add(t);
        }
        return DS_Tinh;
    }

    public static QueQuan getTinhbyTen(String str) {
        str = str.toLowerCase(); // Chuyển đổi chuỗi thành chữ thường
        for (QueQuan tinh : getDSTinh()) {
            String tenTinh = tinh.getTenTinh().toLowerCase(); // Chuyển đổi tên tỉnh thành chữ thường
            if (tenTinh.equals(str)) {
                return tinh;
            }
        }
        // Xử lý trường hợp không tìm thấy tỉnh
        return null;
    }

}
