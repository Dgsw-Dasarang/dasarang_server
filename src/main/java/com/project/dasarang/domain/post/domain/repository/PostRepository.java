package com.project.dasarang.domain.post.domain.repository;

import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByAuthor(Pageable pageable, User author);

}
