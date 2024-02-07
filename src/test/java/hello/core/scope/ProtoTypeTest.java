package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class ProtoTypeTest {

    @Test
    void protoTypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(protoTypeBean.class);
        protoTypeBean protoTypeBean1 = ac.getBean(protoTypeBean.class);
        protoTypeBean protoTypeBean2 = ac.getBean(protoTypeBean.class);
        System.out.println("protoTypeBean1 : "+protoTypeBean1);
        System.out.println("protoTypeBean2 : "+protoTypeBean2);

        Assertions.assertThat(protoTypeBean1).isNotSameAs(protoTypeBean2);
        ac.close(); /* prototype 호출되지 않음 */
    }

    @Scope("prototype")
    static class protoTypeBean{
        @PostConstruct
        public void init(){
            System.out.println("protoTypeBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("protoTypeBean.destroy");
        }
    }
}
