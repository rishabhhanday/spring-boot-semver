package com.poc.semver.model;

import static com.vdurmont.semver4j.Semver.SemverType.NPM;

import com.vdurmont.semver4j.Semver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomSemver extends Semver {

  public CustomSemver(String value) {
    super(value, NPM);
  }

  public int compareVersions(String version1, String version2) {
    int comparisonResult = 0;

    String[] version1Splits = version1.split("\\.");
    String[] version2Splits = version2.split("\\.");
    int maxLengthOfVersionSplits = Math.max(version1Splits.length, version2Splits.length);

    for (int i = 0; i < maxLengthOfVersionSplits; i++) {
      Integer v1 = i < version1Splits.length ? Integer.parseInt(version1Splits[i]) : 0;
      Integer v2 = i < version2Splits.length ? Integer.parseInt(version2Splits[i]) : 0;
      int compare = v1.compareTo(v2);
      if (compare != 0) {
        comparisonResult = compare;
        break;
      }
    }
    return comparisonResult;
  }

  @Override
  public boolean isGreaterThan(String version) {
    return this.compareVersions(this.getValue(), version) > 0;
  }
}
