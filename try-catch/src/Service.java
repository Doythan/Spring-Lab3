public class Service {
    private Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public void 회원가입(int num) {
        System.out.println("service 회원가입 -----------");

        if (num == 1) {
            repository.save();
        } else {
            throw new RuntimeException("회원가입중 서비스에서 오류가 났습니다.");
        }
    }
}
