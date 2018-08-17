package edu.northeastern.cs5200.person.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    List<Admin> findAllAdmins() {
        return (List<Admin>) adminRepository.findAll();
    }

    Admin findAdminById(Long adminId) throws Exception {
        Optional<Admin> data = adminRepository.findById(adminId);
        if (data.isPresent()) {
            return data.get();
        }
        throw new Exception("No admin found with id = " + adminId);
    }

    Admin updateAdmin(Long adminId, Admin newAdmin) throws Exception {
        Optional<Admin> data = adminRepository.findById(adminId);
        if (data.isPresent()) {
            Admin admin = data.get();
            admin.setUsername(newAdmin.getUsername());
            admin.setFirstName(newAdmin.getFirstName());
            admin.setLastName(newAdmin.getLastName());
            admin.setEmail(newAdmin.getEmail());
            admin.setDob(newAdmin.getDob());
            return adminRepository.save(admin);
        }
        throw new Exception("No admin found with id = " + adminId);
    }

    void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}
