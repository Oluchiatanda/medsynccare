package org.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "medication")

public class Medication {
    @DatabaseField(generatedId = true)
    private int medicationId;

    @DatabaseField(canBeNull = false)
    private String medName;

    @DatabaseField(canBeNull = false)
    private String healthCondition;

    @DatabaseField(canBeNull = false)
    private String medDosage;

    @DatabaseField(canBeNull = false)
    private String medTime;

    @DatabaseField(canBeNull = false)
    private String medForm;

    @DatabaseField(canBeNull = false)
    private String medFrequency;

    @DatabaseField(canBeNull = false)
    private String additionalNotes;

    public Medication() {
    }

    public Medication(String medName) {
        this.medName = medName;
        //this.password = password;
    }

    // standard getters, setters
    public int getMedicationId() {
        return medicationId;
    }
    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    public String getHealthCondition() {
        return healthCondition;
    }
    public void setHealthCondition(String healthCondition) {

        this.healthCondition = healthCondition;
    }
    public String getMedName() {
        return medName;
    }
    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getMedTime() {
        return medTime;
    }
    public void setMedTime(String medTime) {
        this.medTime = medTime;
    }

    public String getMedDosage() {
        return medDosage;
    }
    public void setMedDosage(String medDosage) {
        this.medDosage = medDosage;
    }

    public String getMedForm() {
        return medForm;
    }
    public void setMedForm(String medForm) {
        this.medForm = medForm;
    }

    public String getMedFrequency() {
        return medFrequency;
    }
    public void setMedFrequency(String medFrequency) {
        this.medFrequency = medFrequency;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }
    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

}
