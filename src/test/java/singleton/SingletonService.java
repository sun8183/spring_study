package singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    /*
    * 클래스 생성 금지 private
    * */
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 생성됨");
    }
}
