package org.example;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.google.gson.Gson;


import spark.Request;
import spark.Response;
import spark.Route;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) throws Exception {

        //String databaseUrl = "jdbc:mysql://localhost:3306/medsynccare";

        //
        String databaseUrl = "jdbc:mysql://bj7vpoxh4oiqauk8vory-mysql.services.clever-cloud.com:3306/bj7vpoxh4oiqauk8vory";

        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);

                ((JdbcConnectionSource)connectionSource).setUsername("ufwbusqfrj1hpbmo");
        ((JdbcConnectionSource)connectionSource).setPassword("ib3NZQ9PDI97n0xEG7SH");

        //((JdbcConnectionSource)connectionSource).setUsername("Meenah");
        // ((JdbcConnectionSource)connectionSource).setPassword("Meenah@123");

        // instantiate the dao
        Dao<Patient, String> patientDao = DaoManager.createDao(connectionSource, Patient.class);
        Dao<Medication, String> medicationDao = DaoManager.createDao(connectionSource, Medication.class);

        Dao<Reminder, String> reminderDao = DaoManager.createDao(connectionSource, Reminder.class);

        // if you need to create the 'patients' table make this call
        TableUtils.createTableIfNotExists(connectionSource, Patient.class);
        TableUtils.createTableIfNotExists(connectionSource, Medication.class);
        TableUtils.createTableIfNotExists(connectionSource, Reminder.class);


        path("/api", () -> {
            // Endpoint to register a patient
            post("/register", (request, response) -> {
                // Extract data from request
                String requestBody = request.body();
                Gson gson = new Gson();
                Patient patient = gson.fromJson(requestBody, Patient.class);
                // Save patient to the database
                //  System.out.println("omo " + patient.getEmailAddress() + requestBody);
                patientDao.create(patient);

                // Return a success message and response
                // System.out.println("register " + response);
                return "Patient registered successfully";
            });

            //Endpoint to add medication
            post("/addMedication", (request, response) -> {
                // Extract data from request
                String requestBody = request.body();
                Gson gson = new Gson();
                Medication med = gson.fromJson(requestBody, Medication.class);
                // Save user to the database
                medicationDao.create(med);

                // Return a success message and response
                System.out.println("Med added " + response);
                return "Med details added successfully";
            });

            // Endpoint to login
            post("/login", (request, response) -> {

                // Extract JSON body
                String requestBody = request.body();
                Gson gson = new Gson();
                Patient loginRequest = gson.fromJson(requestBody, Patient.class);

                // Retrieve patient from the database
                Patient patient = patientDao.queryForEq("emailAddress", loginRequest.getEmailAddress()).stream().findFirst().orElse(null);

                System.out.println("logg" + loginRequest.getEmailAddress() + " patient " + loginRequest.getPassword());
                System.out.println("logg" + patient.getEmailAddress() + " patient " + patient.getPassword());

                // Validate login credentials
                if (patient != null && patient.getPassword().equals(loginRequest.getPassword())) {
                    // Generate and set a new access token (you may use a proper token generation method)
                    /*String newAccessToken = generateAccessToken();
                    patient.setAccessToken(newAccessToken);
                    patientDao.update(patient);

                     */

                    // Return the access token
                    return "Patient Successfully logged in";
                } else {
                    response.status(401); // Unauthorized
                    return "Invalid login credentials";
                }
            });

            // Endpoint to update notification token
            put("/notification/:id", (request, response) -> {
                int patientId = Integer.parseInt(request.params(":id"));
                String newNotificationToken = request.queryParams("token");

                // Retrieve patient from the database
                Patient patient = patientDao.queryForId(String.valueOf(patientId));

                if (patient != null) {
                    // Update notification token
                    patient.setNotificationToken(newNotificationToken);
                    patientDao.update(patient);

                    return "Notification token updated successfully";
                } else {
                    response.status(404); // Not Found
                    return "Patient not found";
                }
            });

            // Endpoint to create reminder
            post("/reminders", (request, response) -> {
                // Extract JSON body
                String requestBody = request.body();
                Gson gson = new Gson();
                Reminder reminder = gson.fromJson(requestBody, Reminder.class);
                System.out.println("oii"+reminder.getDateTime());

                // Parse the request body and create a new medical adherence reminder
                // Save the reminder locally
                reminderDao.create(reminder);
                return "Reminder created successfully";
            });

            // Endpoint to sync reminder
            post("/sync", (request, response) -> {
                String requestBody = request.body();

                System.out.println("reqlist "+ requestBody);

                // Parse the request body and update local reminders
                List<Reminder> receivedReminders = Arrays.asList(new Gson().fromJson(requestBody, Reminder[].class));
                System.out.println("list1 "+receivedReminders.get(1));

                syncReminders(receivedReminders);
                System.out.println("list "+receivedReminders.get(1));
                return "Sync successful";
            });



        });


/*
        // create an instance of Patient and Medication
        Patient patient = new Patient();
        patient.setFullName("Jim Coakley");
        patient.setEmailAddress("jimcakely@gmail.com");
        patient.setPassword("JIM123");
        patient.setConfirmPassword("JIM123");

        Medication med = new Medication();
        med.setMedName("Metronidazole");
        med.setHealthCondition("Kidney Stones");
        med.setMedDosage("200mg");
        med.setMedTime("8:00am");
        med.setMedForm("Capsule");
        med.setMedFrequency("Three times daily");
        med.setAdditionalNotes("Drink enough water to avoid kidney lumps as side effect");

        // persist the patient and medication object to the database
        patientDao.create(patient);
        medicationDao.create(med);

 */

        // close the connection source
        System.out.println("I got here o2");
        connectionSource.close();

    }
    // Method for syncing reminders
    private static void syncReminders(List<Reminder> reminders) {
        // Logic to update the backend database with the received reminders
        // ...
    }
}