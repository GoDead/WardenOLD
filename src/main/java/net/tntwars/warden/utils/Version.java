package net.tntwars.warden.utils;

public enum Version {

	V1_8("1.8", (byte) 8),
	V1_9("1.9", (byte) 9),
	V1_10("1.10", (byte) 10),
	V1_11("1.11", (byte) 11),
	V1_12("1.12", (byte) 12),
	V1_13("1.13", (byte) 13),
	V1_14("1.14", (byte) 14),
	V1_15("1.15", (byte) 15),
	V1_16("1.16", (byte) 16);

	private String versionName;
	private byte versionId;

	Version(String versionName, byte id) {
		this.versionName = versionName;
		this.versionId = id;
	}

	public String getName() {
		return versionName;
	}

	public byte getVersionId() {
		return versionId;
	}
}
