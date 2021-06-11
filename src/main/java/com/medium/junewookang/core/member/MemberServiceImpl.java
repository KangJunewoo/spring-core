package com.medium.junewookang.core.member;

public class MemberServiceImpl implements MemberService{
    // 오른쪽에 실제 할당하는 부분은 인터페이스(추상체)가 아닌 구현체에 의존한다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
