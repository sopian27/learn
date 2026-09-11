import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class BeanLifecycleRunner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("--- Context siap, bean sudah full lifecycle sampai fase init ---");

        context.close(); // trigger destruction phase
    }

    @Configuration
    static class AppConfig {
        @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
        public TrackedBean trackedBean() {
            return new TrackedBean("nilai-dari-config");
        }
    }

    static class TrackedBean implements InitializingBean, DisposableBean {

        private final String note;

        public TrackedBean(String note) {
            this.note = note;
            System.out.println("1. Instantiation (constructor) — note = " + note);
        }

        @PostConstruct
        public void postConstruct() {
            System.out.println("2. @PostConstruct");
        }

        @Override
        public void afterPropertiesSet() {
            System.out.println("3. InitializingBean.afterPropertiesSet()");
        }

        public void customInit() {
            System.out.println("4. custom init-method (initMethod)");
        }

        @PreDestroy
        public void preDestroy() {
            System.out.println("D1. @PreDestroy");
        }

        @Override
        public void destroy() {
            System.out.println("D2. DisposableBean.destroy()");
        }

        public void customDestroy() {
            System.out.println("D3. custom destroy-method");
        }
    }
}