package vn.homtech.dtls.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of PCRPhanUngSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class PCRPhanUngSearchRepositoryMockConfiguration {

    @MockBean
    private PCRPhanUngSearchRepository mockPCRPhanUngSearchRepository;

}