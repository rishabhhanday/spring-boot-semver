package com.poc.semver;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

import com.poc.semver.model.response.VersionActionRiskResponse;
import com.poc.semver.service.VersionRiskEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EvaluationController {

  @Autowired
  private VersionRiskEvaluation versionRiskEvaluation;

  @PostMapping("/risk/evaluation")
  ResponseEntity<VersionActionRiskResponse> evaluateRisks(@RequestParam String softposVersion) {
    return status(OK)
        .body(versionRiskEvaluation.checkVersionRisk(softposVersion));
  }
}
