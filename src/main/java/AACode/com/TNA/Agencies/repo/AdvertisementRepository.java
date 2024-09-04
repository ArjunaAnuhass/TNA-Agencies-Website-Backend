package AACode.com.TNA.Agencies.repo;

import AACode.com.TNA.Agencies.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Query("SELECT a FROM Advertisement a WHERE a.title LIKE %:keyword% OR a.districtCategory.name LIKE %:keyword%")
    List<Advertisement> searchAdvertisement(@Param("keyword") String keyword);
}
