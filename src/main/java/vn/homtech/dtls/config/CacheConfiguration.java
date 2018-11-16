package vn.homtech.dtls.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(vn.homtech.dtls.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.TinhThanh.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.TinhThanh.class.getName() + ".tinhThanhQuanHuyens", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.QuanHuyen.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.QuanHuyen.class.getName() + ".quanHuyenPhuongXas", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.PhuongXa.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.PhuongXa.class.getName() + ".phuongXaNghiaTrangs", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.PhuongXa.class.getName() + ".phuongXaLietSis", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.PhuongXa.class.getName() + ".phuongXaThanNhans", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.CapBac.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.CapBac.class.getName() + ".capBacLietSis", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.ChucVu.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.ChucVu.class.getName() + ".chucVuLietSis", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.HoSoLietSi.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.HoSoLietSi.class.getName() + ".lietSis", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.HoSoLietSi.class.getName() + ".lietSiMos", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.HoSoLietSi.class.getName() + ".lietSiNhanDangs", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.NhanDang.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.NhanDang.class.getName() + ".nhanDangLietSis", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.NhanDangLietSi.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.DonVi.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.DonVi.class.getName() + ".donViHySinhs", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.DonVi.class.getName() + ".donViHuanLuyens", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.DonVi.class.getName() + ".donViQuanLies", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.DonVi.class.getName() + ".donViThoiKies", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.DonViThoiKy.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.QuanHeThanNhan.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.QuanHeThanNhan.class.getName() + ".quanHeThanNhans", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.HoSoThanNhan.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.HoSoThanNhan.class.getName() + ".thanNhans", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.HoSoThanNhan.class.getName() + ".thanNhanMaus", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.ThanNhanLietSi.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.ThongTinMo.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.ThongTinMo.class.getName() + ".moLietSis", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.NghiaTrang.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.NghiaTrang.class.getName() + ".nghiaTrangMos", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.ThongTinKhaiQuat.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.ThongTinKhaiQuat.class.getName() + ".khaiQuatHaiCots", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.ThongTinKhaiQuat.class.getName() + ".khaiQuatDiVats", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.LoaiDiVat.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.LoaiDiVat.class.getName() + ".loaiDiVats", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.DiVat.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.LoaiHinhThaiHaiCot.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.LoaiHinhThaiHaiCot.class.getName() + ".loaiHinhThaiHaiCots", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.HaiCotLietSi.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.HaiCotLietSi.class.getName() + ".thongTinHinhThaiHocs", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.HaiCotLietSi.class.getName() + ".haiCotMaus", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.HinhThaiHaiCot.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.LoaiMauXetNghiem.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.LoaiMauXetNghiem.class.getName() + ".loaiMauXetNghiems", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.MauXetNghiem.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.MauXetNghiem.class.getName() + ".mauPhanTiches", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.VungADN.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.VungADN.class.getName() + ".vungADNS", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.ThongTinADN.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.ThongTinADN.class.getName() + ".thongTinADNDotBiens", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.DiemDotBien.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.LoaiThaoTac.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.LoaiThaoTac.class.getName() + ".thaoTacHienTais", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.LoaiThaoTac.class.getName() + ".loaiThaoTacs", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.LoaiThaoTac.class.getName() + ".thaoTacTiepTheos", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.ThaoTac.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.PhongBan.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.PhongBan.class.getName() + ".phongLabPhanTiches", jcacheConfiguration);
            cm.createCache(vn.homtech.dtls.domain.PhongBan.class.getName() + ".phongBanThaoTacs", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
