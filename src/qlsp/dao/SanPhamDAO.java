package qlsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import qlsp.model.*;
import qlsp.service.GioHang;
import qlsp.util.*;

public class SanPhamDAO {
	GioHang gh = new GioHang();
    // =========================
    // INSERT SÁCH
    // =========================
	
	public void deleteGioHang() {
		try {
			Connection conn = DBConnection.getConnection();
			String truncate = "Truncate table GioHang" ;
		
			Statement st = conn.createStatement();
			st.executeUpdate(truncate);
			conn.close();
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void insertGioHang(String ma_sp, String ten_sp, double gia_ban, String a, String b) {
		try {
		
			Connection conn = DBConnection.getConnection();
			String sql = "Insert into GioHang Values (?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ma_sp);
			ps.setString(2, ten_sp);
			ps.setDouble(3, gia_ban);
			ps.setString(4, a);
			ps.setString(5, b);
			ps.executeUpdate();
			conn.close();
			System.out.println("Sản phẩm đã được ghi vào dữ liệu giỏ hàng!");
		
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	
    public void insertSach(Sach s) {
        try {
            Connection conn = DBConnection.getConnection();

            // 1. Insert vào SanPham
            String insertS = "INSERT INTO SanPham VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(insertS);
            ps.setString(1, s.getMaSP());
            ps.setString(2, s.getTenSP());
            ps.setDouble(3, s.getGiaBan());
            ps.executeUpdate();

            // 2. Insert vào Sach
            insertS = "INSERT INTO Sach VALUES (?, ?, ?)";
            ps = conn.prepareStatement(insertS);
            ps.setString(1, s.getMaSP());
            ps.setString(2, s.getTacGia());
            ps.setString(3, s.getNhaXuatBan());
            ps.executeUpdate();
            
            conn.close();
            System.out.println("Thêm Sách thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // INSERT ĐIỆN THOẠI
    // =========================
    public void insertDienThoai(DienThoai dt) {
        try {
            Connection conn = DBConnection.getConnection();

            // 1. SanPham
            String insertDT = "INSERT INTO SanPham VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(insertDT);
            ps.setString(1, dt.getMaSP());
            ps.setString(2, dt.getTenSP());
            ps.setDouble(3, dt.getGiaBan());
            ps.executeUpdate();

            // 2. DienThoai
            insertDT = "INSERT INTO DienThoai VALUES (?, ?, ?)";
            ps = conn.prepareStatement(insertDT);
            ps.setString(1, dt.getMaSP());
            ps.setString(2, dt.getHang());
            ps.setInt(3, dt.getRam());
            ps.executeUpdate();

            conn.close();
            System.out.println("Thêm Điện thoại thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // GET ALL (ĐA HÌNH)
    // =========================
    public ArrayList<SanPham> getAll() {
        ArrayList<SanPham> list = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            // 1. Lấy SÁCH (JOIN)
            String sqlSach = "SELECT * FROM SanPham sp JOIN Sach s ON sp.ma_sp = s.ma_sp";
            Statement st1 = conn.createStatement();
            ResultSet rs1 = st1.executeQuery(sqlSach);

            while (rs1.next()) {
                Sach s = new Sach(
                        rs1.getString("ma_sp"),
                        rs1.getString("ten_sp"),
                        rs1.getDouble("gia_ban"),
                        rs1.getString("tac_gia"),
                        rs1.getString("nha_xuat_ban")
                );
                list.add(s);
            }

            // 2. Lấy ĐIỆN THOẠI (JOIN)
            String sqlDT = "SELECT * FROM SanPham sp JOIN DienThoai dt ON sp.ma_sp = dt.ma_sp";
            Statement st2 = conn.createStatement();
            ResultSet rs2 = st2.executeQuery(sqlDT);

            while (rs2.next()) {
                DienThoai dt = new DienThoai(
                        rs2.getString("ma_sp"),
                        rs2.getString("ten_sp"),
                        rs2.getDouble("gia_ban"),
                        rs2.getString("hang_san_xuat"),
                        rs2.getInt("ram_gb")
                );
                list.add(dt);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public SanPham searchById(String sp_Id) {
    	
    	try {
    		
    		Connection conn = DBConnection.getConnection();
    		String search =""; 
    		PreparedStatement ps = conn.prepareStatement(search);
    		ResultSet rs;
    		
        	if (sp_Id.charAt(0) == 'S') {
        		
        		search = "Select * from SanPham sp "
        				+ "join Sach s on sp.ma_sp = s.ma_sp"
        				+ " where sp.ma_sp = ?";
        		ps = conn.prepareStatement(search);
        		ps.setString(1, sp_Id);
        		rs = ps.executeQuery();
        		if (rs.next()) {
        			
        			String ten_sp = rs.getString("ten_sp");
        			double gia_ban = rs.getDouble("gia_ban");
        			String tac_gia = rs.getString("tac_gia");
        			String nha_xuat_ban = rs.getString("nha_xuat_ban");
        			
        			Sach s = new Sach(sp_Id,ten_sp,gia_ban,tac_gia,nha_xuat_ban);

        			insertGioHang(sp_Id,ten_sp,gia_ban,tac_gia,nha_xuat_ban);
        			System.out.println("Thêm sản phẩm thành công!");
        			return s;
        		}
        		
        	}else {
        		
        		search = "Select * from SanPham sp "
        				+ "join DienThoai dt on sp.ma_sp = dt.ma_sp "
        				+ " where sp.ma_sp = ?";
        		ps = conn.prepareStatement(search);
        		ps.setString(1,sp_Id);
        		rs = ps.executeQuery();
        		
        		if (rs.next()) {
        			String ten_sp = rs.getString("ten_sp");
        			double gia_ban = rs.getDouble("gia_ban");
        			String hang_sx = rs.getString("hang_san_xuat");
        			int ram = rs.getInt("ram_gb");
        			
        			DienThoai dt = new DienThoai(sp_Id,ten_sp,gia_ban,hang_sx,ram);
        			insertGioHang(sp_Id,ten_sp,gia_ban,hang_sx,String.valueOf(ram));
        			System.out.println("Thêm sản phẩm thành công!");
            		return dt;
        		}
        		
        	}
			System.out.println("Sản phẩm không tồn tại trong kho!");
			return null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    	SanPham sp = new SanPham();
    	return sp;
    }
}
