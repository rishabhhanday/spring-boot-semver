package com.poc.semver.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.semver.model.response.VersionActionRiskResponse;
import com.poc.semver.service.VersionRiskEvaluation;
import com.vdurmont.semver4j.Semver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ActionRiskConfiguration {

  @Value("classpath:action-risk.json")
  private Resource resource;

  @Value("${softpos.min.version}")
  private String softposMinVersion;

  @Bean
  public Map<String, Map<String, List<String>>> actionRisk() throws IOException {
    return new ObjectMapper().readValue(resource.getFile(), Map.class);
  }

  @Bean
  public VersionRiskEvaluation versionRiskEvaluation(
      Map<String, Map<String, List<String>>> actionRisk) {
    Map<String, List<String>> semverActionRisk = actionRisk.get("semverCheck");
    Semver semver = new Semver(softposMinVersion);

    return (softposVersion -> {
      if (semver.isLowerThan(softposVersion)) {
        return VersionActionRiskResponse
            .builder()
            .actions(Collections.emptyList())
            .risks(Collections.emptyList())
            .build();
      }

      List<String> actionList = new ArrayList<>();
      List<String> riskList = new ArrayList<>();

      semverActionRisk.forEach((risk, actions) -> {
        actionList.add(risk);
        riskList.addAll(actions);
      });

      return VersionActionRiskResponse
          .builder()
          .risks(riskList)
          .actions(actionList)
          .build();
    });
  }
}
