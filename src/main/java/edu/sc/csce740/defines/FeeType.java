package edu.sc.csce740.defines;

/**
 * Fee types
 * <li>{@link #AAS_AE_LAB}</li>
 * <li>{@link #AAS_AH_LAB}</li>
 * <li>{@link #AAS_DANCE_LAB}</li>
 * <li>{@link #AAS_FIELD}</li>
 * <li>{@link #AAS_HS_DRAMA}</li>
 * <li>{@link #AAS_LANGUAGE}</li>
 * <li>{@link #AAS_MARINE_SCIENCE}</li>
 * <li>{@link #AAS_SCIENCE_LAB}</li>
 * <li>{@link #AAS_MEDIA_LAB}</li>
 * <li>{@link #AAS_STUDIO_LAB}</li>
 * <li>{@link #ATHLETIC}</li>
 * <li>{@link #CAPSTONE}</li>
 * <li>{@link #COHORT}</li>
 * <li>{@link #DEPARTMENTAL}</li>
 * <li>{@link #EAC_APOGEE}</li>
 * <li>{@link #EAC_CSCE_101_102_LAB}</li>
 * <li>{@link #EAC_EXEC_MASTER}</li>
 * <li>{@link #EAC_MHIT}</li>
 * <li>{@link #EAC_PROGRAM}</li>
 * <li>{@link #EAC_SYS_DESIGN}</li>
 * <li>{@link #GENERAL}</li>
 * <li>{@link #HEALTH_CENTER_UNDERGRAD_6_11}</li>
 * <li>{@link #HEALTH_CENTER_GRAD_6_8}</li>
 * <li>{@link #HEALTH_CENTER_GRAD_9_11}</li>
 * <li>{@link #HEALTH_CENTER_GA}</li>
 * <li>{@link #INSURANCE}</li>
 * <li>{@link #INTERNATIONAL}</li>
 * <li>{@link #INTERNATIONAL_SHORT_TERM}</li>
 * <li>{@link #INTERNATIONAL_SPONSORED}</li>
 * <li>{@link #NONE}</li>
 * <li>{@link #SIMS}</li>
 * <li>{@link #STUDY_ABROAD}</li>
 * <li>{@link #TECHNOLOGY}</li>
 * <li>{@link #WOODROW}</li>
 */
public enum FeeType {
    /**
     * This is a fee for the Arts and Sciences Art Education Lab.
     */
    AAS_AE_LAB,

    /**
     * This is a fee for the Arts and Sciences Art History Lab.
     */
    AAS_AH_LAB,

    /**
     * This is a fee for the Arts and Sciences Dance Lab.
     */
    AAS_DANCE_LAB,

    /**
     * This is a fee for the Arts and Sciences Field.
     */
    AAS_FIELD,

    /**
     * This is a fee for the Arts and Sciences Drama Lab.
     */
    AAS_HS_DRAMA,

    /**
     * This is a fee for the Arts and Sciences Language Lab.
     */
    AAS_LANGUAGE,

    /**
     * This is a fee for the Arts and Sciences Marine Science Lab.
     */
    AAS_MARINE_SCIENCE,

    /**
     * This is a fee for the Arts and Sciences Art Math Lab.
     */
    AAS_SCIENCE_LAB,

    /**
     * This is a fee for the Arts and Sciences Media Lab.
     */
    AAS_MEDIA_LAB,

    /**
     * This is a fee for the Arts and Sciences Studio Lab.
     */
    AAS_STUDIO_LAB,

    /**
     * This is the tuition on an Athletic Scholarship.
     */
    ATHLETIC,

    /**
     * This is a fee for the Capstone project.
     */
    CAPSTONE,

    /**
     * This is a fee for a Study Abroad Cohort.
     */
    COHORT,

    /**
     * This is the tuition on a Departmental Scholarship.
     */
    DEPARTMENTAL,

    /**
     * This is a fee for the Engineering and Computing APOGEE program.
     */
    EAC_APOGEE,

    /**
     * This is a fee for the CSCE 101 and CSCE 102 labs.
     */
    EAC_CSCE_101_102_LAB,

    /**
     * This is a fee for the Engineering and Computing Executive Master's program.
     */
    EAC_EXEC_MASTER,

    /**
     * This is a fee for the Engineering and Computing MHIT program.
     */
    EAC_MHIT,

    /**
     * This is a fee for all students in the Engineering and Computing program.
     */
    EAC_PROGRAM,

    /**
     * This is a fee for the Engineering and Computing System Design program.
     */
    EAC_SYS_DESIGN,

    /**
     * This is the tuition on a General Scholarship.
     */
    GENERAL,

    /**
     * This is the health center fee for undergraduate students taking 6-11 hours.
     */
    HEALTH_CENTER_UNDERGRAD_6_11,

    /**
     * This is the health center fee for graduate students taking 6-8 hours..
     */
    HEALTH_CENTER_GRAD_6_8,

    /**
     * This is the health center fee for graduate students taking 9-11 hours..
     */
    HEALTH_CENTER_GRAD_9_11,

    /**
     * This is the health center fee for graduate assistants.
     */
    HEALTH_CENTER_GA,

