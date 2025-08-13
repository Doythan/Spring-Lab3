public class DispatcherServlet {

    private Controller controller;

    public DispatcherServlet(Controller controller) {
        this.controller = controller;
    }

    void findUri() {
        MyExceptionHandler handler = new MyExceptionHandler();
        try {
            System.out.println("dispatcher servlet ---------");
            controller.save();
        } catch (RuntimeException e) {
            String html = handler.back(e);
            System.out.println(html);
        }

    }
}
