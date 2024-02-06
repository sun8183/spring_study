package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient {

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = "+ url);
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void connect(){
        System.out.println("connect:"+url);
    }

    public void call(String message){
        System.out.println("call:" + url + ", message:"+message);
    }

    public void disconnect(){
        System.out.println("close:"+url);
    }

    /* 인터페이스 사용 : implements InitializingBean, DisposableBean*/
    /* 의존관계 주입이 종료되면 호출 */

    public void init() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }

    /* 종료시점에 호출 */

    public void close() throws Exception {
        disconnect();
    }
}
