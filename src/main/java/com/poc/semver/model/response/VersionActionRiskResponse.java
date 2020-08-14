package com.poc.semver.model.response;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VersionActionRiskResponse {

  private List<String> actions;
  private List<String> risks;
}
