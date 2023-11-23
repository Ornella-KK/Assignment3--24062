package com.auca.StudentRegistration.Controller;

import com.auca.StudentRegistration.Model.AcademicUnit;
import com.auca.StudentRegistration.Model.Student;
import com.auca.StudentRegistration.Service.StudentService;
import com.auca.StudentRegistration.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/unit" , produces = (MediaType.APPLICATION_JSON_VALUE), consumes = (MediaType.APPLICATION_JSON_VALUE))
public class UnitController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private UnitService unitService;
    //creating
    @PostMapping(value = "/saveAcademicUnit")
    public ResponseEntity<?> saveAcademicUnit(@RequestBody AcademicUnit academicUnit) {
        if (academicUnit != null) {
            String message = unitService.saveAcademicUnit(academicUnit);
            if (message != null) {
                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to save Academic Unit", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //list
    @GetMapping(value = "/listUnits")
    public ResponseEntity<List<AcademicUnit>> listUnits() {
        List<AcademicUnit> units = unitService.listUnits();
        return new ResponseEntity<>(units, HttpStatus.OK);
    }
    //update
    @PutMapping(value = "/updateAcademicUnit")
    public ResponseEntity<String> updateAcademicUnit(@RequestBody AcademicUnit academicUnit) {
        String message = unitService.updateAcademicUnit(academicUnit);
        if (message.equals("Academic unit updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

            //delete
    @DeleteMapping(value = "/deleteUnit/{code}")
    public ResponseEntity<String> deleteUnit(@PathVariable String code) {
        if (code != null) {
            String message = unitService.deleteUnit(code);
            if (message != null) {
                return new ResponseEntity<>("Unit Deleted Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Unit Not Deleted Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
}
