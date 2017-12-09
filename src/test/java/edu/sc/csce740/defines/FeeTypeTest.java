package edu.sc.csce740.defines;

import org.junit.Assert;
import org.junit.Test;

public class FeeTypeTest {
    @Test
    public void testFeeTypeAAS_AE_LABlToString(){
        Assert.assertEquals("AAS_AE_LAB:", FeeType.AAS_AE_LAB.toString());
    }

    @Test
    public void testFeeTypeAAS_AH_LABToString(){
        Assert.assertEquals("AAS_AH_LAB", FeeType.AAS_AH_LAB.toString());
    }

    @Test
    public void testFeeTypeAAS_DANCE_LABToString(){
        Assert.assertEquals("AAS_DANCE_LAB", FeeType.AAS_DANCE_LAB.toString());
    }

    @Test
    public void testFeeTypeAAS_FIELDToString(){
        Assert.assertEquals("AAS_FIELD", FeeType.AAS_FIELD.toString());
    }

    @Test
    public void testFeeTypeAAS_HS_DRAMAToString(){
        Assert.assertEquals("AAS_HS_DRAMA", FeeType.AAS_HS_DRAMA.toString());
    }

    @Test
    public void testFeeTypeAAS_LANGUAGEToString(){
        Assert.assertEquals("AAS_LANGUAGE", FeeType.AAS_LANGUAGE.toString());
    }

    @Test
    public void testFeeTypeAAS_MARINE_SCIENCEToString(){
        Assert.assertEquals("AAS_MARINE_SCIENCE", FeeType.AAS_MARINE_SCIENCE.toString());
    }

    @Test
    public void testFeeTypeAAS_SCIENCE_LABToString(){
        Assert.assertEquals("AAS_SCIENCE_LAB", FeeType.AAS_SCIENCE_LAB.toString());
    }

    @Test
    public void testFeeTypeAAS_MEDIA_LABToString(){
        Assert.assertEquals("AAS_MEDIA_LAB", FeeType.AAS_MEDIA_LAB.toString());
    }

    @Test
    public void testFeeTypeAAS_STUDIO_LABToString(){
        Assert.assertEquals("AAS_STUDIO_LAB", FeeType.AAS_STUDIO_LAB.toString());
    }

    @Test
    public void testFeeTypeATHLETIClToString(){
        Assert.assertEquals("ATHLETIC", FeeType.ATHLETIC.toString());
    }

    @Test
    public void testFeeTypeCAPSTONEToString(){
        Assert.assertEquals("CAPSTONE", FeeType.CAPSTONE.toString());
    }

    @Test
    public void testFeeTypeCOHORTToString(){
        Assert.assertEquals("COHORT", FeeType.COHORT.toString());
    }

    @Test
    public void testFeeTypeDEPARTMENTALToString(){
        Assert.assertEquals("DEPARTMENTAL", FeeType.DEPARTMENTAL.toString());
    }

    @Test
    public void testFeeTypeEAC_APOGEEToString(){
        Assert.assertEquals("EAC_APOGEE", FeeType.EAC_APOGEE.toString());
    }

    @Test
    public void testFeeTypeEAC_CSCE_101_102_LABToString(){
        Assert.assertEquals("EAC_CSCE_101_102_LAB", FeeType.EAC_CSCE_101_102_LAB.toString());
    }

    @Test
    public void testFeeTypeEAC_EXEC_MASTERToString(){
        Assert.assertEquals("EAC_EXEC_MASTER", FeeType.EAC_EXEC_MASTER.toString());
    }

    @Test
    public void testFeeTypeEAC_MHITToString(){
        Assert.assertEquals("EAC_MHIT", FeeType.EAC_MHIT.toString());
    }

    @Test
    public void testFeeTypeEAC_PROGRAMToString(){
        Assert.assertEquals("EAC_PROGRAM", FeeType.EAC_PROGRAM.toString());
    }

    @Test
    public void testFeeTypeEAC_SYS_DESIGNToString(){
        Assert.assertEquals("EAC_SYS_DESIGN", FeeType.EAC_SYS_DESIGN.toString());
    }

    @Test
    public void testFeeTypeGeneralToString(){
        Assert.assertEquals("GENERAL", FeeType.GENERAL.toString());
    }

