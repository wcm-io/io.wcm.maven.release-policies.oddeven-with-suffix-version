/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2022 wcm.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package io.wcm.maven.release.policies.oddevenwithsuffixversion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.maven.shared.release.policy.PolicyException;
import org.apache.maven.shared.release.policy.version.VersionPolicyRequest;
import org.apache.maven.shared.release.policy.version.VersionPolicyResult;
import org.apache.maven.shared.release.versions.VersionParseException;
import org.junit.jupiter.api.Test;

class OddEvenWithSuffixVersionVersionPolicyTest {

  @Test
  void testGetReleaseVersion() throws PolicyException, VersionParseException {
    VersionPolicyRequest request = new VersionPolicyRequest()
        .setVersion("1.10.1-2.17.12-SNAPSHOT");

    VersionPolicyResult result = new OddEvenWithSuffixVersionVersionPolicy().getReleaseVersion(request);

    assertEquals("1.10.2-2.17.12", result.getVersion());
  }

  @Test
  void testGetReleaseVersion_NotSnapshot() throws PolicyException, VersionParseException {
    VersionPolicyRequest request = new VersionPolicyRequest()
        .setVersion("1.10.1-2.17.12");

    VersionPolicyResult result = new OddEvenWithSuffixVersionVersionPolicy().getReleaseVersion(request);

    assertEquals("1.10.2-2.17.12", result.getVersion());
  }

  @Test
  void testGetDevelopmentVersion() throws PolicyException, VersionParseException {
    VersionPolicyRequest request = new VersionPolicyRequest()
        .setVersion("1.10.0-2.17.12");

    VersionPolicyResult result = new OddEvenWithSuffixVersionVersionPolicy().getDevelopmentVersion(request);

    assertEquals("1.10.1-2.17.12-SNAPSHOT", result.getVersion());
  }

  @Test
  void testGetDevelopmentVersion_Snapshot() throws PolicyException, VersionParseException {
    VersionPolicyRequest request = new VersionPolicyRequest()
        .setVersion("1.10.1-2.17.12-SNAPSHOT");

    VersionPolicyResult result = new OddEvenWithSuffixVersionVersionPolicy().getDevelopmentVersion(request);

    assertEquals("1.10.3-2.17.12-SNAPSHOT", result.getVersion());
  }

}
