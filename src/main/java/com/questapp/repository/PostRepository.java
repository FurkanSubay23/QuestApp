package com.questapp.repository;

import com.questapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
   List<Post> findByUserId(Long userId); // findByUserId() yöntemi, JPA tarafından oluşturulan bir sorgu için belirli bir kuralla uygunluk gösterir.
                                                    // Bu yöntem, veritabanında Post nesnesinin userID alanına karşılık gelen kayıtları getirir.

    /*
    @Query("SELECT p FROM Post p WHERE p.userId = :userId")
    List<Post>findByUserId(@Param("userId") Long userId);

     */


}