    @Test
    public void testFeeTypeHEALTH_CENTER_UNDERGRAD_6_11ToString(){
        Assert.assertEquals("HEALTH_CENTER_UNDERGRAD_6_11", FeeType.HEALTH_CENTER_UNDERGRAD_6_11.toString());
    }

    @Test
    public void testFeeTypeHEALTH_CENTER_GRAD_6_8ToString(){
        Assert.assertEquals("HEALTH_CENTER_GRAD_6_8", FeeType.HEALTH_CENTER_GRAD_6_8.toString());
    }

    @Test
    public void testFeeTypeHEALTH_CENTER_GRAD_9_11ToString(){
        Assert.assertEquals("HEALTH_CENTER_GRAD_9_11", FeeType.HEALTH_CENTER_GRAD_9_11.toString());
    }

    @Test
    public void testFeeTypeHEALTH_CENTER_GAToString(){
        Assert.assertEquals("HEALTH_CENTER_GA", FeeType.HEALTH_CENTER_GA.toString());
    }

    @Test
    public void testFeeTypeINSURANCEToString(){
        Assert.assertEquals("INSURANCE", FeeType.INSURANCE.toString());
    }

    @Test
    public void testFeeTypeINTERNATIONALToString(){
        Assert.assertEquals("INTERNATIONAL", FeeType.INTERNATIONAL.toString());
    }

    @Test
    public void testFeeTypeINTERNATIONAL_SHORT_TERMToString(){
        Assert.assertEquals("INTERNATIONAL_SHORT_TERM", FeeType.INTERNATIONAL_SHORT_TERM.toString());
    }

    @Test
    public void testFeeTypeINTERNATIONAL_SPONSOREDToString(){
        Assert.assertEquals("INTERNATIONAL_SPONSORED", FeeType.INTERNATIONAL_SPONSORED.toString());
    }

    @Test
    public void testFeeTypeMATRICULATIONToString(){
        Assert.assertEquals("MATRICULATION", FeeType.MATRICULATION.toString());
    }

    @Test
    public void testFeeTypeNATIONAL_STUDENT_EXCHANGEToString(){
        Assert.assertEquals("NATIONAL_STUDENT_EXCHANGE:", FeeType.NATIONAL_STUDENT_EXCHANGE.toString());
    }

    @Test
    public void testFeeTypeNONEToString(){
        Assert.assertEquals("NONE", FeeType.NONE.toString());
    }

    @Test
    public void testFeeTypeONLINEToString(){
        Assert.assertEquals("ONLINE", FeeType.ONLINE.toString());
    }

    @Test
    public void testFeeTypeSIMSToString(){
        Assert.assertEquals("SIMS", FeeType.SIMS.toString());
    }

    @Test
    public void testFeeTypeSTUDY_ABROADToString(){
        Assert.assertEquals("STUDY_ABROAD", FeeType.STUDY_ABROAD.toString());
    }

    @Test
    public void testFeeTypeSTUDY_ABROAD_EXCHANGEToString(){
        Assert.assertEquals("STUDY_ABROAD_EXCHANGE", FeeType.STUDY_ABROAD_EXCHANGE.toString());
    }

    @Test
    public void testFeeTypeSTUDY_ABROAD_INSURANCEToString(){
        Assert.assertEquals("STUDY_ABROAD_INSURANCE", FeeType.STUDY_ABROAD_INSURANCE.toString());
    }

    @Test
    public void testFeeTypeTECHNOLOGYToString(){
        Assert.assertEquals("TECHNOLOGY", FeeType.TECHNOLOGY.toString());
    }

    @Test
    public void testFeeTypeWOODROWToString(){
        Assert.assertEquals("WOODROW", FeeType.WOODROW.toString());
    }

    @Test
    public void testFeeTypeTUITION_OVER_17_NON_RESToString(){
        Assert.assertEquals("TUITION_OVER_17_NON_RES", FeeType.TUITION_OVER_17_NON_RES.toString());
    }

    @Test
    public void testFeeTypeTUITION_OVER_17_RES_SS_MILToString(){
        Assert.assertEquals("TUITION_OVER_17_RES_SS_MIL", FeeType.TUITION_OVER_17_RES_SS_MIL.toString());
    }
}
