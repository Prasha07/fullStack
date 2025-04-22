package com.backend.E_commerce.Repository;

import com.backend.E_commerce.Entity.Cateogry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CateogryReository extends JpaRepository<Cateogry,Long> {

    @Query("SELECT c FROM Cateogry c WHERE c.name =:name")
    public Cateogry findByName(@Param("name") String name);
    @Query("SELECT c FROM Cateogry c WHERE c.name = :name AND c.parent_cateogry.name = :parentLevel")
    public Cateogry findByNameAndParent(@Param("name") String name,@Param("parentLevel") String parentLevel);
}
