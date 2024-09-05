package AACode.com.TNA.Agencies.repo;

import AACode.com.TNA.Agencies.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Query("SELECT a FROM Advertisement a WHERE lower(a.title) LIKE lower(concat('%', :query, '%')) OR lower(a.districtCategory) LIKE lower(concat('%', :query, '%'))")

    List<Advertisement> findBySearchQuery(String query);

    List<Advertisement> findByCustomerId(Long userId);
}
