package edu.sc.csce740.defines;

public enum College {
    ARTS_AND_SCIENCES,
    ENGINEERING_AND_COMPUTING,
    GRADUATE_SCHOOL;

    @Override
    public String toString() {
        switch(this) {
            case ARTS_AND_SCIENCES:
                return "ARTS_AND_SCIENCES";
            case ENGINEERING_AND_COMPUTING:
                return "ENGINEERING_AND_COMPUTING";
            case GRADUATE_SCHOOL:
                return "GRADUATE_SCHOOL";
            default:
                return super.toString();
        }
    }
}
