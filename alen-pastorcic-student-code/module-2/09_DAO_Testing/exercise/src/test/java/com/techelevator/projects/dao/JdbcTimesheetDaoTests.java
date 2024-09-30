package com.techelevator.projects.dao;

import com.techelevator.projects.model.Timesheet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class JdbcTimesheetDaoTests extends BaseDaoTests {

    private static final Timesheet TIMESHEET_1 = new Timesheet(1, 1, 1,
            LocalDate.parse("2021-01-01"), 1.0, true, "Timesheet 1");
    private static final Timesheet TIMESHEET_2 = new Timesheet(2, 1, 1,
            LocalDate.parse("2021-01-02"), 1.5, true, "Timesheet 2");
    private static final Timesheet TIMESHEET_3 = new Timesheet(3, 2, 1,
            LocalDate.parse("2021-01-01"), 0.25, true, "Timesheet 3");
    private static final Timesheet TIMESHEET_4 = new Timesheet(4, 2, 2,
            LocalDate.parse("2021-02-01"), 2.0, false, "Timesheet 4");

    private JdbcTimesheetDao dao;
    private Timesheet testTimesheet;



    @Before
    public void setup() {
        dao = new JdbcTimesheetDao(dataSource);
        testTimesheet = new Timesheet(0, 1, 2, LocalDate.now(), 5.0, true, null);
    }

    @Test
    public void getTimesheetById_with_valid_id_returns_correct_timesheet() {
        Timesheet timesheet = dao.getTimesheetById(1);
        assertTimesheetsMatch(TIMESHEET_1, timesheet);

        timesheet = dao.getTimesheetById(2);
        assertTimesheetsMatch(TIMESHEET_2, timesheet);
    }

    @Test
    public void getTimesheetById_with_invalid_id_returns_null_timesheet() {
        Timesheet timesheet = dao.getTimesheetById(99);
        Assert.assertNull("Timesheet retrieved with invalid ID should be null", timesheet);
    }

    @Test
    public void getTimesheetsByEmployeeId_with_valid_employee_id_returns_list_of_timesheets_for_employee() {
        List<Timesheet> timesheets = dao.getTimesheetsByEmployeeId(2);
        Assert.assertEquals("There should be two timesheets in employee 1", 2,  timesheets.size());
        assertTimesheetsMatch(TIMESHEET_3, timesheets.get(0));
        assertTimesheetsMatch(TIMESHEET_4, timesheets.get(1));

    }

    @Test
    public void getTimesheetsByEmployeeId_with_invalid_employee_id_returns_empty_list_of_timesheets() {
        List<Timesheet> timesheets = dao.getTimesheetsByEmployeeId(99);
        Assert.assertNotNull("List of employees should be empty", timesheets);
        Assert.assertEquals(0, timesheets.size());

    }

    @Test
    public void getTimesheetsByProjectId_with_valid_project_id_returns_list_of_all_timesheets_for_project() {

    }

    @Test
    public void getTimesheetsByProjectId_with_invalid_project_id_returns_empty_list_of_timesheets() {
        List<Timesheet> timesheets = dao.getTimesheetsByProjectId(99);
        Assert.assertNotNull("List of timesheets should be empty", timesheets);
        Assert.assertEquals(0, timesheets.size());
    }

    @Test
    public void createTimesheet_creates_timesheet() {
        Timesheet createdTimesheet = dao.createTimesheet(testTimesheet);
        Assert.assertNotNull(createdTimesheet);

        int newId = createdTimesheet.getTimesheetId();
        Assert.assertTrue(newId > 0);

        Timesheet retrievedTimesheet = dao.getTimesheetById(newId);
        assertTimesheetsMatch(createdTimesheet, retrievedTimesheet);
    }

    @Test
    public void updateTimesheet_updates_timesheet() {
        Timesheet timesheetToUpdate = dao.getTimesheetById(1);

        timesheetToUpdate.setTimesheetId(1);
        timesheetToUpdate.setProjectId(1);
        timesheetToUpdate.setEmployeeId(1);
        timesheetToUpdate.setDateWorked(LocalDate.parse("2021-01-01"));
        timesheetToUpdate.setHoursWorked(5.0);
        timesheetToUpdate.setBillable(false);
        timesheetToUpdate.setDescription(null);

        Timesheet updatedTimesheet = dao.updateTimesheet(timesheetToUpdate);
        Assert.assertNotNull(updatedTimesheet);

        Timesheet retrievedTimesheet = dao.getTimesheetById(1);
        assertTimesheetsMatch(updatedTimesheet, retrievedTimesheet);
    }

    @Test
    public void deleteTimesheetById_deletes_timesheet() {
        int rowsAffected = dao.deleteTimesheetById(2);
        Assert.assertEquals(1, rowsAffected);

        Timesheet retrievedTimesheet = dao.getTimesheetById(2);
        Assert.assertNull(retrievedTimesheet);
    }

    @Test
    public void getBillableHours_returns_correct_total() {

    }

    private void assertTimesheetsMatch(Timesheet expected, Timesheet actual) {
        Assert.assertEquals(expected.getTimesheetId(), actual.getTimesheetId());
        Assert.assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        Assert.assertEquals(expected.getProjectId(), actual.getProjectId());
        Assert.assertEquals(expected.getDateWorked(), actual.getDateWorked());
        Assert.assertEquals(expected.getHoursWorked(), actual.getHoursWorked(), 0.001);
        Assert.assertEquals(expected.isBillable(), actual.isBillable());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }

}
