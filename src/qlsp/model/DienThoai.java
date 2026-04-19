package qlsp.model;

public class DienThoai extends SanPham implements ICoThue {
	private String hang;
    private int ram;

    public DienThoai() {}

    public DienThoai(String maSP, String tenSP, double giaBan,
                     String hang, int ram) {
        super(maSP, tenSP, giaBan);
        this.hang = hang;
        this.ram = ram;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    @Override
    public double tinhThue() {
        return getGiaBan() * 0.1; // 10%
    }	
    
    
    @Override
	public String toString() {
		return super.toString() + String.format("Hãng: %s | Ram: %d | Thuế: %,.0f", hang,ram,tinhThue());
	}

	
}

