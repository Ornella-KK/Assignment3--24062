package com.auca.StudentRegistration.Service;

import com.auca.StudentRegistration.Model.AcademicUnit;
import com.auca.StudentRegistration.Repository.UnitRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {
    private static final Logger logger = LoggerFactory.getLogger(UnitService.class);

    @Autowired
    private UnitRepo unitRepo;
    public String saveAcademicUnit(AcademicUnit academicUnit) {
        if (academicUnit != null) {
            unitRepo.save(academicUnit);
            return "Academic unit created successfully";
        } else {
            return null;
        }
    }
    public boolean isAcadExists(String code) {
        return unitRepo.existsById(code);
    }

    public List<AcademicUnit> listUnits() {
        return unitRepo.findAll();
    }

    public AcademicUnit getAcademicUnitById(String code) {
        return unitRepo.findById(code).orElse(null);
    }
    public String updateAcademicUnit(AcademicUnit academicUnit) {
        if (academicUnit != null && unitRepo.existsById(academicUnit.getCode())) {
            unitRepo.save(academicUnit);
            return "Academic unit updated successfully";
        } else {
            return "Academic unit not found";
        }
    }

    public String deleteUnit(String code) {
        logger.info("Deleting unit with code: {}", code);
        try {
            if (code != null) {
                if (isAcadExists(code)) {
                    unitRepo.deleteById(code);
                    logger.info("Unit deleted successfully");
                    return "Unit deleted successfully";
                } else {
                    return "Unit not found";
                }
            } else {
                return "Invalid input";
            }
        } catch (Exception e) {
            logger.error("Failed to delete Unit", e);
            return "Unit not deleted";
        }
    }
    public AcademicUnit getUnitByCode(String code) {
        logger.info("Retrieving Unit with code: {}", code);
        try {
            return unitRepo.findById(code).orElse(null);
        } catch (Exception ex) {
            logger.error("Failed to retrieve Unit", ex);
            return null;
        }
    }
    public AcademicUnit getAcademicUnitByCode(String code) {
        return unitRepo.findById(code).orElse(null);
    }
}
