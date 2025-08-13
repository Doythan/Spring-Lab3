public class Controller {

    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    public void save() {
        System.out.println("controller save --------");
        service.회원가입(2);
    }
}
