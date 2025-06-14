package com.crimson.app.crimson.repository;

import com.crimson.app.crimson.dto.ComplaintCategoryDto;
import com.crimson.app.crimson.model.MdlComplaintCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintCategoryRepository extends JpaRepository<MdlComplaintCategory, Long> {

    @Query(value = """
            Select new com.crimson.app.crimson.dto.ComplaintCategoryDto(a.complaintCategoryId, a.category)
                    from MdlComplaintCategory a order by category asc
            """)
    List<ComplaintCategoryDto> findAllCategory();
}