    /**
     * This is the cost fo health insurance.
     */
    INSURANCE,

    /**
     * This is the fee for international students.
     */
    INTERNATIONAL,

    /**
     * This is the fee for short-term international students.
     */
    INTERNATIONAL_SHORT_TERM,

    /**
     * This is the fee for sponsored international students.
     */
    INTERNATIONAL_SPONSORED,

    /**
     * This is the matriculation fee.
     */
    MATRICULATION,

    /**
     * This is the fee for the national student exchange.
     */
    NATIONAL_STUDENT_EXCHANGE,

    /**
     * This is the tuition with no Scholarship.
     */
    NONE,

    /**
     * This is the tuition for online graduate courses.
     */
    ONLINE,

    /**
     * This is the tuition on a SIMS Scholarship.
     */
    SIMS,

    /**
     * This is a fee for the Study Abroad program.
     */
    STUDY_ABROAD,

    /**
     * This is a fee for the Study Abroad Exchange program.
     */
    STUDY_ABROAD_EXCHANGE,

    /**
     * This is a fee for the mandatory Study Abroad insurance.
     */
    STUDY_ABROAD_INSURANCE,

    /**
     * This is the technology fee for all students.
     */
    TECHNOLOGY,

    /**
     * This is the tuition on a Woodrow Scholarship.
     */
    WOODROW,

    /**
     * This is a tuition addon for non-resident undergraduates with 17 hours or more
     */
    TUITION_OVER_17_NON_RES,

    /**
     * This is a tuition addon for resident undergraduates with 17 hours or more or for undergrads on scholarship
     */
    TUITION_OVER_17_RES_SS_MIL;

    /**
     * Convert a FeeType to a string.
     * @return A string representation of a FeeType.
     */
    @Override
    public String toString() {
        switch(this) {
            case AAS_AE_LAB:
                return "AAS_AE_LAB:";
            case AAS_AH_LAB:
                return "AAS_AH_LAB";
            case AAS_DANCE_LAB:
                return "AAS_DANCE_LAB";
            case AAS_FIELD:
                return "AAS_FIELD";
            case AAS_HS_DRAMA:
                return "AAS_HS_DRAMA";
            case AAS_LANGUAGE:
                return "AAS_LANGUAGE";
            case AAS_MARINE_SCIENCE:
                return "AAS_MARINE_SCIENCE";
            case AAS_SCIENCE_LAB:
                return "AAS_SCIENCE_LAB";
            case AAS_MEDIA_LAB:
                return "AAS_MEDIA_LAB";
            case AAS_STUDIO_LAB:
                return "AAS_STUDIO_LAB";
            case ATHLETIC:
                return "ATHLETIC";
            case CAPSTONE:
                return "CAPSTONE";
            case COHORT:
                return "COHORT";
            case DEPARTMENTAL:
                return "DEPARTMENTAL";
            case EAC_APOGEE:
                return "EAC_APOGEE";
            case EAC_CSCE_101_102_LAB:
                return "EAC_CSCE_101_102_LAB";
            case EAC_EXEC_MASTER:
                return "EAC_EXEC_MASTER";
            case EAC_MHIT:
                return "EAC_MHIT";
            case EAC_PROGRAM:
                return "EAC_PROGRAM";
            case EAC_SYS_DESIGN:
                return "EAC_SYS_DESIGN";
            case GENERAL:
                return "GENERAL";
            case HEALTH_CENTER_UNDERGRAD_6_11:
                return "HEALTH_CENTER_UNDERGRAD_6_11";
            case HEALTH_CENTER_GRAD_6_8:
                return "HEALTH_CENTER_GRAD_6_8";
            case HEALTH_CENTER_GRAD_9_11:
                return "HEALTH_CENTER_GRAD_9_11";
            case HEALTH_CENTER_GA:
                return "HEALTH_CENTER_GA";
            case INSURANCE:
                return "INSURANCE";
            case INTERNATIONAL:
                return "INTERNATIONAL";
            case INTERNATIONAL_SHORT_TERM:
                return "INTERNATIONAL_SHORT_TERM";
            case INTERNATIONAL_SPONSORED:
                return "INTERNATIONAL_SPONSORED";
            case MATRICULATION:
                return "MATRICULATION";
            case NATIONAL_STUDENT_EXCHANGE:
                return "NATIONAL_STUDENT_EXCHANGE:";
            case NONE:
                return "NONE";
            case ONLINE:
                return "ONLINE";
            case SIMS:
                return "SIMS";
            case STUDY_ABROAD:
                return "STUDY_ABROAD";
            case STUDY_ABROAD_EXCHANGE:
                return "STUDY_ABROAD_EXCHANGE";
            case STUDY_ABROAD_INSURANCE:
                return "STUDY_ABROAD_INSURANCE";
            case TECHNOLOGY:
                return "TECHNOLOGY";
            case WOODROW:
                return "WOODROW";
            case TUITION_OVER_17_NON_RES:
                return "TUITION_OVER_17_NON_RES";
            case TUITION_OVER_17_RES_SS_MIL:
                return "TUITION_OVER_17_RES_SS_MIL";
            default:
                return super.toString();
        }
    }
}
