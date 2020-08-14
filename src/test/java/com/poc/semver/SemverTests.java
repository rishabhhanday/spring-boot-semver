package com.poc.semver;

import static com.vdurmont.semver4j.Semver.SemverType.NPM;
import static com.vdurmont.semver4j.Semver.VersionDiff.MINOR;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.poc.semver.model.CustomSemver;
import com.vdurmont.semver4j.Semver;
import com.vdurmont.semver4j.SemverException;
import org.junit.jupiter.api.Test;

class SemverTests {

  @Test
  void compareVersion() {
    Semver semver = new Semver("1.2.5");

    assertAll(
        () -> assertTrue(semver.isEquivalentTo("1.2.5")),
        () -> assertTrue(semver.isLowerThan("1.19.0")),
        () -> assertTrue(semver.isGreaterThan("1.2.5-beta")),
        () -> assertThrows(SemverException.class, () -> new Semver("4564654")),
        () -> assertTrue(semver.isGreaterThan("1.2.5-alpha")),
        () -> assertEquals(MINOR, semver.diff("1.19.8")));
  }

  @Test
  void npmVersioningCheck() {
    Semver semver = new Semver("1.2.5", NPM);

    assertAll(
        () -> assertTrue(semver.satisfies("1.2.5")),
        () -> assertTrue(semver.satisfies(">1.0.0")),
        () -> assertTrue(semver.satisfies("^1.1.0")),
        () -> assertTrue(semver.satisfies("~1.2.4"))
    );
  }

  @Test
  void customSemverTests() {
    new CustomSemver("1.5.5").isGreaterThan("1.2.2");
  }
}
