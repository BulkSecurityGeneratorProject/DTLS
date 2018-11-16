package vn.homtech.dtls.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A NghiaTrang.
 */
@Entity
@Table(name = "nghia_trang")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "nghiatrang")
public class NghiaTrang implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_nghia_trang")
    private String maNghiaTrang;

    @Column(name = "ten_nghia_trang")
    private String tenNghiaTrang;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "nguoi_lien_he")
    private String nguoiLienHe;

    @Column(name = "dien_thoai")
    private String dienThoai;

    @Column(name = "email")
    private String email;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne
    @JsonIgnoreProperties("phuongXaNghiaTrangs")
    private PhuongXa phuongXa;

    @OneToMany(mappedBy = "nghiaTrang")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ThongTinMo> nghiaTrangMos = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaNghiaTrang() {
        return maNghiaTrang;
    }

    public NghiaTrang maNghiaTrang(String maNghiaTrang) {
        this.maNghiaTrang = maNghiaTrang;
        return this;
    }

    public void setMaNghiaTrang(String maNghiaTrang) {
        this.maNghiaTrang = maNghiaTrang;
    }

    public String getTenNghiaTrang() {
        return tenNghiaTrang;
    }

    public NghiaTrang tenNghiaTrang(String tenNghiaTrang) {
        this.tenNghiaTrang = tenNghiaTrang;
        return this;
    }

    public void setTenNghiaTrang(String tenNghiaTrang) {
        this.tenNghiaTrang = tenNghiaTrang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public NghiaTrang diaChi(String diaChi) {
        this.diaChi = diaChi;
        return this;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNguoiLienHe() {
        return nguoiLienHe;
    }

    public NghiaTrang nguoiLienHe(String nguoiLienHe) {
        this.nguoiLienHe = nguoiLienHe;
        return this;
    }

    public void setNguoiLienHe(String nguoiLienHe) {
        this.nguoiLienHe = nguoiLienHe;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public NghiaTrang dienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
        return this;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public NghiaTrang email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMoTa() {
        return moTa;
    }

    public NghiaTrang moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public NghiaTrang isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public PhuongXa getPhuongXa() {
        return phuongXa;
    }

    public NghiaTrang phuongXa(PhuongXa phuongXa) {
        this.phuongXa = phuongXa;
        return this;
    }

    public void setPhuongXa(PhuongXa phuongXa) {
        this.phuongXa = phuongXa;
    }

    public Set<ThongTinMo> getNghiaTrangMos() {
        return nghiaTrangMos;
    }

    public NghiaTrang nghiaTrangMos(Set<ThongTinMo> thongTinMos) {
        this.nghiaTrangMos = thongTinMos;
        return this;
    }

    public NghiaTrang addNghiaTrangMo(ThongTinMo thongTinMo) {
        this.nghiaTrangMos.add(thongTinMo);
        thongTinMo.setNghiaTrang(this);
        return this;
    }

    public NghiaTrang removeNghiaTrangMo(ThongTinMo thongTinMo) {
        this.nghiaTrangMos.remove(thongTinMo);
        thongTinMo.setNghiaTrang(null);
        return this;
    }

    public void setNghiaTrangMos(Set<ThongTinMo> thongTinMos) {
        this.nghiaTrangMos = thongTinMos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NghiaTrang nghiaTrang = (NghiaTrang) o;
        if (nghiaTrang.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nghiaTrang.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NghiaTrang{" +
            "id=" + getId() +
            ", maNghiaTrang='" + getMaNghiaTrang() + "'" +
            ", tenNghiaTrang='" + getTenNghiaTrang() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", nguoiLienHe='" + getNguoiLienHe() + "'" +
            ", dienThoai='" + getDienThoai() + "'" +
            ", email='" + getEmail() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
