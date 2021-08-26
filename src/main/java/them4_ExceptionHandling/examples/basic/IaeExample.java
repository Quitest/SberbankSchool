package ru.sbrf.javacourse.exeption.example.basic;

public class IaeExample {

    public static void main(String[] args) {
        SberTechEmployee employee = new SberTechEmployee();
        employee.setGrade(20);
    }

    private static class SberTechEmployee {

        private int grade;

        public int getGrade() {
            return grade;
        }

        /**
         * Throws {@link IllegalArgumentException} when incorrect grade value specified.
         *
         * @param grade employee grade value
         */
        public void setGrade(int grade) {
            if (grade < 0 || grade > 16) {
                throw new IllegalArgumentException("Invalid grade value");
            }

            this.grade = grade;
        }
    }

}
