package qlsp.service;

import java.util.ArrayList;

import qlsp.model.ICoThue;
import qlsp.model.SanPham;

public class GioHang {
	private ArrayList<SanPham> ds = new ArrayList<>();

		// Thêm sản phẩm
    public void themSanPham(SanPham sp) {
        ds.add(sp);
    }

    // Hiển thị
    public void hienThi() {
    	System.out.println("===== Danh sách sản phẩm trong giỏ hàng =====");
        for (SanPham sp : ds) {
            System.out.println(sp);
        }
    }

    // Tính tổng tiền
    public double tinhTongTien() {
        double tong = 0;

        for (SanPham sp : ds) {
            tong += sp.getGiaBan();

            // nếu có thuế
            if (sp instanceof ICoThue) {
                ICoThue t = (ICoThue) sp;
                tong += t.tinhThue();
            }
        }

        return tong;
    }
}
