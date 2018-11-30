package vn.homtech.dtls.web.rest;

import vn.homtech.dtls.DtlsApp;

import vn.homtech.dtls.domain.DonViThoiKy;
import vn.homtech.dtls.repository.DonViThoiKyRepository;
import vn.homtech.dtls.repository.search.DonViThoiKySearchRepository;
import vn.homtech.dtls.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;


import static vn.homtech.dtls.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DonViThoiKyResource REST controller.
 *
 * @see DonViThoiKyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtlsApp.class)
public class DonViThoiKyResourceIntTest {

    private static final Integer DEFAULT_TU_NAM = 1;
    private static final Integer UPDATED_TU_NAM = 2;

    private static final Integer DEFAULT_DEN_NAM = 1;
    private static final Integer UPDATED_DEN_NAM = 2;

    private static final String DEFAULT_DIA_DIEM_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_DIA_DIEM_MO_TA = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_DIEM_XA = "AAAAAAAAAA";
    private static final String UPDATED_DIA_DIEM_XA = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_DIEM_HUYEN = "AAAAAAAAAA";
    private static final String UPDATED_DIA_DIEM_HUYEN = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_DIEM_TINH = "AAAAAAAAAA";
    private static final String UPDATED_DIA_DIEM_TINH = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final String DEFAULT_UDF_1 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_2 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_UDF_3 = "AAAAAAAAAA";
    private static final String UPDATED_UDF_3 = "BBBBBBBBBB";

    @Autowired
    private DonViThoiKyRepository donViThoiKyRepository;

    /**
     * This repository is mocked in the vn.homtech.dtls.repository.search test package.
     *
     * @see vn.homtech.dtls.repository.search.DonViThoiKySearchRepositoryMockConfiguration
     */
    @Autowired
    private DonViThoiKySearchRepository mockDonViThoiKySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDonViThoiKyMockMvc;

    private DonViThoiKy donViThoiKy;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DonViThoiKyResource donViThoiKyResource = new DonViThoiKyResource(donViThoiKyRepository, mockDonViThoiKySearchRepository);
        this.restDonViThoiKyMockMvc = MockMvcBuilders.standaloneSetup(donViThoiKyResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DonViThoiKy createEntity(EntityManager em) {
        DonViThoiKy donViThoiKy = new DonViThoiKy()
            .tuNam(DEFAULT_TU_NAM)
            .denNam(DEFAULT_DEN_NAM)
            .diaDiemMoTa(DEFAULT_DIA_DIEM_MO_TA)
            .diaDiemXa(DEFAULT_DIA_DIEM_XA)
            .diaDiemHuyen(DEFAULT_DIA_DIEM_HUYEN)
            .diaDiemTinh(DEFAULT_DIA_DIEM_TINH)
            .isDeleted(DEFAULT_IS_DELETED)
            .udf1(DEFAULT_UDF_1)
            .udf2(DEFAULT_UDF_2)
            .udf3(DEFAULT_UDF_3);
        return donViThoiKy;
    }

    @Before
    public void initTest() {
        donViThoiKy = createEntity(em);
    }

    @Test
    @Transactional
    public void createDonViThoiKy() throws Exception {
        int databaseSizeBeforeCreate = donViThoiKyRepository.findAll().size();

        // Create the DonViThoiKy
        restDonViThoiKyMockMvc.perform(post("/api/don-vi-thoi-kies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donViThoiKy)))
            .andExpect(status().isCreated());

        // Validate the DonViThoiKy in the database
        List<DonViThoiKy> donViThoiKyList = donViThoiKyRepository.findAll();
        assertThat(donViThoiKyList).hasSize(databaseSizeBeforeCreate + 1);
        DonViThoiKy testDonViThoiKy = donViThoiKyList.get(donViThoiKyList.size() - 1);
        assertThat(testDonViThoiKy.getTuNam()).isEqualTo(DEFAULT_TU_NAM);
        assertThat(testDonViThoiKy.getDenNam()).isEqualTo(DEFAULT_DEN_NAM);
        assertThat(testDonViThoiKy.getDiaDiemMoTa()).isEqualTo(DEFAULT_DIA_DIEM_MO_TA);
        assertThat(testDonViThoiKy.getDiaDiemXa()).isEqualTo(DEFAULT_DIA_DIEM_XA);
        assertThat(testDonViThoiKy.getDiaDiemHuyen()).isEqualTo(DEFAULT_DIA_DIEM_HUYEN);
        assertThat(testDonViThoiKy.getDiaDiemTinh()).isEqualTo(DEFAULT_DIA_DIEM_TINH);
        assertThat(testDonViThoiKy.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testDonViThoiKy.getUdf1()).isEqualTo(DEFAULT_UDF_1);
        assertThat(testDonViThoiKy.getUdf2()).isEqualTo(DEFAULT_UDF_2);
        assertThat(testDonViThoiKy.getUdf3()).isEqualTo(DEFAULT_UDF_3);

        // Validate the DonViThoiKy in Elasticsearch
        verify(mockDonViThoiKySearchRepository, times(1)).save(testDonViThoiKy);
    }

    @Test
    @Transactional
    public void createDonViThoiKyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = donViThoiKyRepository.findAll().size();

        // Create the DonViThoiKy with an existing ID
        donViThoiKy.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDonViThoiKyMockMvc.perform(post("/api/don-vi-thoi-kies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donViThoiKy)))
            .andExpect(status().isBadRequest());

        // Validate the DonViThoiKy in the database
        List<DonViThoiKy> donViThoiKyList = donViThoiKyRepository.findAll();
        assertThat(donViThoiKyList).hasSize(databaseSizeBeforeCreate);

        // Validate the DonViThoiKy in Elasticsearch
        verify(mockDonViThoiKySearchRepository, times(0)).save(donViThoiKy);
    }

    @Test
    @Transactional
    public void getAllDonViThoiKies() throws Exception {
        // Initialize the database
        donViThoiKyRepository.saveAndFlush(donViThoiKy);

        // Get all the donViThoiKyList
        restDonViThoiKyMockMvc.perform(get("/api/don-vi-thoi-kies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(donViThoiKy.getId().intValue())))
            .andExpect(jsonPath("$.[*].tuNam").value(hasItem(DEFAULT_TU_NAM)))
            .andExpect(jsonPath("$.[*].denNam").value(hasItem(DEFAULT_DEN_NAM)))
            .andExpect(jsonPath("$.[*].diaDiemMoTa").value(hasItem(DEFAULT_DIA_DIEM_MO_TA.toString())))
            .andExpect(jsonPath("$.[*].diaDiemXa").value(hasItem(DEFAULT_DIA_DIEM_XA.toString())))
            .andExpect(jsonPath("$.[*].diaDiemHuyen").value(hasItem(DEFAULT_DIA_DIEM_HUYEN.toString())))
            .andExpect(jsonPath("$.[*].diaDiemTinh").value(hasItem(DEFAULT_DIA_DIEM_TINH.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1.toString())))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2.toString())))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3.toString())));
    }
    
