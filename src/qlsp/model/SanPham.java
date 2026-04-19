package qlsp.model;

public class SanPham {
	private String maSP;
    private String tenSP;
    private double giaBan;

    public SanPham() {}

    public SanPham(String maSP, String tenSP, double giaBan) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaBan = giaBan;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    @Override
	public String toString() {
		return String.format("Mã SP: %-5s | Tên: %-15s | Giá: %,.0f | ", maSP, tenSP, giaBan);
	}

}

