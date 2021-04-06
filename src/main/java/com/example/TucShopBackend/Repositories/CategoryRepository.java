package com.example.TucShopBackend.Repositories;

import com.example.TucShopBackend.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    public Category findCategoriesByName(String category);


    @Query(value ="select * from category where active =1" ,nativeQuery = true)
    List<Category> getAll();

    @Query(value ="select * from category c where c.menu_id = (  select m.id from menu m where m.restaurant_id = 1) AND c.active = 1 " ,nativeQuery = true)
    List<Category> getAllByRestId(@Param("id") Long id);

    @Query(value = "select * from category  AND active = 1", nativeQuery = true)
    List<Category>
    getSubCategoriesById(@Param("id") Long id);

    @Query(value = "select * from category where parentid is not null", nativeQuery = true)
    List<Category> getAllSubCategories();

    @Query(value = "select * from category c  where c.menu_id= (select id  from menu m where m.restaurant_id =:id)",nativeQuery = true)
    List<Category> findByRetaurantId(@Param("id") Long id);

//    public String findByName(String categoryName);



}
