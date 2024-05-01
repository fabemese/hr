package hu.cubix.hr.borcsi.model;

public class PositionsOfCompanyWithAvgSalary {
    Double avgSalary;
    String Position;

    public PositionsOfCompanyWithAvgSalary(Double avgSalary, String position) {
        this.avgSalary = avgSalary;
        Position = position;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public Double getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(Double avgSalary) {
        this.avgSalary = avgSalary;
    }
}
