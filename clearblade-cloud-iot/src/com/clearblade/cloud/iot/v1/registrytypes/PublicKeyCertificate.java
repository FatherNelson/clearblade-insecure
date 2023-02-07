/*
 * Copyright 2023 ClearBlade Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.clearblade.cloud.iot.v1.registrytypes;

public class PublicKeyCertificate {

	private PublicKeyCertificateFormat format;
	private String certificate;
	private X509CertificateDetails x509Details;

	public PublicKeyCertificateFormat getFormat() {
		return format;
	}

	public void setFormat(PublicKeyCertificateFormat format) {
		this.format = format;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public X509CertificateDetails getX509Details() {
		return x509Details;
	}

	public void setX509Details(X509CertificateDetails x509Details) {
		this.x509Details = x509Details;
	}

	@Override
	public String toString() {
		return "PublicKeyCertificateFormat=" + this.format.toString() + ",Certificate=" + certificate;
	}

}
