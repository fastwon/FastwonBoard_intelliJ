package com.example.fastwonboard.board.persistence;

import com.example.fastwonboard.board.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {

}
