package Cash;

public class Clerk extends Employee {
    private double allowance;
    private int overtimeHours;
    private static double overtimeRate = 0.5;

    public Clerk() {
    }

    public Clerk(String name, int yearJoined, double basicSalary, double allowance, int overtimeHours) {
        super(name, yearJoined, basicSalary);
        this.allowance = allowance;
        this.overtimeHours = overtimeHours;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(int overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public static double getOvertimeRate() {
        return Clerk.overtimeRate;
    }

    public static void setOvertimeRate(double overtimeRate) {
        Clerk.overtimeRate = overtimeRate;
    }

    public double getOvertimePay() {
        return overtimeHours * overtimeRate;
    }

    public double calculateSalary() {
        return getBasicSalary() + allowance * getOvertimePay();
    }

}
