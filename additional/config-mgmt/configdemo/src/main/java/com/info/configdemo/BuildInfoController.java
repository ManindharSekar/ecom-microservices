package com.info.configdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class BuildInfoController {

    @Value("${build.id:id}")
    String buildId;
    @Value("${build.version:version}")
    String buildVersion;
    @Value("${build.name:name}")
    String buildName;

    @GetMapping("/build-info")
    public String getBuildInfo(){
        return "buidId: "+buildId+", buildName: "+buildName+", buildVersion: "+buildVersion;
    }
}
