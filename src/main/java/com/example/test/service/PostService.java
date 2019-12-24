package com.example.test.service;

import com.example.test.domain.Entity.PostEntity;
import com.example.test.dto.PostDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.test.domain.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// https://github.com/victolee93/Springboot-boardCRUD/blob/master/src/main/java/com/victolee/board/service/BoardService.java

@Service @AllArgsConstructor
public class PostService {

    private PostRepository postRepository;

    @Transactional
    public List<PostDTO> selectPostlist() {
        List<PostEntity> postEntities = postRepository.findAll();
        List<PostDTO> postDTOList = new ArrayList<>();

        for ( PostEntity PostEntity : postEntities) {
            PostDTO postDTO = PostDTO.builder()
                    .idx(PostEntity.getIdx())
                    .title(PostEntity.getTitle())
                    .content(PostEntity.getContent())
                    .author_midx(PostEntity.getAuthor_midx())
                    .created_at(PostEntity.getCreated_at())
                    .updated_at(PostEntity.getUpdated_at())
                    .build();

            postDTOList.add(postDTO);
        }

        return postDTOList;
    }

    @Transactional
    public PostDTO selectPost(Long idx) {
        Optional<PostEntity> boardEntityWrapper = postRepository.findById(idx);
        PostEntity PostEntity = boardEntityWrapper.get();

        PostDTO postDTO = PostDTO.builder()
                .idx(PostEntity.getIdx())
                .tidx(PostEntity.getTidx())
                .title(PostEntity.getTitle())
                .content(PostEntity.getContent())
                .author_midx(PostEntity.getAuthor_midx())
                .created_at(PostEntity.getCreated_at())
                .updated_at(PostEntity.getUpdated_at())
                .build();

        return postDTO;
    }

    @Transactional
    public Long insertPost(PostDTO postDTO) {
        return postRepository.save(postDTO.toEntity()).getIdx();
    }

    @Transactional
    public void deletePost(Long idx) {
        postRepository.deleteById(idx);
    }
}
