package com.booking_system.busBooking.service;

import com.booking_system.busBooking.dto.UserDto;
import com.booking_system.busBooking.entity.User;
import com.booking_system.busBooking.repository.UserRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class UserReportServiceImpl implements UserReportService {
//    @Autowired
//    private UserRepository userRepository;
//
//    public  String exportReport(String format){
//        String reportsPath="D:\\angular\\booking system\\public\\reports\\";
//
//        String fileName="";
//        try {
//            List<User> userList=userRepository.findAll();
//            // Convert the role to String for each user in the list
//            for (User user : userList) {
//                // Convert role to String
//                user.setRoleAsString(user.getRoleAsString());
//            }
//            JasperReport jasperReport= JasperCompileManager.compileReport(ResourceUtils.getFile("D:\\angular\\busBooking\\busBooking\\src\\main\\resources\\Flower_Landscape.jrxml").getAbsolutePath());
//            HashMap<String, Object> map=new HashMap<>();
//            JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(userList);
//            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport, map, beanCollectionDataSource);
//            if(format.equalsIgnoreCase("html")){
//                JasperExportManager.exportReportToHtmlFile(jasperPrint,reportsPath+"user.html");
//                fileName="user.html";
//            }else if(format.equalsIgnoreCase("pdf")) {
//                JasperExportManager.exportReportToHtmlFile(jasperPrint,reportsPath+"user.pdf");
//            }
//        }catch (JRException | FileNotFoundException ex){
//            //todo: handle exception
//            throw new RuntimeException(ex);
//
//        }
//        return fileName;
//
//    }
}
