package kr.seok.shop.service;

import kr.seok.shop.domain.Member;
import kr.seok.shop.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
/* 해당 클래스는 select 하는 로직이 많아 기본적으로 readOnly = true 를 기본으로 설정 */
@Transactional(readOnly = true)
/* final 예약어가 설정된 필드를 생성자 주입 */
@RequiredArgsConstructor
public class MemberService {

    /* final 키워드를 추가하면 컴파일 시점에 memberRepository 를 설정하지 않는 오류를 체크할 수 있다. (보통 기본 생성자를 추가할 때 발견) */
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional //변경
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(
                /* 멀티 스레드 환경에서 비즈니스 로직이 실행되는 경우 duplicate 오류 발생이 가능하므로 member.name 필드에 unique 설정 필요 */
                member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
