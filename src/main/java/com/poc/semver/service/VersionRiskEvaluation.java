package com.poc.semver.service;

import com.poc.semver.model.response.VersionActionRiskResponse;

@FunctionalInterface
public interface VersionRiskEvaluation {

  VersionActionRiskResponse checkVersionRisk(String softposVersion);
}
