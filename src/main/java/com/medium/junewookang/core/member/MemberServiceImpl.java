package com.medium.junewookang.core.member;

public class MemberServiceImpl implements MemberService{

    // 의존관계는 난 몰라. 그냥 실행만 할거야!
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
