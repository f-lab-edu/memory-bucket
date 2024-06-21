package com.zephyr.api.repository;

import com.zephyr.api.domain.AlbumMember;
import com.zephyr.api.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlbumMemberRepository extends JpaRepository<AlbumMember, Long>, AlbumMemberCustomRepository {

    Optional<AlbumMember> findByAlbumIdAndMemberId(Long albumId, Long memberId);

    List<AlbumMember> findByAlbumId(Long albumId);

    List<AlbumMember> findByMember(Member member);

    void deleteByAlbumIdAndMemberId(Long albumId, Long memberId);
}
