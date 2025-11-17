public class App {
    public static void main(String[] args) {
        Service service = new Service();
        service.preLoad();

        Menu menu = new Menu(service);
        menu.mMenu();



    }
}
