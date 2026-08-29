package com.ashvin.parmar.learn.core.injections;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import com.ashvin.parmar.custom.scanning.*;
import org.springframework.beans.factory.annotation.*;

@RestController
public class CustomScanningUsage 
{
@Autowired
private CustomScanningDemo customScanningDemo;
@GetMapping("getCustomScanningOutput")
public String getCustomScanningOutput()
{
return customScanningDemo.getCustomScanningOutput();
}    
}

