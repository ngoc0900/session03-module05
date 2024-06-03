package ra.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.jpa.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
