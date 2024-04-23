package hu.cubix.hr.borcsi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.TreeMap;

@Component
@ConfigurationProperties(prefix = "hr")
public class HrConfigProperties {

    private Salary salary = new Salary();

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public static class Salary {

        private Def def = new Def();
        private Smart smart = new Smart();

        public Def getDef() {
            return def;
        }

        public void setDef(Def def) {
            this.def = def;
        }

        public Smart getSmart() {
            return smart;
        }

        public void setSmart(Smart smart) {
            this.smart = smart;
        }
    }

    public static class Def {
        private int percent;

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }
    }

    public static class Smart {
        private TreeMap<Double, Integer> limit;

        public TreeMap<Double, Integer> getLimit() {
            return limit;
        }

        public void setLimit(TreeMap<Double, Integer> limit) {
            this.limit = limit;
        }
    }


}