    @Test
    @Transactional
    public void getDonViThoiKy() throws Exception {
        // Initialize the database
        donViThoiKyRepository.saveAndFlush(donViThoiKy);

        // Get the donViThoiKy
        restDonViThoiKyMockMvc.perform(get("/api/don-vi-thoi-kies/{id}", donViThoiKy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(donViThoiKy.getId().intValue()))
            .andExpect(jsonPath("$.tuNam").value(DEFAULT_TU_NAM))
            .andExpect(jsonPath("$.denNam").value(DEFAULT_DEN_NAM))
            .andExpect(jsonPath("$.diaDiemMoTa").value(DEFAULT_DIA_DIEM_MO_TA.toString()))
            .andExpect(jsonPath("$.diaDiemXa").value(DEFAULT_DIA_DIEM_XA.toString()))
            .andExpect(jsonPath("$.diaDiemHuyen").value(DEFAULT_DIA_DIEM_HUYEN.toString()))
            .andExpect(jsonPath("$.diaDiemTinh").value(DEFAULT_DIA_DIEM_TINH.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.udf1").value(DEFAULT_UDF_1.toString()))
            .andExpect(jsonPath("$.udf2").value(DEFAULT_UDF_2.toString()))
            .andExpect(jsonPath("$.udf3").value(DEFAULT_UDF_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDonViThoiKy() throws Exception {
        // Get the donViThoiKy
        restDonViThoiKyMockMvc.perform(get("/api/don-vi-thoi-kies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDonViThoiKy() throws Exception {
        // Initialize the database
        donViThoiKyRepository.saveAndFlush(donViThoiKy);

        int databaseSizeBeforeUpdate = donViThoiKyRepository.findAll().size();

        // Update the donViThoiKy
        DonViThoiKy updatedDonViThoiKy = donViThoiKyRepository.findById(donViThoiKy.getId()).get();
        // Disconnect from session so that the updates on updatedDonViThoiKy are not directly saved in db
        em.detach(updatedDonViThoiKy);
        updatedDonViThoiKy
            .tuNam(UPDATED_TU_NAM)
            .denNam(UPDATED_DEN_NAM)
            .diaDiemMoTa(UPDATED_DIA_DIEM_MO_TA)
            .diaDiemXa(UPDATED_DIA_DIEM_XA)
            .diaDiemHuyen(UPDATED_DIA_DIEM_HUYEN)
            .diaDiemTinh(UPDATED_DIA_DIEM_TINH)
            .isDeleted(UPDATED_IS_DELETED)
            .udf1(UPDATED_UDF_1)
            .udf2(UPDATED_UDF_2)
            .udf3(UPDATED_UDF_3);

        restDonViThoiKyMockMvc.perform(put("/api/don-vi-thoi-kies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDonViThoiKy)))
            .andExpect(status().isOk());

        // Validate the DonViThoiKy in the database
        List<DonViThoiKy> donViThoiKyList = donViThoiKyRepository.findAll();
        assertThat(donViThoiKyList).hasSize(databaseSizeBeforeUpdate);
        DonViThoiKy testDonViThoiKy = donViThoiKyList.get(donViThoiKyList.size() - 1);
        assertThat(testDonViThoiKy.getTuNam()).isEqualTo(UPDATED_TU_NAM);
        assertThat(testDonViThoiKy.getDenNam()).isEqualTo(UPDATED_DEN_NAM);
        assertThat(testDonViThoiKy.getDiaDiemMoTa()).isEqualTo(UPDATED_DIA_DIEM_MO_TA);
        assertThat(testDonViThoiKy.getDiaDiemXa()).isEqualTo(UPDATED_DIA_DIEM_XA);
        assertThat(testDonViThoiKy.getDiaDiemHuyen()).isEqualTo(UPDATED_DIA_DIEM_HUYEN);
        assertThat(testDonViThoiKy.getDiaDiemTinh()).isEqualTo(UPDATED_DIA_DIEM_TINH);
        assertThat(testDonViThoiKy.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testDonViThoiKy.getUdf1()).isEqualTo(UPDATED_UDF_1);
        assertThat(testDonViThoiKy.getUdf2()).isEqualTo(UPDATED_UDF_2);
        assertThat(testDonViThoiKy.getUdf3()).isEqualTo(UPDATED_UDF_3);

        // Validate the DonViThoiKy in Elasticsearch
        verify(mockDonViThoiKySearchRepository, times(1)).save(testDonViThoiKy);
    }

    @Test
    @Transactional
    public void updateNonExistingDonViThoiKy() throws Exception {
        int databaseSizeBeforeUpdate = donViThoiKyRepository.findAll().size();

        // Create the DonViThoiKy

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDonViThoiKyMockMvc.perform(put("/api/don-vi-thoi-kies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donViThoiKy)))
            .andExpect(status().isBadRequest());

        // Validate the DonViThoiKy in the database
        List<DonViThoiKy> donViThoiKyList = donViThoiKyRepository.findAll();
        assertThat(donViThoiKyList).hasSize(databaseSizeBeforeUpdate);

        // Validate the DonViThoiKy in Elasticsearch
        verify(mockDonViThoiKySearchRepository, times(0)).save(donViThoiKy);
    }

    @Test
    @Transactional
    public void deleteDonViThoiKy() throws Exception {
        // Initialize the database
        donViThoiKyRepository.saveAndFlush(donViThoiKy);

        int databaseSizeBeforeDelete = donViThoiKyRepository.findAll().size();

        // Get the donViThoiKy
        restDonViThoiKyMockMvc.perform(delete("/api/don-vi-thoi-kies/{id}", donViThoiKy.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DonViThoiKy> donViThoiKyList = donViThoiKyRepository.findAll();
        assertThat(donViThoiKyList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the DonViThoiKy in Elasticsearch
        verify(mockDonViThoiKySearchRepository, times(1)).deleteById(donViThoiKy.getId());
    }

    @Test
    @Transactional
    public void searchDonViThoiKy() throws Exception {
        // Initialize the database
        donViThoiKyRepository.saveAndFlush(donViThoiKy);
        when(mockDonViThoiKySearchRepository.search(queryStringQuery("id:" + donViThoiKy.getId())))
            .thenReturn(Collections.singletonList(donViThoiKy));
        // Search the donViThoiKy
        restDonViThoiKyMockMvc.perform(get("/api/_search/don-vi-thoi-kies?query=id:" + donViThoiKy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(donViThoiKy.getId().intValue())))
            .andExpect(jsonPath("$.[*].tuNam").value(hasItem(DEFAULT_TU_NAM)))
            .andExpect(jsonPath("$.[*].denNam").value(hasItem(DEFAULT_DEN_NAM)))
            .andExpect(jsonPath("$.[*].diaDiemMoTa").value(hasItem(DEFAULT_DIA_DIEM_MO_TA)))
            .andExpect(jsonPath("$.[*].diaDiemXa").value(hasItem(DEFAULT_DIA_DIEM_XA)))
            .andExpect(jsonPath("$.[*].diaDiemHuyen").value(hasItem(DEFAULT_DIA_DIEM_HUYEN)))
            .andExpect(jsonPath("$.[*].diaDiemTinh").value(hasItem(DEFAULT_DIA_DIEM_TINH)))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].udf1").value(hasItem(DEFAULT_UDF_1)))
            .andExpect(jsonPath("$.[*].udf2").value(hasItem(DEFAULT_UDF_2)))
            .andExpect(jsonPath("$.[*].udf3").value(hasItem(DEFAULT_UDF_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DonViThoiKy.class);
        DonViThoiKy donViThoiKy1 = new DonViThoiKy();
        donViThoiKy1.setId(1L);
        DonViThoiKy donViThoiKy2 = new DonViThoiKy();
        donViThoiKy2.setId(donViThoiKy1.getId());
        assertThat(donViThoiKy1).isEqualTo(donViThoiKy2);
        donViThoiKy2.setId(2L);
        assertThat(donViThoiKy1).isNotEqualTo(donViThoiKy2);
        donViThoiKy1.setId(null);
        assertThat(donViThoiKy1).isNotEqualTo(donViThoiKy2);
    }
}