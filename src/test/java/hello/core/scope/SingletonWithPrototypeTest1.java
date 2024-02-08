package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {


    @Test
    void protoTypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean protoTypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean protoTypeBean2 = ac.getBean(PrototypeBean.class);
        protoTypeBean1.addCount();
        assertThat(protoTypeBean1.getCount()).isEqualTo(1);
        protoTypeBean2.addCount();
        assertThat(protoTypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype(){
        /* 수동 컴포넌트 스캔 대상 */
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean c1 = ac.getBean(ClientBean.class);

        assertThat(c1.logic()).isEqualTo(1);

        ClientBean c2 = ac.getBean(ClientBean.class);

        assertThat(c2.logic()).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean{
        private Provider<PrototypeBean> prototypeBean;

        @Autowired
        public ClientBean(Provider<PrototypeBean> prototypeBean){
            this.prototypeBean = prototypeBean;
        }

        public int logic(){
            PrototypeBean p1 = prototypeBean.get();
            p1.addCount();
            return p1.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("prototypeBean.init"+this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("prototypeBean.destroy"+this);
        }
    }
}
