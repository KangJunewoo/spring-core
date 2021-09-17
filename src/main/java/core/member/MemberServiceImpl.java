package core.member;

public class MemberServiceImpl implements MemberService {

    // 구현체 없이 선언만 하면 널포인터익셉션
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
