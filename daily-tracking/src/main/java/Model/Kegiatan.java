package Model;

import java.time.LocalDate;

public class Kegiatan {
    private String namaKegiatan;
    private LocalDate tanggal;
    private boolean selesai;

    public Kegiatan(String namaKegiatan, LocalDate tanggal, boolean selesai) {
        this.namaKegiatan = namaKegiatan;
        this.tanggal = tanggal;
        this.selesai = selesai;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public boolean isSelesai() {
        return selesai;
    }

    public void setSelesai(boolean selesai) {
        this.selesai = selesai;
    }
}
