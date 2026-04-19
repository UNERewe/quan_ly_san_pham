package qlsp.main;

import qlsp.service.GioHang;

import java.util.ArrayList;
import java.util.Scanner;

import qlsp.dao.SanPhamDAO;
import qlsp.model.*;

public class Main {

	public static void main(String[] args) {
		
		try (Scanner sc = new Scanner(System.in)) {
			SanPhamDAO dao = new SanPhamDAO();
			GioHang gh = new GioHang();
			while (true) {
				System.out.println("Lựa chọn: ");
				System.out.println("1. Tạo một giỏ hàng mới");
				System.out.println("2. Sử dụng tiếp giỏ hàng đã tồn tại");
				int choice = sc.nextInt();
				if (choice == 1) {
					dao.deleteGioHang();
				}else if (choice != 2) {
					System.out.println("Lựa chọn không phù hợp!");
					continue;
				}
				while (true) {
					System.out.println("=====Menu=====");
					System.out.println("1. Thêm sản phẩm vào giỏ");
					System.out.println("2. Hiển thị các sản phẩm trong giỏ");
					System.out.println("3. Hiển thị tổng tiền giỏ hàng");
					System.out.println();
					choice = sc.nextInt();
					switch (choice) {
						case 1:
						
							System.out.println("===== Danh sách sản phẩm =====");
							ArrayList<SanPham> list = dao.getAll();
						
							for (SanPham sp : list) System.out.println(sp);	
						
							System.out.print("Chọn mã sản phẩm muốn thêm vào giỏ hàng: ") ;
							System.out.println();
						
							String sp_Id = sc.next();
							gh.themSanPham(dao.searchById(sp_Id));
						
						
							break;
						case 2:
							gh.hienThi();
							break;
						case 3:
							System.out.printf("Tổng tiền: %,.0f\n", gh.tinhTongTien());
							break;
						default:
						
							System.out.println("Lựa chọn không phù hợp");
							break;
					}
				}
			}
		}
		
		// Tạo giỏ hàng
//	    GioHang gh = new GioHang();
//
//	    // Thêm sản phẩm
//	    Sach s = new Sach("S01", "Lap trinh Java", 100000, "Nguyen Van A", "NXB Tre");
//	    DienThoai dt = new DienThoai("DT01", "iPhone 15", 20000000, "Apple", 8);
//
//	    gh.themSanPham(s);
//	    gh.themSanPham(dt);
//
//	    // Hiển thị giỏ hàng
//	    System.out.println("===== GIO HANG =====");
//	    gh.hienThi();
//
//	    // Tổng tiền
//	    System.out.printf("Tong tien: %,.0f\n", gh.tinhTongTien());
	}
}
