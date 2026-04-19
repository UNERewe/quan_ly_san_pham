package qlsp.model;

public class Sach extends SanPham {
	private String tacGia;
    private String nhaXuatBan;

    public Sach() {}

    public Sach(String maSP, String tenSP, double giaBan,
                String tacGia, String nhaXuatBan) {
        super(maSP, tenSP, giaBan);
        this.tacGia = tacGia;
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    @Override
	public String toString() {
		return super.toString() + String.format("Tác giả: %s | Nhà xuất bản: %s", tacGia,nhaXuatBan);
	}

}
