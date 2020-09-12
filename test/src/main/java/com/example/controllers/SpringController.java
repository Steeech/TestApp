package com.example.controllers;

import com.example.model.OperationModel;
import com.example.service.Calculate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.LinkedList;

@Controller
@ControllerAdvice
public class SpringController {

    OperationModel operationModel = new OperationModel();
    @Autowired
    private Calculate calculate;

    @RequestMapping("/")
    public String getIndexPage(Model model) {
        model.addAttribute("operationModel", operationModel);
        model.addAttribute("error", "");
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "submit")
    public String subtract(@ModelAttribute("operationModel") OperationModel operationModel, Model model) {

        if (operationModel.getCategory().equals("task2")) {
            model.addAttribute("result", calculate.task2(operationModel));
            model.addAttribute("error", "");
            return "index";
        } else {
            model.addAttribute("result", calculate.task1(operationModel));
            model.addAttribute("error", "");
            return "index";
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "save")
    public String save(HttpServletResponse response, @ModelAttribute("operationModel") OperationModel operationModel) {

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("category", operationModel.getCategory());
        if(operationModel.getCategory().equals("task2")){
            jsonObj.put("b", operationModel.getB());
            jsonObj.put("a1", new JSONArray());
            jsonObj.put("a2", new JSONArray());
        }
        else {
            jsonObj.put("b", 0);
            jsonObj.put("a1", operationModel.getA1());
            jsonObj.put("a2", operationModel.getA2());
        }

        response.setHeader("Content-Disposition", "attachment;filename=file.json");
        InputStream inputStream = new ByteArrayInputStream(jsonObj.toString().getBytes());
        try {
            FileCopyUtils.copy(inputStream,response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "open")
    public String open(@RequestParam("file") MultipartFile file, Model model) {
        System.out.println(file.getContentType());
        if(!file.isEmpty() && file.getContentType().equals("application/json")) {
            try {
                InputStream inputStream = file.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                JSONObject json = new JSONObject(reader.readLine());
                JSONArray arr = json.getJSONArray("a1");
                LinkedList<String> temp = new LinkedList<>();
                for (int i = 0; i < arr.length(); i++) {
                    temp.add((String) arr.get(i));
                }
                operationModel.setA1(temp);
                arr = json.getJSONArray("a2");
                temp = new LinkedList<>();
                for (int i = 0; i < arr.length(); i++) {
                    temp.add((String) arr.get(i));
                }
                operationModel.setA2(temp);
                operationModel.setB(Integer.toString(json.getInt("b")));
                operationModel.setCategory(json.getString("category"));
                model.addAttribute("operationModel", operationModel);

                if (operationModel.getCategory().equals("task2")) {
                    model.addAttribute("result", calculate.task2(operationModel));
                } else {
                    model.addAttribute("result", calculate.task1(operationModel));
                }
                model.addAttribute("error", "");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                model.addAttribute("error","Error: "+ex.getMessage());
            } catch (JSONException ex){
                System.out.println(ex.getMessage());
                model.addAttribute("error","Error: " + ex.getMessage());
            }
        }
        else{
            model.addAttribute("error","Please, open not empty file with json-format!");
        }
        return "index";
    }
}